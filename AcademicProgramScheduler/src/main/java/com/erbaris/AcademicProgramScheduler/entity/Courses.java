/*----------------------------------------------------------------------
	FILE        : Course.java
	AUTHOR		: Baris Er
	LAST UPDATE	: 18.06.2023

	Courses order calculation class

	Copyleft (c) 2023 by Baris ER
	All Rights Free
-----------------------------------------------------------------------*/
package com.erbaris.AcademicProgramScheduler.entity;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;


public class Courses {
    public int courseQuantity;
    public String[] prerequisites;

    public Courses(int courseQuantity, String[] prerequisites) {
        this.courseQuantity = courseQuantity;
        this.prerequisites = prerequisites;
    }

    public String getSchedule()
    {
        // Create a list with natural order without prerequisite
        var resultList = new ArrayList<Integer>(courseQuantity);

        for(int i = 0; i < courseQuantity; i++){
            resultList.add(i);
        }

        // get prerequisite table in ArrayList<int[]>
        var relations = getCourseRelations();

        // check each element prerequisite and change its place if it is needed.
        for(int i = 0; i < courseQuantity; i++){
            // get Prerequisite table for current element as Optional.
            var preTmpList = getPrerequisiteOptArr(i, relations);
            if(preTmpList.isEmpty())
                continue;
            // find its Prerequisites' max idx
            var maxPrereqIdx = findMaxIdx(preTmpList.get(), resultList);

            // check if replace is required
            if(resultList.indexOf(i) < maxPrereqIdx){
                // check replacement idx should move more according to natural order if prerequisite allows
                while(true){
                    if(maxPrereqIdx < resultList.size() - 1 && resultList.get(maxPrereqIdx + 1) < i &&
                            !isNextIdxPrerequisite(maxPrereqIdx, i, resultList, relations))
                        maxPrereqIdx++;
                    else
                        break;
                }

                // replace element
                replaceOrder(i, maxPrereqIdx, resultList, relations);
            }


        }

        // create result as requested String format
        var result = new StringBuilder();
        resultList.forEach(s -> result.append(s).append(" "));
        return result.toString().trim();
    }
    private void replaceOrder(int i, int maxPrereqIdx, ArrayList<Integer> resultList,ArrayList<int[]> relations)
    {
        // add the elements requested place
        resultList.add(maxPrereqIdx + 1, i);
        // remove first element
        resultList.remove((Object)i);
        // check prerequisite rules of before elements.
        beforePrerequisiteCheck(i, resultList, relations);
    }
    private void beforePrerequisiteCheck(int i, ArrayList<Integer> resultList, ArrayList<int[]> relations)
    {
        int count = 0;
        if(i == 0)
            return;
        // check before elements prerequisite list for the elements i
        for(int j = 0; j < i; j++){
            var preList = getPrerequisiteOptArr(j, relations);
            if(preList.isEmpty())
                continue;
            var preArr = preList.get();
            for(int k = 1; k < preArr.length; k++){
                if(preArr[k] == i)
                    // if this element exists and idx is smaller, that elements should be moved.
                    // count is for keep natural order if more than 1 elements move
                    if(resultList.indexOf(i) > resultList.indexOf(j))
                        replaceOrder(j, resultList.indexOf(i) + count++, resultList, relations);
            }
        }

    }
    private  boolean isNextIdxPrerequisite(int maxPrereqIdx, int i, ArrayList<Integer> resultList, ArrayList<int[]> relations)
    {
        // get prerequisite list of next element
        var nextPrerequisiteList = getPrerequisiteOptArr(resultList.get(maxPrereqIdx + 1), relations);
        if(nextPrerequisiteList.isEmpty())
            return false;
        var prerequisiteArr = nextPrerequisiteList.get();
        // check that prerequisite table contain the element i
        for(int j = 1; j < prerequisiteArr.length; j++)
            if(prerequisiteArr[j] == i)
                return true;
        return false;
    }
    private int findMaxIdx(int[] prereqList, ArrayList<Integer> resultList)
    {
        int maxIdx = resultList.indexOf(prereqList[1]);
        if(prereqList.length > 2)
            for(int i = 2; i < prereqList.length; i++){
                int index = resultList.indexOf(prereqList[i]);
                if(maxIdx < index)
                    maxIdx = index;
            }
        return maxIdx;
    }
    private Optional<int[]> getPrerequisiteOptArr(int idx, ArrayList<int[]> relations)
    {
        for (int[] tmp : relations) {
            if (idx == tmp[0])
                return Optional.of(tmp);
        }
        return Optional.empty();
    }

