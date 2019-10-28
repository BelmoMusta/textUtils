package musta.belmo.utils.textutils.commons;

import musta.belmo.utils.textutils.commons.text.line.TextLinesUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FunctionsTest {
    @Test
    public void capitalizeEachWord() {

        String expected = "Aaa Bbb Ccc";
        String result = StringUtilities.capitalizeEachWord("aaa bbb ccc");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void changeCaseUpper() {
        String text = "aaa bbb ccc";
        String expected = "AAA BBB CCC";
        String result = StringUtilities.changeCase(text, StringUtilities.UPPER_CASE);
        Assert.assertEquals(expected, result);

    }
    @Test
    public void changeCaseLower() {
        String text = "AAA BBB CCC ÉÉÀ";
        String expected = "aaa bbb ccc ééà";
        String result = StringUtilities.changeCase(text, StringUtilities.LOWER_CASE);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void capitalize() {
        String expected = "Aaa bbb ccc";
        String result = StringUtilities.capitalize("aaa bbb ccc");
        Assert.assertEquals(expected, result);
    }
    @Test
    public void capitalizeOneLetter() {
        String expected = "A";
        String result = StringUtilities.capitalize("a");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void deleteEmptyLines() {
        String expected = "aaa \nbbb ccc";
        String result = StringUtilities.deleteEmptyLines("aaa \n\n\n\n\nbbb ccc");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void camelCase() {
        String expected = "AaaBbbCcc";
        String result = StringUtilities.camelCase("aaa bbb ccc");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void reduceWhiteSpaces() {
        String expected = "aaa\n\tbbb ccc";
        String result = StringUtilities.reduceWhiteSpaces("aaa\n\n\n\n\n\t\t\tbbb              ccc");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void delete() {
        String expected = "bbbccc";
        String result = TextLinesUtils.delete("aaabbbccc", "aaa");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void getHighlights() {

        List<HighlightPosition> highlights
                = StringUtilities.getHighlights("aaabbbcccaannaa", "aa");

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
        String result = StringUtilities.replaceAccentedLetters("àâäêéëèîïìôòûùÀÂÄÉÈËÊ");
        //  Assert.assertEquals(expected, result);
    }

    @Test
    public void deleteSymbols() {
        String expected = "abcdefgh";
        String result = StringUtilities.deleteSymbols("abc-de(f+g_h");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void randomString() {
        String result = StringUtilities.randomString(10);
        Assert.assertEquals(10, result.length());
    }

    @Test
    public void testBase64EncodingAndDecoding() {
        String encode = StringUtilities.encode64("abc");
        String result = StringUtilities.decode64(encode);
        Assert.assertEquals("abc", result);
    }

    @Test
    public void testIndent() {
        String result = StringUtilities.indent("abc");
        Assert.assertEquals("\tabc\n", result);
    }

    @Test
    public void testSplitCamelcase() {
        String result = StringUtilities.splitCamelCase("aBcdeFghijKlmNo");
        Assert.assertEquals("a bcde fghij klm no", result);
    }

    @Test
    public void testUncamelcase() {
        String result = TextLinesUtils.deleteLines("a\nb\nc\nd\n",1,3);
        Assert.assertEquals("b\nd\n", result);
    }
    @Test
    public void testAddLines() {
        String result = TextLinesUtils.addLinesAtPositions("this is line 1 \n" +
                "this is line 2\n" +
                "this is line 3\n" +
                "this is line 4\n" +
                "this is line 5\n" +
                "this is line 6",
                "2 add this line at position 2\n" +
                "8 add this line at position 8\n" +
                "0 add this line at position 0\n" +
                "-1 add this line at position -1\n" +
                "7 add this line at position 7\n" +
                "5 add this line at position 5");

        System.out.println(result);
        //Assert.assertEquals("b\nd\n", result);
    }
    @Test
    public void testAddLinesUsingMap() {
        Map<Integer,String> map = new LinkedHashMap<>();
        map.put(1,"line to be added in position 1");
        String result = TextLinesUtils.addLinesAtPositions("this is line 1 \n" +
                "this is line 2\n" +
                "this is line 3\n" +
                "this is line 4\n" +
                "this is line 5\n" +
                "this is line 6",
                map);


         Assert.assertEquals("line to be added in position 1\n" +
                 "this is line 1 \n" +
                 "this is line 2\n" +
                 "this is line 3\n" +
                 "this is line 4\n" +
                 "this is line 5\n" +
                 "this is line 6", result);
    }
}