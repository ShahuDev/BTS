/*
package com.Dev.Bug_Tracking_System2.dtos;

import com.Dev.Bug_Tracking_System2.Model.Bug;
import com.Dev.Bug_Tracking_System2.Repository.CommentRepository;
import com.Dev.Bug_Tracking_System2.Repository.ProjectRepository;
import com.Dev.Bug_Tracking_System2.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;


@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(UserRepository userRepository,
                                   ProjectRepository projectRepository,
                                   CommentRepository commentRepository) {
        ModelMapper modelMapper = new ModelMapper();

        // Disable implicit mapping
        modelMapper.getConfiguration()
                .setSkipNullEnabled(true)
                .setMatchingStrategy(MatchingStrategies.STRICT);

        // Create explicit mapping
        modelMapper.createTypeMap(BugRequestDTO.class, Bug.class)
                .addMappings(mapper -> {
                    // Skip these fields from automatic mapping
                    mapper.skip(Bug::setUser);
                    mapper.skip(Bug::setProject);
                    mapper.skip(Bug::setComment);
                    mapper.skip(Bug::setBugId);
                    // Map the fields we want to copy
                    mapper.map(BugRequestDTO::getTitle, Bug::setTitle);
                    mapper.map(BugRequestDTO::getDescription, Bug::setDescription);
                    mapper.map(BugRequestDTO::getStatus, Bug::setBugStatus);
                    mapper.map(BugRequestDTO::getPriority, Bug::setPriority);
                })
                .setPostConverter(context -> {
                    BugRequestDTO source = context.getSource();
                    Bug destination = context.getDestination();

                    if (source.getAssigned_To() != null) {
                        destination.setUser(userRepository.findById(source.getAssigned_To())
                                .orElseThrow(() -> new EntityNotFoundException(
                                        String.format("User not found with ID: %d", source.getAssigned_To()))));
                    }

                    destination.setProject(projectRepository.findById(source.getProjectId())
                            .orElseThrow(() -> new EntityNotFoundException(
                                    String.format("Project not found with ID: %d", source.getProjectId()))));

                    destination.setComment(commentRepository.findById(source.getCommentId())
                            .orElseThrow(() -> new EntityNotFoundException(
                                    String.format("Comment not found with ID: %d", source.getCommentId()))));

                    destination.setTimestamp(LocalDate.now());
                    return destination;
                });

        return modelMapper;
    }
}
*/