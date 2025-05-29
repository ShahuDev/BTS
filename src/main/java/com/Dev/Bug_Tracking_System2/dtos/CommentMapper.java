package com.Dev.Bug_Tracking_System2.dtos;

import com.Dev.Bug_Tracking_System2.Model.Comment;
import com.Dev.Bug_Tracking_System2.Repository.BugRepository;
import com.Dev.Bug_Tracking_System2.Repository.CommentRepository;
import com.Dev.Bug_Tracking_System2.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class CommentMapper {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private  BugRepository bugRepository;

    @Autowired
    private  UserRepository userRepository;

    public  CommentResponseDTO mapToResponseDTO(Comment comment){
        CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
        commentResponseDTO.setCommentId(comment.getCommentId());
        commentResponseDTO.setContent(comment.getContent());
        commentResponseDTO.setTimestamp(comment.getTimestamp().toString());
        commentResponseDTO.setUserId(comment.getUser().getUserId());
        commentResponseDTO.setUsername(comment.getUser().getUsername());
        commentResponseDTO.setBugId(comment.getBug().getBugId());
        commentResponseDTO.setBugTitle(comment.getBug().getTitle());
        return commentResponseDTO;
    }

    public  Comment toEntity(CommentRequestDTO commentRequestDTO){
        Comment comment = new Comment();
        comment.setContent(commentRequestDTO.getContent());
        comment.setBug(bugRepository.findById(commentRequestDTO.getBugId()).orElseThrow(()-> new IllegalArgumentException("Bug not found with ID: " + commentRequestDTO.getBugId())));
        comment.setUser(userRepository.findById(commentRequestDTO.getUserId()).orElseThrow(()-> new IllegalArgumentException("User not found with ID: " + commentRequestDTO.getUserId())));
        return comment;
    }

    public  CommentResponseDTO mapToResponseDTO(Comment comment, int bugId){
        CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
        commentResponseDTO.setCommentId(comment.getCommentId());
        commentResponseDTO.setContent(comment.getContent());
        commentResponseDTO.setTimestamp(comment.getTimestamp().toString());
        commentResponseDTO.setUserId(comment.getUser().getUserId());
        commentResponseDTO.setUsername(comment.getUser().getUsername());
        commentResponseDTO.setBugId(bugId);
        commentResponseDTO.setBugTitle(comment.getBug().getTitle());
        return commentResponseDTO;
    }

    public  List<CommentResponseDTO> mapToResponseDTOList(List<Comment> comments){
        List<CommentResponseDTO> list = new ArrayList<>();
        for (Comment comment : comments) {
            CommentResponseDTO commentResponseDTO = mapToResponseDTO(comment);
            list.add(commentResponseDTO);
        }
        return list;
    }

    public  List<CommentResponseDTO> mapToResponseDTOListByBugId(List<Comment> comments, int bugId){
        return comments.stream()
                .map(comment -> mapToResponseDTO(comment, bugId))
                .toList();
    }
}
