package musta.belmo.utils.textutils.gui;

import musta.belmo.utils.textutils.commons.VoidConsumer;

import javax.swing.*;


/**
 * Created by mustabelmo on 13/05/2018.
 */
public class ActionButton extends JButton {
    private VoidConsumer actionToPerform ;

    public ActionButton() {
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

    public ActionButton(Icon icon) {
        super(icon);
    }

    public ActionButton(String text) {
        super(text);
    }

    public ActionButton(Action a) {
        super(a);
    }

    public ActionButton(String text, Icon icon) {
        super(text, icon);
    }
}
