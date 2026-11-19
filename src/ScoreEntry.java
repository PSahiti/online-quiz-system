/**
 * Stores the result of one player: name, score and total number of questions.
 */
public class ScoreEntry {
    private final String name;
    private final int score;
    private final int total;

    public ScoreEntry(String name, int score, int total) {
        this.name = name;
        this.score = score;
        this.total = total;
    }

    public String getName() { return name; }
    public int getScore() { return score; }
    public int getTotal() { return total; }
}
