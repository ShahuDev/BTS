package com.Dev.Bug_Tracking_System2.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;



@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;  //pk
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;


}



