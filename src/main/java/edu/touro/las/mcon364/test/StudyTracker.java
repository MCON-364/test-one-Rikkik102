package edu.touro.las.mcon364.test;

import java.util.*;

public class StudyTracker {

    private final Map<String, List<Integer>> scoresByLearner = new HashMap<>();
    private final Deque<UndoStep> undoStack = new ArrayDeque<>();
    // Helper methods already provided for tests and local inspection.
    public Optional<List<Integer>> scoresFor(String name) {
        return Optional.ofNullable(scoresByLearner.get(name));
    }

    public Set<String> learnerNames() {
        return scoresByLearner.keySet();
    }
    /**
     * Problem 11
     * Add a learner with an empty score list.
     *
     * Return:
     * - true if the learner was added
     * - false if the learner already exists
     *
     * Throw IllegalArgumentException if name is null or blank.
     */
    public boolean addLearner(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name is null or blank");
        }
       else if (scoresByLearner.containsKey(name)) {
           return false;
       }
       else {
           scoresByLearner.put(name, new ArrayList<>());
           return true;
       }
    }

    /**
     * Problem 12
     * Add a score to an existing learner.
     *
     * Return:
     * - true if the score was added
     * - false if the learner does not exist
     *
     * Valid scores are 0 through 100 inclusive.
     * Throw IllegalArgumentException for invalid scores.
     *
     * This operation should be undoable.
     */
    public boolean addScore(String name, int score) {
    if (score < 0 || score > 100) {
        throw new IllegalArgumentException("Invalid score");
    }
    else if (!(scoresByLearner.containsKey(name))) {
            return false;
        }
    else {
        scoresByLearner.get(name).add(score);
        undoStack.push(() -> scoresByLearner.get(name).removeLast());
        return true;
    }
    }

    /**
     * Problem 13
     * Return the average score for one learner.
     *
     * Return Optional.empty() if:
     * - the learner does not exist, or
     * - the learner has no scores
     */
    public Optional<Double> averageFor(String name) {
        if (scoresFor(name).isEmpty()) {
            return Optional.empty();
        }
        else {
            return Optional.of(scoresFor(name).get().stream()
                    .mapToInt(Integer::intValue)
                    .average()
                    .getAsDouble());
        }
    }

    /**
     * Problem 14
     * Convert a learner average into a letter band.
     *
     * A: 90+
     * B: 80-89.999...
     * C: 70-79.999...
     * D: 60-69.999...
     * F: below 60
     *
     * Return Optional.empty() when no average exists.
     */
    public Optional<String> letterBandFor(String name) {
        if (scoresFor(name).isEmpty()) {
            return Optional.empty();
        }
        else {
            double average = scoresFor(name).get().stream()
                    .mapToInt(Integer::intValue)
                    .average()
                    .getAsDouble();

            int firstNumber = (int)(average / 10);
            String letterGrade = switch(firstNumber) {
                case 10, 9 ->  { yield "A";}
                case 8 -> { yield "B"; }
                case 7 -> { yield "C"; }
                case 6 -> { yield "D"; }
                default -> { yield "F"; }
            };
            return Optional.of(letterGrade);
        }
    }

    /**
     * Problem 15
     * Undo the most recent state-changing operation.
     *
     * Return true if something was undone.
     * Return false if there is nothing to undo.
     */
    public boolean undoLastChange() {
        if  (undoStack.isEmpty()) {
            return false;
        }
        else {
            UndoStep action = undoStack.pop();
            action.undo();
            return true;
        }
    }
}
