package com.karsonnichols.model.searchForChange;

public class SearchForChange {
    private String line;
    private String searchFor;
    private String changeTo;

    public SearchForChange (String line, String searchFor, String changeTo) {
        this.line = line;
        this.searchFor = searchFor;
        this.changeTo = changeTo;
    }

    public String getChange () {
        String trimmedLine = this.trim(this.line);

        // returns changed line
        return this.line.replace(trimmedLine, this.changeTo + "\n");
    }

    public boolean changeHappened() {

        // check to see if change happened
        if(this.line.contains(this.searchFor)){
            // call change
            return true;
        }
        return false;
    }

    public String trim(String untrimmed) {
        return untrimmed.trim();
    }
}
