package com.company;

import java.util.Scanner;

public class Main {
    private static int[][] blockwiseBits;
    private static   int [] arrayStoringBit = new int [10000];
    public static int [][] wordsFromBlocks;
    public static int[] blocks = new int[1024];
    public static    int number_of_block ;
    public static int[][] words = new int[80][64];
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
         pad(arrayStoringBit,iterator2);
        for(int l=0;l<number_of_block;l++) {
            wordGenerator(blockwiseBits[l]);
        }


    }




    public static void pad(int actualBinaryFormStored[], int arrayLength){
        int iteratorForBlocksWithin0To1024,last_initialization;
        int[] sizeIsStoredIn128Bits=new int[128];
        int Length = arrayLength;
        int count=127;
        int runningBitPosition = 0,currentBlock;

        //finding the number of blockS keeping mind of 18 bit ie length
        if( arrayLength+128%1024==0){
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
        for(;iteratorForBlocksWithin0To1024<1024-128;iteratorForBlocksWithin0To1024++){
            blockwiseBits[currentBlock][iteratorForBlocksWithin0To1024]=0;
        }
        while(Length>0){
            sizeIsStoredIn128Bits[count]=Length%2;
            Length=Length/2;
            count--;
        }
        while(count>=0){
            sizeIsStoredIn128Bits[count]=0;
            count--;
        }
        count=0;
        while (count<128){
            blockwiseBits[currentBlock][iteratorForBlocksWithin0To1024]=sizeIsStoredIn128Bits[count];
            iteratorForBlocksWithin0To1024++;
            count++;
        }
        for(int u=0;u<=currentBlock;u++){
            for(int q=0;q<1024;q++){
                System.out.print(blockwiseBits[u][q]+" ");

            }
            System.out.println("no "+ u +" has been completed with padding");
        }

        return ;

    }
    public static  void wordGenerator(int[] blocks){
        int  iteratorForBlocks=0;
        int [] afterRightShiftby1Bit = new int[64];
        int [] afterRightShiftby8Bit = new int[64];
        int [] afterRightShiftby19Bit = new  int[64];
        int [] afterRightShiftby61Bit = new int[64];
        int [] afterLefttShiftby7Bit= new int[64];
        int [] afterLefttShiftby6Bit= new int[64];
      //first 16 word generated from the plain text
        for(int l=0;l<16;l++){
            for(int h=0;h<64;h++) {
                words[l][h]=blocks[iteratorForBlocks];
                iteratorForBlocks++;
            }
        }



    }
    //intermdeiate rotatori function for sigma0to512 and sigma1to512
    public static int [] LefttShiftRotatorBy7Bit(int []word){
        int [] newWord = new int[64];
        for(int q=7;q<64;q++){
            newWord[q-7] = word[q];
        }
        for(int q=57;q<64;q++){
            newWord[q] = 0;
        }
        return newWord;
    }
    //intermdeiate rotatori function for sigma0to512 and sigma1to512
    public static int [] LefttShiftRotatorBy6Bit(int []word){
        int [] newWord = new int[64];
        for(int q=6;q<64;q++){
            newWord[q-6] = word[q];
        }
        for(int q=58;q<64;q++){
            newWord[q] = 0;
        }
        return newWord;
    }
    //intermdeiate rotatori function for sigma0to512 and sigma1to512
    public static int [] circularRightShiftRotatorBy1Bit(int []word){
        int [] newWord = new int[64];
        for(int q=0;q<64;q++){
            newWord[q]=word[q+1%64];
        }
        return newWord;
    }
    //intermdeiate rotatori function for sigma0to512 and sigma1to512
    public static int [] circularRightShiftRotatorBy8Bit(int []word){
        int [] newWord = new int[64];
        for(int q=0;q<64;q++){
            newWord[q]=word[q+8%64];
        }
        return newWord;
    }
    //intermdeiate rotatori function for sigma0to512 and sigma1to512
    public static int [] circularRightShiftRotatorBy19Bit(int []word){
        int [] newWord = new int[64];
        for(int q=0;q<64;q++){
            newWord[q]=word[q+19%64];
        }
        return newWord;
    }
    //intermdeiate rotatori function for sigma0to512 and sigma1to512
    public static int [] circularRightShiftRotatorBy61Bit(int []word){
        int [] newWord = new int[64];
        for(int q=0;q<64;q++){
            newWord[q]=word[q+61%64];
        }
        return newWord;
    }
    public static  int[] exclusiveOR(int []firstPart,int []secondPart){
        int [] newWord = new int[64];
        for(int u=0;u<64;u++){
            if(firstPart[u]==1 && secondPart[u]==0){
                newWord[u]=1;
            }else if(firstPart[u]==0 && secondPart[u]==1){
                newWord[u]=1;
            }
            else if(firstPart[u]==1 && secondPart[u]==1){
                newWord[u]=0;
            }
            else if(firstPart[u]==0 && secondPart[u]==0){
                newWord[u]=0;
            }
        }
        return newWord;
    }





}
