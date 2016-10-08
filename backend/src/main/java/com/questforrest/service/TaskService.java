package com.questforrest.service;

import com.questforrest.dto.TaskDto;
import com.questforrest.model.Task;
import com.questforrest.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by root on 08.10.16.
 */
@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ModelMapper modelMapper;

    public TaskDto getTest(Long taskId) {
        Task task = taskRepository.findOne(taskId);
        return modelMapper.map(task, TaskDto.class);
    }

    public List<TaskDto> getTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(task -> modelMapper.map(task, TaskDto.class))
                .collect(Collectors.toList());
    }
}
