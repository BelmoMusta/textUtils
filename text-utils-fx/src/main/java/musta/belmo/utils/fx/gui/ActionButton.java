package musta.belmo.utils.fx.gui;

import javafx.scene.control.Button;
import musta.belmo.utils.textutils.commons.Actions;


/**
 * Created by DELL on 06/08/2018.
 */
public class ActionButton extends Button {

    private Actions actions;


    public void setActions(Actions actions) {
        this.actions = actions;
    }

    public Actions getActions() {
        return actions;
    }


}
