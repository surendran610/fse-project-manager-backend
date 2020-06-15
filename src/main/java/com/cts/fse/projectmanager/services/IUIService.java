package com.cts.fse.projectmanager.services;

import java.util.List;

import com.cts.fse.projectmanager.dto.ParentTaskUI;
import com.cts.fse.projectmanager.dto.ProjectCreateDTO;
import com.cts.fse.projectmanager.dto.ProjectDataTableDTO;
import com.cts.fse.projectmanager.dto.ProjectTaskDTO;
import com.cts.fse.projectmanager.dto.TaskCreateDTO;
import com.cts.fse.projectmanager.dto.TaskUI;
import com.cts.fse.projectmanager.dto.UIUsers;
import com.cts.fse.projectmanager.dto.Users;

public interface IUIService {
	public boolean createUser(Users user);
	public boolean updateUser(Users user,long id);
	public boolean createProject(ProjectCreateDTO projectCreateDTO);
	public boolean updateProject(ProjectCreateDTO projectCreateDTO,long id);
	public boolean createTask(TaskCreateDTO taskCreateDTO);
	public Users findUserById(long id);
	public List<UIUsers>findAllUsers();
	public void deleteUserByid(long id);
	public List<ProjectDataTableDTO>findAllProjects();
	public List<ParentTaskUI>findAllParentTask();
	List<ProjectTaskDTO> findAllProjectTaskMap();
	List<TaskUI>findAllTaskByProjectId(long id);
	public boolean updateTask(TaskCreateDTO taskCreateDTO,long id);
}
