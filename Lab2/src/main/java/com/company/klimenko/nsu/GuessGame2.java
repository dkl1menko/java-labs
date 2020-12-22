package com.company.klimenko.nsu;


import java.time.Instant;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Math.abs;

public class GuessGame2 {

    public static void main(String[] args) {
        Random random = new Random(Instant.now().hashCode());
        int i = abs(random.nextInt()) % 100 + 1;
        System.out.println("I guess number");
        Scanner keyboard = new Scanner(System.in);
        int guess;
        do {
            guess = keyboard.nextInt();
            if (guess == i)
                System.out.println("Your guess is correct. Congratulations!");
            else if (guess < i)
                System.out
                        .println("Your guess is smaller than the secret number.");
            else System.out.println("Your guess is greater than the secret number.");

        } while (guess != i);
    }
}
