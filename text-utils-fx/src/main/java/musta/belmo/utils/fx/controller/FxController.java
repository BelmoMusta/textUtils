package musta.belmo.utils.fx.controller;

import com.sun.javafx.scene.control.behavior.TextAreaBehavior;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import musta.belmo.utils.fx.gui.ActionButton;
import musta.belmo.utils.textutils.commons.Actions;
import musta.belmo.utils.textutils.commons.Commons;
import musta.belmo.utils.textutils.commons.Functions;
import org.kordamp.ikonli.fontawesome.FontAwesome;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;


/**
 * Created by DELL on 04/08/2018.
 */
public class FxController {

    @FXML
    TextArea inputText;

    @FXML
    HBox buttons;

    @FXML
    public void initialize() throws IOException {

        TextAreaBehavior textAreaBehavior = new TextAreaBehavior(inputText);
        textAreaBehavior.dispose();
        VBox[] vBoxes = new VBox[(Actions.values().length / 4) + 1];
        for (int i = 0; i < vBoxes.length; i++) {
            vBoxes[i] = new VBox();
            buttons.getChildren().addAll(vBoxes[i]);
        }

        int y = 0;
        int idx = 0;
        for (Actions actions : Actions.values()) {
            ActionButton button = new ActionButton();
            String iconDescription = Commons.readFromProperties(actions.name());
            button.setGraphic(FontIcon.of(FontAwesome.findByDescription(iconDescription)));
            button.setActions(actions);
            Tooltip tooltip = new Tooltip();
            tooltip.setText(actions.getLabel());
            button.setTooltip(tooltip);
            button.setOnAction(this::editText);

            if (y != 0 && y % 4 == 0) {
                idx++;
            }
            vBoxes[idx].getChildren().add(button);

            y++;

        }


    }

    public void editText(ActionEvent actionEvent) {
        Actions actions = ((ActionButton) actionEvent.getSource()).getActions();
        if (actions != null) {
            switch (actions) {
                case DELETE_EMPTY_LINES:
                    inputText.setText(Functions.deleteEmptyLines(inputText.getText()));
                    break;
                case CAPITALIZE:
                    inputText.setText(Functions.capitalize(inputText.getText()));
                    break;
                case CAMELCASE:
                    inputText.setText(Functions.camelCase(inputText.getText()));
                    break;
                case TO_UPPER_CASE:
                    inputText.setText(Functions.changeCase(inputText.getText(), true));
                    break;
                case TO_LOWERCASE:
                    inputText.setText(Functions.changeCase(inputText.getText(), false));
                    break;
                case TEST_REGEX:
                     /*
                        String inputDialog = JOptionPane.showInputDialog(this.mPanel, "Enter REGEX pattern to highlight:", "");
                        List<HighlightPosition> highlights = Functions.getHighlights(inputText.getText(),
                                inputDialog);
                        inputText.addHighlisghts(highlights);
                   */
                    break;
                case CAPITALIZE_EACH_WORDS:
                    inputText.setText(Functions.capitalizeEachWord(inputText.getText()));
                    break;
                case DELETE:
                     /*
                        String inputDialog = JOptionPane.showInputDialog(this.mPanel, "Enter REGEX pattern to delete:", "");
                        String result = Functions.delete(inputText.getText(), inputDialog);
                        inputText.setText(result);
                     */
                    break;
                case REDUCE_WHITE_SPACE:
                    inputText.setText(Functions.reduceWhiteSpaces(inputText.getText()));
                    break;
                case ENCODE_64:
                    inputText.setText(Functions.encode64(inputText.getText()));
                    break;
                case DECODE_64:
                    inputText.setText(Functions.decode64(inputText.getText()));
                    break;
                case INDENT:
                    inputText.setText(Functions.indent(inputText.getText()));
                    break;
                case UNCAMELCASE:
                    inputText.setText(Functions.uncamelcase(inputText.getText()));
                    break;
                case REPLACE_ACCENTED:
                    inputText.setText(Functions.replaceAccentedLetters(inputText.getText()));
                    break;
                case DELETE_SYMBOLS:
                    inputText.setText(Functions.deleteSymbols(inputText.getText()));
                    break;
                case RANDOM_STRING:
                     /*
                        int length = 0;
                        String inputDialog = JOptionPane.showInputDialog(this.mPanel, "Random String length:", "0");
                        if (StringUtils.isNumeric(inputDialog)) {
                            length = Integer.parseInt(inputDialog);
                            if (length < 0) {
                                length = 0;
                            }
                        }
                        inputText.setText(Functions.randomString(length));
                     */
                    break;

                case DELETE_LINES:
                     /*
                        String inputDialog = JOptionPane.showInputDialog(this.mPanel, "lines to be deleted:\n(separated by space or ,)", "");
                        String[] linesStr = inputDialog.split("[,\\s;]");
                        Integer[] lines = new Integer[linesStr.length];
                        for (int i = 0; i < linesStr.length; i++) {
                            lines[i] = Integer.valueOf(linesStr[i]);
                        }
                        String deleteLines = Functions.deleteLines(inputText.getText(), lines);

                        inputText.setText(deleteLines);
                   */
                    break;
                case REDO:
                    //  inputText.redo();
                    break;
                case UNDO:
                    //inputText.undo();
                    break;
                case ADD_LINE:
                     /*
                        FileChooser fileChooser = new FileChooser();
                        fileChooser.asOpenDialog()
                                .showDialog(null, "Ouvrir");

                        fileChooser.doWhenApproved(() -> {
                            File chosenFile = fileChooser.getSelectedFile();
                            String textWithAddedLines = Functions.addLinesAt(inputText.getText(), chosenFile);
                            inputText.setText(textWithAddedLines);
                        }).doWhenCanceled(() -> System.out.println("File Chooser Canceled"));
                   */
                    break;
            }
        }

        inputText.setText(Functions.deleteEmptyLines(inputText.getText()));
    }
}
