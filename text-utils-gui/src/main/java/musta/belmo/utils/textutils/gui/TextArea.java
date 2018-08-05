package musta.belmo.utils.textutils.gui;

import musta.belmo.utils.textutils.commons.HighlightPosition;

import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;

public class TextArea extends JTextArea {

    TextArea inputText;
    private boolean fromSetter;
    private UndoManager undoManager;

    public TextArea() {
        super();

        undoManager = new UndoManager();
        Document doc = getDocument();
        doc.addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                undoManager.addEdit(e.getEdit());
            }
        });

        InputMap im = getInputMap(JComponent.WHEN_FOCUSED);
        ActionMap am = getActionMap();
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()), "undo");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()), "redo");
        am.put("undo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                undo();
            }
        });
        am.put("redo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redo();
            }
        });
    }

    public void redo() {
        try {
            if (undoManager.canRedo()) {
                undoManager.redo();
                if (fromSetter) {
                    undoManager.redo();
                    fromSetter = false;
                }
            }
        } catch (CannotUndoException exp) {
            exp.printStackTrace();
        }
    }

    public void undo() {
        try {
            if (undoManager.canUndo()) {
                undoManager.undo();
                if (fromSetter) {
                    undoManager.undo();
                    fromSetter = false;
                }
            }
        } catch (CannotUndoException exp) {
            exp.printStackTrace();
        }
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

    public void addHighlisghts(List<HighlightPosition> highlights) {
        Highlighter highlighter = super.getHighlighter();
        highlighter.removeAllHighlights();
        highlights.forEach(highlightPosition -> {
            try {
                highlighter.addHighlight(
                        highlightPosition.getStart(),
                        highlightPosition.getEnd(),
                        DefaultHighlighter.DefaultPainter);
            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }
        });
    }

    @Override
    public void setText(String t) {
        super.setText(t);
        fromSetter = true;
    }
}
