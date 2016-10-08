package com.questforrest.repository;

import com.questforrest.model.TaskProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ira Zyabkina on 08.10.2016.
 */
@Repository
public interface TaskProgressRepository extends JpaRepository<TaskProgress, Long> {

}
