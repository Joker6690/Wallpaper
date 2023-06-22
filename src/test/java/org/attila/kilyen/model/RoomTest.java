package org.attila.kilyen.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RoomTest {
    Room room = new Room(0, 0, 0);

    @Test
    void testCompareTo() {
        int result = room.compareTo(new Room(0, 0, 0));
        Assertions.assertEquals(0, result);
    }

    @Test
    void testEquals() {
        boolean result = room.equals(new Room(0,0,0));
        Assertions.assertEquals(true, result);
    }

    @Test
    void testHashCode() {
        int result = room.hashCode();
        Assertions.assertEquals(0, result);
    }

    @Test
    void testToString() {
        String result = room.toString();
        Assertions.assertEquals("Room{originalFormat=0x0x0, length=0, width=0, height=0, surfaceArea=0}", result);
    }
}
