package com.devDevs.Bug.Tracking.system.Services;

import com.devDevs.Bug.Tracking.system.Models.Comment;
import com.devDevs.Bug.Tracking.system.Repositories.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServices {

    @Autowired
    private CommentRepo commentRepo;

    public ResponseEntity<Comment> addComment(Comment comment) {
         Comment comment1=commentRepo.save(comment);
       return new ResponseEntity<>(comment1,HttpStatus.OK);
    }

        public ResponseEntity<List<Comment>> getAllCommentsByBugId (long bugId){
            List<Comment> comments = commentRepo.findByBugId(bugId);

            if (!comments.isEmpty()) {
                return new ResponseEntity<>(comments, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }


    public ResponseEntity<Comment> deleteComment(long id) {
        var comment = commentRepo.getReferenceById(id);
        if (comment==null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else{
            commentRepo.delete(comment);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    }
