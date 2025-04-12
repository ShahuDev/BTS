package com.devDevs.Bug.Tracking.system.Controllers;

import com.devDevs.Bug.Tracking.system.Models.Project;
import com.devDevs.Bug.Tracking.system.Models.User;
import com.devDevs.Bug.Tracking.system.Services.ProjectServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectServices projectServices;

    // to return list of all projects
    @GetMapping("/list")
    public ResponseEntity<List<Project>> allProjectList(){
        return projectServices.allProjectList();
    }

    /*
    public ResponseEntity<List<Project>> allProjectList(){
        return new ResponseEntity.ok(projectServices.getAllProject())
    }
     */

    // this is to create new project.
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Project> createNewProject(Project project){
        return projectServices.createNewProject(project);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectDetails(@PathVariable long id){
        return projectServices.getProjectById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Project> updateTheProject(@PathVariable long id){
        return projectServices.updateProject(id);
    }

    @DeleteMapping("/id")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<HttpStatus> deleteTheProject(@PathVariable long id){
        return projectServices.deleteProject(id);
    }



}


// completed