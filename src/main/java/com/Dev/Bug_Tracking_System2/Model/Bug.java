package com.Dev.Bug_Tracking_System2.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bugId;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BugStatus bugStatus=BugStatus.OPEN;// default value.

    @Enumerated(EnumType.STRING)  // to store enum name instead of ordinal(number).
    @Column(nullable = false)
    private BugPriority priority=BugPriority.P4_LOW;  // initial value.

    @ManyToOne
    @JoinColumn(name = "assigned_to")// foreign key
    @JsonIgnore
    private User user;
    @ManyToOne
    @JoinColumn(name = "project_Id")  // creates a foreign key column
    @JsonIgnore
    private  Project project;
    @OneToMany(mappedBy = "bug",cascade = CascadeType.ALL)// foreign key
    @JsonIgnore
    private List<Comment> comment;

    private LocalDate timestamp =LocalDate.now();

    public enum BugPriority{
        P1_CRITICAL,
        P2_HIGH,
        P3_MEDIUM,
        P4_LOW;
    }
    public enum BugStatus {
        OPEN,
        IN_PROGRESS,
        RESOLVED,
        CLOSED,
        REOPENED
    }

}
