package com.karsonnichols.controller.userInterfaceActions;

import com.karsonnichols.model.CreateFilePath;
import com.karsonnichols.model.ExcludePath;
import com.karsonnichols.model.ReadFile;
import com.karsonnichols.model.WriteToSource;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class FindProjectPath implements ActionListener {
    private JFrame mainFrame;

    private JTextArea inputSearchFor;
    private JTextArea inputStringReplaceTo;
    private ArrayList<String> excludedPathsList;

    private JLabel inputFileText;

    private JCheckBox isLine;

    public FindProjectPath (JFrame mainFrame, JTextArea inputSearchFor, JTextArea inputStringReplaceTo, ArrayList<String> excludedPathsList, JLabel inputFileText, JCheckBox isLine) {
        this.mainFrame = mainFrame;
        this.inputSearchFor = inputSearchFor;
        this.inputStringReplaceTo = inputStringReplaceTo;
        this.excludedPathsList = excludedPathsList;
        this.inputFileText = inputFileText;
        this.isLine = isLine;
    }

    public void actionPerformed(ActionEvent event) {

        // opens file window
        JFileChooser fileChooser = new JFileChooser();
        // allows for files and directories to be selected
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int option = fileChooser.showOpenDialog(mainFrame);

        // check to make sure right action was preformed
        if (option == JFileChooser.APPROVE_OPTION) {
            // String to hold file data
            String afterLineRead = "";

            // gets file and turns it to string path
            File file = fileChooser.getSelectedFile();
            String userPathToFile = file.getAbsolutePath();

            // set the path
            CreateFilePath filePath = new CreateFilePath(userPathToFile, excludedPathsList);

            ReadFile readFile = new ReadFile();

            // if its file run as file
            // otherwise run though files in directory
            if (!file.isDirectory()) {

                String startPath = filePath.getStartPath();
                // make global so can write to file

                // set path for login
                readFile.setPath(startPath);
                // pass inputs
                String newStringFile = readFile.getNewFile(inputSearchFor, inputStringReplaceTo, isLine);

                WriteToSource writeToSource = new WriteToSource(newStringFile, startPath);
                writeToSource.writeFiles();

                // show user what path was changed
                inputFileText.setText(startPath);

            } else {
                filePath.setPaths();

                for (String path : filePath.getPaths()) {

                    // returns true if path is excluded
//                    ExcludePath excludePath = new ExcludePath(path, excludedPathsList);
//                    if (excludePath.isExcluded()) {
//                        continue;
//                    }

                    // set path for login
                    readFile.setPath(path);
                    // pass inputs
                    String newStringFile = readFile.getNewFile(inputSearchFor, inputStringReplaceTo, isLine);

                    WriteToSource writeToSource = new WriteToSource(newStringFile, path);
                    writeToSource.writeFiles();

                    // show user what path was changed ***Needs work***
                    // inputFileText.setText(filePath.getPaths() + "\n");
                }

            }
            this.inputSearchFor.setText("");
            this.inputStringReplaceTo.setText("");
            this.excludedPathsList.clear();

        } else {
            System.out.println("Could not open file");
        }
    }
}
