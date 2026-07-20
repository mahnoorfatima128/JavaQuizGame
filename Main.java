import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {
        new LoginScreen();
    }
}

// Player class
class Player {
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

    public void updateScore(int points) {
        score += points;
    }
}

// Admin class
class Admin {
    private String username;
    private String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean login(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) && this.password.equals(inputPassword);
    }
}

// Question class
class Question {
    private String questionText;
    private String option1, option2, option3, option4;
    private int correctAnswer;

    public Question(String questionText, String option1, String option2, String option3, String option4, int correctAnswer) {
        this.questionText = questionText;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public boolean validateAnswer(int answer) {
        return answer == correctAnswer;
    }
}

// QuestionDatabase class
class QuestionDatabase {
    private Question question1, question2;

    public QuestionDatabase() {
        loadQuestions();
    }

    private void loadQuestions() {
        question1 = new Question("What is 2+2?", "3", "4", "5", "6", 2);
        question2 = new Question("What is the capital of France?", "Berlin", "Paris", "Rome", "Madrid", 2);
    }

    public Question[] getAllQuestions() {
        return new Question[]{question1, question2};
    }
}

// Login GUI
class LoginScreen extends JFrame {
    public LoginScreen() {
        setTitle("Quiz Game - Login");
        setSize(350, 180);
        setLayout(new GridLayout(3, 2, 10, 10));
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField();
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField();
        JButton loginBtn = new JButton("Login");

        loginBtn.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());

            Admin admin = new Admin("Mahnoor Fatima", "1234");
            if (admin.login(username, password)) {
                JOptionPane.showMessageDialog(this, "Admin login successful!");
                JOptionPane.showMessageDialog(this, "Welcome " + username + "!");
                JOptionPane.showMessageDialog(this, "You are now logged in as an admin.");
                JOptionPane.showMessageDialog(this, "You can now start the quiz.");
                dispose();
                new QuizWindow(userField.getText());
            } else {
                JOptionPane.showMessageDialog(this, "Login failed!");
            }
        });

        add(userLabel); add(userField);
        add(passLabel); add(passField);
        add(new JLabel()); add(loginBtn);
        setVisible(true);
    }
}

// Quiz GUI
class QuizWindow extends JFrame {
    private int score = 0;
    private int currentQuestion = 0;
    private Question[] questions;
    private Player player;

    private JLabel questionLabel;
    private JRadioButton option1, option2, option3, option4;
    private ButtonGroup group;
    private JButton nextBtn;

    public QuizWindow(String playerName) {
        player = new Player(playerName);
        questions = new QuestionDatabase().getAllQuestions();

        setTitle("Quiz Game");
        setSize(500, 300);
        setLayout(new GridLayout(6, 1, 5, 5));
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        questionLabel = new JLabel();
        option1 = new JRadioButton();
        option2 = new JRadioButton();
        option3 = new JRadioButton();
        option4 = new JRadioButton();
        group = new ButtonGroup();
        group.add(option1); group.add(option2); group.add(option3); group.add(option4);

        nextBtn = new JButton("Next");
        nextBtn.addActionListener(e -> checkAnswerAndNext());

        add(questionLabel);
        add(option1);
        add(option2);
        add(option3);
        add(option4);
        add(nextBtn);

        loadQuestion();
        setVisible(true);
    }

    private void loadQuestion() {
        if (currentQuestion < questions.length) {
            Question q = questions[currentQuestion];
            questionLabel.setText(q.getQuestionText());
            option1.setText(q.getOption1());
            option2.setText(q.getOption2());
            option3.setText(q.getOption3());
            option4.setText(q.getOption4());
            group.clearSelection();
        } else {
            JOptionPane.showMessageDialog(this, "Quiz Over! " + player.getName() + ", your score is: " + score + "/" + (questions.length * 10));
            JOptionPane.showMessageDialog(this, "Thank you for playing!");
            System.exit(0);
            dispose();
        }
    }

    private void checkAnswerAndNext() {
        int selected = -1;
        if (option1.isSelected()) selected = 1;
        else if (option2.isSelected()) selected = 2;
        else if (option3.isSelected()) selected = 3;
        else if (option4.isSelected()) selected = 4;

        if (selected != -1 && questions[currentQuestion].validateAnswer(selected)) {
            player.updateScore(10);
            score = player.getScore();
        }

        currentQuestion++;
        loadQuestion();
    }
}
