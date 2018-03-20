package javacourses;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    static File leaderBoardFile = new File("leader-board.txt");

    public static void main(String[] args) {
        ArrayList<GameResult> leaderboard = new ArrayList<>();
        loadLeaderBoard(leaderboard);
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
        leaderboard.sort(Comparator
                .<GameResult>comparingInt(g -> g.attempts)
                .<GameResult>thenComparingLong(g -> g.time));
        printLeaderBoard(leaderboard);
        saveLeaderBoard(leaderboard);
        System.out.println("Ok! See You next time!");
    }

    private static void loadLeaderBoard(ArrayList<GameResult> leaderboard) {
        if (!leaderBoardFile.exists()) {
            return;
        }
        try (Scanner in = new Scanner(leaderBoardFile)) {
            while (in.hasNext()) {
                GameResult r = new GameResult();
                r.userName = in.next();
                r.attempts = in.nextInt();
                r.time = in.nextLong();
                leaderboard.add(r);
            }
        } catch (IOException e) {
            System.out.println("Something wrong when reading file");
        }
    }

    private static void saveLeaderBoard(ArrayList<GameResult> leaderboard) {
        try (PrintWriter out = new PrintWriter(leaderBoardFile)) {
            for (GameResult r : leaderboard) {
                out.printf("%-10s %d %d\n", r.userName, r.attempts, r.time);
            }
        } catch (IOException e) {
            System.out.println("Something wrong");
        }
    }

    private static void printLeaderBoard(ArrayList<GameResult> leaderboard) {
        int shownCount = 0;
        for (GameResult r : leaderboard) {
            System.out.printf("%-10s \t %d \t %.2f\n", r.userName, r.attempts, r.time / 1000.0);
        shownCount++;
        if(shownCount ==5){
            break;
        }
        }
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
                result.time = t2 - t1;
                result.attempts = i;
                return result;
            }
        }


        if (myNum != userNum) {
            System.out.println("You lost!");
        }
        return null;
    }

    static int askNumber() {
        for (; ; ) {
            try {
                int num = scanner.nextInt();
                if (num <= 100 && num >= 1) {
                    return num;
                }
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("oops!");
            }
            System.out.print("Oh, no! Wrong number. Try again: ");
        }

    }

    static String askAnswer() {
        for (; ; ) {
            String answer = scanner.next();
            if (answer.equalsIgnoreCase("Yes") || answer.equalsIgnoreCase("No")) {
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