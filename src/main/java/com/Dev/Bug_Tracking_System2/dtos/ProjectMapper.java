package com.Dev.Bug_Tracking_System2.dtos;

import com.Dev.Bug_Tracking_System2.Model.Project;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectMapper {
    
    /**
     * Converts a ProjectRequestDTO to a Project entity
     * @param requestDTO the ProjectRequestDTO to convert
     * @return the Project entity
     */
    public static Project toEntity(ProjectRequestDTO requestDTO) {
        Project project = new Project();
        
        // Set properties from DTO to entity
        project.setName(requestDTO.getName());
        project.setDescription(requestDTO.getDescription());
        project.setCreated_at(LocalDate.now());

        // Set other properties as needed
        
        return project;
    }
    
    /**
     * Updates an existing Project entity with data from a ProjectRequestDTO
     * @param project the Project entity to update
     * @param requestDTO the ProjectRequestDTO containing new data
     * @return the updated Project entity
     */
    public static Project updateEntityFromDTO(Project project, ProjectRequestDTO requestDTO) {
        // Update only non-null fields
        if (requestDTO.getName() != null) {
            project.setName(requestDTO.getName());
        }
        if (requestDTO.getDescription() != null) {
            project.setDescription(requestDTO.getDescription());
        }


        // Update other properties as needed
        
        return project;
    }
    
    /**
     * Converts a Project entity to a ProjectResponseDTO
     * @param project the Project entity to convert
     * @return the ProjectResponseDTO
     */
    public static ProjectResponseDTO toResponseDTO(Project project) {
        ProjectResponseDTO responseDTO = new ProjectResponseDTO();
        
        // Set properties from entity to DTO
        responseDTO.setId(project.getProjectId());
        responseDTO.setName(project.getName());
        responseDTO.setDescription(project.getDescription());
        responseDTO.setCreatedAt(project.getCreated_at());
        responseDTO.setCreatedAt(project.getCreated_at());

        // Set other properties as needed
        
        return responseDTO;
    }


    /**
     * Converts a list of Project entities to a list of ProjectResponseDTOs
     * @param projects the list of Project entities
     * @return the list of ProjectResponseDTOs
     */
    public  List<ProjectResponseDTO> toResponseDTOList(List<Project> projects) {
        return projects.stream()
                .map(ProjectMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}