package com.Dev.Bug_Tracking_System2.dtos;

import com.Dev.Bug_Tracking_System2.Model.Bug;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BugRequestDTO {

    private String title;
    private String description;
    private Bug.BugStatus status;
    private Bug.BugPriority priority;
    private Integer assigned_To;
    private int projectId;



}
