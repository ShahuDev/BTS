package com.devDevs.Bug.Tracking.system.Controllers;
import java.util.*;
import com.devDevs.Bug.Tracking.system.Models.Bug;
import com.devDevs.Bug.Tracking.system.Services.BugServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/bugs")
public class BugController {

    @Autowired
    private BugServices bugServices;
    @PostMapping("/create")
    public ResponseEntity<Bug> createBug(Bug bug){
        return bugServices.createBug(bug);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Bug>> getBugList(){
        return bugServices.getListOfBugs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bug> getBugById(@PathVariable long id){
        return bugServices.getBugById(id);
    }
  /*  @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('DEVELOPER')")
    public ResponseEntity<Bug> updateBugDetails(@PathVariable long id,@RequestParam String status,@RequestParam String assignee){
           var updatedBug = bugServices.updatedBugById(id,status,assignee);
           return ResponseEntity.ok(updatedBug);
    }
*/
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Bug> deleteBug(@PathVariable long id){
        return bugServices.deleteBug(id);
    }
}

// completed