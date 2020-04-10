package com.karsonnichols.model.searchForChange;

/**
 * This file searches a line for change
 * other two methods will also return the change in a new line
 * needs to be abstracted out?
 */

public class SearchForChange {
    private String line;
    private String searchFor;
    private String changeTo;

    public SearchForChange (String line, String searchFor, String changeTo) {
        this.line = line;
        this.searchFor = searchFor;
        this.changeTo = changeTo;
    }

    // if line change happen
    public String getLineChange () {
        String trimmedLine = this.line.trim();

        log();

        // returns changed line
        return this.line.replace(trimmedLine, this.changeTo);
    }

    // if item change happen
    public String getItemChange () {
        log();

        return this.line.replace(this.searchFor, this.changeTo);
    }

    public boolean changeHappened() {

        // check to see if change happened
        if(this.line.contains(this.searchFor)){
            // call change
            return true;
        }
        return false;
    }

    // log changes in a file
    public void log () {
        System.out.println("In line: " + this.line.trim());
        System.out.println("Changed from: " + this.searchFor);
        System.out.println("Changed to: " + this.changeTo);
    }
}
