package musta.belmo.utils.textutils;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FunctionsTest {
    @Test
    public void capitalizeEachWord() {

        String expected = "Aaa Bbb Ccc";
        String result = Functions.capitalizeEachWord("aaa bbb ccc");
        assertEquals(expected, result);
    }

    @Test
    public void changeCase() {
        String expected = "AAA BBB CCC";
        String result = Functions.changeCase("aaa bbb ccc", Functions.UPPER_CASE);
        assertEquals(expected, result);
        result = Functions.changeCase(expected, Functions.LOWER_CASE);
        assertEquals("aaa bbb ccc", result);
    }

    @Test
    public void capitalize() {
        String expected = "Aaa bbb ccc";
        String result = Functions.capitalize("aaa bbb ccc");
        assertEquals(expected, result);
    }

    @Test
    public void deleteEmptyLines() {

        String expected = "aaa\n bbb ccc";
        String result = Functions.deleteEmptyLines("aaa\n\n\n\n\n bbb ccc");
        assertEquals(expected, result);
    }

    @Test
    public void camelCase() {
        String expected = "AaaBbbCcc";
        String result = Functions.camelCase("aaa bbb ccc");
        assertEquals(expected, result);
    }

    @Test
    public void reduceWhiteSpaces() {
        String expected = "aaa\n\tbbb ccc";
        String result = Functions.reduceWhiteSpaces("aaa\n\n\n\n\n\t\t\tbbb              ccc");
        assertEquals(expected, result);
    }

    @Test
    public void delete() {
        String expected = "bbbccc";
        String result = Functions.delete("aaabbbccc", "aaa");
        assertEquals(expected, result);
    }

    @Test
    public void getHighlights() {

        List<HighlightPosition> highlights
                = Functions.getHighlights("aaabbbcccaannaa", "aa");

        HighlightPosition highlightPosition0 = new HighlightPosition(0, 2);
        HighlightPosition highlightPosition1 = new HighlightPosition(9, 11);
        HighlightPosition highlightPosition2 = new HighlightPosition(13, 15);

        assertTrue(highlights.contains(highlightPosition0));
        assertTrue(highlights.contains(highlightPosition1));
        assertTrue(highlights.contains(highlightPosition2));

    }

}