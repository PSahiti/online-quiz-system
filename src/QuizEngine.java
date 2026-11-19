import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Manages the question bank (Array), the question Queue (FIFO)
 * and the Stack of wrongly answered questions (LIFO).
 */
public class QuizEngine {
    private Question[] bank;                       // Array: question bank
    private final Queue<Question> questionQueue = new LinkedList<>();  // Queue: FIFO
    private final Stack<Question> wrongAnswers = new Stack<>();        // Stack: LIFO
    private int score;
    private int totalQuestions;

    public QuizEngine() {
        loadQuestions();
    }

    /** Fills the array with the 10 predefined questions. */
    private void loadQuestions() {
        bank = new Question[] {
            new Question("Which data structure works on the FIFO principle?",
                new String[]{"Stack", "Queue", "Array", "Tree"}, 1),
            new Question("Which data structure works on the LIFO principle?",
                new String[]{"Queue", "Array", "Stack", "Tree"}, 2),
            new Question("What is the index of the first element of a Java array?",
                new String[]{"0", "1", "-1", "2"}, 0),
            new Question("Which operation adds an element to a Stack?",
                new String[]{"pop()", "push()", "peek()", "poll()"}, 1),
            new Question("What is the time complexity of accessing arr[i]?",
                new String[]{"O(n)", "O(log n)", "O(1)", "O(n^2)"}, 2),
            new Question("Which Queue method removes and returns the front element?",
                new String[]{"peek()", "add()", "offer()", "poll()"}, 3),
            new Question("What is the time complexity of enqueue in a Queue?",
                new String[]{"O(1)", "O(n)", "O(log n)", "O(n^2)"}, 0),
            new Question("Which sorting algorithm repeatedly selects the smallest element?",
                new String[]{"Merge Sort", "Quick Sort", "Selection Sort", "Heap Sort"}, 2),
            new Question("Which Java class implements a Stack in java.util?",
                new String[]{"HashMap", "Scanner", "Stack", "Random"}, 2),
            new Question("Which keyword is used to create an object in Java?",
                new String[]{"class", "this", "new", "static"}, 2)
        };
        totalQuestions = bank.length;
    }

    /** Clears the Queue and Stack, resets the score and enqueues every question. O(N) */
    public void startQuiz() {
        questionQueue.clear();
        wrongAnswers.clear();
        score = 0;
        for (Question q : bank) {
            questionQueue.add(q);          // enqueue
        }
    }

    public boolean hasNextQuestion() { return !questionQueue.isEmpty(); }

    /** Removes and returns the front question of the Queue (dequeue). */
    public Question nextQuestion() { return questionQueue.poll(); }

    /** Checks the answer; correct -> score+1, wrong -> push on Stack. */
    public boolean submitAnswer(Question q, int choice) {
        if (q.isCorrect(choice)) {
            score++;
            return true;
        }
        wrongAnswers.push(q);              // remember wrong answer
        return false;
    }

    public Stack<Question> getWrongAnswers() { return wrongAnswers; }
    public int getScore() { return score; }
    public int getTotalQuestions() { return totalQuestions; }
}
