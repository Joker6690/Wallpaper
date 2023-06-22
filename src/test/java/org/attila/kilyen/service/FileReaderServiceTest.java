package org.attila.kilyen.service;

import org.attila.kilyen.exception.WallpaperException;
import org.attila.kilyen.model.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class FileReaderServiceTest {
    FileReaderService fileReaderService = new FileReaderService();
    private final String PATH = "src/test/resources/test.txt";
    @Test
    void testGetRoomsFromFile() {
        List<Room> result = Assertions.assertDoesNotThrow(() -> fileReaderService.getRoomsFromFile(PATH));
        Assertions.assertEquals(List.of(new Room(0, 0, 0, 0),
                new Room(2, 2, 2, 28), new Room(1, 2, 3, 24)), result);
    }


    @Test
    void testCreateRoom() {
        Room result = Assertions.assertDoesNotThrow(() -> fileReaderService.createRoom("1x2x3", 0));
        Assertions.assertEquals(new Room(1, 2, 3, 24), result);
    }
    @Test
    void testCreateRoom_exception_noSeparator() {
        WallpaperException ex = Assertions.assertThrows(WallpaperException.class, () -> fileReaderService.createRoom("0", 0));
        Assertions.assertEquals("The data is malformed on line: 0", ex.getMessage());
    }

    @Test
    void testCreateRoom_exception_notEnoughSeparator() {
        WallpaperException ex = Assertions.assertThrows(WallpaperException.class, () -> fileReaderService.createRoom("0x2", 2));
        Assertions.assertEquals("The data is malformed on line: 2", ex.getMessage());
    }

    @Test
    void testCreateRoom_exception_tooManySeparator() {
        WallpaperException ex = Assertions.assertThrows(WallpaperException.class,
                () -> fileReaderService.createRoom("0x2x3x2", 4));
        Assertions.assertEquals("The data is malformed on line: 4", ex.getMessage());
    }

    @Test
    void testCalculateSurfaceArea() {
        int result = Assertions.assertDoesNotThrow(() -> fileReaderService.calculateSurfaceArea(3, 3, 3, 0));
        Assertions.assertEquals(63, result);
    }

    @Test
    void testCalculateSurfaceArea_exception_negativeLength() {
        WallpaperException ex = Assertions.assertThrows(WallpaperException.class,
                () ->  fileReaderService.calculateSurfaceArea(-3, 3, 3, 2));
        Assertions.assertEquals("Data contains negative digits on line: 2", ex.getMessage());
    }

    @Test
    void testCalculateSurfaceArea_exception_negativeWidth() {
        WallpaperException ex = Assertions.assertThrows(WallpaperException.class,
                () ->  fileReaderService.calculateSurfaceArea(3, -3, 3, 3));
        Assertions.assertEquals("Data contains negative digits on line: 3", ex.getMessage());
    }

    @Test
    void testCalculateSurfaceArea_exception_negativeHeight() {
        WallpaperException ex = Assertions.assertThrows(WallpaperException.class,
                () ->  fileReaderService.calculateSurfaceArea(3, 3, -3, 4));
        Assertions.assertEquals("Data contains negative digits on line: 4", ex.getMessage());
    }
}
