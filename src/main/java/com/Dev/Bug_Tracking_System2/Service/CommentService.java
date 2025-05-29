package com.Dev.Bug_Tracking_System2.Service;

import com.Dev.Bug_Tracking_System2.Repository.CommentRepository;
import com.Dev.Bug_Tracking_System2.dtos.CommentMapper;
import com.Dev.Bug_Tracking_System2.dtos.CommentRequestDTO;
import com.Dev.Bug_Tracking_System2.dtos.CommentResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Autowired
    public CommentService(CommentRepository commentRepository,CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }


    public ResponseEntity<CommentResponseDTO> createComment(CommentRequestDTO comment){
        try {
            var c=commentRepository.save(commentMapper.toEntity(comment));
            return ResponseEntity.ok(commentMapper.mapToResponseDTO(c));
        } catch (RuntimeException e) {
            ResponseEntity.badRequest().body(null);
            throw new RuntimeException("Some unexpected server error occurred. Please try again later. Error: " + e.getMessage(), e);

        }
    }

    public ResponseEntity<CommentResponseDTO> getCommentById(int id){
        try{
            var comment = commentRepository.findById(id);
            return comment.map(value -> new ResponseEntity<>(commentMapper.mapToResponseDTO(value), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<String> deleteComment(int id){
        try{
            commentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<CommentResponseDTO> updateComment(int id, CommentRequestDTO commentDTO){
        try {
            var comment = commentRepository.findById(id);
            var newComment= commentMapper.toEntity(commentDTO);
            comment.ifPresent(value -> {
                value.setUser(newComment.getUser());
                value.setContent(newComment.getContent());
                value.setTimestamp(newComment.getTimestamp());
                commentRepository.save(value);
            });
            return comment.map(value -> new ResponseEntity<>(commentMapper.mapToResponseDTO(value), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            ResponseEntity.badRequest().body(null);
            throw new RuntimeException("some unexpected server error occurred. Please try again later. Error: " + e.getMessage(), e);
        }
    }


    public ResponseEntity<List<CommentResponseDTO>> getAllCommentsOfBug(int id){
        try{
            var p= commentRepository.findAllByBugBugId(id);
            return  new ResponseEntity<>(commentMapper.mapToResponseDTOList(p),HttpStatus.OK);
        } catch (RuntimeException e) {
            ResponseEntity.badRequest().body(null);
            throw new RuntimeException("some unexpected server error occurred. Please try again later. Error: " + e.getMessage(), e);
        }

    }

    public ResponseEntity<List<CommentResponseDTO>> getAllCommentsOfUser(int id){
        try{
            var p= commentRepository.findAllByUserUserId(id);
            return  new ResponseEntity<>(commentMapper.mapToResponseDTOList(p),HttpStatus.OK);
        } catch (RuntimeException e) {
            ResponseEntity.badRequest().body(null);
            throw new RuntimeException("some unexpected server error occurred. Please try again later. Error: " + e.getMessage(), e);
        }

    }

    public ResponseEntity<List<CommentResponseDTO>> getAllComments() {
        return ResponseEntity.ok(commentMapper.mapToResponseDTOList(commentRepository.findAll()));
    }
}
