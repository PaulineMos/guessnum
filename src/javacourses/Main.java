package javacourses;

import java.util.NoSuchElementException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            Random random = new Random();

            String answer;

            do {
                System.out.println("I think of number from 1 to 100. Try to guess it.");

                int myNum = random.nextInt(100) + 1;
                int userNum = 0;

                System.out.println("Spoiler" + myNum);
                for (int i = 0; i < 10; i++) {
                    System.out.println("My try is " + (i + 1));
                    userNum = askNumber();

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
                answer = askAnswer();

            } while (answer.equalsIgnoreCase("Yes"));
        } catch (NoSuchElementException e) {
            System.out.println("Oh! It's pity! You decided to cancel the game!");
            }
        System.out.println("Ok! See You next time!");

    }
   static  int askNumber(){
        for (;;) {
            try {
                int num = scanner.nextInt();
                if (num <= 100 && num >= 1) {
                    return num;
                }
            } catch (InputMismatchException e){
                scanner.next();
                System.out.println("oops!");
            }
            System.out.print("Oh, no! Wrong number. Try again: ");
        }

   }
   static String askAnswer(){
        for (;;) {
            String answer = scanner.next();
            if (answer.equalsIgnoreCase("Yes") || answer.equalsIgnoreCase("No")){
                return answer;
            }
            System.out.println("Sorry, write Yes or No!");
        }
   }
// static  int askNumber(){
//        int num;
//        do {
//             num = scanner.nextInt();
//            if (num > 100 || num < 1) {
//                System.out.print("Oh, no! Wrong number. Try again:");
//            }
//        } while (num > 100 || num < 1);
//        return num;
//   }
}