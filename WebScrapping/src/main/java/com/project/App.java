package com.project;

import java.util.*;
/**
 * Hello world!
 *
 */
public class App 
{
	public static List<Integer> findCommonElements(int[] arr1, int[] arr2, int[] arr3) {
        Set<Integer> commonSet = new HashSet<>();
        List<Integer> commonList = new ArrayList<>();

        for (int num : arr1) {
            if (contains(arr2, num) && contains(arr3, num)) {
                if (!commonSet.contains(num)) {
                    commonSet.add(num);
                    commonList.add(num);
                }
            }
        }

        return commonList;
    }
	public static boolean contains(int[] arr, int num) {
        for (int value : arr) {
            if (value == num) {
                return true;
            }
        }
        return false;
    }
	private static List<Integer> findOddElements(List<Integer> arr) {
		List<Integer> ans=new ArrayList<>();
		for(int i:arr) {
			if(i%2!=0) {
				ans.add(i);
			}
		}
		return ans;
	}
	private static List<Integer> findEvenElements(List<Integer> arr) {
		List<Integer> ans=new ArrayList<>();
		for(int i:arr) {
			if(i%2==0) {
				ans.add(i);
			}
		}
		return ans;
	}
    public static void main( String[] args )
    {
    	int arr1[]={1,4,6,8,4,60,2,50} ;
    	int arr2[] ={56,25,2,5,60,20,50};
    	int arr3[]={45,67,4,2,5,6,2,50,60};
    	
    	List<Integer> ans=new ArrayList<>();
    	for(int i=0;i<arr1.length;i++) {
    		ans.add(arr1[i]);
    	}
    	for(int i=0;i<arr2.length;i++) {
    		ans.add(arr2[i]);
    	}
    	for(int i=0;i<arr3.length;i++) {
    		ans.add(arr3[i]);
    	}
    	Collections.sort(ans);
    	List<Integer> commonElements=findCommonElements(arr1,arr2,arr3);
    	List<Integer> oddElements =findOddElements(ans);
    	List<Integer> evenElements =findEvenElements(ans);
    	for(Integer i:commonElements) {
    		System.out.print(i+ " ");
    	}
    }
	
}
