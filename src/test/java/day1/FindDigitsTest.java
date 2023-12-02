package day1;

import day1.FindDigits;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FindDigitsTest {

    @org.junit.jupiter.api.Test
    void scanForDigitsOnly() {
        assertIterableEquals(List.of("5","8","3","5"), FindDigits.scanForDigits("583sevenhjxlqzjgbzxhkcl5", false));
    }
    @Test
    void scanForDigitsAndWords() {
        assertIterableEquals(List.of("5","8","3","7","5"), FindDigits.scanForDigits("583sevenhjxlqzjgbzxhkcl5", true));
        assertIterableEquals(List.of("5","8","3","7","8","2"), FindDigits.scanForDigits("583sevenhjxlqzjgbzxeightwo", true));
    }
}