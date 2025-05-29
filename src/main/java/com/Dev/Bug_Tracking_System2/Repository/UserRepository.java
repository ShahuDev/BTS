package com.Dev.Bug_Tracking_System2.Repository;

import com.Dev.Bug_Tracking_System2.Model.Role;
import com.Dev.Bug_Tracking_System2.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUsername(String username);

    User findByEmail(String email);
    
    User findByRole(Role role);
}
