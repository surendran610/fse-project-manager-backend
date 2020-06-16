package com.cts.fse.projectmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.fse.projectmanager.dto.ParentTaskUI;
import com.cts.fse.projectmanager.dto.ProjectCreateDTO;
import com.cts.fse.projectmanager.dto.ProjectDataTableDTO;
import com.cts.fse.projectmanager.dto.ProjectTaskDTO;
import com.cts.fse.projectmanager.dto.ResponseEntity;
import com.cts.fse.projectmanager.dto.TaskCreateDTO;
import com.cts.fse.projectmanager.dto.TaskUI;
import com.cts.fse.projectmanager.dto.UIUsers;
import com.cts.fse.projectmanager.dto.Users;
import com.cts.fse.projectmanager.services.UIService;
import com.cts.fse.projectmanager.utils.FseConstants;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/fse/api/")
public class UIController {

	@Autowired
	private UIService uiService;

	@PostMapping("/users")
	public ResponseEntity<String> createUser(@RequestBody Users user) {
		ResponseEntity<String> response = new ResponseEntity<>();
		boolean status = false;
		status = uiService.createUser(user);
		if (status) {
			response.setResponse("Persisted");
			response.setResponseCode(FseConstants.SUCCESS);
		} else {
			response.setResponse("Not Persisted");
			response.setResponseCode(FseConstants.INTERNALSERVERERROR);
		}
		return response;
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<String> updateUser(@RequestBody Users user, @PathVariable("id") long id) {
		ResponseEntity<String> response = new ResponseEntity<>();
		boolean status = false;
		status = uiService.updateUser(user, id);
		if (status) {
			response.setResponse("Persisted");
			response.setResponseCode(FseConstants.SUCCESS);
		} else {
			response.setResponse("Not Persisted");
			response.setResponseCode(FseConstants.INTERNALSERVERERROR);
		}
		return response;
	}

	@GetMapping("/users/{userid}")
	public Users getUser(@PathVariable("userid") long userid) {
		Users user = uiService.findUserById(userid);
		return user;
	}

	@GetMapping("/users")
	public List<UIUsers> getAllUsers() {
		return uiService.findAllUsers();
	}
	
	@DeleteMapping("/users/{userid}")  
	public void deleteBook(@PathVariable("userid") long userid)   
	{  
		uiService.deleteUserByid(userid);  
	}  

	@PostMapping("/project")
	public ResponseEntity<String> createProject(@RequestBody ProjectCreateDTO projectCreateDTO) {
		ResponseEntity<String> response = new ResponseEntity<>();
		boolean status = false;
		status = uiService.createProject(projectCreateDTO);
		if (status) {
			response.setResponse("Persisted");
			response.setResponseCode(FseConstants.SUCCESS);
		} else {
			response.setResponse("Not Persisted");
			response.setResponseCode(FseConstants.INTERNALSERVERERROR);
		}
		return response;
	}
	
	@PutMapping("/project/{id}")
	public ResponseEntity<String> updateProject(@RequestBody ProjectCreateDTO projectCreateDTO, @PathVariable("id") long id) {
		ResponseEntity<String> response = new ResponseEntity<>();
		boolean status = false;
		status = uiService.updateProject(projectCreateDTO, id);
		if (status) {
			response.setResponse("Persisted");
			response.setResponseCode(FseConstants.SUCCESS);
		} else {
			response.setResponse("Not Persisted");
			response.setResponseCode(FseConstants.INTERNALSERVERERROR);
		}
		return response;
	}
	
	@GetMapping("/project")
	public List<ProjectDataTableDTO>findAllProjects(){
		return uiService.findAllProjects();
	}
	
	@PostMapping("/task")
	public ResponseEntity<String> createTask(@RequestBody TaskCreateDTO taskCreateDTO) {
		ResponseEntity<String> response = new ResponseEntity<>();
		boolean status = false;
		status = uiService.createTask(taskCreateDTO);
		if (status) {
			response.setResponse("Persisted");
			response.setResponseCode(FseConstants.SUCCESS);
		} else {
			response.setResponse("Not Persisted");
			response.setResponseCode(FseConstants.INTERNALSERVERERROR);
		}
		return response;
	}
	
	@GetMapping("/parenttask")
	public List<ParentTaskUI>findAllParentTask(){
		return uiService.findAllParentTask();
	}
	
	@GetMapping("/projecttask")
	public List<ProjectTaskDTO>findAllProjectTaskMap(){
		return uiService.findAllProjectTaskMap();
	}
	
	@GetMapping("/task/{projectId}")
	public List<TaskUI>findAllTaskByProject(@PathVariable("projectId")long projectId){
		return uiService.findAllTaskByProjectId(projectId);
	}
	
	@PostMapping("/task/{id}")
	public ResponseEntity<String> updateTask(@RequestBody TaskCreateDTO taskCreateDTO,@PathVariable("id") long id) {
		ResponseEntity<String> response = new ResponseEntity<>();
		boolean status = false;
		status = uiService.updateTask(taskCreateDTO, id);
		if (status) {
			response.setResponse("Persisted");
			response.setResponseCode(FseConstants.SUCCESS);
		} else {
			response.setResponse("Not Persisted");
			response.setResponseCode(FseConstants.INTERNALSERVERERROR);
		}
		return response;
	}

}
