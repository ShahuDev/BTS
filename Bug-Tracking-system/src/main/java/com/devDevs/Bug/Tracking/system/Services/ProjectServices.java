package com.devDevs.Bug.Tracking.system.Services;

import com.devDevs.Bug.Tracking.system.Models.Project;
import com.devDevs.Bug.Tracking.system.Repositories.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServices {

    @Autowired
    private ProjectRepo projectRepo;

    public ResponseEntity<List<Project>> allProjectList() {
        List<Project> list= projectRepo.findAll();
        if(list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(list,HttpStatus.OK);
    }

    public ResponseEntity<Project> createNewProject(Project project) {
        return new ResponseEntity<>(projectRepo.save(project),HttpStatus.CREATED);
    }

    public ResponseEntity<Project> getProjectById(long id) {
        Project project= projectRepo.getReferenceById(id);
        return (project == null) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(project, HttpStatus.OK);
    }

    public ResponseEntity<Project> updateProject(long id) {
        Project project= projectRepo.getReferenceById(id);
        return (project==null) ? new ResponseEntity<>(HttpStatus.BAD_REQUEST): new ResponseEntity<>(project,HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> deleteProject(long id) {
        projectRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
