package com.questforrest.controller;

import com.questforrest.dto.QuestDto;
import com.questforrest.service.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by root on 08.10.16.
 */
@RestController
@RequestMapping("/quest")
public class QuestController {
    @Autowired
    private QuestService questService;

    @RequestMapping(value = "/{questId}", method = RequestMethod.GET)
    public QuestDto getQuest(@PathVariable Long questId) {
        return questService.getQuest(questId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<QuestDto> getQuests() {
        return questService.getQuests();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addQuest(@RequestBody QuestDto questDto) {
        questService.addQuest(questDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
