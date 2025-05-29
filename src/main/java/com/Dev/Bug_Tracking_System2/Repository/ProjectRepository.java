package com.Dev.Bug_Tracking_System2.Repository;

import com.Dev.Bug_Tracking_System2.Model.Bug;
import com.Dev.Bug_Tracking_System2.Model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Integer> {
}
