package musta.belmo.utils.fx.gui;

import javafx.event.ActionEvent;

/**
 * Created by DELL on 06/08/2018.
 */
public class FXActionEvent extends ActionEvent {


    @Override
    public ActionButton getSource() {
        return (ActionButton) super.getSource();
    }
}
