package com.karsonnichols.main;

import com.karsonnichols.model.replace.CreateFilePath;
import com.karsonnichols.model.replace.MakeChanges;
import com.karsonnichols.view.UserInterface;

public class Main {

    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface("Simple file copy");
        userInterface.build();

        // Users path to files need to be changed
        // this is recurise function that will reed all the sub files in directory
        // writes back to the file reads from
        /****SUB DIRECTORIES CANNOT BE TO DEEP AND WILL READ ALL THE FILES*****/

        // set false for testing gui
        if(false){
            // input directory it will loop over all sub directories and find the files within them
            CreateFilePath filePath = new CreateFilePath("/home/karson.nichols/Desktop/share/WebStorm/KHDEV-ACTIVE/_site/views/services/zzznewcards.php");
//            GetFilePath filePath = new GetFilePath("put path");

            if(filePath.getPaths().size() == 0){
                // searches a specific file
                new MakeChanges(filePath.getStartPath());
            }else {
                filePath.setPaths();
                // searches array of paths
                new MakeChanges(filePath.getPaths());
            }
        }

    }

}
