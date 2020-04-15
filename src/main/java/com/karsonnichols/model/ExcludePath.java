package com.karsonnichols.model;

import java.util.ArrayList;

/**
 * This file returns true if path needs to be excluded
 */

public class ExcludePath {
    private String path;
    private ArrayList<String> excludePath;
    private String fileName;

    public ExcludePath (String path, ArrayList<String> excludePath) {
        this.path = path;
        this.excludePath = excludePath;
        this.fileName = "";
    }

    public boolean isExcluded () {
        // Gets the name of the file
        int endOfPathIndex = this.path.lastIndexOf("/") + 1;

        for(int i = endOfPathIndex; i < this.path.length(); i++){
            fileName += path.charAt(i);
        }

        // Checks to see if needs to be excluded
        if(this.excludePath.contains(this.fileName)){
            System.out.println("Skipped " + fileName);
            return true;
        }
        return false;
    }
}
