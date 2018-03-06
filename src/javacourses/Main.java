package javacourses;


import java.util.*;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {

       // System.out.println("Current time is " + t);
        ArrayList<GameResult> leaderboard = new ArrayList<>();
        try {
            String answer;
            do {
                System.out.println("What is your name?");
                String name = scanner.next();
                GameResult r = doGame(name);
                if (r != null) {
                   leaderboard.add(r);
                }
                System.out.println("Do you want to try again?");
                answer = askAnswer();

            } while (answer.equalsIgnoreCase("Yes"));
        } catch (NoSuchElementException e) {
            System.out.println("Oh! It's pity! You decided to cancel the game!");
            }
            for (GameResult r : leaderboard){
                System.out.println(r.userName + "\t" + r.attempts + "\t" + r.time);
            }
        System.out.println("Ok! See You next time!");
    }

    private static GameResult doGame(String userName) {
        System.out.println("Hello, " + userName);
        System.out.println("I think of number from 1 to 100. Try to guess it.");

        long t1 = System.currentTimeMillis();

        int myNum = random.nextInt(100) + 1;
        int userNum = 0;

        System.out.println("Spoiler" + myNum);

        GameResult result = new GameResult();
        result.userName = userName;

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
                long t2 = System.currentTimeMillis();
                long time = t2 - t1;
                result.time = time;
                result.attempts = i;
                return result;
            }
        }


        if (myNum != userNum) {
            System.out.println("You lost!");
        }
        return null;
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