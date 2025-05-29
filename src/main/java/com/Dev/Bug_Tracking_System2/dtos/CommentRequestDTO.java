package com.Dev.Bug_Tracking_System2.dtos;

import lombok.Data;

@Data
public class CommentRequestDTO {
    private String content;
    private int bugId;
    private int userId;
}
