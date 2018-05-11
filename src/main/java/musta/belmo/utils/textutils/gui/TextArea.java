package musta.belmo.utils.textutils.gui;

import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class TextArea extends JTextArea {

    public TextArea() {
        super();

        UndoManager undoManager = new UndoManager();
        Document doc =  getDocument();
        doc.addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                undoManager.addEdit(e.getEdit());
            }
        });
        InputMap im =  getInputMap(JComponent.WHEN_FOCUSED);
        ActionMap am =  getActionMap();
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()), "Undo");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()), "Redo");
        am.put("Undo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (undoManager.canUndo()) {
                        undoManager.undo();
                    }
                } catch (CannotUndoException exp) {
                    exp.printStackTrace();
                }
            }
        });
        am.put("Redo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (undoManager.canRedo()) {
                        undoManager.redo();
                    }
                } catch (CannotUndoException exp) {
                    exp.printStackTrace();
                }
            }
        });

    }

    public TextArea(String text) {
        super(text);
    }

    public TextArea(int rows, int columns) {
        super(rows, columns);
    }

    public TextArea(String text, int rows, int columns) {
        super(text, rows, columns);
    }

    public TextArea(Document doc) {
        super(doc);
    }

    public TextArea(Document doc, String text, int rows, int columns) {
        super(doc, text, rows, columns);
    }
}
