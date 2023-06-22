package org.attila.kilyen.service;

import org.attila.kilyen.model.Room;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class SolutionServiceTest {
    @Mock
    FileReaderService fileReaderService;
    @InjectMocks
    SolutionService solutionService;

    private AutoCloseable closable;

    @BeforeEach
    void setUp() {
        closable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSolution() {
        String PATH = "src/test/resources/test.txt";
        when(fileReaderService.getRoomsFromFile(PATH)).thenReturn(anyList());

        solutionService.getSolution(PATH);

        verify(fileReaderService, times(1)).getRoomsFromFile(PATH);
    }

    @Test
    void testGetTotalWallpaperNeeded() {
        int result = solutionService.getTotalWallpaperNeeded(List.of(new Room(0, 0, 0, 0)));
        Assertions.assertEquals(0, result);
    }

    @Test
    void testListCubicRooms() {
        List<Room> result = solutionService.listCubicRooms(List.of(new Room(1, 1, 1, 7),
                new Room(2, 2, 2, 28), new Room(1, 2, 3, 24)));
        Assertions.assertEquals(List.of(new Room(2, 2, 2, 28),
                new Room(1, 1, 1, 7)), result);
    }

    @Test
    void testListCubicRooms_emptyList() {
        List<Room> result = solutionService.listCubicRooms(List.of(new Room(3, 2, 1, 24),
                new Room(1, 2, 3, 24), new Room(1, 1, 2, 11)));
        Assertions.assertEquals(Collections.EMPTY_LIST, result);
    }

    @Test
    void testListIdenticalRooms() {
        List<Room> result = solutionService.listIdenticalRooms(List.of(new Room(2, 2, 2, 28),
                new Room(2, 2, 2, 28), new Room(1, 1, 1, 7),
                new Room(1, 1, 1, 7), new Room(0, 0, 0, 0)));
        Assertions.assertEquals(List.of(new Room(2, 2, 2, 28),
                new Room(1, 1, 1, 7)), result);
    }

    @Test
    void testListIdenticalRooms_emptyList() {
        List<Room> result = solutionService.listIdenticalRooms(List.of(new Room(2, 2, 2, 28),
                new Room(1, 1, 1, 7), new Room(1, 1, 2, 11),
                new Room(0, 0, 0, 0)));
        Assertions.assertEquals(Collections.EMPTY_LIST, result);
    }

    @Test
    void testPrintSolutionLists() {
        String result = solutionService.printSolutionLists(List.of(new Room(0, 0, 0, 0)));
        Assertions.assertEquals("\n"+new Room(0,0,0,0), result);
    }

    @AfterEach
    public void releaseMocks() throws Exception {
        closable.close();
    }
}