package com.erbaris.AcademicProgramScheduler.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class CoursesTest {

    @Test
    public void givenInputTest1_exceptedOrderProvided()
    {
        int q = 4;
        String[] prerequisite = {"1 0", "2 0", "3 1 2"};
        var c = new Courses(q, prerequisite);
        String result = c.getSchedule();
        String expected = "0 1 2 3";
        Assertions.assertEquals(result, expected);
    }

    @Test
    public void givenInputTest2_exceptedOrderProvided()
    {
        int q = 7;
        String[] prerequisite = {"0 1 2", "1 3", "2 3", "3 4 5", "4 6", "5 6"};
        var c = new Courses(q, prerequisite);
        String result = c.getSchedule();
        String expected = "6 4 5 3 1 2 0";
        Assertions.assertEquals(result, expected);
    }

    @Test
    public void givenInputTest3_exceptedOrderProvided()
    {
        int q = 8;
        String[] prerequisite = {"4 0 2", "0 1 6", "2 3 7", "1 5", "6 5", "3 5", "7 5"};
        var c = new Courses(q, prerequisite);
        String result = c.getSchedule();
        String expected = "5 1 3 6 0 7 2 4";
        Assertions.assertEquals(result, expected);
    }

    @Test
    public void givenInputTest4_exceptedOrderProvided()
    {
        int q = 4;
        String[] prerequisite = {"2 1 3"};
        var c = new Courses(q, prerequisite);
        String result = c.getSchedule();
        String expected = "0 1 3 2";
        Assertions.assertEquals(result, expected);
    }

    @Test
    public void fast_givenInputTest1_exceptedOrderProvided()
    {
        int q = 4;
        String[] prerequisite = {"1 0", "2 0", "3 1 2"};
        var c = new Courses(q, prerequisite);
        String result = c.getScheduleFast();
        String expected = "0 1 2 3";
        Assertions.assertEquals(result, expected);
    }

    @Test
    public void fast_givenInputTest2_exceptedOrderProvided()
    {
        int q = 7;
        String[] prerequisite = {"0 1 2", "1 3", "2 3", "3 4 5", "4 6", "5 6"};
        var c = new Courses(q, prerequisite);
        String result = c.getScheduleFast();
        String expected = "6 4 5 3 1 2 0";
        Assertions.assertEquals(result, expected);
    }

    @Test
    public void fast_givenInputTest3_exceptedOrderProvided()
    {
        int q = 8;
        String[] prerequisite = {"4 0 2", "0 1 6", "2 3 7", "1 5", "6 5", "3 5", "7 5"};
        var c = new Courses(q, prerequisite);
        String result = c.getScheduleFast();
        String expected = "5 1 3 6 0 7 2 4";
        Assertions.assertEquals(result, expected);
    }

    @Test
    public void fast_givenInputTest4_exceptedOrderProvided()
    {
        int q = 4;
        String[] prerequisite = {"2 1 3"};
        var c = new Courses(q, prerequisite);
        String result = c.getScheduleFast();
        String expected = "0 1 3 2";
        Assertions.assertEquals(result, expected);
    }



}
