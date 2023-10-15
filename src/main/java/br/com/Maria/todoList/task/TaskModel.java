package br.com.Maria.todoList.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
@Entity(name ="tb_tasks")
public class TaskModel {
@Id
@GeneratedValue(generator="UUID")
private UUID id;
 private String description;
 @Column(length=50)
 private String title;
 private LocalDateTime startAt;
 private LocalDateTime endAt;
 private String priority;
 @CreationTimestamp
 private LocalDateTime createdAt;

 private UUID idUser;
 
 
 
 
public UUID getIdUser() {
	return idUser;
}
public void setIdUser(UUID idUser) {
	this.idUser = idUser;
}
public UUID getId() {
	return id;
}
public void setId(UUID id) {
	this.id = id;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}

public LocalDateTime getStartAt() {
	return startAt;
}
public void setStartAt(LocalDateTime startAt) {
	this.startAt = startAt;
}
public LocalDateTime getEndAt() {
	return endAt;
}
public void setEndAt(LocalDateTime endAt) {
	this.endAt = endAt;
}
public String getPriority() {
	return priority;
}
public void setPriority(String priority) {
	this.priority = priority;
}
public LocalDateTime getCreatedAt() {
	return createdAt;
}
public void setCreatedAt(LocalDateTime createdAt) {
	this.createdAt = createdAt;
}
public String getTitle() {
	return title;
}

 public void setTitle(String title) throws Exception{
	 if(title.length() > 50) {
		throw new Exception("O campo title deve conter no maximo 50 caracteres");
	 }
	 this.title =title;
 }
 
 
 
}
