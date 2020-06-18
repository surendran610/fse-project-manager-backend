package com.cts.fse.projectmanager.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

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
import com.cts.fse.projectmanager.utils.InvalidRequestException;

@SpringBootTest
public class UIControllerTest {

	@InjectMocks
	private UIController uiController;
	
	@Mock
	private UIService uiService;
	
	@Test
	public void testCreateUser200() throws InvalidRequestException {
		when(uiService.createUser(any())).thenReturn(true);
		ResponseEntity<String> response=uiController.createUser(getUser());
		assertEquals(response.getResponseCode(), "200");
	}
	
	@Test
	public void testCreateUser500() throws InvalidRequestException {
		when(uiService.createUser(any())).thenReturn(false);
		ResponseEntity<String> response=uiController.createUser(getUser());
		assertEquals(response.getResponseCode(), "500");
	}
	
	@Test
	public void testUpdateUser() throws InvalidRequestException {
		when(uiService.updateUser(getUser(),1)).thenReturn(false);
		ResponseEntity<String> response=uiController.updateUser(getUser(),1);
		assertEquals(response.getResponseCode(), "500");	
	}
	
		
	@Test
	public void testGetUser() throws InvalidRequestException {
		when(uiService.findUserById(1)).thenReturn(getUser());
		Users response=uiController.getUser(1);
		assertEquals(response.getEmpId(), "12345");	
	}
	
	@Test
	public void testGetAllUsers() throws InvalidRequestException{
		when(uiService.findAllUsers()).thenReturn(listUser());
		List<UIUsers> response=uiController.getAllUsers();
		assertEquals(response.size(), 1);	
	}

	@Test
	public void testDeleteBook() throws InvalidRequestException{
		doNothing().when(uiService).deleteUserByid(1);
		uiController.deleteBook(1);
	}
	
	@Test
	public void testCreateProjec() throws InvalidRequestException{
		when(uiService.createProject(any())).thenReturn(true);
		ResponseEntity<String> response=uiController.createProject(getProjectCreateDTO());
		assertEquals(response.getResponseCode(), "200");
	}
	
	@Test
	public void testupdateProject() throws InvalidRequestException{
		when(uiService.updateProject(getProjectCreateDTO(), 1)).thenReturn(false);
		ResponseEntity<String> response=uiController.updateProject(getProjectCreateDTO(),1);
		assertEquals(response.getResponseCode(), "500");
	}
	
	@Test
	public void testfindAllProjects() throws InvalidRequestException{
		List<ProjectDataTableDTO>allProjects=new ArrayList<ProjectDataTableDTO>();
		allProjects.add(getProjectDataTableDTO());
		when(uiService.findAllProjects()).thenReturn(allProjects);
		List<ProjectDataTableDTO>findAllProjects=uiController.findAllProjects();
		assertEquals(findAllProjects.size(), 1);	
	}
	
	@Test
	public void testUpdateTask() throws InvalidRequestException{
		when(uiService.updateTask(getTaskCreateDTO(),1)).thenReturn(false);
		ResponseEntity<String> response=uiController.updateTask(getTaskCreateDTO(),1);
		assertEquals(response.getResponseCode(), "500");
	}
	
	@Test
	public void testCreateTask() throws InvalidRequestException{
		when(uiService.createTask(getTaskCreateDTO())).thenReturn(false);
		ResponseEntity<String> response=uiController.createTask(getTaskCreateDTO());
		assertEquals(response.getResponseCode(), "500");
	}
	
	@Test
	public void testfindAllParentTask() throws InvalidRequestException{
		List<ParentTaskUI>findAllParentTask=new ArrayList<ParentTaskUI>();
		when(uiService.findAllParentTask()).thenReturn(findAllParentTask);
		List<ParentTaskUI>res=uiController.findAllParentTask();
		assertEquals(res.size(), 0);
	}
	
	@Test
	public void testfindAllProjectTaskMap() throws InvalidRequestException{
		List<ProjectTaskDTO>findAllProjectTaskMap=new ArrayList<ProjectTaskDTO>();
		when(uiService.findAllProjectTaskMap()).thenReturn(findAllProjectTaskMap);
		List<ProjectTaskDTO>res=uiController.findAllProjectTaskMap();
		assertEquals(res.size(), 0);
	}
	
	@Test
	public void testfindAllTaskByProject() throws InvalidRequestException{
		List<TaskUI>findAllTaskByProject=new ArrayList<TaskUI>();
		when(uiService.findAllTaskByProjectId(1)).thenReturn(findAllTaskByProject);
		List<TaskUI>res=uiController.findAllTaskByProject(1);
		assertEquals(res.size(), 0);
	}
	
	private Users getUser() {
		Users user=new Users();
		user.setEmpId("12345");
		user.setFirstName("Suren");
		user.setLastName("Suren");
		return user;
	}

	private UIUsers getUIUser() {
		UIUsers user=new UIUsers();
		user.setEmpId("12345");
		user.setFirstName("Suren");
		user.setLastName("Suren");
		return user;
	}

	
	private List<UIUsers> listUser() {
		List<UIUsers>userLst=new ArrayList<UIUsers>();
		userLst.add(getUIUser());
		return userLst;
	}
	
	public ProjectCreateDTO  getProjectCreateDTO() {
		ProjectCreateDTO projectCreateDTO=new ProjectCreateDTO();
		projectCreateDTO.setEndDate(new Date());
		projectCreateDTO.setStartDate(new Date());
		projectCreateDTO.setPriority(1);
		projectCreateDTO.setProject("Test");
		projectCreateDTO.setUserId(1);
		return projectCreateDTO;
	}
	
	public ProjectDataTableDTO getProjectDataTableDTO() {
		ProjectDataTableDTO projectDataTableDTO=new ProjectDataTableDTO();
		projectDataTableDTO.setEndDate("17/06/2020");
		projectDataTableDTO.setFirstNameUser("Test");
		projectDataTableDTO.setPriority(1);
		projectDataTableDTO.setProjectId(1);
		projectDataTableDTO.setProjectName("Test");
		projectDataTableDTO.setStartDate("17/06/2020");
		projectDataTableDTO.setTaskCompletedCount(1);
		projectDataTableDTO.setTaskCount(1);
		projectDataTableDTO.setUserId(1);
		return projectDataTableDTO;
	}
	
	public TaskCreateDTO getTaskCreateDTO() {
		TaskCreateDTO taskCreateDTO=new TaskCreateDTO();
		taskCreateDTO.setEndDate(null);
		taskCreateDTO.setIsParentTask("true");
		taskCreateDTO.setParentId(1);
		taskCreateDTO.setProjectId(1);
		taskCreateDTO.setStartDate(null);
		taskCreateDTO.setStatus("Completed");
		taskCreateDTO.setTask("Test");
		taskCreateDTO.setUserId(1);
		return taskCreateDTO;
	}
}
