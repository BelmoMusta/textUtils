package musta.belmo.utils.textutils.gui;

import musta.belmo.utils.textutils.VoidConsumer;

import javax.swing.*;


/**
 * Created by mustabelmo on 13/05/2018.
 */
public class SaveAsButton extends JButton {
    private VoidConsumer actionToPerform ;

    public SaveAsButton() {
        super();
        addActionListener(event->{
            if(actionToPerform!=null) {
                actionToPerform.perform();
            }
        });
    }

    public void setActionToPerform(VoidConsumer actionToPerform) {
        this.actionToPerform = actionToPerform;
    }

    public SaveAsButton(Icon icon) {
        super(icon);
    }

    public SaveAsButton(String text) {
        super(text);
    }

    public SaveAsButton(Action a) {
        super(a);
    }

    public SaveAsButton(String text, Icon icon) {
        super(text, icon);
    }
}
