import java.util.Scanner;
import java.util.Stack;

/**
 * Online Quiz System - console menu.
 * Uses Array + Queue + Stack + Selection Sort (DSA project).
 */
public class Main {
    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        QuizEngine engine = new QuizEngine();
        Leaderboard board = new Leaderboard();

        System.out.println("=====================================");
        System.out.println("        ONLINE QUIZ SYSTEM");
        System.out.println("=====================================");

        while (true) {
            System.out.println("\n1. Start Quiz");
            System.out.println("2. View Leaderboard");
            System.out.println("3. Exit");
            int choice = readNumber("Enter your choice (1-3): ", 1, 3);

            if (choice == 1) {
                playQuiz(engine, board);
            } else if (choice == 2) {
                board.display();
            } else {
                System.out.println("Thank you for playing. Goodbye!");
                break;
            }
        }
    }

    private static void playQuiz(QuizEngine engine, Leaderboard board) {
        System.out.print("Enter your name: ");
        String name = SC.hasNextLine() ? SC.nextLine().trim() : "";
        if (name.isEmpty()) {
            name = "Guest";
        }

        engine.startQuiz();
        int number = 1;
        while (engine.hasNextQuestion()) {
            Question q = engine.nextQuestion();
            System.out.println("\nQ" + number + ". " + q.getText());
            String[] opts = q.getOptions();
            for (int i = 0; i < opts.length; i++) {
                System.out.println("   " + (i + 1) + ") " + opts[i]);
            }
            int answer = readNumber("Your answer (1-4): ", 1, 4);
            if (engine.submitAnswer(q, answer)) {
                System.out.println("Correct!");
            } else {
                System.out.println("Wrong! Correct answer: " + q.getCorrectAnswer());
            }
            number++;
        }

        int total = engine.getTotalQuestions();
        System.out.println("\n" + name + ", your score: " + engine.getScore() + "/" + total);
        board.add(new ScoreEntry(name, engine.getScore(), total));
        showReview(engine.getWrongAnswers());
        board.display();
    }

    /** Pops the Stack so the LAST wrong question is shown first (LIFO). */
    private static void showReview(Stack<Question> wrong) {
        if (wrong.isEmpty()) {
            System.out.println("Perfect score! Nothing to review.");
            return;
        }
        System.out.println("\n--- Review of wrong answers (last wrong first) ---");
        while (!wrong.isEmpty()) {
            Question w = wrong.pop();
            System.out.println("Q: " + w.getText());
            System.out.println("   Correct answer: " + w.getCorrectAnswer());
        }
    }

    /** Keeps asking until the player enters a whole number between min and max. */
    private static int readNumber(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            if (!SC.hasNextLine()) {          // input closed: exit cleanly
                System.out.println("\nNo more input. Exiting.");
                System.exit(0);
            }
            String line = SC.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if (value >= min && value <= max) {
                    return value;
                }
            } catch (NumberFormatException e) {
                // fall through to the error message
            }
            System.out.println("Invalid input. Enter a number from " + min + " to " + max + ".");
        }
    }
}
