package com.karsonnichols.main;

import com.karsonnichols.model.replace.GetFilePath;
import com.karsonnichols.model.replace.MakeChanges;
import com.karsonnichols.view.GUI;

import java.io.File;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        GUI gui = new GUI("Simple file copy");
        gui.build();
        System.out.println(gui.getFilePath());;


//        GetFilePath myTestFilePath = new GetFilePath();

        MakeChanges changes;
        // Users path to files need to be changed
        // this is recurise function that will reed all the sub files in directory
        // writes back to the file reads from
        /****SUB DIRECTORIES CANNOT BE TO DEEP AND WILL READ ALL THE FILES*****/

        // set false for testing gui
        if(false){
            // input directory it will loop over all sub directories and find the files within them
            GetFilePath filePath = new GetFilePath("/home/karson.nichols/Desktop/share/WebStorm/KHDEV-ACTIVE/_site/views/services/zzznewcards.php");

            if(filePath.getPaths().size() == 0){
                // searches a specific file
                new MakeChanges(filePath.getStartPath());
            }else {
                // searches array of paths
                new MakeChanges(filePath.getPaths());
            }
        }

    }

}
