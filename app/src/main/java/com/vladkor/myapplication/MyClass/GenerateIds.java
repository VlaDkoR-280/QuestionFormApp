package com.vladkor.myapplication.MyClass;

import java.util.ArrayList;
import java.util.Random;

public class GenerateIds {

    static public int[] GenerateRandomPosIds(int maxId){
        int[] a = new int[maxId + 1];
        for(int i = 0; i < a.length; i++){
            a[i] = -1;
        }
        Random rand = new Random();
        for(int i = 0; i < a.length; i++){
            int l = rand.nextInt(maxId + 1);
            if(CheckForExistence(a, l)){
                a[i] = l;
            }else{
                i--;
            }
        }

        return a;
    }

    private static boolean CheckForExistence(int[] a, int k){
        if(a.length < 1) return true;

        boolean answer = true;
        for(int i = 0; i < a.length; i++){
            if(a[i] == k) answer = false;
        }

        return answer;
    }

}
