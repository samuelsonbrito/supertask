package com.supero.api.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import com.supero.api.repository.TaskRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.supero.api.models.Task;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false")  
@RestController
@RequestMapping(value = "/api")
@Api(value="API REST Tasks")
public class TaskResource {
	
	@Autowired
	TaskRepository taskRepository;
	
	@ApiOperation(value="Retorna lista de tasks")
	@GetMapping("tasks")
	public List<Task> findAll(){
		return taskRepository.findAll();
	}
	
	@ApiOperation(value="Retorna task única")
	@GetMapping("task/{id}")
	public Task findById(@PathVariable(value="id") long id) {
		return taskRepository.findById(id);
	}
	
	@ApiOperation(value="Salva task")
	@PostMapping("task")
	@ResponseStatus(HttpStatus.CREATED)
	public Task save(@RequestBody @Valid Task contact) {
		return taskRepository.save(contact);
	}
	
	@ApiOperation(value="Deleta task")
	@DeleteMapping("task/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void dalete(@PathVariable(value="id") long id) {
		taskRepository.deleteById(id);
	}
	
	@ApiOperation(value="Atualiza task")
	@PutMapping("task")
	public Task update(@RequestBody @Valid Task contact) {
		return taskRepository.save(contact);
	}
	
}
