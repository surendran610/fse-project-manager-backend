package com.cts.fse.projectmanager.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.fse.projectmanager.dto.ParentTask;
import com.cts.fse.projectmanager.dto.ParentTaskUI;
import com.cts.fse.projectmanager.dto.Project;
import com.cts.fse.projectmanager.dto.ProjectCreateDTO;
import com.cts.fse.projectmanager.dto.ProjectDataTableDTO;
import com.cts.fse.projectmanager.dto.ProjectTaskDTO;
import com.cts.fse.projectmanager.dto.Task;
import com.cts.fse.projectmanager.dto.TaskCreateDTO;
import com.cts.fse.projectmanager.dto.TaskUI;
import com.cts.fse.projectmanager.dto.UIUsers;
import com.cts.fse.projectmanager.dto.Users;
import com.cts.fse.projectmanager.repository.ParentTaskRepository;
import com.cts.fse.projectmanager.repository.ProjectRepository;
import com.cts.fse.projectmanager.repository.TaskRepository;
import com.cts.fse.projectmanager.repository.UsersRepository;

@SpringBootTest
public class UIServiceTest {

	@InjectMocks
	private UIService uiService;

	@Mock
	private UsersRepository usersRepository;

	@Mock
	private ProjectRepository projectRepository;

	@Mock
	private TaskRepository taskRepository;

	@Mock
	private ParentTaskRepository parentTaskRepository;

	@Test
	public void createUserTest() {
		when(usersRepository.save(any())).thenReturn(getUser());
		boolean isSaved = uiService.createUser(getUser());
		assertTrue(isSaved);
	}

	@Test
	public void updateUserTestempty() {
		when(usersRepository.findById(any())).thenReturn(Optional.empty());
		boolean isSaved = uiService.updateUser(getUser(), 1);
		System.out.println(isSaved);
		assertFalse(isSaved);
	}

	@Test
	public void updateUserTest() {
		when(usersRepository.findById(any())).thenReturn(Optional.of(getUser()));
		boolean isSaved = uiService.updateUser(getUser(), 1);
		assertTrue(isSaved);
	}

	@Test
	public void findUserByIdTest() {
		when(usersRepository.findById(any())).thenReturn(Optional.of(getUser()));
		Users user = uiService.findUserById(1);
		assertEquals(user.getFirstName(), "Suren");
	}

	@Test
	public void findAllUsersTest() {
		List<Users> usersList = new ArrayList<Users>();
		usersList.add(getUser());
		when(usersRepository.findAll()).thenReturn(usersList);
		List<UIUsers> uiuserList = uiService.findAllUsers();
		assertEquals(uiuserList.size(), 1);
	}

	@Test
	public void deleteUserByIdTest() {
		doNothing().when(usersRepository).deleteById(1l);
		uiService.deleteUserByid(1l);
	}

	@Test
	public void createProjectTest() {
		when(usersRepository.findById(any())).thenReturn(Optional.of(getUser()));
		when(usersRepository.save(any())).thenReturn(getUser());
		when(projectRepository.save(any())).thenReturn(getProject());
		boolean isSaved = uiService.createProject(getProjectCreateDTO());
		assertTrue(isSaved);
	}

	@Test
	public void updateProjectTest() {
		when(projectRepository.findById(any())).thenReturn(Optional.of(getProject()));
		when(usersRepository.findById(any())).thenReturn(Optional.of(getUser()));
		when(usersRepository.save(any())).thenReturn(getUser());
		when(projectRepository.save(any())).thenReturn(getProject());
		boolean isSaved = uiService.updateProject(getProjectCreateDTO(), 1);
		assertTrue(isSaved);
	}

	@Test
	public void createTaskTrue() {
		when(parentTaskRepository.save(any())).thenReturn(getParentTask());
		when(projectRepository.findById(any())).thenReturn(Optional.of(getProject()));
		when(usersRepository.findById(any())).thenReturn(Optional.of(getUser()));
		when(taskRepository.save(any())).thenReturn(getTask());
		uiService.createTask(getTaskCreateDTO());
	}
	
	@Test
	public void updateTaskTrue() {
		when(parentTaskRepository.save(any())).thenReturn(getParentTask());
		when(projectRepository.findById(any())).thenReturn(Optional.of(getProject()));
		when(usersRepository.findById(any())).thenReturn(Optional.of(getUser()));
		when(taskRepository.save(any())).thenReturn(getTask());
		when(taskRepository.findById(any())).thenReturn(Optional.of(getTask()));
		when(parentTaskRepository.findById(any())).thenReturn(Optional.of(getParentTask()));
		uiService.updateTask(getTaskCreateDTO(),1);
	}
	
