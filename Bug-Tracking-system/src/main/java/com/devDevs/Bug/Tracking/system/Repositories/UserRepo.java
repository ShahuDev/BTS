package com.devDevs.Bug.Tracking.system.Repositories;

import com.devDevs.Bug.Tracking.system.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);

}

