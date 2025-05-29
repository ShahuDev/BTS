package com.Dev.Bug_Tracking_System2.Controller;

import com.Dev.Bug_Tracking_System2.Model.Bug;
import com.Dev.Bug_Tracking_System2.Service.BugService;
import com.Dev.Bug_Tracking_System2.dtos.BugRequestDTO;
import com.Dev.Bug_Tracking_System2.dtos.BugResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/bugs")
public class BugController {

    @Autowired
    private BugService bugServices;

    /*
        This is a method to create a Bug.
        tried and tested, working fine.
     */
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('DEVELOPER') or hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<BugResponseDTO> createBug(@RequestBody BugRequestDTO bug){
        return bugServices.createBug(bug);
    }

    // method to show the total list of bugs. tested and working fine.
    @GetMapping("/listAll")
  // @PreAuthorize("hasAuthority('DEVELOPER') or hasAuthority('ADMIN') or hasAuthority('USER')")
    @PreAuthorize("hasAuthority('DEVELOPER')  or hasAuthority('USER')")
    public ResponseEntity<List<Bug>> getBugList(){
    	return bugServices.getListOfBugs();
    }

    // this is a method to get bug by bugId. tested and working fine.
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('DEVELOPER') or hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<BugResponseDTO> getBugById(@PathVariable int id){
        return bugServices.getBugById(id);
    }

    // This method updates the bug details
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('DEVELOPER') or hasAuthority('ADMIN')")
    public ResponseEntity<BugResponseDTO> updateBugDetails(@PathVariable int id,@RequestParam String status,@RequestParam int assignee){
        return bugServices.updatedBugById(id, status, assignee);
    }

    // this is a method delete the bug.

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteBug(@PathVariable int id){
        return bugServices.deleteBug(id);
    }

    @GetMapping("/listByProjects/{projectId}")
    @PreAuthorize("hasAuthority('DEVELOPER') or hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<List<BugResponseDTO>> getListOfBugsByProjectId(@PathVariable int projectId){
        return bugServices.getListOfBugsByProjectId(projectId);
    }

    @GetMapping("/listByAssignedDeveloper/{developerId}")
    @PreAuthorize("hasAuthority('DEVELOPER') or hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<List<BugResponseDTO>> getListOfBugsByAssignedDeveloperId(@PathVariable int developerId){
        return bugServices.getListOfBugsByAssignedDeveloperId(developerId);
    }

}

// completed

//