package musta.belmo.utils.textutils.commons;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.swing.text.html.Option;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Functions {

    public static final boolean UPPER_CASE = true;
    public static final boolean LOWER_CASE = false;
    private static final String CAMELCASE_REGEX = "(?<!(^|[A-Z\\d]))((?=[A-Z\\d])|[A-Z](?=[\\d]))|(?<!^)(?=[A-Z\\d][a-z])";
    private static final String SYMBOLS_REGEX = "[^\\p{L}\\p{Nd} ]+";


    public static String capitalizeEachWord(String text) {
        StringBuilder sb = new StringBuilder();
        Stream.of(text.split("\\s+")).forEach(str -> sb.append(capitalize(str)).append(' '));
        return sb.toString().trim();
    }

    public static String changeCase(String text, boolean upper) {
        return Optional.ofNullable(text)
                .filter(str -> upper)
                .map(String::toUpperCase)
                .orElseGet(() ->
                        Optional.ofNullable(text)
                                .map(String::toLowerCase)
                                .orElse(null));

    }

    public static String capitalize(String text) {
        return Optional.ofNullable(text)
                .map(string -> Character.toUpperCase(string.charAt(0)) + string.substring(1))
                .orElse(null);
    }

    public static String uncapitalize(String text) {
        return Optional.ofNullable(text)
                .map(string -> Character.toLowerCase(string.charAt(0)) + string.substring(1))
                .orElse(null);
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
        return Optional.ofNullable(old)
                .map(str -> str.replaceAll(regex, ""))
                .orElse(null);
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
        final StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(input);
        while (scanner.hasNextLine()) {

            sb.append('\t')
                    .append(scanner.nextLine()).append('\n');
        }
        scanner.close();
        return sb.toString();

    }

    public static String splitCamelCase(String input) {
        return Stream.of(input.split(CAMELCASE_REGEX))
                .map(Functions::uncapitalize)
                .collect(Collectors.joining(" "));
    }

    public static String replaceAccentedLetters(String input) {
        String string = Normalizer.normalize(input, Normalizer.Form.NFD);
        string = string.replaceAll("[^\\p{ASCII}]", "");
        return string;
    }

    public static String deleteSymbols(String input) {
        return delete(input, SYMBOLS_REGEX);
    }


    public static String randomString(int length) {
        return randomString(length, true, true, true);
    }

    private static String randomString(int length,
                                       boolean withNumeric,
                                       boolean withLowerCase,
                                       boolean withUpperCase) {
        StringBuilder sb = new StringBuilder();

        while (sb.length() < length) {
            char randomUpperCaseChar = (char) ('A' + new Random().nextInt('Z' - 'A'));
            char randomLowerCaseChar = (char) ('a' + new Random().nextInt('z' - 'a'));
            char randomNumChar = (char) ('0' + new Random().nextInt('9' - '0'));


            int randomChoice = new Random().nextInt(3);

            if (withUpperCase && randomChoice % 3 == 0)
                sb.append(randomUpperCaseChar);
            else if (withLowerCase && randomChoice % 3 == 1)
                sb.append(randomLowerCaseChar);
            else if (withNumeric && randomChoice % 3 == 2)
                sb.append(randomNumChar);
        }
        return sb.toString();
    }

    public static String deleteLines(String text, Integer... lines) {

        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(text);

        List<Integer> list = Arrays.asList(lines);
        int counter = 1;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (list.contains(Integer.valueOf(counter))) {
                counter++;
                continue;
            }

            sb.append(line).append('\n');
            counter++;
        }
        sc.close();
        return sb.toString();
    }

    public static String addLinesAtPositions(String text, String string) {
        return addLinesAtPositions(text, convertToTextLines(string));
    }

    public static String addLinesAtPositions(String text, File file) {
        return addLinesAtPositions(text, convertToTextLines(file));
    }

    public static String addLinesAtPositions(String text, Map<Integer, String> linesToAdd) {
        return addLinesAtPositions(text, convertToTextLine(linesToAdd));
    }

    private static String addLinesAtPositions(String text, Set<TextLine> setOfLinesToAdd) {
        final List<String> listLines = convertStringToListOfLines(text);
        final List<String> mergedText = addLinesToPositions(setOfLinesToAdd, listLines);
        return convertLinesToString(mergedText);
    }

    private static String convertLinesToString(List<String> listLines) {
        return listLines.stream()
                .collect(Collectors.joining("\n"));
    }

    private static List<String> addLinesToPositions(Set<TextLine> setOfLinesToAdd, List<String> listLines) {
        final List<String> result = new ArrayList<>(listLines);
        int insertedLines = 0;
        Set<TextLine> textLinesWithPositivePositions = setOfLinesToAdd.stream()
                .filter(textLine -> textLine.getLineNumber() > 0)
                .collect(Collectors.toSet());
        for (TextLine line : textLinesWithPositivePositions) {
            int position = insertedLines + line.getLineNumber() - 1;
            if (result.size() > position) {
                result.add(position, line.getContent());
            } else {
                result.add(line.getContent());
            }
            insertedLines++;
        }
        List<String> negativeLines = addNegativeLinesAtFirst(setOfLinesToAdd);
        negativeLines.addAll(result);
        return negativeLines;
    }

    private static List<String> addNegativeLinesAtFirst(Set<TextLine> listLines) {
        return listLines.stream().filter((textLine -> textLine.getLineNumber() <= 0))
                .map(TextLine::getContent)
                .collect(Collectors.toList());

    }

    private static List<String> convertStringToListOfLines(String text) {
        return Optional.ofNullable(text)
                .map(txt -> txt.split("\\n"))
                .map(Arrays::asList)
                .orElse(Arrays.asList())
                .stream()
                .collect(Collectors.toList());
    }

    private static Set<TextLine> convertToTextLine(Map<Integer, String> linesToAdd) {
        return linesToAdd.entrySet().stream()
                .map(entry -> new TextLine(entry.getKey(), entry.getValue()))
                .collect(Collectors.toSet());
    }

    private static Set<TextLine> convertToTextLines(File linesToAdd) {
        FileReader reader = null;
        try {
            reader = new FileReader(linesToAdd);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return convertReadableTextToTextLines(reader);
    }

    private static Set<TextLine> convertToTextLines(String linesToAdd) {
        final StringReader reader = new StringReader(linesToAdd);
        return convertReadableTextToTextLines(reader);
    }

    private static Set<TextLine> convertReadableTextToTextLines(Readable readable) {
        final Set<TextLine> setOfLines = new TreeSet<>();
        if (Objects.nonNull(readable)) {
            final Scanner sc = new Scanner(readable);
            while (sc.hasNextLine()) {
                TextLine textLine = new TextLine(sc.nextInt(), trimWitheSpacesAtStart(sc.nextLine()));
                setOfLines.add(textLine);
            }
        }
        return setOfLines;
    }

    public static String formatXML(String text) {
        try {
            Source xmlInput = new StreamSource(new StringReader(removeCommentsFromXML(text)));
            StringWriter stringWriter = new StringWriter();
            StreamResult xmlOutput = new StreamResult(stringWriter);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", 4);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(xmlInput, xmlOutput);
            return xmlOutput.getWriter().toString();
        } catch (TransformerException e) {
            throw new RuntimeException(e); // simple exception handling, please review it
        }

    }

    private static String trimWitheSpacesAtStart(String input) {
        return delete(input, "^[\\t ]");
    }

    public static String formatJSON(String input) {
        final String json;
        Object json_ = new JSONTokener(input).nextValue();
        if (json_ instanceof JSONObject) {
            json = new LinkedJSONObject(input).toString(2);
        } else if (json_ instanceof JSONArray) {
            json = new JSONArray(input).toString(2);
        } else {
            json = input;
        }

        return json;
    }

    private static String removeCommentsFromXML(String text) {
        return text.replaceAll("<!--.*-->", "");
    }


}
