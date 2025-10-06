package com.example.task_service.service;

import com.example.task_service.model.Task;
import com.example.task_service.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;
    
    // Получить все задачи
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    
    // Получить задачу по ID
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }
    
    // Создать задачу
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }
    
    // Обновить задачу
    public Task updateTask(Long id, Task taskDetails) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setTitle(taskDetails.getTitle());
            task.setDescription(taskDetails.getDescription());
            task.setDueDate(taskDetails.getDueDate());
            task.setCompleted(taskDetails.isCompleted());
            return taskRepository.save(task);
        }
        return null;
    }
    
    // Удалить задачу
    public boolean deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    // Отметить задачу как выполненную
    public Task markAsCompleted(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setCompleted(true);
            return taskRepository.save(task);
        }
        return null;
    }
    
    // Снять отметку о выполнении
    public Task markAsIncomplete(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setCompleted(false);
            return taskRepository.save(task);
        }
        return null;
    }
    
    // Получить задачи на сегодня
    public List<Task> getTasksForToday() {
        return taskRepository.findByDueDate(LocalDate.now());
    }
    
    // Получить задачи на неделю
    public List<Task> getTasksForWeek() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(7);
        return taskRepository.findTasksForWeek(startDate, endDate);
    }
    
    // Получить задачи на месяц
    public List<Task> getTasksForMonth() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusMonths(1);
        return taskRepository.findTasksForMonth(startDate, endDate);
    }
    
    // Получить задачи по статусу выполнения
    public List<Task> getTasksByCompletionStatus(boolean completed) {
        return taskRepository.findByCompleted(completed);
    }
    
    // Получить задачи на сегодня по статусу
    public List<Task> getTasksForTodayByStatus(boolean completed) {
        return taskRepository.findByDueDateAndCompleted(LocalDate.now(), completed);
    }
}