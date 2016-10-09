package com.questforrest.service;

import com.questforrest.dto.AnswerResponseDto;
import com.questforrest.dto.QuestMetadataResponseDto;
import com.questforrest.dto.QuestProgressResponseDto;
import com.questforrest.dto.TaskProgressDto;
import com.questforrest.model.*;
import com.questforrest.repository.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Created by Ira Zyabkina on 09.10.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:test.app.properties")
@SpringBootTest
public class QuestServiceTest {
    private static final String TEAM_NAME = "SUPER_PUPER_TEAM";

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

    @After
    public void after(){
        taskProgressRepository.deleteAll();
        participantRepository.deleteAll();
        questProgressRepository.deleteAll();
        userRepository.deleteAll();
        questRepository.deleteAll();
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
        Quest quest = generateQuest();
        questRepository.save(quest);

        User user = generateUser();
        userRepository.save(user);

        QuestProgressResponseDto team = questService.createTeam(quest.getId(), "TOKEN", TEAM_NAME);
        assertNotNull(team);
        assertEquals(team.getTeamName(), TEAM_NAME);
        assertSame(team.getParticipants().size(), 1);
        List<Quest> questsUserEnrolled = questRepository.findQuestsUserEnrolled(user.getId());
        assertTrue(questsUserEnrolled.stream().map(Quest::getId).collect(Collectors.toList()).contains(quest.getId()));
    }

    @Test
    public void checkAnswer() throws Exception {
        Quest quest = generateQuest();
        questRepository.save(quest);

        User user = generateUser();
        userRepository.save(user);

        QuestProgress questProgress = new QuestProgress();
        questProgress.setCode(randomString());
        questProgress.setStatus(QuestProgress.Status.NOT_STARTED);
        questProgress.setTeamName(randomString());
        questProgress.setQuest(quest);
        questProgressRepository.save(questProgress);

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

    private User generateUser() {
        User user = new User();
        user.setName(randomString());
        user.setAvatarUrl(randomString());
        user.setCity(randomString());
        user.setLogin(randomString());
        user.setPassword(randomString());
        user.setToken("TOKEN");
        return user;
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

    private String randomString() {
        return new BigInteger(130, random).toString(32);
    }
}