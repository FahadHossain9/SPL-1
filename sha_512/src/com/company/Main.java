package com.company;

import java.util.Scanner;

public class Main {
    private static int[][] blockwiseBits;
    private static   int [] arrayStoringBit = new int [10000];
    public static int [][] wordsFromBlocks;
    public static void main(String[] args) {


        //taking input text as string
        Scanner in = new Scanner(System.in);
        System.out.print("Enter your text: ");
        String str = in.next();


        //making the string to character array
        char [] chars = str.toCharArray();
        int [] arrayStoringAsciiValues = new int[100000];
        int iterator=0,iterator2=0;
        int[] bitform = new int[8];


         while(iterator<str.length()){
            arrayStoringAsciiValues[iterator] = (int)chars[iterator] ;
            int asciiValueOfACharacter = arrayStoringAsciiValues[iterator];
            //storing the reverse binary form
            int iteratorFromReverse=0;
            while(asciiValueOfACharacter>0){
                bitform[iteratorFromReverse]=asciiValueOfACharacter%2;
                asciiValueOfACharacter=asciiValueOfACharacter/2;
                iteratorFromReverse++;
            }

            //adding 0 to  make each charcter binary form to 8 bit
            while(iteratorFromReverse<=7){
                bitform[iteratorFromReverse]=0;
                iteratorFromReverse++;
            }

            //reverse the reverse form to store actual binary form
            iteratorFromReverse=7;
            while(iteratorFromReverse>=0){
               arrayStoringBit[iterator2]= bitform[iteratorFromReverse];
               iterator2++;
               iteratorFromReverse--;
            }
            iterator++;
        }

         //passing the bits to pad function to make th array multiple of 1024 bits
        blockwiseBits = pad(arrayStoringBit,iterator2);
        wordGenerator(blockwiseBits);

        in.close();
    }




    public static int[][] pad(int actualBinaryFormStored[], int arrayLength){
        int iteratorForBlocksWithin0To1024,last_initialization;
       int runningBitPosition = 0,currentBlock;
       int number_of_block ;
       if( arrayLength%1024==0){
          number_of_block = arrayLength/1024;
       }else {
           number_of_block = arrayLength/1024 + 1;
       }
        System.out.println("number of blocks are " + number_of_block);
         blockwiseBits = new int [number_of_block][1024];
       //initialize the block from plain text which is already 1024 long & do not need padding
       for( currentBlock=0;currentBlock<number_of_block-1;currentBlock++) {
           for ( iteratorForBlocksWithin0To1024 = 0; iteratorForBlocksWithin0To1024 < 1024 ; iteratorForBlocksWithin0To1024++) {
                 blockwiseBits[currentBlock][iteratorForBlocksWithin0To1024]=actualBinaryFormStored[runningBitPosition];
                 runningBitPosition++;
           }

       }
       //last block which is short of 1024 bit & need padding
        iteratorForBlocksWithin0To1024=0;
         while(runningBitPosition<arrayLength){
             blockwiseBits[currentBlock][iteratorForBlocksWithin0To1024] =actualBinaryFormStored[runningBitPosition];
             runningBitPosition++;
             iteratorForBlocksWithin0To1024++;
         }
         blockwiseBits[currentBlock][iteratorForBlocksWithin0To1024]=1;
         iteratorForBlocksWithin0To1024++;
         //padding to make it 1024
      for(;iteratorForBlocksWithin0To1024<1024;iteratorForBlocksWithin0To1024++){
          blockwiseBits[currentBlock][iteratorForBlocksWithin0To1024]=0;
      }
        for(int u=0;u<=currentBlock;u++){
            for(int q=0;q<1024;q++){
                System.out.print(blockwiseBits[u][q]+" ");

            }
            System.out.println("no "+ u +" has been completed with padding");
        }

      return blockwiseBits;

    }
 public static void  wordGenerator(int [][]blockwiseBits){

 }



}
