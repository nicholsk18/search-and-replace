package com.karsonnichols.model.replace;

import com.karsonnichols.model.replace.replaceline.ReplaceLine;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Place where the actual changes happen
 */

public class MakeChanges {
    // copy file ***Check if String is better for temp storage***
    private File copyFile = new File("copy.php");
    private WriteToSource writeToSource;

    public MakeChanges(String path){
        searchForChange(path);
        // must be in the loop to keep the right copy for writing
        this.writeToSource= new WriteToSource(this.copyFile, new File(path));
        this.writeToSource.writeFiles();
    }

    public MakeChanges(ArrayList<String> paths){
        // loop over files in a path
        for(String file : paths){
            searchForChange(file);
            // must be in the loop to keep the right copy for writing
            this.writeToSource = new WriteToSource(this.copyFile, new File(file));
            this.writeToSource.writeFiles();
        }
    }

    // magic
    public void searchForChange(String file){

        try{
            // create a writer obj
            FileWriter fileWriter = new FileWriter(this.copyFile);
            // scan current file
            System.out.println(file);
            Scanner scanner = new Scanner(new FileReader(file));

            // loop over each line in file
            while (scanner.hasNextLine()){
                // variable that contains current line
                String line = scanner.nextLine();

                // new obj that replace line if match is found
                ReplaceLine replaceLine = new ReplaceLine(line);
                // place new line
                String newLine = replaceLine.getLine();
                // write new line <-line above and below can be combined->
                fileWriter.write(newLine);

            } // close while loop
            // Close file
            fileWriter.close();

        } catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
