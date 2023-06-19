package org.attila.kilyen.model;

public class Room implements Comparable<Room> {

    private final int length;
    private final int width;
    private final int height;
    private final int surfaceArea;

    public Room(int length, int width, int height) {
        this.length = length;
        this.width = width;
        this.height = height;

        this.surfaceArea = calculateSurfaceArea();
    }

    private int calculateSurfaceArea() {
        int wall1 = this.length * this.width;
        int wall2 = this.width * this.height;
        int wall3 = this.height * this.length;
        int smallestWall =  wall1 < wall2 && wall1 < wall3 ? wall1
                : wall2 < wall1 && wall2 < wall3 ? wall2 : wall3;
        return 2 * (wall1 + wall2 + wall3) + smallestWall;
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
        return "\n Room{" +
                "originalFormat=" + length + "x" + width + "x" + height +
                ", length=" + length +
                ", width=" + width +
                ", height=" + height +
                ", surfaceArea=" + surfaceArea +
                "}";
    }
}
