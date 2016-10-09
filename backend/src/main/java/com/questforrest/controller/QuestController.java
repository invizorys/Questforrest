package com.questforrest.controller;

import com.questforrest.dto.CreateQuestRequestDto;
import com.questforrest.service.QuestService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quest")
public class QuestController {
    @Autowired
    private QuestService questService;

    @CrossOrigin
    @RequestMapping(value = "/{questId}", method = RequestMethod.GET)
    public ResponseEntity getQuest(@PathVariable Long questId) {
        return new ResponseEntity<>(questService.getQuestMetadata(questId), HttpStatus.OK);
    }

    @RequestMapping(value = "/progress/{questId}", method = RequestMethod.GET)
    public ResponseEntity getQuestProgress(@PathVariable Long questId, HttpServletRequest request) {
        String token = request.getHeader("token");
        return token == null ?
                new ResponseEntity(HttpStatus.UNAUTHORIZED) :
                new ResponseEntity<>(questService.getQuestProgress(questId, token), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getQuests(HttpServletRequest request) {
        String token = request.getHeader("token");
        return new ResponseEntity<>(token == null ?
                questService.getQuests() :
                questService.getQuests(token), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addQuest(@RequestBody CreateQuestRequestDto questDto) {
        questService.addQuest(questDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{questId}/enroll/{questCode}", method = RequestMethod.POST)
    public ResponseEntity enroll(@PathVariable Long questId, @PathVariable String questCode, HttpServletRequest request) {
        String token = request.getHeader("token");
        return token == null ?
                new ResponseEntity(HttpStatus.UNAUTHORIZED) :
                new ResponseEntity<>(questService.enroll(questId, token, questCode), HttpStatus.OK);
    }

    @RequestMapping(value = "/resolve/{taskProgressId}", method = RequestMethod.POST)
    public ResponseEntity resolveTask(@PathVariable Long taskProgressId, @RequestBody String answer, HttpServletRequest request) {
        String token = request.getHeader("token");
        return token == null ?
                new ResponseEntity(HttpStatus.UNAUTHORIZED) :
                new ResponseEntity<>(questService.checkAnswer(taskProgressId, answer), HttpStatus.OK);
    }

    @RequestMapping(value = "/{questId}/team/{teamName}", method = RequestMethod.POST)
    public ResponseEntity createTeam(@PathVariable Long questId, @PathVariable String teamName, HttpServletRequest request) {
        String token = request.getHeader("token");
        return token == null ?
                new ResponseEntity(HttpStatus.UNAUTHORIZED) :
                new ResponseEntity<>(questService.createTeam(questId, token, teamName), HttpStatus.OK);
    }
}