	@Test
	public void createTaskfalse() {
		TaskCreateDTO taskCreateDTO=getTaskCreateDTO();
		taskCreateDTO.setIsParentTask("false");
		when(parentTaskRepository.save(any())).thenReturn(getParentTask());
		when(projectRepository.findById(any())).thenReturn(Optional.of(getProject()));
		when(usersRepository.findById(any())).thenReturn(Optional.of(getUser()));
		when(parentTaskRepository.findById(any())).thenReturn(Optional.of(getParentTask()));
		when(taskRepository.save(any())).thenReturn(getTask());
		uiService.createTask(taskCreateDTO);
	}
	
	@Test
	public void updateTaskfalse() {
		TaskCreateDTO taskCreateDTO=getTaskCreateDTO();
		taskCreateDTO.setIsParentTask("false");
		when(parentTaskRepository.save(any())).thenReturn(getParentTask());
		when(projectRepository.findById(any())).thenReturn(Optional.of(getProject()));
		when(usersRepository.findById(any())).thenReturn(Optional.of(getUser()));
		when(taskRepository.save(any())).thenReturn(getTask());
		when(taskRepository.findById(any())).thenReturn(Optional.of(getTask()));
		when(parentTaskRepository.findById(any())).thenReturn(Optional.of(getParentTask()));
		uiService.updateTask(taskCreateDTO,1);
	}
	
	@Test
	public void findAllProjectTest() {
		List<Project> projectList = new ArrayList<Project>();
		projectList.add(getProject());
		when(projectRepository.findAll()).thenReturn(projectList);
		List<ProjectDataTableDTO> datable = uiService.findAllProjects();
		assertEquals(datable.size(), 1);
	}
	
	@Test
	public void findAllParentTaskTest() {
		List<ParentTask> parentTaskList=new ArrayList<ParentTask>();
		parentTaskList.add(getParentTask());
		when(parentTaskRepository.findAll()).thenReturn(parentTaskList);
		List<ParentTaskUI> parentUiList=uiService.findAllParentTask();
		assertEquals(parentUiList.size(), 1);
	}

	@Test
	public void findAllProjectTaskMapTest() {
		List<Task> allTask=new ArrayList<Task>();
		allTask.add(getTask());
		when(taskRepository.findAll()).thenReturn(allTask);
		List<ProjectTaskDTO> allProjectTask =uiService.findAllProjectTaskMap();
		assertEquals(allProjectTask.size(), 1);
	}
	@Test
	public void findAllTaskByProjectIdTest() {
		List<Task> allTask=new ArrayList<Task>();
		allTask.add(getTask());
		when(taskRepository.findAllTaskByProjectId(1l)).thenReturn(allTask);
		List<TaskUI> list=uiService.findAllTaskByProjectId(1l);
		assertEquals(list.size(), 1);
	}
	private Project getProject() {
		Project project = new Project();
		project.setProject("Test");
		project.setEndDate(new Date());
		project.setPriority(1);
		project.setStartDate(new Date());
		Set<Users> userSet = new HashSet<>();
		userSet.add(getUser());
		project.setUsers(userSet);
		project.setId(1l);
		return project;
	}

	public ProjectCreateDTO getProjectCreateDTO() {
		ProjectCreateDTO projectCreateDTO = new ProjectCreateDTO();
		projectCreateDTO.setEndDate(new Date());
		projectCreateDTO.setStartDate(new Date());
		projectCreateDTO.setPriority(1);
		projectCreateDTO.setProject("Test");
		projectCreateDTO.setUserId(1);
		return projectCreateDTO;
	}

	private Users getUser() {
		Users user = new Users();
		user.setEmpId("12345");
		user.setFirstName("Suren");
		user.setLastName("Suren");
		user.setId(1l);
		return user;
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
	
	public ParentTask getParentTask() {
		ParentTask parent = new ParentTask();
		parent.setId(1l);
		parent.setParentTask("Parent");
		return parent;
	}
	
	public Task getTask(){
		Task task=new Task();
		task.setEndDate(new Date());
		task.setId(1l);
		task.setParentTask(getParentTask());
		task.setPriority(1);
		task.setProject(getProject());
		task.setStartDate(new Date());
		task.setStatus("Started");
		task.setTask("Test");
		task.setUsers(getUser());
		return task;
	}
}
