package com.karsonnichols.main;

import com.karsonnichols.model.FilePath;
import com.karsonnichols.model.ReplaceLine;
import com.karsonnichols.model.WriteToSource;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Users path to files need to be changed
        // this is recurise function that will reed all the sub files in directory
        // writes back to the file reads from
        /****SUB DIRECTORIES CANNOT BE TO DEEP AND WILL READ ALL THE FILES*****/

        String userInputPath = "/home/karson.nichols/Desktop/share/WebStorm/KHDEV-ACTIVE/_site/views/services/";
        // needs testing
//        String userInputFile = Paths.get(input).toAbsolutePath();
        FilePath filePath = new FilePath(userInputPath);

        ArrayList<String> paths = filePath.getPaths();

        // copy file
        File copyFile = new File("copy.php");

        for(String file : paths){
            try{
                // create a writer obj
                FileWriter fileWriter = new FileWriter(copyFile);
                // scan current file
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

            // must be in the loop to keep the right copy for writing
            WriteToSource writeToSource = new WriteToSource(copyFile, new File(file));
            writeToSource.writeFiles();
        }

    }

}
