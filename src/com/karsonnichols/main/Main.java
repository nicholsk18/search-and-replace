package com.karsonnichols.main;

import com.karsonnichols.model.replace.CreateFilePath;
import com.karsonnichols.view.UserInterface;

public class Main {

    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface("Simple file copy");
        userInterface.build();

        // Users path to files need to be changed
        // this is recurise function that will reed all the sub files in directory
        // writes back to the file reads from
        /****SUB DIRECTORIES CANNOT BE TO DEEP AND WILL READ ALL THE FILES*****/


    }

}
