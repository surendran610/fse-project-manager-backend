package com.cts.fse.projectmanager.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class UIService implements IUIService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private ParentTaskRepository parentTaskRepository;

	@Override
	public boolean createUser(Users user) {
		try {
			usersRepository.save(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateUser(Users user, long id) {
		try {
			Optional<Users> userOpt = usersRepository.findById(id);
			if (userOpt.isPresent()) {
				Users updateUser = userOpt.get();
				updateUser.setEmpId(user.getEmpId());
				updateUser.setFirstName(user.getFirstName());
				updateUser.setLastName(user.getLastName());
				usersRepository.save(updateUser);
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	@Override
	public Users findUserById(long id) {
		Optional<Users> userOpt = usersRepository.findById(id);
		if (userOpt.isPresent()) {
			return userOpt.get();
		} else {
			return null;
		}
	}

	@Override
	public List<UIUsers> findAllUsers() {
		List<Users> usersList = usersRepository.findAll();
		List<UIUsers> listUsers = new ArrayList<>();
		usersList.stream().forEach(user -> {
			UIUsers users = new UIUsers();
			users.setId(user.getId());
			users.setFirstName(user.getFirstName());
			users.setLastName(user.getLastName());
			users.setEmpId(user.getEmpId());
			listUsers.add(users);
		});
		return listUsers;
	}

	@Override
	public void deleteUserByid(long id) {
		usersRepository.deleteById(id);
	}

	@Override
	public boolean createProject(ProjectCreateDTO projectCreateDTO) {
		try {
			Project project = new Project();
			project.setPriority(projectCreateDTO.getPriority());
			project.setEndDate(projectCreateDTO.getEndDate());
			project.setStartDate(projectCreateDTO.getStartDate());
			project.setProject(projectCreateDTO.getProject());

			Optional<Users> userOpt = usersRepository.findById(projectCreateDTO.getUserId());
			Users user = userOpt.get();
			project.getUsers().add(user);
			user.getProject().add(project);

			projectRepository.save(project);
			usersRepository.save(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean createTask(TaskCreateDTO taskCreateDTO) {
		try {
			if ("true".equals(taskCreateDTO.getIsParentTask())) {
				ParentTask parent = new ParentTask();
				parent.setParentTask(taskCreateDTO.getTask());
				parent = parentTaskRepository.save(parent);

				Task task = new Task();
				Optional<Users> userOpt = usersRepository.findById(taskCreateDTO.getUserId());
				Users user = userOpt.get();
				task.setUsers(user);
				Optional<Project> projectOpt = projectRepository.findById(taskCreateDTO.getProjectId());
				Project project = projectOpt.get();
				task.setProject(project);
				task.setParentTask(parent);
				task.setTask(taskCreateDTO.getTask());
				taskRepository.save(task);
				return true;
			} else {
				Task task = new Task();
				task.setTask(taskCreateDTO.getTask());
				Optional<Users> userOpt = usersRepository.findById(taskCreateDTO.getUserId());
				Users user = userOpt.get();
				task.setUsers(user);
				Optional<Project> projectOpt = projectRepository.findById(taskCreateDTO.getProjectId());
				Project project = projectOpt.get();
				task.setProject(project);
				Optional<ParentTask> parentOpt = parentTaskRepository.findById(taskCreateDTO.getParentId());
				ParentTask parentTask = parentOpt.get();
				task.setParentTask(parentTask);
				task.setEndDate(new Date());
				task.setStartDate(new Date());
				task.setStatus(taskCreateDTO.getStatus());
				taskRepository.save(task);
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<ProjectDataTableDTO> findAllProjects() {
		List<ProjectDataTableDTO> tableList = new ArrayList<>();
		SimpleDateFormat expectedformatter = new SimpleDateFormat("yyyy-MM-dd");
		List<Project> projectList = projectRepository.findAll();
		projectList.forEach(project -> {
			ProjectDataTableDTO projectDataTableDTO = new ProjectDataTableDTO();
			projectDataTableDTO.setProjectId(project.getId());
			Set<Users> projectUserSet = project.getUsers();
			if (!projectUserSet.isEmpty()) {
				for (Users user : projectUserSet) {
					projectDataTableDTO.setUserId(user.getId());
					projectDataTableDTO.setFirstNameUser(user.getFirstName());
				}
			}
			if (project.getEndDate() != null) {
				projectDataTableDTO.setEndDate(expectedformatter.format(project.getEndDate()));
			}
			if (project.getStartDate() != null) {
				projectDataTableDTO.setStartDate(expectedformatter.format(project.getStartDate()));
			}
			projectDataTableDTO.setPriority(project.getPriority());
			projectDataTableDTO.setProjectName(project.getProject());
			projectDataTableDTO.setTaskCompletedCount(taskRepository.getTaskCompletedCount(project.getId()));
			projectDataTableDTO.setTaskCount(taskRepository.getTaskCount(project.getId()));
			tableList.add(projectDataTableDTO);
		});
		return tableList;
	}

	@Override
	public boolean updateProject(ProjectCreateDTO projectCreateDTO, long id) {
		try {
			Optional<Project> projectOpt = projectRepository.findById(id);
			if (projectOpt.isPresent()) {
				Project project = new Project();
				project.setPriority(projectCreateDTO.getPriority());
				project.setEndDate(projectCreateDTO.getEndDate());
				project.setStartDate(projectCreateDTO.getStartDate());
				project.setProject(projectCreateDTO.getProject());

				Optional<Users> userOpt = usersRepository.findById(projectCreateDTO.getUserId());
				Users user = userOpt.get();
				project.getUsers().add(user);
				user.getProject().add(project);

				projectRepository.save(project);
				usersRepository.save(user);
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	@Override
	public List<ParentTaskUI> findAllParentTask() {
		List<ParentTask> parentTaskList = parentTaskRepository.findAll();
		List<ParentTaskUI> parentTaskUIList = new ArrayList<>();
		parentTaskList.forEach(parenTask -> {
			ParentTaskUI parentTaskUI = new ParentTaskUI();
			parentTaskUI.setId(parenTask.getId());
			parentTaskUI.setParentTaskName(parenTask.getParentTask());
			parentTaskUIList.add(parentTaskUI);
		});
		return parentTaskUIList;
	}

	@Override
	public List<ProjectTaskDTO> findAllProjectTaskMap() {
		List<ProjectTaskDTO> allProjectTask = new ArrayList<>();
		List<Task> allTask = taskRepository.findAll();
		allTask.forEach(task -> {
			ProjectTaskDTO projectTaskDTO = new ProjectTaskDTO();
			projectTaskDTO.setId(task.getProject().getId());
			projectTaskDTO.setProjectName(task.getProject().getProject());
			allProjectTask.add(projectTaskDTO);
		});
		return allProjectTask;
	}

	@Override
	public List<TaskUI> findAllTaskByProjectId(long id) {
		List<TaskUI> taskUIList=new ArrayList<TaskUI>();
		SimpleDateFormat expectedformatter = new SimpleDateFormat("yyyy-MM-dd");
		List<Task> allTask = taskRepository.findAllTaskByProjectId(id);
		allTask.forEach(task -> {
			TaskUI taskUI =new TaskUI();
			taskUI.setId(task.getId());
			taskUI.setTask(task.getTask());
			taskUI.setPriority(task.getPriority());
			if(task.getUsers()!=null){
			taskUI.setUserFirstName(task.getUsers().getFirstName());
			taskUI.setUserId(task.getUsers().getId());
			}
			if(task.getParentTask()!=null){
			taskUI.setParentTask(task.getParentTask().getParentTask());
			taskUI.setParentTaskId(task.getParentTask().getId());
			}
			if(task.getProject()!=null){
				taskUI.setProjectId(task.getProject().getId());
			}
			if(task.getStartDate()!=null)
			taskUI.setStartDate(expectedformatter.format(task.getStartDate()));
			if(task.getEndDate()!=null)
			taskUI.setEndDate(expectedformatter.format(task.getEndDate()));
			taskUIList.add(taskUI);
		});
		return taskUIList;
	}

	@Override
	public boolean updateTask(TaskCreateDTO taskCreateDTO, long id) {
		try {
			if ("true".equals(taskCreateDTO.getIsParentTask())) {
				Optional<Task> taskOpt = taskRepository.findById(id);
				Task task=taskOpt.get();
				Optional<Users> userOpt = usersRepository.findById(taskCreateDTO.getUserId());
				Users user = userOpt.get();
				task.setUsers(user);
				Optional<ParentTask> parentOpt=parentTaskRepository.findById(taskCreateDTO.getParentId());
				ParentTask parent=parentOpt.get();
				Optional<Project> projectOpt = projectRepository.findById(taskCreateDTO.getProjectId());
				Project project = projectOpt.get();
				task.setProject(project);
				task.setParentTask(parent);
				task.setTask(taskCreateDTO.getTask());
				taskRepository.save(task);
				return true;
			} else {
				Optional<Task> taskOpt = taskRepository.findById(id);
				Task task=taskOpt.get();
				Optional<Users> userOpt = usersRepository.findById(taskCreateDTO.getUserId());
				Users user = userOpt.get();
				task.setUsers(user);
				Optional<Project> projectOpt = projectRepository.findById(taskCreateDTO.getProjectId());
				Project project = projectOpt.get();
				task.setProject(project);
				Optional<ParentTask> parentOpt = parentTaskRepository.findById(taskCreateDTO.getParentId());
				ParentTask parentTask = parentOpt.get();
				task.setParentTask(parentTask);
				task.setEndDate(new Date());
				task.setStartDate(new Date());
				task.setStatus(taskCreateDTO.getStatus());
				taskRepository.save(task);
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}

}
