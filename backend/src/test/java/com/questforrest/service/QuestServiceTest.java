package com.questforrest.service;

import com.questforrest.Application;
import com.questforrest.dto.AnswerResponseDto;
import com.questforrest.dto.QuestMetadataResponseDto;
import com.questforrest.dto.TaskProgressDto;
import com.questforrest.model.*;
import com.questforrest.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Created by Ira Zyabkina on 09.10.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:test.app.properties")
@SpringBootTest
public class QuestServiceTest {

    @Autowired
    private QuestService questService;
    @Autowired
    private QuestRepository questRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private TaskProgressRepository taskProgressRepository;
    @Autowired
    private QuestProgressRepository questProgressRepository;

    private SecureRandom random = new SecureRandom();

    private String randomString() {
        return new BigInteger(130, random).toString(32);
    }

    @Test
    public void getQuestMetadata() throws Exception {
        Quest quest = generateQuest();

        questRepository.save(quest);

        QuestMetadataResponseDto questMetadata = questService.getQuestMetadata(quest.getId());
        assertNotNull(questMetadata);
        assertEquals(questMetadata.getName(), quest.getName());
        assertEquals(questMetadata.getDescription(), quest.getDescription());
        assertEquals(questMetadata.getMaxPlayers(), quest.getMaxPlayers());
        assertEquals(questMetadata.getPictureUrl(), quest.getPictureUrl());

        questRepository.delete(quest);
    }

    @Test
    public void getQuestProgress() throws Exception {

    }

    @Test
    public void getQuests() throws Exception {

    }

    @Test
    public void addQuest() throws Exception {

    }

    @Test
    public void enroll() throws Exception {

    }

    @Test
    public void createTeam() throws Exception {

    }

    @Test
    public void checkAnswer() throws Exception {
        Quest quest = generateQuest();
        questRepository.save(quest);

        QuestProgress questProgress = new QuestProgress();
        questProgress.setCode(randomString());
        questProgress.setStatus(QuestProgress.Status.NOT_STARTED);
        questProgress.setTeamName(randomString());
        questProgress.setQuest(quest);
        questProgressRepository.save(questProgress);

        User user = new User();
        user.setName(randomString());
        user.setAvatarUrl(randomString());
        user.setCity(randomString());
        user.setLogin(randomString());
        user.setPassword(randomString());
        user.setToken("TOKEN");

        userRepository.save(user);

        Participant participant = new Participant();
        participant.setUser(user);
        participant.setQuestProgress(questProgress);
        participantRepository.save(participant);

        TaskProgress taskProgress = new TaskProgress();
        taskProgress.setQuestProgress(questProgress);
        Task task = quest.getTasks().get(0);
        taskProgress.setTask(task);
        taskProgressRepository.save(taskProgress);


        AnswerResponseDto wrongAnswerResponseDto = questService.checkAnswer(taskProgress.getId(), "WRONG ANSWER");
        assertNotNull(wrongAnswerResponseDto);
        assertFalse(wrongAnswerResponseDto.isRightAnswer());
        assertFalse(wrongAnswerResponseDto.getQuestProgressResponseDto().getTaskProgresses().stream()
                .filter(TaskProgressDto::isSolved)
                .filter(taskProgressDto -> taskProgressDto.getId().equals(taskProgress.getId()))
                .collect(Collectors.toList()).size() > 0);

        AnswerResponseDto rightAnswerResponseDto = questService.checkAnswer(taskProgress.getId(), task.getSolution());
        assertNotNull(rightAnswerResponseDto);
        assertTrue(rightAnswerResponseDto.isRightAnswer());
        assertTrue(rightAnswerResponseDto.getQuestProgressResponseDto().getTaskProgresses().stream()
                .filter(TaskProgressDto::isSolved)
                .filter(taskProgressDto -> taskProgressDto.getId().equals(taskProgress.getId()))
                .collect(Collectors.toList()).size() > 0);
    }

    private Quest generateQuest() {
        Quest quest = new Quest();
        quest.setName(randomString());
        quest.setDescription(randomString());
        quest.setMaxPlayers(10);
        quest.setPictureUrl(randomString());

        Task task = new Task();
        task.setDescription(randomString());
        task.setName(randomString());
        task.setPictureUrl(randomString());
        task.setSolution(randomString());
        task.setTaskOrderNumber(10);
        task.setType(Task.Type.TEXT);
        task.setQuest(quest);
        quest.setTasks(Collections.singletonList(task));
        return quest;
    }
}