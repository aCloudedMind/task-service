package com.example.task_service.repository;

import com.example.task_service.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
    // Найти задачи по статусу выполнения
    List<Task> findByCompleted(boolean completed);
    
    // Найти задачи по дате выполнения
    List<Task> findByDueDate(LocalDate dueDate);
    
    // Найти задачи по дате выполнения и статусу
    List<Task> findByDueDateAndCompleted(LocalDate dueDate, boolean completed);
    
    // Найти задачи на неделю
    @Query("SELECT t FROM Task t WHERE t.dueDate BETWEEN :startDate AND :endDate")
    List<Task> findTasksForWeek(@Param("startDate") LocalDate startDate, 
                               @Param("endDate") LocalDate endDate);
    
    // Найти задачи на месяц
    @Query("SELECT t FROM Task t WHERE t.dueDate BETWEEN :startDate AND :endDate")
    List<Task> findTasksForMonth(@Param("startDate") LocalDate startDate, 
                                @Param("endDate") LocalDate endDate);
}