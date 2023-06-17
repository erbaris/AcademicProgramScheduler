# AcademicProgramScheduler
AcademicProgramScheduler


Case:
Consider an academic program that consists of NN courses. Every course is designated by an integer index from 00 to N-1N-1.
Some courses have others as prerequisites. For example, if course 2 is a prerequisite of course 3 you must finish course 2 before enrolling in course 3.
You can attend only a single course at a time. Build a schedule to complete all courses in a linear sequence and to satisfy all prerequisites for every course.
If more than one variant of possible schedule exists, use the schedule where courses with smaller indices are finished first.
Input:
The first line contains a positive integer representing the number of courses in the academic program.
Each additional line describes the prerequisites of a given course as a space-delimited set of indices. Every set of prerequisites has at least two indices.
The first index in the set denotes the course for which a prerequisite exists. All other indices designate which courses are required for the first.

For example:
4
1 0
2 0
3 1 2
The first row means that there are 4 courses in the academic program. The second and third rows define that course 0 must be taken before courses 1 and 2. And the fourth row defines that courses 1 and 2 must be taken before course 3.

Output:
A schedule containing a space-delimited list of courses, in the order of their attendance. For example:
0 1 2 3
For this example, another schedule exists 0 2 1 3. But we must select the variant 0 1 2 3 where course 1 (with a smaller index) is attended before the course 2 (with a greater index).

Test 1
Test Input
4
1 0
2 0
3 1 2
Expected Output
0 1 2 3



Test 2
Test Input
7
0 1 2
1 3
2 3
3 4 5
4 6
5 6
Expected Output
6 4 5 3 1 2 0

Test 3
Test Input
8
4 0 2
0 1 6
2 3 7
1 5
6 5
3 5
7 5
Expected Output
5 1 3 6 0 7 2 4

Test 4
Test Input
4
2 1 3
Expected Output
0 1 3 2
