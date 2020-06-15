package com.cts.fse.projectmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cts.fse.projectmanager.dto.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
	
	@Query(value="select count(t.project_id) from task t ,project p where t.project_id=p.id and t.project_id=:projectId",nativeQuery = true) 
	Long getTaskCount(@Param("projectId")long projectId);
	
	@Query(value="select count(t.status) from task t ,project p where t.project_id=p.id and t.status='completed' and t.project_id=:projectId",nativeQuery = true) 
	Long getTaskCompletedCount(@Param("projectId")long projectId);
	
	@Query(value="select * from task t ,parent_task p where t.project_id=:projectId and p.id=t.parent_id",nativeQuery = true)
	List<Task>findAllTaskByProjectId(@Param("projectId")long projectId);
}
