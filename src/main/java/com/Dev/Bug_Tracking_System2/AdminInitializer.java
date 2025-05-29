package com.Dev.Bug_Tracking_System2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.Dev.Bug_Tracking_System2.Model.Role;
import com.Dev.Bug_Tracking_System2.Model.User;
import com.Dev.Bug_Tracking_System2.Repository.UserRepository;
import com.Dev.Bug_Tracking_System2.Service.UserService;


@Component
public class AdminInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) {
        if ((userRepository.findByRole(Role.ADMIN))== null) { // ✅ Check if admin exists
            User admin = new User();
            admin.setUsername("Michael_Scott");
            admin.setEmail("michaelScott@dm.com");
            admin.setPassword("123"); // ✅ Hash this before saving
            admin.setRole(Role.ADMIN);

            userService.saveUser(admin); // ✅ Save admin to DB
            System.out.println("Admin user created successfully.");
        } else {
            System.out.println("Admin user already exists.");
        }
    }
}
