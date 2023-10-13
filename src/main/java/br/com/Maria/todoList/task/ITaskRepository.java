package br.com.Maria.todoList.task;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ITaskRepository extends JpaRepository<TaskModel, Long> {

}
