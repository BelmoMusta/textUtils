package musta.belmo.utils.textutils;

import org.apache.commons.lang3.StringUtils;

import java.text.Normalizer;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Functions {

    public static final boolean UPPER_CASE = true;
    public static final boolean LOWER_CASE = false;
    private static final String CAMELCASE_REGEX = "(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])";
    private static final String SYMBOLS_REGEX = "[^\\p{L}\\p{Nd} ]+";


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

    public static List<HighlightPosition> getHighlights(String inputText, String regex) {
        List<HighlightPosition> highlightPositions = new ArrayList<>();
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(inputText);

        while (m.find()) {
            HighlightPosition highlightPosition = new HighlightPosition(m.start(), m.end());
            highlightPositions.add(highlightPosition);
        }
        return highlightPositions;
    }

    public static String encode64(String input) {
        return new String(Base64.getEncoder().encode(input.getBytes()));

    }

    public static String decode64(String input) {
        return new String(Base64.getDecoder().decode(input.getBytes()));
    }

    public static String indent(String input) {
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(input);
        while (scanner.hasNextLine()) {

            sb.append('\t')
                    .append(scanner.nextLine()).append('\n');
        }
        scanner.close();
        return sb.toString();

    }

    public static String uncamelcase(String input) {
        StringBuilder sb = new StringBuilder();
        Stream.of(input.split(CAMELCASE_REGEX)).forEach(word -> sb.append(word).append(' '));
        return sb.toString().trim();
    }

    public static String replaceAccentedLetters(String input) {
        String string = Normalizer.normalize(input, Normalizer.Form.NFD);
        string = string.replaceAll("[^\\p{ASCII}]", "");
        return string;
    }

    public static String deleteSymbols(String input) {
        return delete(input, SYMBOLS_REGEX);
    }


}
