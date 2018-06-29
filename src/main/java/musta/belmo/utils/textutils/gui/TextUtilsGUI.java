package musta.belmo.utils.textutils.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import musta.belmo.utils.textutils.Actions;
import musta.belmo.utils.textutils.Functions;
import musta.belmo.utils.textutils.HighlightPosition;
import musta.belmo.utils.textutils.VoidConsumer;
import org.apache.commons.lang3.StringUtils;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.fontawesome.FontAwesome;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TextUtilsGUI {
    private JComboBox<Actions> actionChoice;
    private TextArea inputText;
    private JButton applyButton;
    private JPanel mPanel;
    private JScrollPane mScrollPane;
    private ActionButton saveAs;
    private JLabel labelL;
    private JLabel labelC;
    String lastInput;

    private JButton btnIndent;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JButton button13;
    private JButton button14;
    private JButton button15;
    private JButton button16;
    private JButton button17;
    private JButton button18;


    private TextUtilsGUI() {


        $$$setupUI$$$();
        $setupButtons$();


        TextLineNumber textLineNumber = new TextLineNumber(inputText);
        mScrollPane.setRowHeaderView(textLineNumber);


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

    private VoidConsumer getActionFrom(Actions action) {
        VoidConsumer consumer = null;
        if (action != null) {
            switch (action) {
                case DELETE_EMPTY_LINES:
                    consumer = () -> inputText.setText(Functions.deleteEmptyLines(inputText.getText()));
                    break;
                case CAPITALIZE:
                    consumer = () -> inputText.setText(Functions.capitalize(inputText.getText()));
                    break;
                case CAMELCASE:
                    consumer = () -> inputText.setText(Functions.camelCase(inputText.getText()));
                    break;
                case TO_UPPER_CASE:
                    consumer = () -> inputText.setText(Functions.changeCase(inputText.getText(), true));
                    break;
                case TO_LOWERCASE:
                    consumer = () -> inputText.setText(Functions.changeCase(inputText.getText(), false));
                    break;
                case TEST_REGEX:
                    consumer = () -> {
                        String inputDialog = JOptionPane.showInputDialog(this.mPanel, "Enter REGEX pattern to highlight:", "");
                        List<HighlightPosition> highlights = Functions.getHighlights(inputText.getText(),
                                inputDialog);
                        inputText.addHighlisghts(highlights);
                    };
                    break;
                case CAPITALIZE_EACH_WORDS:
                    consumer = () -> inputText.setText(Functions.capitalizeEachWord(inputText.getText()));
                    break;
                case DELETE:
                    consumer = () -> {
                        String inputDialog = JOptionPane.showInputDialog(this.mPanel, "Enter REGEX pattern to delete:", "");
                        String result = Functions.delete(inputText.getText(), inputDialog);
                        inputText.setText(result);
                    };
                    break;
                case REDUCE_WHITE_SPACE:
                    consumer = () -> inputText.setText(Functions.reduceWhiteSpaces(inputText.getText()));
                    break;
                case ENCODE_64:
                    consumer = () -> inputText.setText(Functions.encode64(inputText.getText()));
                    break;
                case DECODE_64:
                    consumer = () -> inputText.setText(Functions.decode64(inputText.getText()));
                    break;
                case INDENT:
                    consumer = () -> inputText.setText(Functions.indent(inputText.getText()));
                    break;
                case UNCAMELCASE:
                    consumer = () -> inputText.setText(Functions.uncamelcase(inputText.getText()));
                    break;
                case REPLACE_ACCENTED:
                    consumer = () -> inputText.setText(Functions.replaceAccentedLetters(inputText.getText()));
                    break;
                case DELETE_SYMBOLS:
                    consumer = () -> inputText.setText(Functions.deleteSymbols(inputText.getText()));
                    break;

                case RANDOM_STRING:
                    consumer = () -> {
                        int length = 0;
                        String inputDialog = JOptionPane.showInputDialog(this.mPanel, "Random String length:", "0");
                        if (StringUtils.isNumeric(inputDialog)) {
                            length = Integer.parseInt(inputDialog);
                            if (length < 0) {
                                length = 0;
                            }
                        }
                        inputText.setText(Functions.randomString(length));
                    };
                    break;

                case DELETE_LINES:
                    consumer = () -> {
                        String inputDialog = JOptionPane.showInputDialog(this.mPanel, "lines to be deleted:\n(separated by space or ,)", "");
                        String[] linesStr = inputDialog.split("[,\\s;]");
                        Integer[] lines = new Integer[linesStr.length];
                        for (int i = 0; i < linesStr.length; i++) {
                            lines[i] = Integer.valueOf(linesStr[i]);
                        }
                        String deleteLines = Functions.deleteLines(inputText.getText(), lines);

                        inputText.setText(deleteLines);
                    };
                    break;
                case REDO:
                    consumer = () -> inputText.redo();
                    break;
                case UNDO:
                    consumer = () -> inputText.undo();
                    break;
            }
        }

        return consumer;
    }

    private void $setupButtons$() {


        List<JButton> listButton = new ArrayList();
        Actions[] values = Actions.values();
        listButton.add(btnIndent);
        listButton.add(button1);
        listButton.add(button2);
        listButton.add(button3);
        listButton.add(button4);
        listButton.add(button5);
        listButton.add(button6);
        listButton.add(button7);
        listButton.add(button8);
        listButton.add(button9);
        listButton.add(button10);
        listButton.add(button11);
        listButton.add(button12);
        listButton.add(button13);
        listButton.add(button14);
        listButton.add(button15);
        listButton.add(button16);
        listButton.add(button17);
        listButton.add(button18);

        try {
            for (int i = 0; i < values.length; i++) {

                Ikon byDescription = FontAwesome.findByDescription(readFromProperties(values[i].name()));

                FontIcon fontIcon = FontIcon.of(byDescription);
                JButton jButton = listButton.get(i);
                jButton.setIcon(fontIcon);
                jButton.setToolTipText(values[i].getLabel());
                jButton.setText("");
                final int y = i;
                jButton.addActionListener(e -> getActionFrom(values[y]).perform());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFromProperties(String key) throws IOException {
        InputStream resourceAsStream = TextUtilsGUI.class.getClassLoader().getResource("application.properties").openStream();

        Properties properties = new Properties();
        properties.load(resourceAsStream);
        return properties.getProperty(key);
    }

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFrame jf = new JFrame("Text Utils");
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
        btnIndent = new JButton();
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
        mPanel.setLayout(new GridLayoutManager(7, 7, new Insets(0, 0, 0, 0), -1, -1));
        mScrollPane = new JScrollPane();
        mPanel.add(mScrollPane, new GridConstraints(5, 1, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        inputText = new TextArea();
        inputText.setBackground(new Color(-1317));
        inputText.setForeground(new Color(-16777216));
        inputText.setLineWrap(true);
        mScrollPane.setViewportView(inputText);
        saveAs = new ActionButton();
        saveAs.setText("Save as ...");
        mPanel.add(saveAs, new GridConstraints(6, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelL = new JLabel();
        labelL.setText("L:");
        mPanel.add(labelL, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        labelC = new JLabel();
        labelC.setText("C:");
        mPanel.add(labelC, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        mPanel.add(spacer1, new GridConstraints(2, 0, 5, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(1, 1), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        mPanel.add(panel1, new GridConstraints(0, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        button16 = new JButton();
        button16.setText("Button");
        panel1.add(button16);
        button7 = new JButton();
        button7.setText("Button");
        panel1.add(button7);
        button6 = new JButton();
        button6.setText("Button");
        panel1.add(button6);
        button5 = new JButton();
        button5.setText("Button");
        panel1.add(button5);
        button4 = new JButton();
        button4.setText("Button");
        panel1.add(button4);
        button3 = new JButton();
        button3.setText("Button");
        panel1.add(button3);
        button2 = new JButton();
        button2.setText("Button");
        panel1.add(button2);
        button1 = new JButton();
        button1.setText("Button");
        panel1.add(button1);
        btnIndent.setText("");
        panel1.add(btnIndent);
        final Spacer spacer2 = new Spacer();
        panel1.add(spacer2);
        button17 = new JButton();
        button17.setText("Button");
        mPanel.add(button17, new GridConstraints(2, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        button18 = new JButton();
        button18.setText("Button");
        mPanel.add(button18, new GridConstraints(3, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        mPanel.add(panel2, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        button12 = new JButton();
        button12.setText("Button");
        panel2.add(button12);
        button8 = new JButton();
        button8.setText("Button");
        panel2.add(button8);
        button9 = new JButton();
        button9.setText("Button");
        panel2.add(button9);
        button10 = new JButton();
        button10.setText("Button");
        panel2.add(button10);
        button11 = new JButton();
        button11.setText("Button");
        panel2.add(button11);
        button13 = new JButton();
        button13.setText("Button");
        panel2.add(button13);
        button14 = new JButton();
        button14.setText("Button");
        panel2.add(button14);
        button15 = new JButton();
        button15.setText("Button");
        panel2.add(button15);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mPanel;
    }
}
