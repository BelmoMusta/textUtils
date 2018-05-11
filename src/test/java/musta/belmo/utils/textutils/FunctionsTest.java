package musta.belmo.utils.textutils;

import org.junit.Test;

import static org.junit.Assert.*;

public class FunctionsTest {
    @Test
    public void capitalizeEachWord()  {

        String expected = "Aaa Bbb Ccc";
        String result = Functions.capitalizeEachWord("aaa bbb ccc");
        assertEquals(expected,result);
    }

    @Test
    public void changeCase()  {
        String expected = "AAA BBB CCC";
        String result = Functions.changeCase("aaa bbb ccc",Functions.UPPER_CASE);
        assertEquals(expected,result);
        result = Functions.changeCase(expected,Functions.LOWER_CASE);
        assertEquals("aaa bbb ccc",result);
    }

    @Test
    public void capitalize()  {
        String expected = "Aaa bbb ccc";
        String result = Functions.capitalize("aaa bbb ccc");
        assertEquals(expected,result);
    }

    @Test
    public void deleteEmptyLines()  {

        String expected = "aaa\n\n\n\n\n bbb ccc";
        String result = Functions.deleteEmptyLines("aaa bbb ccc");
        assertEquals(expected,result);
    }

    @Test
    public void camelCase()  {
        String expected = "AaaBbbCcc";
        String result = Functions.camelCase("aaa bbb ccc");
        assertEquals(expected,result);
    }

    @Test
    public void reduceWhiteSpaces()  {
        String expected = "aaa\n\tbbb ccc";
        String result = Functions.reduceWhiteSpaces("aaa\n\n\n\n\n\t\t\tbbb              ccc");
        assertEquals(expected,result);
    }

    @Test
    public void delete()  {
        String expected = "bbbccc";
        String result = Functions.delete("aaabbbccc", "aaa");
        assertEquals(expected,result);
    }

}