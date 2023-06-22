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
                roomList.add(createRoom(line, lineNo));
                lineNo++;
            }
        } catch (IOException | WallpaperException e) {
            System.out.println(e.getMessage());
        }
        return roomList;
    }

    public Room createRoom(String line, int lineNo) throws WallpaperException {
        if(line.contains("x") && line.indexOf('x') != line.lastIndexOf('x') && line.split("x").length < 4) {
            List<Integer> attribs = Arrays.stream(line.split("x")).map(Integer::parseInt).toList();
            return new Room(attribs.get(0), attribs.get(1), attribs.get(2),
                    calculateSurfaceArea(attribs.get(0), attribs.get(1), attribs.get(2), lineNo));
        }
        throw new WallpaperException("The data is malformed on line: " + lineNo);
    }

    public int calculateSurfaceArea(int length, int width, int height, int lineNo) throws WallpaperException {
        if (length < 0 || width < 0 || height < 0) {
            throw new WallpaperException("Data contains negative digits on line: " + lineNo);
        }
        int wall1 = length * width;
        int wall2 = width * height;
        int wall3 = height * length;
        int smallestWall =  wall1 < wall2 && wall1 < wall3 ? wall1
                : wall2 < wall1 && wall2 < wall3 ? wall2 : wall3;
        return 2 * (wall1 + wall2 + wall3) + smallestWall;
    }
}
