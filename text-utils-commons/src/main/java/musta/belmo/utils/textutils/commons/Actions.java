package musta.belmo.utils.textutils.commons;

public enum Actions {
    DELETE("Delete"),
    DELETE_EMPTY_LINES("Delete empty lines"),
    CAPITALIZE("Capitalize"),
    TO_LOWERCASE("To lowercase"),
    TO_UPPER_CASE("To uppercase"),
    TEST_REGEX("Highlight"),
    CAPITALIZE_EACH_WORDS("Capitalize each word"),
    CAMELCASE("CamelCase"),
    UNCAMELCASE("Decompose CamelCase"),
    REDUCE_WHITE_SPACE("Reduce white spaces"),
    ENCODE_64("Encode 64"),
    DECODE_64("Decode 64"),
    INDENT("Indent"),
    REPLACE_ACCENTED("Replace accented letters"),
    DELETE_SYMBOLS("Delete symbols"),
    RANDOM_STRING("Random String"),
    DELETE_LINES("Delete lines"),
    UNDO("undo"),
    REDO("redo"),
    ADD_LINE("Add line at"),
    FORMAT_XML("Add line at");

    private String label;

    Actions(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
