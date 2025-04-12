package com.devDevs.Bug.Tracking.system.Repositories;

import com.devDevs.Bug.Tracking.system.Models.Bug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BugRepo extends JpaRepository<Bug,Long> {


}
