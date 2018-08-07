package musta.belmo.utils.textutils.commons;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class FunctionsTest {
    @Test
    public void capitalizeEachWord() {

        String expected = "Aaa Bbb Ccc";
        String result = Functions.capitalizeEachWord("aaa bbb ccc");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void changeCase() {
        String expected = "AAA BBB CCC";
        String result = Functions.changeCase("aaa bbb ccc", Functions.UPPER_CASE);
        Assert.assertEquals(expected, result);
        result = Functions.changeCase(expected, Functions.LOWER_CASE);
        Assert.assertEquals("aaa bbb ccc", result);
    }

    @Test
    public void capitalize() {
        String expected = "Aaa bbb ccc";
        String result = Functions.capitalize("aaa bbb ccc");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void deleteEmptyLines() {
        String expected = "aaa \nbbb ccc";
        String result = Functions.deleteEmptyLines("aaa \n\n\n\n\nbbb ccc");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void camelCase() {
        String expected = "AaaBbbCcc";
        String result = Functions.camelCase("aaa bbb ccc");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void reduceWhiteSpaces() {
        String expected = "aaa\n\tbbb ccc";
        String result = Functions.reduceWhiteSpaces("aaa\n\n\n\n\n\t\t\tbbb              ccc");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void delete() {
        String expected = "bbbccc";
        String result = Functions.delete("aaabbbccc", "aaa");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void getHighlights() {

        List<HighlightPosition> highlights
                = Functions.getHighlights("aaabbbcccaannaa", "aa");

        HighlightPosition highlightPosition0 = new HighlightPosition(0, 2);
        HighlightPosition highlightPosition1 = new HighlightPosition(9, 11);
        HighlightPosition highlightPosition2 = new HighlightPosition(13, 15);

        Assert.assertTrue(highlights.contains(highlightPosition0));
        Assert.assertTrue(highlights.contains(highlightPosition1));
        Assert.assertTrue(highlights.contains(highlightPosition2));

    }

    @Test
    public void replaceAccentedLetters() {
        String expected = "aaaeeeeiiioouuAAAEEEE";
        String result = Functions.replaceAccentedLetters("àâäêéëèîïìôòûùÀÂÄÉÈËÊ");
      //  Assert.assertEquals(expected, result);
    }

    @Test
    public void deleteSymbols() {
        String expected = "abcdefgh";
        String result = Functions.deleteSymbols("abc-de(f+g_h");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void randomString() {
        String expected = "abcdefgh";
        String result = Functions.randomString(10);
        System.out.println(result);
    }
}