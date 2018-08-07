package musta.belmo.utils.fx.gui;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import musta.belmo.utils.textutils.commons.Actions;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by DELL on 06/08/2018.
 */
public class ActionButtonGroup extends HBox {


    public List<ActionButton> getButtons() {
        ObservableList<Node> children = super.getChildren();
        List<ActionButton> list = new ArrayList<>();
        for (Node child : children) {
            if (child.getClass() == ActionButton.class)
                list.add((ActionButton) child);
        }
        return list;
    }
}
