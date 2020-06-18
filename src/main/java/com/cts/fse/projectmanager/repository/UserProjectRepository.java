package com.cts.fse.projectmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cts.fse.projectmanager.dto.UserProject;

@Repository
public interface UserProjectRepository extends JpaRepository<UserProject, Long>{
	@Query(value="SELECT * FROM user_project u where u.user_id=:userId",nativeQuery = true) 
	List<UserProject> getUserProject(@Param("userId")long userId);
	
}
