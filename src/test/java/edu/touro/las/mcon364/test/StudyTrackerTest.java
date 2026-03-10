package edu.touro.las.mcon364.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudyTrackerTest {
    @Test
    void testAddLearner() {
        StudyTracker st = new StudyTracker();
        assertTrue(st.addLearner("Jayden"));
        assertTrue(st.getScoresByLearner().containsKey("Jayden"));
        assertFalse(st.addLearner("Jayden"));
    }

    @Test
    void testAddScore() {
        StudyTracker st = new StudyTracker();
        assertFalse(st.addScore("Harvey", 78));
        st.addLearner("Harvey");
        assertTrue(st.addScore("Harvey", 78));
        assertTrue(st.getScoresByLearner().get("Harvey").contains(78));
        assertTrue(st.addScore("Harvey", 88));
        assertEquals(2, st.getScoresByLearner().get("Harvey").size());
    }

    @Test
    void testAverageFor() {
        StudyTracker st = new StudyTracker();
        assertTrue(st.averageFor("Ron").isEmpty());
        st.addLearner("Aaron");
        st.addScore("Aaron", 90);
        st.addScore("Aaron", 80);
        st.addScore("Aaron", 95);
        st.addScore("Aaron", 75);
        assertEquals(85, st.averageFor("Aaron").get());
    }

    @Test
    void testLetterGradeFor() {
        StudyTracker st = new StudyTracker();
        assertTrue(st.averageFor("Ron").isEmpty());
        st.addLearner("Lyon");
        st.addScore("Lyon", 90);
        st.addScore("Lyon", 80);
        assertEquals("B",  st.letterBandFor("Lyon").get());
    }

    @Test
    void testUndo() {
        StudyTracker st = new StudyTracker();
        assertFalse(st.undoLastChange());
        st.addLearner("Carla");
        st.addScore("Carla", 90);
        assertTrue(st.getScoresByLearner().get("Carla").contains(90));
        assertTrue(st.undoLastChange());
        assertTrue(st.getScoresByLearner().get("Carla").isEmpty());
    }
}
