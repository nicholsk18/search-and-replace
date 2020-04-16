package com.karsonnichols.controller.userInterfaceActions;

/**
 * This file creates a list of excluded paths
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ExcludeAddedPath implements ActionListener {
    private JTextArea skippedFiles;
    private JTextArea inputStringExclude;
    private ArrayList<String> excludedPaths;

    private double width;

    public ExcludeAddedPath(JTextArea inputStringExclude, JTextArea skippedFiles) {
        this.inputStringExclude = inputStringExclude;
        this.excludedPaths = new ArrayList<String>();
        this.skippedFiles = skippedFiles;
        this.width = 0;
    }

    public void actionPerformed(ActionEvent event) {
        this.excludedPaths.add(this.inputStringExclude.getText());
        showExcludedPaths();
        this.inputStringExclude.setText("");
    }

    public ArrayList<String> getExcludedPaths() {
        return this.excludedPaths;
    }

    private void showExcludedPaths() {
        this.width += this.skippedFiles.getText().length();
        System.out.println(this.width);
        System.out.println(this.skippedFiles.getText().length());
        if(this.width > 550) {
            this.skippedFiles.append("\n");
            this.width = 0;
            System.out.println(this.width);
        }
        this.skippedFiles.append(excludedPaths.get(excludedPaths.size()-1) + "; ");
    }
}
