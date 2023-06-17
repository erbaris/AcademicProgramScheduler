/*----------------------------------------------------------------------
	FILE        : CourseUtil.java
	AUTHOR		: Baris Er
	LAST UPDATE	: 17.06.2023

	Utility class for Courses class

	Copyleft (c) 2023 by Baris ER
	All Rights Free
-----------------------------------------------------------------------*/
package com.erbaris.AcademicProgramScheduler.util;

import com.erbaris.AcademicProgramScheduler.entity.Courses;
import org.csystem.util.console.Console;

import java.util.ArrayList;

public class CourseUtil {
    private CourseUtil() {};

    public static Courses getCourses()
    {
        int quantity = Console.readInt("quantity:");
        var prerequisites = new ArrayList<String>();
        int i = 0;
        while(true){
            var tmp = Console.readLine("Prerequisites (0 for calculate):").trim();
            try {
                if(Integer.parseInt(tmp) == 0)
                    break;
            }
            catch (NumberFormatException ignore) {
            }
            prerequisites.add(tmp);
        }
        var prerequisitesStr = new String[prerequisites.size()];
        for(int j = 0; j < prerequisitesStr.length; j++)
            prerequisitesStr[j] = prerequisites.get(j);

        return new Courses(quantity, prerequisitesStr);
    }

}
