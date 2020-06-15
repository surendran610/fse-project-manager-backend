package com.cts.fse.projectmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.fse.projectmanager.dto.ParentTask;

@Repository
public interface ParentTaskRepository extends JpaRepository<ParentTask, Long>{

}
