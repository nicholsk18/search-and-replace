package com.karsonnichols.model.readFile;

import com.karsonnichols.model.searchForChange.SearchForChange;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ReadFile {
    private String path;

    public void setPath(String path) {
        this.path = path;
    }

    public String getNewFile(JTextArea inputSearchFor, JTextArea inputStringReplaceTo, JCheckBox isLine){
        String afterLineRead = "";
        try(Scanner scanner = new Scanner(new FileReader(path))) {
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();

                SearchForChange searchForChange = new SearchForChange(line, inputSearchFor.getText(), inputStringReplaceTo.getText());
                if(searchForChange.changeHappened()){

                    System.out.println("In file " + this.path);

                    if(isLine.isSelected()){
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
            e.printStackTrace();
        }
        return afterLineRead;
    }
}
