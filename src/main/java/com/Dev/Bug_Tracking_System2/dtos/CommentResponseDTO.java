package com.Dev.Bug_Tracking_System2.dtos;

import lombok.Data;

@Data
public class CommentResponseDTO {
    private int commentId;
    private String content;
    private String timestamp;
    private int userId;
    private String username;
    private int bugId;
    private String bugTitle;
}
