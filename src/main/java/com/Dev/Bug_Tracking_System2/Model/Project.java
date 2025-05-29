package com.Dev.Bug_Tracking_System2.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // optional but useful generates all id without mentioning.
    private int projectId;
    private String name;
    private String description;
    private LocalDate created_at = LocalDate.now();
    @OneToMany(mappedBy = "project") // reverse mapping.
    @JsonIgnore
    private List<Bug> bugsList;


}
