package com.Dev.Bug_Tracking_System2.Controller;

import com.Dev.Bug_Tracking_System2.Service.ProjectService;
import com.Dev.Bug_Tracking_System2.dtos.ProjectRequestDTO;
import com.Dev.Bug_Tracking_System2.dtos.ProjectResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }
    // tested and working.
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProjectResponseDTO> createProject(@RequestBody ProjectRequestDTO project){
        return projectService.createProject(project);
    }
    // tested and working.
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('DEVELOPER') or hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<List<ProjectResponseDTO>> getAllProjects(){
        return projectService.getAllProjects();
    }
    // tested and working.
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteProject(@PathVariable int id){
        return projectService.deleteProject(id);
    }
    // tested and working
    @GetMapping("/id/{id}")
    @PreAuthorize("hasAuthority('DEVELOPER') or hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<ProjectResponseDTO> getProjectById(@PathVariable int id){
        return projectService.getProjectById(id);
    }
    // tested and working.
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProjectResponseDTO> updateById(@RequestBody ProjectRequestDTO project, @PathVariable int id){
        return projectService.updateProject(project,id);
    }
}
