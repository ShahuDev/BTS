package com.devDevs.Bug.Tracking.system.Repositories;

import com.devDevs.Bug.Tracking.system.Models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepo extends JpaRepository<Project,Long> {
}
