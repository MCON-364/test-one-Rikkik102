package edu.touro.las.mcon364.test;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FunctionalWarmupTest {
    @Test
    void testGetCurrentDate() {
        assertEquals(LocalDate.now().getMonthValue(), FunctionalWarmup.currentMonthSupplier().get());
    }

    @Test
    void testCleanLables() {
        assertEquals(List.of("HELLO"), FunctionalWarmup.cleanLabels(List.of("   ", " hello     ")));
    }
    @Test
    void testLongerThanFive() {
        assertTrue(FunctionalWarmup.longerThanFive().test("RRRRRRRRR"));
        assertFalse(FunctionalWarmup.longerThanFive().test("RR"));
    }
    @Test
    void testPositiveAndEven() {
        assertTrue(FunctionalWarmup.positiveAndEven().test(44));
        assertFalse(FunctionalWarmup.positiveAndEven().test(-44));
        assertFalse(FunctionalWarmup.positiveAndEven().test(43));
    }
}
