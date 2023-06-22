package org.attila.kilyen.service;

import org.attila.kilyen.model.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

class SolutionServiceTest {
    @Mock
    FileReaderService fileReaderService;
    @InjectMocks
    SolutionService solutionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetSolution() {
        when(fileReaderService.getRoomsFromFile(anyString())).thenReturn(List.of(new Room(0, 0, 0)));

        solutionService.getSolution("filePath");
    }

    @Test
    void testGetTotalWallpaperNeeded() {
        int result = solutionService.getTotalWallpaperNeeded(List.of(new Room(0, 0, 0)));
        Assertions.assertEquals(0, result);
    }

    @Test
    void testListCubicRooms() {
        List<Room> result = solutionService.listCubicRooms(List.of(new Room(0, 0, 0)));
        Assertions.assertEquals(List.of(new Room(0, 0, 0)), result);
    }

    @Test
    void testListIdenticalRooms() {
        List<Room> result = solutionService.listIdenticalRooms(List.of(new Room(0, 0, 0)));
        Assertions.assertEquals(List.of(new Room(0, 0, 0)), result);
    }
}