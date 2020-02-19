package com.company;

import javax.swing.*;
import java.util.Scanner;

public class Main {

  private static   int [] array_storing_bit = new int [10000];
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter your text: ");
        String str = in.next();
        char [] chars = str.toCharArray();
        int [] x = new int[100000];
        int i=0,y=0;

         int[] bitform = new int[8];

        System.out.println("length of the input is "+str.length());
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
        padding(array_storing_bit,y);

        in.close();
    }
    public static void padding(int arr[],int array_Length){
        int i,last_initialization;
       int running_bit_position = 0,m;
       int number_of_block ;
       if( array_Length%1024==0){
          number_of_block = array_Length/1024;
       }else {
           number_of_block = array_Length/1024 + 1;
       }
        System.out.println("number of blocks are " + number_of_block);
       int[][] blockwise_bits = new int [number_of_block][1024];
       for( m=0;m<number_of_block-1;m++) {
           for ( i = 0; i < 1024 ; i++) {
                 blockwise_bits[m][i]=arr[running_bit_position];
                 running_bit_position++;
           }

       }
        System.out.println(running_bit_position);
        i=0;
         while(running_bit_position<array_Length){
             blockwise_bits[m][i] =arr[running_bit_position];
             running_bit_position++;
             i++;
         }
         blockwise_bits[m][i]=1;
         i++;
         //padding to make it 1024
      for(;i<1024;i++){
          blockwise_bits[m][i]=0;
      }for(int u=0;u<=m;u++){
      for(int q=0;q<1024;q++){
          System.out.print(blockwise_bits[u][q]+" ");

      }
            System.out.println("no "+ u+" has been completed with padding");
      }

    }


}
