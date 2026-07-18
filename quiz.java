package quizgame;

import java.util.ArrayList;
import java.util.Scanner;

public class Quiz {

    private ArrayList<Question> questions = new ArrayList<>();
    private Scanner input = new Scanner(System.in);

    public Quiz() {

        questions.add(new Question(
                "1. Which language is used to develop Android apps?",
                new String[]{"1. Java", "2. HTML", "3. SQL", "4. CSS"},
                1));

        questions.add(new Question(
                "2. Which keyword is used to create an object in Java?",
                new String[]{"1. class", "2. new", "3. object", "4. this"},
                2));

        questions.add(new Question(
                "3. Which company developed Java?",
                new String[]{"1. Microsoft", "2. Sun Microsystems", "3. Google", "4. Apple"},
                2));

        questions.add(new Question(
                "4. Which loop executes at least once?",
                new String[]{"1. for", "2. while", "3. do-while", "4. foreach"},
                3));

        questions.add(new Question(
                "5. Which symbol is used to end a Java statement?",
                new String[]{"1. :", "2. ;", "3. ,", "4. ."},
                2));
    }

    public void startQuiz(Player player) {

        for (Question q : questions) {

            System.out.println("\n" + q.getQuestion());

            for (String option : q.getOptions()) {
                System.out.println(option);
            }

            System.out.print("Enter your answer (1-4): ");
            int answer = input.nextInt();

            if (q.isCorrect(answer)) {
                System.out.println("Correct!");
                player.increaseScore();
            } else {
                System.out.println("Wrong!");
            }
        }

        player.displayResult(questions.size());
    }
}