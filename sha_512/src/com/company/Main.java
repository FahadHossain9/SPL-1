package com.company;

import javax.swing.*;
import java.util.Scanner;

public class Main {
//public void padding(int []);
  private static   int [] array_storing_bit = new int [10000];
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter your text: ");
        String str = in.next();
        char [] chars = str.toCharArray();
        int [] x = new int[10];
        int i=0,y=0;

         int[] bitform = new int[8];


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
        padding(array_storing_bit);
        for(int l=0;l<y;l++) {
            System.out.print(array_storing_bit[l]+" ");
        }
        in.close();
    }
    public static void padding(int arr[]){
        int i,last_initialization;
       int array_Length =  arr.length;
       int running_bit_position = 0,m;
       int number_of_block ;
       if( array_Length%1024==0){
          number_of_block = array_Length/1024;
       }else {
           number_of_block = array_Length/1024 + 1;
       }
       int[][] blockwise_bits = new int [number_of_block][1024];
       for( m=0;m<number_of_block;m++) {
           for ( i = 0; i < 1024 && running_bit_position<array_Length; i++) {
                 blockwise_bits[m][i]=arr[running_bit_position];
                 running_bit_position++;
           }
       }

       last_initialization = array_Length-m*1024-i;
       
       for(;last_initialization<1024;last_initialization++){
           blockwise_bits[m][last_initialization]=0;
       }
      for(int q=0;q<1024;q++){
          System.out.print(blockwise_bits[m][q]+" ");
      }
    }


}
