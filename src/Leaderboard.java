/**
 * Keeps up to 10 ScoreEntry objects in an array and sorts them
 * from highest score to lowest using Selection Sort.
 */
public class Leaderboard {
    private static final int CAPACITY = 10;
    private final ScoreEntry[] entries = new ScoreEntry[CAPACITY];
    private int count = 0;

    /** Adds a result and re-sorts. If the board is full, the lowest entry is replaced only if the new one is better. */
    public void add(ScoreEntry entry) {
        if (count < CAPACITY) {
            entries[count++] = entry;
        } else if (isBetter(entry, entries[count - 1])) {
            entries[count - 1] = entry;    // array is sorted, so the last entry is the lowest
        } else {
            return;
        }
        sort();
    }

    /** Selection Sort: O(M^2), M <= 10. */
    private void sort() {
        for (int i = 0; i < count - 1; i++) {
            int best = i;
            for (int j = i + 1; j < count; j++) {
                if (isBetter(entries[j], entries[best])) {
                    best = j;
                }
            }
            ScoreEntry temp = entries[i];
            entries[i] = entries[best];
            entries[best] = temp;
        }
    }

    /** Higher score wins; equal scores are ordered alphabetically by name. */
    private boolean isBetter(ScoreEntry a, ScoreEntry b) {
        if (a.getScore() != b.getScore()) {
            return a.getScore() > b.getScore();
        }
        return a.getName().compareToIgnoreCase(b.getName()) < 0;
    }

    public boolean isEmpty() { return count == 0; }

    public void display() {
        System.out.println("\n========== LEADERBOARD ==========");
        if (isEmpty()) {
            System.out.println("No results yet. Play a quiz first!");
        } else {
            System.out.printf("%-6s %-20s %s%n", "Rank", "Name", "Score");
            for (int i = 0; i < count; i++) {
                System.out.printf("%-6d %-20s %d/%d%n", i + 1, entries[i].getName(),
                        entries[i].getScore(), entries[i].getTotal());
            }
        }
        System.out.println("=================================");
    }
}
