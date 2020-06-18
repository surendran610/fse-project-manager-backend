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
import com.cts.fse.projectmanager.utils.InvalidRequestException;

public interface IUIService {
	public boolean createUser(Users user) throws InvalidRequestException;
	public boolean updateUser(Users user,long id)throws InvalidRequestException;
	public boolean createProject(ProjectCreateDTO projectCreateDTO)throws InvalidRequestException;
	public boolean updateProject(ProjectCreateDTO projectCreateDTO,long id)throws InvalidRequestException;
	public boolean createTask(TaskCreateDTO taskCreateDTO)throws InvalidRequestException;
	public Users findUserById(long id)throws InvalidRequestException;
	public List<UIUsers>findAllUsers()throws InvalidRequestException;
	public void deleteUserByid(long id)throws InvalidRequestException;
	public List<ProjectDataTableDTO>findAllProjects()throws InvalidRequestException;
	public List<ParentTaskUI>findAllParentTask()throws InvalidRequestException;
	List<ProjectTaskDTO> findAllProjectTaskMap()throws InvalidRequestException;
	List<TaskUI>findAllTaskByProjectId(long id)throws InvalidRequestException;
	public boolean updateTask(TaskCreateDTO taskCreateDTO,long id)throws InvalidRequestException;
}
