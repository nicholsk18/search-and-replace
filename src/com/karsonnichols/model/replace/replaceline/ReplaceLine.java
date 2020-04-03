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
        try (Scanner scanner = new Scanner(Paths.get("replace-line.csv").toAbsolutePath())){

            while (scanner.hasNextLine()){
                // scan line
                String line = scanner.nextLine();
                if(line.startsWith("//") || line.isEmpty()){
                    continue;
                }

                // splits line in to two string one for what you searching for and what to replace the line with
                splitLine(line);

                if(this.line.contains(this.searchFor)){
                    // trims string to match actual string and preserve white space
                    String trimmed = this.line.trim();
                    // replace the whole matched line
                    String changed = this.line.replace(trimmed,this.changeTo + "\n");

                    // log change
                    System.out.println("line changed from: " + trimmed + "\n to: " + changed.trim());

                    return changed;
                }

            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return this.line + "\n";
    }

    // splits line in two parts
    public void splitLine(String replaceLine){
        String[] parts = replaceLine.split(":");
        this.searchFor = parts[0];
        this.changeTo = parts[1];
    }

}
