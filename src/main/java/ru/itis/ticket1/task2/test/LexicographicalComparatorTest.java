package ru.itis.ticket1.task2.test;

import org.junit.Test;
import ru.itis.ticket1.task2.LexicographicalComparator;

import static org.junit.Assert.assertTrue;

public class LexicographicalComparatorTest {

    @Test
    public void testCompare() {
        LexicographicalComparator comparator = new LexicographicalComparator();

        // Пример простого сравнения
        // Пример простого сравнения в лексикографическом порядке
        assertTrue(comparator.compare(10, 2) < 0); // "10" лексикографически меньше, чем "2"
        assertTrue(comparator.compare(2, 10) > 0); // "2" лексикографически больше, чем "10"
        assertTrue(comparator.compare(123, 45) < 0); // "123" лексикографически меньше, чем "45"
        assertTrue(comparator.compare(999, 1000) > 0); // "999" лексикографически больше, чем "1000"
        assertTrue(comparator.compare(100, 100) == 0); // "100" == "100" лексикографически
    }
}
