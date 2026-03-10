package edu.touro.las.mcon364.test;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasicStreamsTest {
//        scoresByCourse.put("Algorithms", List.of(91, 84, 96, 88));
//        scoresByCourse.put("Databases", List.of(77, 81, 79, 85));
//        scoresByCourse.put("Networks", List.of(68, 73, 70, 75));
//        scoresByCourse.put("Java", List.of(95, 92, 90, 98));

    @Test
    void testGetSortedCourseNames() {
        BasicStreamsQuiz bsq = new BasicStreamsQuiz();
        assertEquals(List.of("Algorithms", "Databases", "Java", "Networks"),
                bsq.getSortedCourseNames());
    }

    @Test
    void testCountScoresAtLeast() {
        BasicStreamsQuiz bsq = new BasicStreamsQuiz();
        assertEquals(1, bsq.countScoresAtLeast(97));
        assertEquals(16, bsq.countScoresAtLeast(50));
        assertEquals(15, bsq.countScoresAtLeast(70));
    }

    @Test
    void testFirstLongWord() {
        BasicStreamsQuiz bsq = new BasicStreamsQuiz();
        assertTrue(bsq.firstLongWord(List.of("S", "erdf", "arssdea"), 10).isEmpty());
        assertEquals("arssdea", bsq.firstLongWord(List.of("S", "erdf", "arssdea"), 6).get());
        assertEquals("erdf", bsq.firstLongWord(List.of("S", "erdf", "arssdea"), 3).get());
    }

    @Test
    void testSquareAll() {
        BasicStreamsQuiz bsq = new BasicStreamsQuiz();
        assertEquals(List.of(1, 4, 9, 25), bsq.squareAll(List.of(1, 2, 3, 5)));
    }

    @Test
    void testAveragePassingScore() {
        BasicStreamsQuiz bsq = new BasicStreamsQuiz();
        assertEquals(84, (int)bsq.averagePassingScore());
    }
}
