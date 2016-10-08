package com.questforrest.controller;

import com.questforrest.dto.QuestDto;
import com.questforrest.dto.TaskDto;
import com.questforrest.model.Task;
import com.questforrest.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by root on 08.10.16.
 */
@RestController
@RequestMapping("task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/{questId}", method = RequestMethod.GET)
    public TaskDto getQuest(@PathVariable Long questId){
        return taskService.getTest(questId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<TaskDto> getQuests(){
        return taskService.getTasks();
    }
}
