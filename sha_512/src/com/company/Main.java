package com.company;

import javax.swing.*;
import java.util.Scanner;

public class Main {
  //  public  static int k=0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter your text: ");
        String str = in.next();
        char [] chars = str.toCharArray();
        int [] x = new int[10];
        int i=0,y=0;

         int[] bitform = new int[8];
         int [] array_storing_bit = new int [10000];

         while(i<str.length()){
            x[i] = (int)chars[i] ;
            int num = x[i];
            int j=0;
            while(num>0){
                bitform[j]=num%2;
                num=num/2;
                j++;
            }
            while(j<=7){
                bitform[j]=0;
                j++;
            }
            j=7;
            while(j>=0){
               array_storing_bit[y]= bitform[j];
               y++;
               j--;
            }
            i++;
        }
        for(int l=0;l<y;l++) {
            System.out.print(array_storing_bit[l]+" ");
        }
        in.close();
    }
    void padding()
}
