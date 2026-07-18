package quizgame;

public class Player {

    private String name;
    private int score;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore() {
        score++;
    }

    public void displayResult(int totalQuestions) {
        System.out.println("\n=================================");
        System.out.println("          QUIZ RESULT");
        System.out.println("=================================");
        System.out.println("Player : " + name);
        System.out.println("Score  : " + score + " / " + totalQuestions);

        double percentage = (score * 100.0) / totalQuestions;
        System.out.printf("Percentage : %.2f%%\n", percentage);

        if (percentage >= 80) {
            System.out.println("Grade : A");
            System.out.println("Excellent!");
        } else if (percentage >= 60) {
            System.out.println("Grade : B");
            System.out.println("Good Job!");
        } else if (percentage >= 40) {
            System.out.println("Grade : C");
            System.out.println("Keep Practicing!");
        } else {
            System.out.println("Grade : F");
            System.out.println("Better Luck Next Time!");
        }

        System.out.println("=================================");
    }
}