    private ArrayList<int[]> getCourseRelations()
    {
        var result = new ArrayList<int[]>();
        for (String prerequisite : prerequisites) {
            result.add(getCourseInt(prerequisite));
        }
        return result;
    }
    private int[] getCourseInt(String str)
    {
        ArrayList<Integer> resultList = new ArrayList<>();
        Arrays.stream(str.split(" ")).map(Integer::parseInt).forEach(resultList::add);

        var result = new int[resultList.size()];
        for (int i = 0; i < result.length; i++){
            result[i] = resultList.get(i);
        }
        return result;
    }


    /// *** BELOW IS SECOND FAST SOLUTION *** ///

    public String getScheduleFast()
    {
        // Create an empty list
        var resultList = new ArrayList<Integer>(courseQuantity);

        // get prerequisite table in ArrayList<int[]>
        var relations = getCourseRelations();

        resultList.add(0);

        for(int i = 1; i < courseQuantity; i++) {
            var minMaxIdx = findMinMaxIdx(i, relations, resultList, true);
            var idx = findIdxAccNaturalOrder(i, minMaxIdx[0], minMaxIdx[1], resultList);
            resultList.add(idx, i);
        }

        naturalOrderCheck(relations, resultList);

        // create result as requested String format
        var result = new StringBuilder();
        resultList.forEach(s -> result.append(s).append(" "));
        return result.toString().trim();
    }
    private void replaceOrderWithoutCheck(int i, int newIdx, ArrayList<Integer> resultList)
    {
        // remove element
        resultList.remove((Object)i);
        // add the elements requested place
        resultList.add(newIdx, i);
    }

    private int[] findMinMaxIdx(int i, ArrayList<int[]> relations, ArrayList<Integer> resultList, boolean accordingToPreviousElement)
    {
        // get Prerequisite table for current element as Optional.
        var preTmpList = getPrerequisiteOptArr(i, relations);
        int minIdx = 0;
        int maxIdx = resultList.size();

        // find minIdx according to i's Prerequisite
        if(preTmpList.isPresent())
            for(int p : preTmpList.get()){
                if (p == i)
                    continue;
                int tmpIdx = resultList.indexOf(p);
                if(tmpIdx != -1 && minIdx < tmpIdx)
                    minIdx = tmpIdx;
            }

        var limit = courseQuantity;
        if(accordingToPreviousElement)
            limit = i;

        // find maxIdx according to previous elements' Prerequisite
        for(int j = 0; j < limit; j++){
            var preList = getPrerequisiteOptArr(j, relations);
            if(preList.isEmpty())
                continue;
            var preArr = preList.get();
            for(int k = 1; k < preArr.length; k++){
                if(preArr[k] == i){
                    int tmpIdx = resultList.indexOf(j);
                    if( tmpIdx < maxIdx)
                        maxIdx = tmpIdx;
                }
            }
        }
        return new int[] {minIdx, maxIdx};
    }

    private int findIdxAccNaturalOrder(int i, int minIdx, int maxIdx, ArrayList<Integer> resultList)
    {
        // find place according to natural order
        int Idx = maxIdx;
        for(int j = minIdx; j < maxIdx; j++){
            if(i < resultList.get(j))
                Idx = j;
        }
        return Idx;
    }

    private void naturalOrderCheck(ArrayList<int[]> relations, ArrayList<Integer> resultList)
    {
        for(int i= 0; i < courseQuantity; i++){
            var minMaxIdx = findMinMaxIdx(i, relations, resultList, false);
            for (int j = minMaxIdx[0] + 1; j < minMaxIdx[1]; j++)
                if (i < resultList.get(j)) {
                    if(resultList.get(j - 1) != i)
                        replaceOrderWithoutCheck(i, j, resultList);
                    break;
                }
        }

    }
}
