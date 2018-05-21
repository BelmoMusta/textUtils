package musta.belmo.utils.textutils.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import musta.belmo.utils.textutils.Actions;
import musta.belmo.utils.textutils.Functions;
import musta.belmo.utils.textutils.HighlightPosition;
import org.apache.commons.lang3.StringUtils;

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

        $$$setupUI$$$();
        actionChoice.setRenderer(new ActionRenderer());
        Stream.of(Actions.values())
                .sorted(Comparator.comparing(Actions::name))
                .forEach(action -> actionChoice.addItem(action));



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
                        int length = 0;

                        if (StringUtils.isNumeric(regexField.getText())) {
                            length = Integer.parseInt(regexField.getText());
                        }
                        inputText.setText(Functions.randomString(length));
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


    private void addCaretListenerForCursor() {
        inputText.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {

                JTextArea editArea = (JTextArea) e.getSource();
                int linenum = 1;
                int columnnum = 1;
                int caretpos = editArea.getCaretPosition();

                try {
                    linenum = editArea.getLineOfOffset(caretpos);
                    columnnum = caretpos - editArea.getLineStartOffset(linenum) + 1;
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }
                linenum += 1;
                labelC.setText("C:" + columnnum);
                labelL.setText("L:" + linenum);

            }
        });
    }


    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        mPanel = new JPanel();
        mPanel.setLayout(new GridLayoutManager(5, 7, new Insets(0, 0, 0, 0), -1, -1));
        final JLabel label1 = new JLabel();
        label1.setText("Action");
        mPanel.add(label1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Regex");
        mPanel.add(label2, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        applyButton = new JButton();
        applyButton.setText("Apply");
        mPanel.add(applyButton, new GridConstraints(2, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mScrollPane = new JScrollPane();
        mPanel.add(mScrollPane, new GridConstraints(3, 1, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        inputText.setBackground(new Color(-1317));
        inputText.setForeground(new Color(-16777216));
        inputText.setLineWrap(true);
        mScrollPane.setViewportView(inputText);
        regexField = new JTextField();
        mPanel.add(regexField, new GridConstraints(1, 3, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        actionChoice = new JComboBox();
        mPanel.add(actionChoice, new GridConstraints(0, 3, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(1, -1), null, null, 0, false));
        saveAs = new SaveAsButton();
        saveAs.setText("Save as ...");
        mPanel.add(saveAs, new GridConstraints(4, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelL = new JLabel();
        labelL.setText("L:");
        mPanel.add(labelL, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        labelC = new JLabel();
        labelC.setText("C:");
        mPanel.add(labelC, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        mPanel.add(spacer1, new GridConstraints(0, 0, 5, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(1, 1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mPanel;
    }
}
