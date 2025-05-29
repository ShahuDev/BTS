package com.Dev.Bug_Tracking_System2.Service;

import com.Dev.Bug_Tracking_System2.Exceptions.EntityNotFoundException;
import com.Dev.Bug_Tracking_System2.Model.Bug;
import com.Dev.Bug_Tracking_System2.Model.Comment;
import com.Dev.Bug_Tracking_System2.Model.User;
import com.Dev.Bug_Tracking_System2.Repository.BugRepository;
import com.Dev.Bug_Tracking_System2.Repository.CommentRepository;
import com.Dev.Bug_Tracking_System2.Repository.ProjectRepository;
import com.Dev.Bug_Tracking_System2.Repository.UserRepository;
import com.Dev.Bug_Tracking_System2.dtos.BugMapper;
import com.Dev.Bug_Tracking_System2.dtos.BugRequestDTO;
import com.Dev.Bug_Tracking_System2.dtos.BugResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;



@Service
@Slf4j
public class BugService {

    @Autowired
    private BugRepository bugRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EmailService emailService;

    /*
    * This method is used to create a new Bug.
    * completed and Tested. working ok.
     */
    public ResponseEntity<BugResponseDTO> createBug(BugRequestDTO bug) {

        var bugObject = BugMapper.mapToBug(bug, projectRepository, userRepository);

        var savedBug=bugRepository.save(bugObject);

        // Create and map new Comment
        Comment comment = new Comment();
        comment.setTimestamp(LocalDate.now());
        comment.setBug(savedBug);
        comment.setUser(savedBug.getUser());
         var savedComment=commentRepository.save(comment);

        // 🔥 Attach the comment to bug
        savedBug.setComment(null);
        bugRepository.save(savedBug); // Optional, but ensures DB reflects it
        log.info("Bug created successfully");
        //SendEmailToAssignedDeveloper(savedBug);
        return new ResponseEntity<>(BugMapper.mapToResponseDTO(bugObject), HttpStatus.CREATED);
    }

    /*
        The logic gets the list of bugs according to the project id;
     */
    public ResponseEntity<List<BugResponseDTO>> getListOfBugsByProjectId(int projectId) {
        var bugs = bugRepository.findAllByProject_ProjectId(projectId);
        if(bugs.isEmpty()){
            log.error("No bugs found for project id: {}",projectId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("Bugs found for project id: {}",projectId);
        // Map the bug entities to DTOs
        List<BugResponseDTO> bugDTO = bugs.stream()
                .map(BugMapper::mapToResponseDTO)
                .toList();

        return new ResponseEntity<>(bugDTO,HttpStatus.OK);
    }



    public ResponseEntity<List<BugResponseDTO>> getListOfBugsByAssignedDeveloperId(int developerId) {
        var bugs = bugRepository.findAllByUser_UserId(developerId);
        if(bugs.isEmpty()){
            log.error("No bugs found for developer id: {}",developerId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<BugResponseDTO> bugDTO = bugs.stream()
                .map(BugMapper::mapToResponseDTO)
                .toList();
        log.info("Bugs found for developer id: {}",developerId);
        return new ResponseEntity<>(bugDTO,HttpStatus.OK);
    }

    // this is the logic to find the bug by id.
public ResponseEntity<BugResponseDTO> getBugById(int id) {
    return bugRepository.findById(id)
            .map(bug -> new ResponseEntity<>(BugMapper.mapToResponseDTO(bug), HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
}


    /*
        this logic is to update bug status and assignee
        not tested yet.
     */

    public ResponseEntity<BugResponseDTO> updatedBugById(int id, String status, int assigneeId) {
    try {
        Bug bug = bugRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Bug not found with ID: " + id));
            
        // Update status
        bug.setBugStatus(Bug.BugStatus.valueOf(status));
        
        // Find and set assignee
        User assignee = userRepository.findById(assigneeId)
            .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + assigneeId));
        bug.setUser(assignee);
        
        var saved = bugRepository.save(bug);
        log.info("Bug with id: {} updated successfully", id);
        return new ResponseEntity<>(BugMapper.mapToResponseDTO(saved), HttpStatus.OK);
    } catch (Exception e) {
        log.error("Error updating bug with id: {}, error: {}", id, e.getMessage());
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

    /*
        this is the logic to delete the bug.

     */

    public ResponseEntity<String> deleteBug(int id) {
        try {
            bugRepository.deleteById(id);
            log.info("Bug with id: {} deleted successfully",id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            log.error("Bug with id: {} not found",id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<List<Bug>> getListOfBugs() {
        if(bugRepository.findAll().isEmpty()){
            log.error("No bugs found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("Bugs found");
        return new ResponseEntity<>(bugRepository.findAll(),HttpStatus.OK);
    }

    public void SendEmailToAssignedDeveloper(Bug bug) {
        try{
            emailService.sendEmail(bug.getUser().getEmail(),"New Bug Assigned to you",
                    """
                            Hello Developer!
                            A new bug has been assigned to you.
                            following are the details of the bug:
                            Bug Id: %s
                            Bug Title: %s
                            Bug Description: %s
                            Bug Status: %s
                            Bug Priority: %s
                            Bug Timestamp: %s
                            Bug Project Id: %s
                            """.formatted(bug.getBugId(),bug.getTitle(),bug.getDescription(),bug.getBugStatus(),bug.getPriority(),bug.getTimestamp(),bug.getProject().getProjectId())
                    );
            log.info("Email sent successfully");
        } catch (Exception e) {
            log.error("Email not sent");
            throw new RuntimeException(e);
        }
    }
}