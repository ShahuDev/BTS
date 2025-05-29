package com.Dev.Bug_Tracking_System2.dtos;

import com.Dev.Bug_Tracking_System2.Model.Bug;
import com.Dev.Bug_Tracking_System2.Model.Comment;
import com.Dev.Bug_Tracking_System2.Model.Project;
import com.Dev.Bug_Tracking_System2.Model.User;
import com.Dev.Bug_Tracking_System2.Repository.CommentRepository;
import com.Dev.Bug_Tracking_System2.Repository.ProjectRepository;
import com.Dev.Bug_Tracking_System2.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Slf4j
public class BugMapper {

    public static Bug mapToBug(BugRequestDTO bugRequest,
                               ProjectRepository projectRepository,
                               UserRepository userRepository) {

        // Create and map basic properties
        Bug bug = new Bug();
        bug.setTitle(bugRequest.getTitle());
        bug.setDescription(bugRequest.getDescription());
        bug.setBugStatus(bugRequest.getStatus());
        bug.setPriority(bugRequest.getPriority());
        bug.setTimestamp(LocalDate.now());

        // Map Project (required)
        Project project = projectRepository.findById(bugRequest.getProjectId())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Project not found with ID: %d", bugRequest.getProjectId())));
        bug.setProject(project);

        // Map User (optional)
        if (bugRequest.getAssigned_To() != null) {
            User user = userRepository.findById(bugRequest.getAssigned_To())
                    .orElseThrow(() -> new EntityNotFoundException(
                            String.format("User not found with ID: %d", bugRequest.getAssigned_To())));
            bug.setUser(user);
        }


        return bug;
    }

    public static BugResponseDTO mapToResponseDTO(Bug bug) {
        BugResponseDTO responseDTO = new BugResponseDTO();

        // Map basic properties
        responseDTO.setBugId(bug.getBugId());
        responseDTO.setTitle(bug.getTitle());
        responseDTO.setDescription(bug.getDescription());
        responseDTO.setStatus(bug.getBugStatus().toString());
        responseDTO.setPriority(bug.getPriority().toString());
        responseDTO.setTimestamp(bug.getTimestamp());

        // Map optional relationships
        if (bug.getUser() != null) {
            responseDTO.setAssigned_To(bug.getUser().getUsername());
        }
        if (bug.getProject() != null) {
            responseDTO.setProjectId(bug.getProject().getProjectId());
        }


        return responseDTO;
    }
}
