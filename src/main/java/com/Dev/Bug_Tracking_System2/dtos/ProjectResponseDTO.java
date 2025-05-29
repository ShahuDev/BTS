package com.Dev.Bug_Tracking_System2.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectResponseDTO {

    private int Id;
    private String Name;
    private String Description;
    private LocalDate createdAt;
}
