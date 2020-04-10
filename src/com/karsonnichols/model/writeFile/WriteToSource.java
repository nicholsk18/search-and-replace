package com.karsonnichols.model.writeFile;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * This file reads a copy that changes are located in
 * and uses that copy to write back to the file
 * where changes where intended to be made at
 */

public class WriteToSource {
    private String readFrom;
    private String writeTo;

    public WriteToSource (String readFrom, String writeTo){
        this.readFrom = readFrom;
        this.writeTo = writeTo;
    }

    public void writeFiles(){
        FileWriter writer;

        try {
//            writer = new FileWriter(this.writeTo);
            writer = new FileWriter("test.php");
            Scanner scanner = new Scanner(this.readFrom);
            while (scanner.hasNextLine()){
                writer.write(scanner.nextLine());
                writer.write("\n");
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
