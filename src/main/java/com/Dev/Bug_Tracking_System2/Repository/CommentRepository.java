package com.Dev.Bug_Tracking_System2.Repository;

import com.Dev.Bug_Tracking_System2.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {

    List<Comment> findAllByBugBugId(int bugId);

    List<Comment> findAllByUserUserId(int userId);


}
