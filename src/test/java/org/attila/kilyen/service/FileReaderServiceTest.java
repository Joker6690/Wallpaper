package org.attila.kilyen.service;

import org.attila.kilyen.exception.WallpaperException;
import org.attila.kilyen.model.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class FileReaderServiceTest {
    FileReaderService fileReaderService = new FileReaderService();
    private final String PATH = "src/test/resources/test.txt";
    private final String WRONG_PATH = "src/test/resources/test2.txt";
    @Test
    void testGetRoomsFromFile() {
        List<Room> result = Assertions.assertDoesNotThrow(() -> fileReaderService.getRoomsFromFile(PATH));
        Assertions.assertEquals(List.of(new Room(0, 0, 0),
                new Room(2, 2, 2),new Room(1, 2, 3)), result);
    }

    @Test
    void testGetRoomsFromFile_() {
        Exception ex = Assertions.assertThrows(IOException.class, () -> fileReaderService.getRoomsFromFile(WRONG_PATH));
        Assertions.assertEquals("", ex.getMessage());
    }

    @Test
    void testCreateRoom() throws WallpaperException {
        Room result = fileReaderService.createRoom("line", 0);
        Assertions.assertEquals(new Room(0, 0, 0), result);
    }
}
