package quizgame;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("====================================");
        System.out.println("         JAVA QUIZ GAME");
        System.out.println("====================================");

        System.out.print("Enter your name: ");
        String name = input.nextLine();

        Player player = new Player(name);
        Quiz quiz = new Quiz();

        System.out.println("\nWelcome, " + player.getName() + "!");
        System.out.println("The quiz is starting...");
        System.out.println("------------------------------------");

        quiz.startQuiz(player);

        System.out.println("\nThank you for playing!");
        input.close();
    }
}