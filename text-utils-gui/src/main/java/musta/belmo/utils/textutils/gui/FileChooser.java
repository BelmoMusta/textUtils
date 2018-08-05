package musta.belmo.utils.textutils.gui;

import musta.belmo.utils.textutils.commons.VoidConsumer;

import javax.swing.*;
import java.awt.*;

public class FileChooser extends JFileChooser {

    private Integer dialogRetValue;

    public FileChooser directoriesOnly() {
        setFileSelectionMode(DIRECTORIES_ONLY);
        return this;
    }

    public FileChooser filesOnly() {
        setFileSelectionMode(FILES_ONLY);
        return this;
    }

    public FileChooser filesAndDirectories() {
        setFileSelectionMode(FILES_AND_DIRECTORIES);
        return this;
    }

    public FileChooser asOpenDialog() {
        setDialogType(OPEN_DIALOG);
        return this;
    }

    public FileChooser asSaveDialog() {
        setDialogType(SAVE_DIALOG);
        return this;
    }

    @Override
    public int showDialog(Component parent, String approveButtonText) {
        dialogRetValue = super.showDialog(parent, approveButtonText);
        return dialogRetValue;
    }

    @Override
    public int showOpenDialog(Component parent) throws HeadlessException {
        dialogRetValue = super.showOpenDialog(parent);
        return dialogRetValue;
    }

    @Override
    public int showSaveDialog(Component parent) throws HeadlessException {
        dialogRetValue = super.showSaveDialog(parent);
        return dialogRetValue;
    }


    public boolean isApproved() {
        return dialogRetValue != null && dialogRetValue == APPROVE_OPTION;
    }

    public boolean isCanceled() {
        return dialogRetValue != null && dialogRetValue == CANCEL_OPTION;
    }

    public boolean hasErrors() {
        return dialogRetValue != null && dialogRetValue == ERROR_OPTION;
    }

    public FileChooser doWhenApproved(VoidConsumer consumer) {
        if (consumer != null && isApproved()) {
            consumer.perform();
        }
        return this;
    }

    public FileChooser doWhenCanceled(VoidConsumer consumer) {
        if (consumer != null && isCanceled()) {
            consumer.perform();
        }
        return this;
    }


    public FileChooser doWhenError(VoidConsumer consumer) {
        if (consumer != null && hasErrors()) {
            consumer.perform();
        }
        return this;
    }


}
