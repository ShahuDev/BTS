package com.Dev.Bug_Tracking_System2.Controller;

import com.Dev.Bug_Tracking_System2.Service.CommentService;
import com.Dev.Bug_Tracking_System2.dtos.CommentRequestDTO;
import com.Dev.Bug_Tracking_System2.dtos.CommentResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;
    // working
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('DEVELOPER') or hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<CommentResponseDTO> createComment(@RequestBody CommentRequestDTO commentRequestDTO){
        return commentService.createComment(commentRequestDTO);
    }

    // working
    @GetMapping("/getCommentById/{id}")
    @PreAuthorize("hasAuthority('DEVELOPER') or hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<CommentResponseDTO> getCommentById(@PathVariable int id){
        return commentService.getCommentById(id);
    }

    //working
    @DeleteMapping("/deleteComment/{id}")
    @PreAuthorize("hasAuthority('DEVELOPER')")
    public ResponseEntity<String> deleteComment(@PathVariable int id){
        return commentService.deleteComment(id);
    }
    // working
    @PutMapping("/updateComment/{id}")
    @PreAuthorize("hasAuthority('DEVELOPER') or hasAuthority('ADMIN')")
    public ResponseEntity<CommentResponseDTO> updateComment(@PathVariable int id, CommentRequestDTO commentDTO){
        return commentService.updateComment(id, commentDTO);
    }

    // working
    @GetMapping("/bug/{id}")
    @PreAuthorize("hasAuthority('DEVELOPER') or hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<List<CommentResponseDTO>> getAllCommentsOfBug(@PathVariable int id){
        return commentService.getAllCommentsOfBug(id);
    }

    // working
    @GetMapping("/user/{id}")
    @PreAuthorize("hasAuthority('DEVELOPER') or hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<List<CommentResponseDTO>> getAllCommentsOfUser(@PathVariable int id){
        return commentService.getAllCommentsOfUser(id);
    }
    // working
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('DEVELOPER') or hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<List<CommentResponseDTO>> getAllComments(){
        return commentService.getAllComments();
    }
}
