package com.company;

public class LogicSha256 {
    //Compression function Logic for sha256
    public static int conditionFunction(int e, int f, int g) {
        return (e & f) ^ (~e & g);
    }
}
