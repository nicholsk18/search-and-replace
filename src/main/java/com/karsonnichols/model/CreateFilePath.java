package com.karsonnichols.model;

/**
 * This class takes a path in
 * recursively checks for sub directories
 * puts all the files in to ArrayList
 * Returns the arrays list of all the paths in that directory and subdirectories
 * (!!!MIGHT HAVE STACKOVERFLOERROR IF DEEP ENOUGH!!!)
 */

import java.io.File;
import java.util.ArrayList;

public class CreateFilePath {
    private String startPath;
    private String fileFolder;
    private ArrayList<String> paths;

    private ArrayList<String> excludeFolder;

    // need to be File to search dir
    // other one sets path as a String
    private File file;

    public CreateFilePath(String startPath, ArrayList<String> excludeFolder){
        this.startPath = startPath;
        this.excludeFolder = excludeFolder;
        this.fileFolder = "";
        this.paths = new ArrayList<String>();
        this.file = new File(this.startPath);
    }

    public String getStartPath () {
        return this.startPath;
    }

    public void setPaths() {
        // list all the files in start path
        File[] files = this.file.listFiles();
        // this method adds file paths to array list
        findPath(files);
    }

    public ArrayList<String> getPaths(){
        // return new paths
        return this.paths;
    }

    // finds path to a file from main directory if it has subdirectories
    // uses recursion
    private void findPath(File[] files){

        // Checks to see if a file is a directory
        // if yes calls findPath again
        try{
            for(File file : files){
                if(file.isDirectory()){
                    // exclude
                    if(this.excludeFolder.contains(file.getName())){
                        System.out.println("Folder excluded " + this.excludeFolder);
                        continue;
                    }

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
                        paths.add(this.startPath + "/" + this.fileFolder + "/" + file.getName());
                    } else if(this.excludeFolder.contains(file.getName())){
                        System.out.println("File excluded " + this.excludeFolder);
                        continue;
                    } else {
                        paths.add(this.startPath + "/" + file.getName());
                    }
                }
            }
        } catch (Exception ex){
            ex.printStackTrace();
            System.out.println("File was not found");
        }
    }

}