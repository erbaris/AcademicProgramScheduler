package com.erbaris.AcademicProgramScheduler;


import com.erbaris.AcademicProgramScheduler.util.CourseUtil;

public class AcademicProgramSchedulerAppSimulation {

	public static void main(String[] args) {
		run();
	}
	public static void run()
	{
		var courses = CourseUtil.getCourses();
		System.out.printf("Schedule is : %s\n", courses.getSchedule());
	}

}
