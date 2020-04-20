package com.karsonnichols.view;

/**
 * This is the GUI
 * on actionListener it will run all the code to make changes
 *
 * want to change to only run when user is ready
 * not when they select path
 */

import com.karsonnichols.controller.userInterfaceActions.ExcludeAddedPath;
import com.karsonnichols.controller.userInterfaceActions.FindProjectPath;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class UserInterface {
    private JFrame mainFrame;
    private JPanel mainPanel;

    private JTextArea inputSearchFor;
    private JTextArea inputStringReplaceTo;
    private JTextArea inputStringExclude;

    private JLabel inputFileText;
    private JTextArea skippedFiles;

    private JCheckBox isLine;

    
    public UserInterface(String name){
        this.mainFrame = new JFrame(name);
        this.mainPanel  = new JPanel(new BorderLayout());
    }

    public void build() {

        // numbers add padding
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        // text
        JPanel nested = new JPanel(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        // sets margin on top of the button
        gridBagConstraints.insets = new Insets(20, 0, 0, 0);

        // START BORDER STYLES
        // empty border for spacing
        EmptyBorder border = new EmptyBorder(10, 20, 5, 20);
        border.getBorderInsets();
        // line to create the outline of border
        LineBorder line = new LineBorder(Color.black, 2, true);
        // combine them together and put it in to label
        CompoundBorder compound = new CompoundBorder(line, border);

        // create a new font
        Font font = new Font("Verdana", Font.PLAIN, 20);

        // create buttons and labels
        JLabel inputSearchForText = new JLabel("What are you searching for: ", SwingConstants.CENTER);
        // ***grid bag needs to be above the file adding to nested to work correctly*** //
        // layout
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        nested.add(inputSearchForText, gridBagConstraints);

        inputSearchFor = new JTextArea("", 1, SwingConstants.CENTER);
        inputSearchFor.setColumns(10);
        inputSearchFor.setFont(font);
        // layout
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        nested.add(inputSearchFor, gridBagConstraints);

        JLabel inputStringReplaceToText = new JLabel("What do you want to replace with: ", SwingConstants.CENTER);
        // layout
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        nested.add(inputStringReplaceToText, gridBagConstraints);

        inputStringReplaceTo = new JTextArea("", 1, SwingConstants.CENTER);
        inputStringReplaceTo.setColumns(10);
        inputStringReplaceTo.setFont(font);
        // layout
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        nested.add(inputStringReplaceTo, gridBagConstraints);

        JLabel inputStringExcludeText = new JLabel("Excluded File/Folder (Include extension for file. Leave blank for none.)", SwingConstants.CENTER);
        // layout
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        nested.add(inputStringExcludeText, gridBagConstraints);

        inputStringExclude = new JTextArea("", 1, SwingConstants.CENTER);
        inputStringExclude.setColumns(10);
        inputStringExclude.setFont(font);
        // layout
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        nested.add(inputStringExclude, gridBagConstraints);

        // need to add spacing
        JButton addExcludedPath = new JButton("Add path");
        // layout
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        nested.add(addExcludedPath, gridBagConstraints);

        // skipped files
        skippedFiles = new JTextArea(1, SwingConstants.CENTER);
        skippedFiles.setEditable(false);
        // layout
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        nested.add(skippedFiles, gridBagConstraints);

        JLabel isFileText = new JLabel("Change the hole line? (Check for yes)", SwingConstants.CENTER);
        // layout
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        nested.add(isFileText, gridBagConstraints);

        isLine = new JCheckBox();
        // layout
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        nested.add(isLine, gridBagConstraints);


        inputFileText = new JLabel("Insert location of project", SwingConstants.CENTER);
        inputFileText.setBorder(compound);
        // layout
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        nested.add(inputFileText, gridBagConstraints);

        JButton findFile = new JButton("Find File");
        // layout
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        nested.add(findFile, gridBagConstraints);

        // adds paths that need to be excluded
        ExcludeAddedPath excludeAddedPath = new ExcludeAddedPath(inputStringExclude, skippedFiles);
        addExcludedPath.addActionListener(excludeAddedPath);

        // file button that starts the search
        findFile.addActionListener(new FindProjectPath(mainFrame, inputSearchFor, inputStringReplaceTo, excludeAddedPath.getExcludedPaths(), inputFileText, isLine));

        mainPanel.add(BorderLayout.CENTER, nested);
        mainFrame.getContentPane().add(BorderLayout.CENTER, mainPanel);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1280, 780);
        mainFrame.setVisible(true);

    }

}