package com.karsonnichols.model.replace;

/**
 * This class takes a path in
 * recursively checks for sub directories
 * puts all the files in to ArrayList
 * Returns the arrays list of all the paths in that directory and subdirectories
 * (!!!MIGHT HAVE STACKOVERFLOERROR IF DEEP ENOUGH!!!)
 */

import java.io.File;
import java.util.ArrayList;

public class GetFilePath {
    private String startPath;
    private String fileFolder;
    private ArrayList<String> paths;

    public GetFilePath(String startPath){
        this.startPath = startPath;
        this.fileFolder = "";
        this.paths = new ArrayList<>();
    }

    public String getStartPath () {
        return this.startPath;
    }

    public ArrayList<String> getPath(){
        File file = new File(this.startPath);
        if(file.isFile()){
            // no need to add to array
            return this.paths;
        }
        // check to see if path is already a file


        // list all the files in start path
        File[] files = file.listFiles();
        // this method adds file paths to array list
        findPath(files);

        // return new paths
        return this.paths;
    }

    // finds path to a file from main directory if it has subdirectories
    // uses recursion
    public void findPath(File[] files){

        // Checks to see if a file is a directory
        // if yes calls findPath again
        try{
            for(File file : files){
                if(file.isDirectory()){
                    System.out.println("Directory: " + file.getName());
                    // set folder for this path
                    // allows to get multiple file from same directory
                    this.fileFolder = file.getName();
                    // run findPath again to look in that folder
                    findPath(file.listFiles());

                    // clear the fileFolder after recursion is done
                    this.fileFolder = "";
                } else {

                    // if has a folder add folder to path
                    // other wise exclude the folder
                    if (!this.fileFolder.isEmpty()){
                        // is already has a folder go back inside
                        paths.add(this.startPath  + this.fileFolder + "/" + file.getName());
                    } else {
                        paths.add(this.startPath + file.getName());
                    }
                }
            }
        } catch (Exception ex){
            ex.printStackTrace();
            System.out.println("File was not found");
        }
    }

}