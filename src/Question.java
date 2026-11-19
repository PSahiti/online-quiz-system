/**
 * Represents one multiple-choice question: text, 4 options and the correct option index.
 */
public class Question {
    private final String text;
    private final String[] options;   // always 4 options
    private final int correctIndex;   // 0-based index of the correct option

    public Question(String text, String[] options, int correctIndex) {
        this.text = text;
        this.options = options;
        this.correctIndex = correctIndex;
    }

    public String getText() { return text; }

    public String[] getOptions() { return options; }

    public String getCorrectAnswer() { return options[correctIndex]; }

    /** @param choice the player's choice, 1 to 4 */
    public boolean isCorrect(int choice) { return choice - 1 == correctIndex; }
}
