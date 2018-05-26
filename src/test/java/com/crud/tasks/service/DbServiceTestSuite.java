package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTestSuite {
    @InjectMocks
    DbService dbService;

    @Mock
    TaskRepository taskRepository;

    @Test
    public void testGetAllTasks() {
        //Given
        List<Task> taskList = new ArrayList<>();
        Task task = new Task(1L, "name", "content");
        taskList.add(task);
        when(taskRepository.findAll()).thenReturn(taskList);
        //When
        List<Task> testTaskList = dbService.getAllTasks();
        //Then
        testTaskList.forEach(task1 -> {
            assertEquals(1L, task1.getId(), 0);
            assertEquals("name", task1.getTitle());
            assertEquals("content", task1.getContent());
        });
    }

    @Test
    public void testGetById() {
        //Given
        Task task = new Task(1L, "name", "content");
        Long id = 1L;
        when(taskRepository.findOne(id)).thenReturn(task);
        //When
        Task testTask = dbService.getTaskById(id);
        //Then
        assertEquals(1L, testTask.getId(), 0);
        assertEquals("name", testTask.getTitle());
        assertEquals("content", testTask.getContent());
    }

    @Test
    public void testSaveTask() {
        //Given
        Task task = new Task(1L, "name", "content");
        when(taskRepository.save(task)).thenReturn(task);
        //When
        Task testTask = dbService.saveTask(task);
        //Then
        assertEquals(1L, testTask.getId(), 0);
        assertEquals("name", testTask.getTitle());
        assertEquals("content", testTask.getContent());
    }

    @Test
    public void testDeleteTask() {
        //Given
        Long id = 1L;
        //When & Then
        dbService.deleteTask(id);
        verify(taskRepository,times(1)).delete(id);
    }
}
