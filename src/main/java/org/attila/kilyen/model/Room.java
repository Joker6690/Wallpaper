package org.attila.kilyen.model;

public class Room implements Comparable<Room> {

    private final int length;
    private final int width;
    private final int height;
    private final int surfaceArea;

    public Room(int length, int width, int height, int surfaceArea) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.surfaceArea = surfaceArea;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSurfaceArea() {
        return surfaceArea;
    }

    @Override
    public int compareTo(Room other) {
        return Integer.compare(this.surfaceArea, other.surfaceArea);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (length != room.length) return false;
        if (width != room.width) return false;
        return height == room.height;
    }

    @Override
    public int hashCode() {
        int result = length;
        result = 31 * result + width;
        result = 31 * result + height;
        return result;
    }

    @Override
    public String toString() {
        return "Room{" +
                "originalFormat=" + length + "x" + width + "x" + height +
                ", length=" + length +
                ", width=" + width +
                ", height=" + height +
                ", surfaceArea=" + surfaceArea +
                "}";
    }
}
