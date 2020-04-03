/**
 * This class takes a path in
 * recursively checks for sub directories
 * puts all the files in to ArrayList
 * Returns the arrays list of all the paths in that directory and subdirectories (Might have StackOverfloError if deep enough)
 */


import java.io.File;
import java.util.ArrayList;

public class FilePath {
    private String startPath;
    private String fileFolder;
    private ArrayList<String> paths;

    public FilePath(String startPath){
        this.startPath = startPath;
        this.fileFolder = "";
        this.paths = new ArrayList<>();
    }

    public ArrayList<String> getPaths(){
        // list all the files in start path
        File[] files = new File(this.startPath).listFiles();
        // this method adds file paths to array list
        makePath(files);

        // return new paths
        return this.paths;
    }

    // Makes changes to files
    public void makePath(File[] files){

        for(File file : files){
            if(file.isDirectory()){
                System.out.println("Directory: " + file.getName());
                this.fileFolder = file.getName();
                makePath(file.listFiles());

                // clear the fileFolder from previous folder
                this.fileFolder = "";
            } else {

                // if has a folder add folder to path
                // other wise exclude the folder
                if (!this.fileFolder.isEmpty()){
                    paths.add(this.startPath  + this.fileFolder + "/" + file.getName());
                } else {
                    paths.add(this.startPath + file.getName());
                }
            }
        }
    }

}