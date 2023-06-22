package org.attila.kilyen.service;

import org.attila.kilyen.model.Room;

import java.util.*;

public class SolutionService {

    private final FileReaderService fileReaderService;

    public SolutionService(FileReaderService fileReaderService) {
        this.fileReaderService = fileReaderService;
    }

    public void getSolution(String filePath) {
        List<Room> rooms = fileReaderService.getRoomsFromFile(filePath);
        System.out.println("Total square feet of wallpaper needed: " + getTotalWallpaperNeeded(rooms) + "sq. ft.");
        System.out.println("List of rooms with cubic shape (descending): " + printSolutionLists(listCubicRooms(rooms)));
        System.out.println("List of rooms that are appearing more than once: " + printSolutionLists(listIdenticalRooms(rooms)));
    }

    public int getTotalWallpaperNeeded(List<Room> rooms) {
        return rooms.stream().mapToInt(Room::getSurfaceArea).sum();
    }

    public List<Room> listCubicRooms(List<Room> rooms) {
        return rooms.stream()
                .filter(room -> (room.getLength() == room.getWidth() && room.getWidth() == room.getHeight()))
                .sorted(Comparator.reverseOrder()).toList();
    }

    public List<Room> listIdenticalRooms(List<Room> rooms) {
        List<Room> duplicates = new ArrayList<>();
        Set<Room> uniques = new HashSet<>();
        rooms.forEach(room -> {
                if(!uniques.add(room)) {
                    duplicates.add(room);
                }
        });
        return duplicates;
    }

    public String printSolutionLists(List<Room> rooms) {
        StringBuilder sb = new StringBuilder();
        rooms.forEach(room -> sb.append("\n").append(room.toString()));
        return sb.toString();
    }
}
