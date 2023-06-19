package org.attila.kilyen.service;

import org.attila.kilyen.exception.WallpaperException;
import org.attila.kilyen.model.Room;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileReaderService {

    public List<Room> getRoomsFromFile(String path) {
        List<Room> roomList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line ;
            int lineNo = 0;

            while ((line = reader.readLine()) != null) {
                System.out.println( lineNo + " ---> " + line);
                roomList.add(createRoom(line, lineNo));
                lineNo++;
            }
        } catch (IOException | WallpaperException e) {
            System.out.println(e.getMessage());
        }
        return roomList;
    }

    public Room createRoom(String line, int lineNo) throws WallpaperException {
        if(line.contains("x")) {
            List<Integer> attribs = Arrays.stream(line.split("x")).map(Integer::parseInt).toList();
            return new Room(attribs.get(0), attribs.get(1), attribs.get(2));
        }
        throw new WallpaperException("The data is malformed on line: " + lineNo);
    }
}
