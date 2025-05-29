package com.Dev.Bug_Tracking_System2.Service;
import com.Dev.Bug_Tracking_System2.Exceptions.EntityNotFoundException;
import com.Dev.Bug_Tracking_System2.Model.Project;
import com.Dev.Bug_Tracking_System2.Repository.ProjectRepository;
import com.Dev.Bug_Tracking_System2.dtos.ProjectMapper;
import com.Dev.Bug_Tracking_System2.dtos.ProjectRequestDTO;
import com.Dev.Bug_Tracking_System2.dtos.ProjectResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;

@Slf4j
@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectMapper projectMapper;


    public ResponseEntity<ProjectResponseDTO> createProject(@RequestBody ProjectRequestDTO project){
        try {
            var projectEntity = ProjectMapper.toEntity(project);
            var savedProject = projectRepository.save(projectEntity);
            log.info("Project created successfully");
            return new ResponseEntity<>(ProjectMapper.toResponseDTO(savedProject), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            log.error("Error creating project: {}", e.getMessage());
            throw new RuntimeException("Some upexpected server error occurred. Please try again later. Error: " + e.getMessage(), e);

        }
    }
    public ResponseEntity<ProjectResponseDTO> updateProject(ProjectRequestDTO projectDTO, int id) {
        try {
            // Fetch the project by ID, throw if not found
            Project existingProject = projectRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + id));

            // Update the existing project with values from the DTO
            Project updatedProject = ProjectMapper.updateEntityFromDTO(existingProject, projectDTO);

            // Save updated project
            Project savedProject = projectRepository.save(updatedProject);

            log.info("Project with ID {} updated successfully", id);
            return ResponseEntity.ok(ProjectMapper.toResponseDTO(savedProject));
        } catch (RuntimeException e) {
            log.error("Error updating project with ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Unexpected server error occurred. Please try again later. Error: " + e.getMessage(), e);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public ResponseEntity<ProjectResponseDTO> getProjectById(int id){
        try{
            var project = projectRepository.findById(id);
            if(project.isEmpty()){
                log.error("Project not found with id: {}",id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            log.info("Project found with id: {}",id);
            return new ResponseEntity<>(ProjectMapper.toResponseDTO(project.get()),HttpStatus.OK);
        }catch (RuntimeException e){
            log.error("Error getting project: {}", e.getMessage());
            throw new RuntimeException("Some upexpected server error occurred. Please try again later. Error: " + e.getMessage(), e);
        }
    }
    public ResponseEntity<List<ProjectResponseDTO>> getAllProjects(){
        var list = projectRepository.findAll();
        if(list.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else{
            return new ResponseEntity<>(projectMapper.toResponseDTOList(list),HttpStatus.OK);
        }

    }
    public ResponseEntity<String> deleteProject(int id){
        try{
            projectRepository.deleteById(id);
            log.info("Project with id: {} deleted successfully",id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (RuntimeException e){
            log.error("Error deleting project with id: {}", e.getMessage());
            throw new RuntimeException("Some unexpected server error occurred. Please try again later. Error: " + e.getMessage(), e);
        }
    }

}
