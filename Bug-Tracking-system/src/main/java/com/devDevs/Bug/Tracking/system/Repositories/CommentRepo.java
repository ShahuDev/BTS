package com.devDevs.Bug.Tracking.system.Repositories;

import com.devDevs.Bug.Tracking.system.Models.Bug;
import com.devDevs.Bug.Tracking.system.Models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment,Long> {
    List<Comment> findByBugId(long bugId);
}
