package com.devDevs.Bug.Tracking.system.Controllers;

import com.devDevs.Bug.Tracking.system.Models.Comment;
import com.devDevs.Bug.Tracking.system.Services.CommentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentServices commentServices;

    @PutMapping("/add")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Comment> addComment(Comment comment){
        return commentServices.addComment(comment);
    }

    @GetMapping("/{bugId}")
    public ResponseEntity<List<Comment>> getAllCommentsByBugId(@PathVariable long BugId){
        return commentServices.getAllCommentsByBugId(BugId);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Comment> deleteComment(@PathVariable long id){
        return commentServices.deleteComment(id);
    }


}

// completed.