package com.questforrest.controller;

import com.questforrest.dto.AnswerResponseDto;
import com.questforrest.dto.CreateQuestRequestDto;
import com.questforrest.dto.QuestProgressResponseDto;
import com.questforrest.dto.QuestListResponseDto;
import com.questforrest.service.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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
    public QuestProgressResponseDto getQuest(@PathVariable Long questId) {
        return questService.getQuest(questId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public QuestListResponseDto getQuests() {
        return questService.getQuests();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addQuest(@RequestBody CreateQuestRequestDto questDto) {
        questService.addQuest(questDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{questId}/enroll/{questCode}", method = RequestMethod.POST)
    public ResponseEntity enroll(@PathVariable Long questId, @PathVariable String questCode, HttpRequest request) {
        List<String> tokens = request.getHeaders().get("token");
        return tokens.isEmpty() ?
                new ResponseEntity(HttpStatus.UNAUTHORIZED) :
                new ResponseEntity<>(questService.enroll(questId, tokens.get(0), questCode), HttpStatus.OK);
    }

    @RequestMapping(value = "/resolve/{taskProgressId}", method = RequestMethod.POST)
    public ResponseEntity resolveTask(@PathVariable Long taskProgressId, @RequestBody String answer, HttpRequest request) {
        List<String> tokens = request.getHeaders().get("token");
        return tokens.isEmpty() ?
                new ResponseEntity(HttpStatus.UNAUTHORIZED) :
                new ResponseEntity<>(questService.checkAnswer(taskProgressId, answer), HttpStatus.OK);
    }

    @RequestMapping(value = "/{questId}/team/{teamName}", method = RequestMethod.POST)
    public ResponseEntity createTeam(@PathVariable Long questId, @PathVariable String teamName, HttpRequest request) {
        List<String> tokens = request.getHeaders().get("token");
        return tokens.isEmpty() ?
                new ResponseEntity(HttpStatus.UNAUTHORIZED) :
                new ResponseEntity<>(questService.createTeam(questId, tokens.get(0), teamName), HttpStatus.OK);
    }

}
