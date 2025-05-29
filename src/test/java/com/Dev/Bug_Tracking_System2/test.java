package com.Dev.Bug_Tracking_System2;

import com.Dev.Bug_Tracking_System2.Service.BugService;
import com.Dev.Bug_Tracking_System2.dtos.BugRequestDTO;
import com.Dev.Bug_Tracking_System2.dtos.BugResponseDTO;
import com.Dev.Bug_Tracking_System2.Model.Bug;
import com.Dev.Bug_Tracking_System2.Model.Bug.BugPriority;
import com.Dev.Bug_Tracking_System2.Model.Bug.BugStatus;
import com.Dev.Bug_Tracking_System2.Repository.BugRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BugServiceTest {

    @Mock
    private BugRepository bugRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private BugService bugService;

    @Test
    void testCreateBug_ReturnsExpectedResponse() {
        // Arrange
        BugRequestDTO requestDTO = new BugRequestDTO();
        requestDTO.setTitle("Login error");
        requestDTO.setDescription("Throws NPE");
        requestDTO.setStatus(BugStatus.OPEN);
        requestDTO.setPriority(BugPriority.P1_CRITICAL);

        Bug bugEntity = new Bug();
        bugEntity.setBugId(1);
        bugEntity.setTitle("Login error");
        bugEntity.setDescription("Throws NPE");
        bugEntity.setBugStatus(BugStatus.OPEN);
        bugEntity.setPriority(BugPriority.P1_CRITICAL);
        bugEntity.setTimestamp(LocalDate.now());

        BugResponseDTO responseDTO = new BugResponseDTO();
        responseDTO.setBugId(1);
        responseDTO.setTitle("Login error");
        responseDTO.setDescription("Throws NPE");

        // Mock behavior
        when(modelMapper.map(requestDTO, Bug.class)).thenReturn(bugEntity);
        when(bugRepository.save(bugEntity)).thenReturn(bugEntity);
        when(modelMapper.map(bugEntity, BugResponseDTO.class)).thenReturn(responseDTO);

        // Act
        ResponseEntity<BugResponseDTO> response = bugService.createBug(requestDTO);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Login error", response.getBody().getTitle());
        assertEquals("Throws NPE", response.getBody().getDescription());

        // Verify
        verify(modelMapper).map(requestDTO, Bug.class);
        verify(bugRepository).save(bugEntity);
        verify(modelMapper).map(bugEntity, BugResponseDTO.class);
    }
}
