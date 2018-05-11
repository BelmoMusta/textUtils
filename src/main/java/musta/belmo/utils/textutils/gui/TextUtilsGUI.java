package musta.belmo.utils.textutils.gui;

import musta.belmo.utils.textutils.Actions;
import musta.belmo.utils.textutils.Functions;


import javax.swing.*;
import java.awt.*;
import java.util.stream.Stream;

public class TextUtilsGUI {
    private JComboBox<Actions> actionChoice;
    private JTextArea inputText;
    private JButton applyButton;
    private JPanel mPanel;
    private JTextField regexField;
    private JScrollPane mScrollPane;

      private TextUtilsGUI() {
        Stream.of(Actions.values()).forEach(actions ->
                actionChoice.addItem(actions));
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
                    case CAPITALIZE_EACH_WORDS:
                        inputText.setText(Functions.capitalizeEachWord(inputText.getText()));
                        break;
                    case DELETE:
                        String old = inputText.getText();
                        Functions.delete(old,regexField.getText());
                        inputText.setText(old.replaceAll(regexField.getText(), ""));
                        break;

                    case REDUCE_WHITE_SPACE:
                        String old_ = inputText.getText();
                        inputText.setText(Functions.reduceWhiteSpaces(old_));
                        break;

                }
            }
        });

        /*******************CTRL Z*************************/

        /********************************************/
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
        inputText = new TextArea();// TODO: place custom component creation code here;
    }
}
