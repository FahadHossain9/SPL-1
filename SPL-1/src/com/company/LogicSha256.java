package com.company;

import java.math.BigInteger;

public class LogicSha256 {
    //Compression function Logic for sha256
    public static int conditionFunction(int e, int f, int g) {
        return (e & f) ^ (~e & g);
    }
    //Majority Function takes 3 buffers
    public static int majorityFunction(int a, int b, int c) {
        return (a & b) ^ (a & c) ^ (b & c);
    }

    //does circular right shift by n bits
    public static int ROTR(int x, int n) {
        return (x >>> n) | (x << (Integer.SIZE - 1));
    }

    //will be used in round function(0-512)
    public static int summation0To256(int x) {
        return ROTR(x, 2) ^ ROTR(x, 13) ^ ROTR(x, 22);
    }
    //will be used in round function(1-512)
    public static int summation1To256(int x) {
        return ROTR(x, 6) ^ ROTR(x, 11) ^ ROTR(x, 25);
    }

    //will be used in generating words function(0-512)
    public static int sigma0To256(int x) {
        return ROTR(x, 7) ^ ROTR(x, 18) ^ (x >>> 3);
    }

    //will be used in generating words function(1-512)
    public static int sigma1To256(int x) {
        return ROTR(x, 17) ^ ROTR(x, 19) ^ (x >>> 10);
    }

    public static byte[] padding(byte[] input) {

        //no matter what it is necessary to append at least 17 bytes
        //128 bit=16 byte for the message size
        //1byte=8bits needed to add 1
        //rests should be filled with zeros
        int size = input.length + 9;
        while (size % 64 != 0) {
            size += 1;
        }

        // The padded byte array will be stored here
        byte[] storageOfPaddedMessage = new byte[size];

        // Copy over the old stuff
        for (int i = 0; i < input.length; i++) {
            storageOfPaddedMessage[i] = input[i];
        }

        // Add the '1' bit as in hexa 1000 0000 is equivalent to  x8
        storageOfPaddedMessage[input.length] = (byte) 0x80;
        //Converting the original length of the input into byte
        byte[] lenghtInByte = BigInteger.valueOf(input.length * 8).toByteArray();

        //Add this to the end of our padded input message
        for (int i = lenghtInByte.length; i > 0; i--) {
            storageOfPaddedMessage[size - i] = lenghtInByte[lenghtInByte.length - i];
        }
        //Before padding the input message size
        System.out.printf("Total message length in bits before padding: %d\n", input.length * 8);
        //After padding the input message size
        System.out.printf("Total message length in bits after padding: %d\n", storageOfPaddedMessage.length * 8);

        return storageOfPaddedMessage;
    }

    public static int arrayToInt(byte[] input, int j) {
        int v = 0;
        for (int i = 0; i < 4; i++) {
            v = (v << 4) + (input[i + j] & 0xff);
        }
        return v;
    }

    public static int[][] convertToBlocks(byte[] input) {
        // a block has 512 bits =64 bytes = 16 int
        int[][] blocks = new int[input.length / 64][16];

        //in each block
        for (int i = 0; i < input.length / 64; i++) {
            //for each long in every block
            for (int j = 0; j < 16; j++) {
                //setting up block value
                blocks[i][j] = arrayToInt(input, i * 64 + j * 4);
            }
        }
        return blocks;
    }

    public static int[][] Message(int[][] M) {
        int[][] W = new int[M.length][64];

        // For each block in the input
        for (int i = 0; i < M.length; i++) {

            System.out.printf("W for block %d\n", i);

            // For each long in the block
            for (int j = 0; j < 16; j++) {
                // Set the initial values of W to be the value of the input directly
                W[i][j] = M[i][j];

                System.out.printf("W(%d): %016x\n", j, W[i][j]);

            }

            // For the rest of the values
            for (int j = 16; j < 64; j++) {
                // Do some math from the SHA512 algorithm
                W[i][j] = sigma1To256(W[i][j - 2]) + W[i][j - 7] + sigma0To256(W[i][j - 15]) + W[i][j - 16];

                System.out.printf("W(%d): %016x\n", j, W[i][j]);

            }

            System.out.println("=====================================");



        }
        return W;
    }
}


