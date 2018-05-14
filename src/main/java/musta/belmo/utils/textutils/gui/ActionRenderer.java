package musta.belmo.utils.textutils.gui;

import musta.belmo.utils.textutils.Actions;

import javax.swing.*;
import java.awt.*;

public class ActionRenderer extends JLabel implements
        javax.swing.ListCellRenderer<Actions> {

    public ActionRenderer() {
        setOpaque(true);
        setVerticalAlignment(CENTER);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Actions> list, Actions value, int index, boolean isSelected, boolean cellHasFocus) {

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        setText(value.getLabel());
        return this;
    }
}
