package algospot.book;


import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TestClass {
    
    private static final double PI = 3.141592;

    @Test
    public void test() {
        assertEquals(0, 0 | 0);
        assertEquals(1, 0 | 1);
        assertEquals(1, 1 | 0);
        assertEquals(1, 1 | 1);
    }
    
    public static void main(String[] args) throws IOException {
        System.out.println(Math.toDegrees(Math.PI));
//        System.out.println(Math.toDegrees(Math.atan(0.5)));
//        System.out.println(Math.sin(Math.toRadians(30)));
//        System.out.println(Math.toDegrees(Math.asin(0.5)));
    }
}
