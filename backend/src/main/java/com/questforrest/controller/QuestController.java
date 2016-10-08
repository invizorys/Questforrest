package com.questforrest.controller;

import com.questforrest.dto.QuestDto;
import com.questforrest.model.Quest;
import com.questforrest.service.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by root on 08.10.16.
 */
@RestController
@RequestMapping("quest")
public class QuestController {
    @Autowired
    private QuestService questService;

    @RequestMapping(value = "/{questId}", method = RequestMethod.GET)
    public QuestDto getQuest(@PathVariable Long questId){
        return questService.getQuest(questId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<QuestDto> getQuests(){
        return questService.getQuests();
    }


}
