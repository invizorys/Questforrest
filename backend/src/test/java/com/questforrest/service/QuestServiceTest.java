package com.questforrest.service;

import com.questforrest.model.Quest;
import com.questforrest.model.Task;
import org.junit.Test;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

/**
 * Created by Ira Zyabkina on 09.10.2016.
 */
//@SpringRu
public class QuestServiceTest {
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