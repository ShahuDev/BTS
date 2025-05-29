package com.Dev.Bug_Tracking_System2.dtos;

import com.Dev.Bug_Tracking_System2.Model.Bug;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BugResponseDTO {

    private int bugId;
    private String title;
    private String description;
    private String status;
    private String priority;
    private String assigned_To;
    private int projectId;
    private LocalDate timestamp;


}
