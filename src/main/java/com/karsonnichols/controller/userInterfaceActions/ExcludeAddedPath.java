package com.karsonnichols.controller.userInterfaceActions;

/**
 * This file creates a list of excluded paths
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ExcludeAddedPath implements ActionListener {
    private JTextArea inputStringExclude;
    private ArrayList<String> excludedPaths;

    public ExcludeAddedPath(JTextArea inputStringExclude) {
        this.inputStringExclude = inputStringExclude;
        this.excludedPaths = new ArrayList<String>();
    }

    public void actionPerformed(ActionEvent event) {
        this.excludedPaths.add(this.inputStringExclude.getText());
        this.inputStringExclude.setText("");
    }

    public ArrayList<String> getExcludedPaths() {
        return this.excludedPaths;
    }
}
