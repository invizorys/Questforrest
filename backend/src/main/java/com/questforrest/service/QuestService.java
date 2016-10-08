package com.questforrest.service;

import com.questforrest.dto.*;
import com.questforrest.model.*;
import com.questforrest.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by root on 08.10.16.
 */
@Service
public class QuestService {
    @Autowired
    private QuestRepository questRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private QuestProgressRepository questProgressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private TaskProgressRepository taskProgressRepository;

    @Transactional(readOnly = true)
    public QuestMetadataResponseDto getQuestMetadata(Long questId) {
        Quest quest = questRepository.findOne(questId);
        return modelMapper.map(quest, QuestMetadataResponseDto.class);
    }

    @Transactional(readOnly = true)
    public QuestProgressResponseDto getQuestProgress(Long questId, String token) {
        QuestProgress quest = questProgressRepository.findQuestProgressByQuestIdAndUserToken(questId, token);
        return getQuestProgressResponseDto(quest);
    }

    @Transactional(readOnly = true)
    public QuestListResponseDto getQuests(String token) {
        User user = userRepository.findUserByToken(token);
        List<Quest> questsUserEnrolled = questRepository.findQuestsUserEnrolled(user.getId());
        List<Quest> quests = questRepository.findAll();
        List<QuestShortInfoDto> questShortInfoDtos = quests.stream()
                .map(quest -> {
                    QuestShortInfoDto questShortInfoDto = modelMapper.map(quest, QuestShortInfoDto.class);
                    questShortInfoDto.setEnrolled(questsUserEnrolled.contains(quest));
                    return questShortInfoDto;
                })
                .collect(Collectors.toList());
        return new QuestListResponseDto(questShortInfoDtos);
    }


    @Transactional(readOnly = true)
    public QuestListResponseDto getQuests() {
        List<Quest> quests = questRepository.findAll();
        List<QuestShortInfoDto> questShortInfoDtos = quests.stream()
                .map(quest ->  modelMapper.map(quest, QuestShortInfoDto.class))
                .collect(Collectors.toList());
        return new QuestListResponseDto(questShortInfoDtos);
    }


    @Transactional
    public void addQuest(CreateQuestRequestDto questDto) {
        Quest quest = modelMapper.map(questDto, Quest.class);
        quest.getTasks().forEach(task -> task.setQuest(quest));
        questRepository.save(quest);
    }

    @Transactional
    public QuestProgressResponseDto enroll(Long questId, String token, String questCode) {
        QuestProgress questProgress = questProgressRepository.findQuestProgress(questId, questCode);
        persistParticipant(token, questProgress);
        return getQuestProgressResponseDto(questProgress);
    }

    @Transactional
    public QuestProgressResponseDto createTeam(Long questId, String token, String teamName) {
        Quest questMetaData = questRepository.findOne(questId);
        QuestProgress questProgress = new QuestProgress();
        questProgress.setTeamName(teamName);
        questProgress.setQuest(questMetaData);
        questProgress.setStatus(QuestProgress.Status.NOT_STARTED);
        questProgress.setParticipants(new ArrayList<>());
        questProgress.setCode(generateQuestCode());
        for (Task task : questMetaData.getTasks()) {
            TaskProgress taskProgress = new TaskProgress();
            taskProgress.setTask(task);
            taskProgress.setQuestProgress(questProgress);
            questProgress.getTaskProgresses().add(taskProgress);
        }
        questProgressRepository.save(questProgress);
        persistParticipant(token, questProgress);
        return getQuestProgressResponseDto(questProgress);
    }

    @Transactional
    public AnswerResponseDto checkAnswer(Long taskProgressId, String answer) {
        TaskProgress taskProgress = taskProgressRepository.findOne(taskProgressId);
        boolean rightAnswer = taskProgress.getTask().getSolution().equals(answer);
        QuestProgress questProgress = taskProgress.getQuestProgress();
        if (rightAnswer) {
            taskProgress.setSolved(true);
            checkQuestIsFinished(questProgress);
        }
        AnswerResponseDto answerResponseDto = new AnswerResponseDto();
        answerResponseDto.setQuestProgressResponseDto(getQuestProgressResponseDto(questProgress));
        answerResponseDto.setRightAnswer(rightAnswer);
        return answerResponseDto;
    }

    private QuestProgressResponseDto getQuestProgressResponseDto(QuestProgress quest) {
        List<TaskProgressDto> tasks = quest.getTaskProgresses().stream()
                .map(this::parseTaskDto)
                .collect(Collectors.toList());
        List<UserShortInfoDto> users = quest.getParticipants().stream()
                .map(participant -> modelMapper.map(participant.getUser(), UserShortInfoDto.class))
                .collect(Collectors.toList());
        return new QuestProgressResponseDto(quest.getTeamName(), quest.getCode(), tasks, users);
    }

    private void checkQuestIsFinished(QuestProgress questProgress) {
        QuestProgress.Status newQuestStatus = QuestProgress.Status.FINISHED;
        for (TaskProgress progress : questProgress.getTaskProgresses()) {
            if (!progress.isSolved()) {
                newQuestStatus = QuestProgress.Status.IN_PROGRESS;
                break;
            }
        }
        questProgress.setStatus(newQuestStatus);
    }

    private String generateQuestCode() {
        return new BigInteger(16, new SecureRandom()).toString();
    }

    private void persistParticipant(String token, QuestProgress questProgress) {
        User user = userRepository.findUserByToken(token);
        Participant participant = new Participant();
        participant.setQuestProgress(questProgress);
        participant.setUser(user);
        participantRepository.save(participant);
    }

    private TaskProgressDto parseTaskDto(TaskProgress taskProgress) {
        TaskProgressDto taskDto = modelMapper.map(taskProgress.getTask(), TaskProgressDto.class);
        taskDto.setSolved(taskProgress.isSolved());
        return taskDto;
    }
}
