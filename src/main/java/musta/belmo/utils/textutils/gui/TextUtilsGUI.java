package musta.belmo.utils.textutils.gui;

import musta.belmo.utils.textutils.Actions;
import musta.belmo.utils.textutils.Functions;
import musta.belmo.utils.textutils.HighlightPosition;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class TextUtilsGUI {
    private JComboBox<Actions> actionChoice;
    private TextArea inputText;
    private JButton applyButton;
    private JPanel mPanel;
    private JTextField regexField;
    private JScrollPane mScrollPane;
    private SaveAsButton saveAs;
    private JLabel labelL;
    private JLabel labelC;


    private TextUtilsGUI() {

        actionChoice.setRenderer(new ActionRenderer());
        Stream.of(Actions.values())
                .sorted(Comparator.comparing(Actions::name))
                .forEach(action -> actionChoice.addItem(action ));

        TextLineNumber textLineNumber = new TextLineNumber(inputText);
        mScrollPane.setRowHeaderView(textLineNumber);

        applyButton.addActionListener(e -> {
            Actions action = (Actions) actionChoice.getSelectedItem();
            if (action != null) {
                switch (action) {
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
                        List<HighlightPosition> highlights = Functions.getHighlights(inputText.getText(), regexField.getText());
                        inputText.addHighlisghts(highlights);
                        break;
                    case CAPITALIZE_EACH_WORDS:
                        inputText.setText(Functions.capitalizeEachWord(inputText.getText()));
                        break;
                    case DELETE:
                        String result = Functions.delete(inputText.getText(), regexField.getText());
                        inputText.setText(result);
                        break;
                    case REDUCE_WHITE_SPACE:
                        inputText.setText(Functions.reduceWhiteSpaces(inputText.getText()));
                        break;

                    case ENCODE_64:
                        inputText.setText(Functions.encode64(inputText.getText()));
                        break;

                    case INDENT:
                        inputText.setText(Functions.indent(inputText.getText()));
                        break;
                }
            }
        });
        addCaretListenerForCursor();
        saveAs.setActionToPerform(() -> {
            JFileChooser jFileChooser = new JFileChooser();
            if (jFileChooser.showSaveDialog(mPanel) == JFileChooser.APPROVE_OPTION) {
                File file = jFileChooser.getSelectedFile();
                try {
                    FileWriter fileWriter = new FileWriter(file);
                    fileWriter.append(inputText.getText());
                    fileWriter.close();
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


    void addCaretListenerForCursor() {
        inputText.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {

                JTextArea editArea = (JTextArea) e.getSource();
                int linenum = 1;
                int columnnum = 1;
                int caretpos = editArea.getCaretPosition();

                try {
                    linenum = editArea.getLineOfOffset(caretpos);
                    columnnum = caretpos - editArea.getLineStartOffset(linenum);
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }
                linenum += 1;
                labelC.setText("C:" + columnnum);
                labelL.setText("L:" + linenum);

            }
        });
    }


}
