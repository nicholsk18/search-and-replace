package com.karsonnichols.model;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ReadFile {
    private String path;

    public void setPath(String path) {
        this.path = path;
    }

    // gets line reads through it
    // passes info to a class that determines if change is needed
    public String getNewFile(JTextArea inputSearchFor, JTextArea inputStringReplaceTo, JCheckBox isLine){
        // line tha hold the hole file
        // used string for faster reading back?
        String afterLineRead = "";
        try(Scanner scanner = new Scanner(new FileReader(path))) {
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();

                // this searches and makes changes
                SearchForChange searchForChange = new SearchForChange(line, inputSearchFor.getText(), inputStringReplaceTo.getText());
                if(searchForChange.changeHappened()){

                    // logs out when path when change happens
                    System.out.println("In file " + this.path);

                    if(isLine.isSelected()){
                        // input change in to a line
                        afterLineRead += searchForChange.getLineChange() + "\n";
                        continue;
                    }
                    afterLineRead += searchForChange.getItemChange() + "\n";
                    continue;
                }

                // returns old line w/o change
                afterLineRead += line + "\n";
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not read file");
            e.printStackTrace();
        }
        return afterLineRead;
    }
}
