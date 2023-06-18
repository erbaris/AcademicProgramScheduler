package com.erbaris.AcademicProgramScheduler;


import com.erbaris.AcademicProgramScheduler.entity.Courses;
import com.erbaris.AcademicProgramScheduler.util.CourseUtil;

public class AcademicProgramSchedulerAppSimulation {

	public static void main(String[] args) {
		run();
	}
	public static void run()
	{
		int q1 = 4;
		String[] prerequisite1 = {"1 0", "2 0", "3 1 2"};
		var c1 = new Courses(q1, prerequisite1);
		System.out.printf("Test 1 Schedule is \n  with Fast: %s\n  without Fast: %s\n", c1.getScheduleFast(), c1.getSchedule());

		int q2 = 7;
		String[] prerequisite2 = {"0 1 2", "1 3", "2 3", "3 4 5", "4 6", "5 6"};
		var c2 = new Courses(q2, prerequisite2);
		System.out.printf("Test 2 Schedule is \n  with Fast: %s\n  without Fast: %s\n", c2.getScheduleFast(), c2.getSchedule());

		int q3 = 8;
		String[] prerequisite3 = {"4 0 2", "0 1 6", "2 3 7", "1 5", "6 5", "3 5", "7 5"};
		var c3 = new Courses(q3, prerequisite3);
		System.out.printf("Test 3 Schedule is \n  with Fast: %s\n  without Fast: %s\n", c3.getScheduleFast(), c3.getSchedule());

		int q4 = 4;
		String[] prerequisite4 = {"2 1 3"};
		var c4 = new Courses(q4, prerequisite4);
		System.out.printf("Test 4 Schedule is \n  with Fast: %s\n  without Fast: %s\n", c4.getScheduleFast(), c4.getSchedule());


		var courses = CourseUtil.getCourses();
		System.out.printf("Your Schedule is \n  with Fast: %s\n  without Fast: %s\n", courses.getScheduleFast(), courses.getSchedule());
		System.out.printf("Schedule is : %s\n", courses.getSchedule());
	}

}
