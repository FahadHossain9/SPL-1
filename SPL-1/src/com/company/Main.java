package com.company;
import javax.swing.*;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        //If the input is not in correct format
        // if(args.length<1)
        Scanner sc = new Scanner(System.in);
        String  input = sc.nextLine();
        System.out.println("Which level of encryption do you want?\n [Press 1] Sha512\n [Press 2] Sha256\n");
        int choice;
        choice=sc.nextInt();
        if(choice==1) {
            String hashMessage = sha512(input.getBytes());
        }
        else if(choice==2) {
            String hashMessage2 = sha256(input.getBytes());
        }
    }
    //function that will do sha 512 hashing
    public static String sha512(byte[] input){
        //Padding
        input = LogicSha512.padding(input);

        //Generating Blocks
        long [][] blocks = LogicSha512.convertToBlocks(input);

        //Getting expanded message
        long[][] W = LogicSha512.Message(blocks);

        //initializing bufer that will ultimately
        // store the hash message
        long[] buffer = Constants.initialBuffer.clone();

        //For every blocks
        for(int i=0;i<blocks.length;i++){
            // a-h is set to the buffer initially
            long a = buffer[0];
            long b = buffer[1];
            long c = buffer[2];
            long d = buffer[3];
            long e = buffer[4];
            long f = buffer[5];
            long g = buffer[6];
            long h = buffer[7];

            //perform round operation for 80 times in each block
            //update the a to h buffer in each round
            for (int j = 0; j < 80; j++) {
                long t1 = h + LogicSha512.summation1To512(e) + LogicSha512.conditionFunction(e, f, g) + Constants.K[j] + W[i][j];
                long t2 = LogicSha512.summation0To512(a) + LogicSha512.majorityFunction(a, b, c);
                h = g;
                g = f;
                f = e;
                e = d + t1;
                d = c;
                c = b;
                b = a;
                a = t1 + t2;

                // Print out a-h

                System.out.printf("R%d abcdefgh: %016x %016x %016x %016x %016x %016x %016x %016x\n", j, a, b, c, d, e, f, g, h);
            }
            // After finishing the compression, save the state to the buffer
            buffer[0] = a + buffer[0];
            buffer[1] = b + buffer[1];
            buffer[2] = c + buffer[2];
            buffer[3] = d + buffer[3];
            buffer[4] = e + buffer[4];
            buffer[5] = f + buffer[5];
            buffer[6] = g + buffer[6];
            buffer[7] = h + buffer[7];

            System.out.printf("Hash for block %d: %016x %016x %016x %016x %016x %016x %016x %016x\n", i, buffer[0], buffer[1], buffer[2], buffer[3], buffer[4], buffer[5], buffer[6], buffer[7]);
        }

        // After everything is done, return the final hash as a string
        String result = "";
        for (int i = 0; i < 8; i++) {
            result += String.format("%016x", buffer[i]);
        }

        return result;
    }

    //fUNCTION that will do the actual Sha256
    public static String sha256(byte[] input){
        //Padding
        input = LogicSha256.padding(input);

        //Generating Blocks
        int [][] blocks = LogicSha256.convertToBlocks(input);

        //Getting expanded message
        int[][] W = LogicSha256.Message(blocks);

        //initializing bufer that will ultimately
        // store the hash message
        int[] buffer = Constants.initialBuffer256.clone();

        //For every blocks
        for(int i=0;i<blocks.length;i++){
            // a-h is set to the buffer initially
            int a = buffer[0];
            int b = buffer[1];
            int c = buffer[2];
            int d = buffer[3];
            int e = buffer[4];
            int f = buffer[5];
            int g = buffer[6];
            int h = buffer[7];

            //perform round operation for 64 times in each block
            //update the a to h buffer in each round
            for (int j = 0; j < 64; j++) {
                int t1 = h + LogicSha256.summation1To256(e) + LogicSha256.conditionFunction(e, f, g) + Constants.K256[j] + W[i][j];
                int t2 = LogicSha256.summation0To256(a) + LogicSha256.majorityFunction(a, b, c);
                h = g;
                g = f;
                f = e;
                e = d + t1;
                d = c;
                c = b;
                b = a;
                a = t1 + t2;

                // Print out a-h

                System.out.printf("R%d abcdefgh: %08x %08x %08x %08x %08x %08x %08x %08x\n", j, a, b, c, d, e, f, g, h);
            }
            // After finishing the compression, save the state to the buffer
            buffer[0] = a + buffer[0];
            buffer[1] = b + buffer[1];
            buffer[2] = c + buffer[2];
            buffer[3] = d + buffer[3];
            buffer[4] = e + buffer[4];
            buffer[5] = f + buffer[5];
            buffer[6] = g + buffer[6];
            buffer[7] = h + buffer[7];

            System.out.printf("Hash for block %d: %08x %08x %08x %08x %08x %08x %08x %08x\n", i, buffer[0], buffer[1], buffer[2], buffer[3], buffer[4], buffer[5], buffer[6], buffer[7]);
        }
        // After everything is done, return the final hash as a string
        String result = "";
        for (int i = 0; i < 8; i++) {
            result += String.format("%08x", buffer[i]);
        }

        return result;
    }
}

