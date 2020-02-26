package com.company;

import java.util.Scanner;

public class Main {

  private static   int [] arrayStoringBit = new int [10000];
    public static void main(String[] args) {
        //taking input text as string
        Scanner in = new Scanner(System.in);
        System.out.print("Enter your text: ");
        String str = in.next();
        //making the string to character array
        char [] chars = str.toCharArray();
        int [] arrayStoringAsciiValues = new int[100000];
        int iterator=0,y=0;
        int[] bitform = new int[8];

         while(iterator<str.length()){
            arrayStoringAsciiValues[iterator] = (int)chars[iterator] ;
            int num = arrayStoringAsciiValues[iterator];
            //storing the reverse binary form
            int j=0;
            while(num>0){
                bitform[j]=num%2;
                num=num/2;
                j++;
            }
            //adding 0 to  make each charcter binary form to 8 bit
            while(j<=7){
                bitform[j]=0;
                j++;
            }
            //reverse the reverse form to store actual binary form
            j=7;
            while(j>=0){
               arrayStoringBit[y]= bitform[j];
               y++;
               j--;
            }
            iterator++;
        }
        pad(arrayStoringBit,y);

        in.close();
    }
    public static void pad(int arr[], int arrayLength){
        int i,last_initialization;
       int running_bit_position = 0,m;
       int number_of_block ;
       if( arrayLength%1024==0){
          number_of_block = arrayLength/1024;
       }else {
           number_of_block = arrayLength/1024 + 1;
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
         while(running_bit_position<arrayLength){
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
            System.out.println("no "+ u +" has been completed with padding");
      }

    }


}
