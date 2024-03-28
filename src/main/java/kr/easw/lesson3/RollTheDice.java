package kr.easw.lesson3;

import java.util.Random;

public class RollTheDice {
    private static int[] frequency = new int[6];

    private static final Random RANDOM;

    static {
        RANDOM = new Random();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 600; i++) {
            if (RANDOM.nextDouble() < 0.1) {
                fillArray(RANDOM.nextDouble() * 720);
            } else {
                fillArray(RANDOM.nextDouble() * 360);
            }
        }

        for (int i = 0; i < frequency.length; i++) {
            System.out.println((i + 1) + "번: " + frequency[i]);
        }
    }
    
    private static void fillArray(double result) {
        int index = (int) (result / 60);
        if (index >= frequency.length) {
            frequency = extendArray(index + 1);
        }
        frequency[index]++;
    }


    private static int[] extendArray(int newSize) {
        int[] newFrequency = new int[newSize];
        for (int i = 0; i < frequency.length; i++) {
            newFrequency[i] = frequency[i];
        }
        return newFrequency;
    }

}