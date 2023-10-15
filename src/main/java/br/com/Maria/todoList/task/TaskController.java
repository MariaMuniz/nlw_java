package br.com.Maria.todoList.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.Maria.todoList.util.Utils;
import jakarta.servlet.http.HttpServletRequest;



@RestController
@RequestMapping("/tasks")
public class TaskController {

	
	
	@Autowired
	private ITaskRepository taskRepository;
	
	
	@PostMapping("/")
	public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
	
		var idUser =  request.getAttribute("idUser");
		taskModel.setIdUser((UUID) idUser);
		
		var currentDate =LocalDateTime.now();
		if(currentDate.isAfter(taskModel.getStartAt())||currentDate.isAfter(taskModel.getEndAt()) ) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("a data de inicio deve ser maior que a data de atual.");	
		}
		
		if(currentDate.isAfter(taskModel.getEndAt())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("a data de inicio deve ser menor que a data de termino.");	
		}
		var task =this.taskRepository.save(taskModel);
		return ResponseEntity.status(HttpStatus.OK).body(task);
	}
	
	@GetMapping("/")
	public List<TaskModel> list(HttpServletRequest request) {
		var idUser =  request.getAttribute("idUser");
		var tasks = this.taskRepository.findByIdUser((UUID) idUser);
		return tasks;
	}
	@PutMapping("/{id}")
	public ResponseEntity Update(@RequestBody TaskModel taskModel, HttpServletRequest request, @PathVariable UUID id) {
	
		var task = this.taskRepository.findById(id).orElse(null);
		
		if(task ==null) {
				 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("task não existe");
		}
		var idUser =  request.getAttribute("idUser");
		if(!task.getIdUser().equals(idUser)) {
		 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario não tem permissão");
		}
		Utils.copyNonNullProperties(taskModel, task);
		var taskUpdated =this.taskRepository.save(task);
		return ResponseEntity.ok().body(taskUpdated);
	}
	}

