import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApplication {
    private static final int QUESTION_DURATION = 10; // Duration for each question in seconds
    private static int score = 0;
    private static int currentQuestionIndex = 0;
    private static Question[] questions;

    public static void main(String[] args) {
        // Define quiz questions
        questions = new Question[]{
                new Question("What is the capital of France?",
                        new String[]{"A. London", "B. Paris", "C. Rome", "D. Berlin"},
                        "B"),
                // Add more questions here...
        };

        // Start the quiz
        takeQuiz();
    }

    private static void takeQuiz() {
        Timer timer = new Timer();
        Scanner scanner = new Scanner(System.in);

        // Display questions one by one
        for (Question question : questions) {
            System.out.println(question.getQuestion());
            for (String option : question.getOptions()) {
                System.out.println(option);
            }
            System.out.print("Enter your answer (A/B/C/D): ");

            // Start the timer for each question
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("\nTime's up!");
                    displayNextQuestion();
                }
            }, QUESTION_DURATION * 1000);

            String userAnswer = scanner.nextLine().toUpperCase();

            // Cancel the timer when user submits the answer
            timer.cancel();

            // Check the answer
            if (userAnswer.equals(question.getCorrectAnswer())) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Incorrect! The correct answer is " + question.getCorrectAnswer() + ".\n");
            }
            currentQuestionIndex++;
        }

        // Display result
        System.out.println("Quiz ended!\nYour score: " + score + "/" + questions.length);
    }

    private static void displayNextQuestion() {
        if (currentQuestionIndex < questions.length - 1) {
            System.out.println("\nNext question:");
        }
    }
}

class Question {
    private String question;
    private String[] options;
    private String correctAnswer;

    public Question(String question, String[] options, String correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
