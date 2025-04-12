package com.devDevs.Bug.Tracking.system.Services;

import com.devDevs.Bug.Tracking.system.Models.Bug;
import com.devDevs.Bug.Tracking.system.Models.User;
import com.devDevs.Bug.Tracking.system.Repositories.BugRepo;
import com.devDevs.Bug.Tracking.system.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BugServices {

    @Autowired
    private BugRepo bugRepo;
    @Autowired
    private UserRepo userRepo;
    public ResponseEntity<Bug> createBug(Bug bug) {
        bugRepo.save(bug);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<List<Bug>> getListOfBugs() {
        var list= bugRepo.findAll();
        return (list==null) ? new ResponseEntity<>(HttpStatus.BAD_REQUEST): new ResponseEntity<>(list,HttpStatus.OK);
    }

    public ResponseEntity<Bug> getBugById(long id) {
        var bug= bugRepo.getReferenceById(id);
        return (bug==null)? new ResponseEntity<>(HttpStatus.BAD_REQUEST): new ResponseEntity<>(bug,HttpStatus.OK);
    }

    public ResponseEntity<Bug> deleteBug(long id) {
        var bug= bugRepo.getReferenceById(id);
        if(bug == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        bugRepo.delete(bugRepo.getReferenceById(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

 /*   public Bug updatedBugById(long id, String status, String assignee) {
        Bug bug= bugRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Bug not Found"));
        User assign = userRepo.findByUsername(assignee)
                .orElseThrow(() -> new RuntimeException("User not found"));

        bug.setStatus(status);
        bug.setAssigned_to(assign);

        return bugRepo.save(bug);

        }

*/
}
