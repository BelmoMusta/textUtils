package musta.belmo.utils.textutils;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.stream.Stream;

public class Functions {

    public static final boolean UPPER_CASE = true;
    public static final boolean LOWER_CASE = false;

    public static String capitalizeEachWord(String text) {
        StringBuilder sb = new StringBuilder();
        Stream.of(text.split("\\s+")).forEach(str -> sb.append(capitalize(str)).append(' '));
        return sb.toString().trim();
    }

    public static String changeCase(String text, boolean upper) {
        if (upper)
            return StringUtils.upperCase(text);
        return StringUtils.lowerCase(text);
    }

    public static String capitalize(String text) {
        return StringUtils.capitalize(text);
    }

    public static String deleteEmptyLines(String inputText) {
        return inputText.replaceAll("(?m)^[ \t]*\r?\n", "");
    }

    public static String camelCase(String input) {
        return Arrays.stream(input.split("\\s+")).reduce((a, b) ->
                StringUtils.capitalize(a) + StringUtils.capitalize(b)).get();
    }

    public static String reduceWhiteSpaces(String input) {
        String ret = input;
        String[] whiteSpaceRegex = {" {2,}", "\\t{2,}", "\\n{2,}", "\\r{2,}"};
        String[] replacement = {" ", "\t", "\n", "\r"};

        for (int i = 0; i < whiteSpaceRegex.length; i++) {
            ret = ret.replaceAll(whiteSpaceRegex[i], replacement[i]);
        }
        return ret;
    }


    public static String delete(String old, String regex) {
        return old.replaceAll(regex, "");
    }
}
