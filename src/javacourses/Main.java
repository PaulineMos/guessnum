package javacourses;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        String answer;

        do {
            System.out.println("I think of number from 1 to 100. Try to guess it.");

            int myNum = random.nextInt(100) + 1;
            int userNum = 0;

            System.out.println(myNum);
            for (int i = 0; i < 10; i++) {
                System.out.println("My try is " + (i + 1));
                userNum = scanner.nextInt();

                System.out.println(userNum);

                if (myNum > userNum) {
                    System.out.println("My number is more ");
                } else if (myNum < userNum) {
                    System.out.println("My number is less");
                } else {
                    System.out.println("Bingo!");
                    break;
                }
            }
            if (myNum == userNum) {

                System.out.println("Do you want to try again?");
            } else {
                System.out.println("You lost!");
                System.out.println("Do you want to try again?");
            }
            answer = scanner.next();

        } while (answer.equals("Yes"));
        System.out.println("Ok! See You next time!");
    }
}