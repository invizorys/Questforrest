package com.questforrest.service;

import com.questforrest.dto.QuestDto;
import com.questforrest.model.Quest;
import com.questforrest.model.Task;
import com.questforrest.repository.QuestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private TaskService taskService;


    @Transactional
    public QuestDto getQuest(Long questId) {
        Quest quest = questRepository.getOne(questId);
        return modelMapper.map(quest, QuestDto.class);
    }

    @Transactional
    public List<QuestDto> getQuests() {
        List<Quest> quests = questRepository.findAll();
        return quests.stream()
                .map(quest -> modelMapper.map(quest, QuestDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public void addQuest(QuestDto questDto) {
        Quest quest = modelMapper.map(questDto, Quest.class);
        for (Task task : quest.getTasks()) {
            task.setQuest(quest);
        }
        questRepository.save(quest);
    }
}
