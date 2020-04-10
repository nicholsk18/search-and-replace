package com.karsonnichols.main;

import com.karsonnichols.view.UserInterface;

public class Main {

    public static void main(String[] args) {

        // Everything happens in the gui
        // after button click it runs the code
        UserInterface userInterface = new UserInterface("Simple file copy");
        userInterface.build();
    }

}
