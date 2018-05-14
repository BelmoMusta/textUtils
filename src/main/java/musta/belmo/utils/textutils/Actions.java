package musta.belmo.utils.textutils;

public enum Actions {
    DELETE("Delete"),
    DELETE_EMPTY_LINES("Delete empty lines"),
    CAPITALIZE("Capitalize"),
    TO_LOWERCASE("To lowercase"),
    TO_UPPER_CASE("To uppercase"),
    TEST_REGEX("Highlight"),
    CAPITALIZE_EACH_WORDS("Capitalize each word"),
    CAMELCASE("CamelCase"),
    REDUCE_WHITE_SPACE("Reduce white spaces"),
    ENCODE_64("Encode 64"),
    INDENT("Indent");

    private String label;

    Actions(String label) {

        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
