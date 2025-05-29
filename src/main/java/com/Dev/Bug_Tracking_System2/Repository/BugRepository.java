package com.Dev.Bug_Tracking_System2.Repository;

import com.Dev.Bug_Tracking_System2.Model.Bug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BugRepository extends JpaRepository<Bug,Integer> {

    // find all the bugs with a project id.
    List<Bug> findAllByProject_ProjectId(int projectId);

    // find the bug with the help of a bug assigned to the Developer id.
    List<Bug> findAllByUser_UserId(int developerId);
}
