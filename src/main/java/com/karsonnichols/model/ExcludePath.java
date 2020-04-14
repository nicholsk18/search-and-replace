package com.karsonnichols.model;

/**
 * This file returns true if path needs to be excluded
 */

public class ExcludePath {
    private String path;
    private String excludePath;
    private String fileName;

    public ExcludePath (String path, String excludePath) {
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
        if(fileName.equals(excludePath)){
            System.out.println("Skipped " + fileName);
            return true;
        }
        return false;
    }
}
