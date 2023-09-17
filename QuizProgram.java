import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class QuizQuestion {
    private String question;
    private String[] options;
    private int correctOption;

    public QuizQuestion(String question, String[] options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }
}

public class QuizProgram {
    private static ArrayList<QuizQuestion> questions = new ArrayList<>();
    private static int score = 0;
    private static int currentQuestionIndex = 0;
    private static Timer timer = new Timer();
    private static Scanner scanner = new Scanner(System.in);

    static {
        questions.add(new QuizQuestion(
                "What is the capital of France?",
                new String[]{"A) London", "B) Madrid", "C) Paris", "D) Rome"},
                2
        ));
        // Add more questions here
    }

    public static void main(String[] args) {
        startQuiz();
    }

    public static void startQuiz() {
        if (currentQuestionIndex < questions.size()) {
            QuizQuestion currentQuestion = questions.get(currentQuestionIndex);

            System.out.println(currentQuestion.getQuestion());

            for (String option : currentQuestion.getOptions()) {
                System.out.println(option);
            }

            setTimer(10); // Set timer for 10 seconds

            String userAnswer = scanner.nextLine().toUpperCase();

            timer.cancel();

            if (userAnswer.equals(currentQuestion.getOptions()[currentQuestion.getCorrectOption() - 1].toUpperCase())) {
                score++;
            }

            currentQuestionIndex++;
            startQuiz();
        } else {
            showResult();
        }
    }

    public static void setTimer(int seconds) {
        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("Time's up!");
                scanner.nextLine(); // Consume the newline character
                currentQuestionIndex++;
                startQuiz();
            }
        }, seconds * 1000);
    }

    public static void showResult() {
        System.out.println("Quiz finished!");
        System.out.println("Your score: " + score + "/" + questions.size());

        // Optional: Display correct/incorrect answers summary
        // For that, you need to keep track of each user's answer

        scanner.close();
    }
}
