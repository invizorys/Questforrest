package com.questforrest.service;

import com.questforrest.Application;
import com.questforrest.dto.QuestMetadataResponseDto;
import com.questforrest.model.Quest;
import com.questforrest.model.Task;
import com.questforrest.repository.QuestRepository;
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

    private SecureRandom random = new SecureRandom();

    private String randomString() {
        return new BigInteger(130, random).toString(32);
    }

    @Test
    public void getQuestMetadata() throws Exception {
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

    }

}