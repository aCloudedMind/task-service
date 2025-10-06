package com.example.task_service.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class SimpleTaskController {
    
    private final List<Task> tasks = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();
    
    public static class Task {
        private Long id;
        private String title;
        private String description;
        private String dueDate;
        private boolean completed;
        
        // Конструкторы
        public Task() {}
        
        public Task(Long id, String title, String description, String dueDate, boolean completed) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.dueDate = dueDate;
            this.completed = completed;
        }
        
        // Геттеры и сеттеры
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        
        public String getDueDate() { return dueDate; }
        public void setDueDate(String dueDate) { this.dueDate = dueDate; }
        
        public boolean isCompleted() { return completed; }
        public void setCompleted(boolean completed) { this.completed = completed; }
    }
    
    // GET все задачи
    @GetMapping
    public List<Task> getAllTasks() {
        return tasks;
    }
    
    // POST создать задачу
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        task.setId(counter.incrementAndGet());
        tasks.add(task);
        return task;
    }
    
    // GET задачи на сегодня
    @GetMapping("/today")
    public List<Task> getTasksForToday() {
        return tasks; // упрощенная версия
    }
}