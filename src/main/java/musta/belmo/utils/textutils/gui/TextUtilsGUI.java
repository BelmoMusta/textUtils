package musta.belmo.utils.textutils.gui;

import musta.belmo.utils.textutils.Actions;
import musta.belmo.utils.textutils.Functions;
import musta.belmo.utils.textutils.HighlightPosition;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class TextUtilsGUI {
    private JComboBox<Actions> actionChoice;
    private JTextArea inputText;
    private JButton applyButton;
    private JPanel mPanel;
    private JTextField regexField;
    private JScrollPane mScrollPane;
    private SaveAsButton saveAs;


    private TextUtilsGUI() {
        Stream.of(Actions.values()).forEach(actionChoice::addItem);

        TextLineNumber textLineNumber = new TextLineNumber(inputText);
        mScrollPane.setRowHeaderView(textLineNumber);
        applyButton.addActionListener(e -> {
            Actions action = (Actions) actionChoice.getSelectedItem();
            if (action != null) {
                switch (action) {
                    case DELETE_EMPTY_LINES:
                        inputText.setText(Functions.deleteEmptyLines(inputText.getText()));
                        break;
                    case TRIM:
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
                        java.util.List<HighlightPosition> highlights = Functions.getHighlights(inputText.getText(), regexField.getText());
                        Highlighter highlighter = inputText.getHighlighter();
                        highlighter.removeAllHighlights();
                        highlights.forEach(highlightPosition -> {
                            try {
                                highlighter.addHighlight(
                                        highlightPosition.getStart(),
                                        highlightPosition.getEnd(),
                                        DefaultHighlighter.DefaultPainter);
                            } catch (BadLocationException e1) {
                                e1.printStackTrace();
                            }

                        });


                        break;
                    case CAPITALIZE_EACH_WORDS:
                        inputText.setText(Functions.capitalizeEachWord(inputText.getText()));
                        break;
                    case DELETE:
                        String old = inputText.getText();
                        Functions.delete(old, regexField.getText());
                        inputText.setText(old.replaceAll(regexField.getText(), ""));
                        break;
                    case REDUCE_WHITE_SPACE:
                        inputText.setText(Functions.reduceWhiteSpaces(inputText.getText()));
                        break;
                }
            }
        });

        saveAs.setActionToPerform(() -> {
            JFileChooser jFileChooser = new JFileChooser();
            if (jFileChooser.showSaveDialog(mPanel) == JFileChooser.APPROVE_OPTION) {
                File f = jFileChooser.getSelectedFile();
                try {
                    FileWriter fw = new FileWriter(f);
                    fw.append(inputText.getText());
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFrame jf = new JFrame();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                jf.setContentPane(new TextUtilsGUI().mPanel);
                jf.pack();
                jf.setLocation(400, 400);
                jf.setMinimumSize(new Dimension() {{
                    setSize(800, 400);
                }});
                jf.setVisible(true);
                jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
        thread.start();
    }

    private void createUIComponents() {
        inputText = new TextArea();
    }
}
