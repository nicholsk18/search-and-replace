package com.karsonnichols.model.replace.replaceline;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * This file uses the replace.csv as source for changes
 * it replace the hole line where a change is found
 * otherwise writes back the line it took in
 */

public class ReplaceLine {
    private String line;

    private String searchFor;
    private String changeTo;

    public ReplaceLine(String line){
        this.line = line;
    }

    public String getLine () {
        // once get input from gui remove this
        try (Scanner scanner = new Scanner(Paths.get("replace-line.csv").toAbsolutePath())){

            // once get input from gui remove this
            while (scanner.hasNextLine()){
                // scan line
                String line = scanner.nextLine();
                if(line.startsWith("//") || line.isEmpty()){
                    continue;
                }

                // once get input from gui remove this
                this.splitLine(line);

            }
        } catch (IOException ex){
            ex.printStackTrace();
        }

        // set from gui to check if changing only line or item
        boolean isLine = false;
        return this.lineChange(this.line, this.searchFor, this.changeTo, isLine);
    }

    // once get input from gui remove this
    public void splitLine(String replaceLine){
        String[] parts = replaceLine.split(":");
        this.searchFor = parts[0];
        this.changeTo = parts[1];
    }

    public String lineChange (String line, String searchFor, String changeTo, boolean isLine) {
        if(line.contains(searchFor)){
            // trims string to match actual string and preserve white space
            String trimmed = this.trim(line);
            if(isLine){
                // replace the whole matched line
                String changed = line.replace(trimmed,changeTo + "\n");

                // log change
                System.out.println("line changed from: " + trimmed + "\n to: " + changed.trim());

                return changed;
            }

            // replace only the search term
            return line.replace(searchFor,changeTo + "\n");

        }

        return line + "\n";
    }

    public String trim(String untrimmed) {
        // just for debug output
        return untrimmed.trim();
    }

}
