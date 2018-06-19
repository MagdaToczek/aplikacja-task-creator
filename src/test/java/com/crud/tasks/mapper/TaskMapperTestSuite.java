package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTestSuite {
    @Autowired
    TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L,"title", "content");
        //When
        Task mappedTask = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(1, mappedTask.getId(),0);
        assertEquals("title", mappedTask.getTitle());
        assertEquals("content", mappedTask.getContent());
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(1L,"title", "content");
        //When
        TaskDto mappedTaskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(1,mappedTaskDto.getId(),0);
        assertEquals("title", mappedTaskDto.getTitle());
        assertEquals("content", mappedTaskDto.getContent());
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        Task task = new Task(1L,"title", "content");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        //When
        List<TaskDto> mappedTaskDtos = taskMapper.mapToTaskDtoList(tasks);
        //Then
        mappedTaskDtos.forEach(taskDto -> {
            assertEquals(1L, taskDto.getId(),0);
            assertEquals("title", taskDto.getTitle());
            assertEquals("content", taskDto.getContent());
        });
    }
}
