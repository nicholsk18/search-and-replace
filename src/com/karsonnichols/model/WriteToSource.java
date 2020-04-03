package com.karsonnichols.model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WriteToSource {
    private File readFrom;
    private File writeTo;

    public WriteToSource (File readFrom, File writeTo){
        this.readFrom = readFrom;
        this.writeTo = writeTo;
    }

    public void writeFiles(){
        FileWriter writer;

        try {
            writer = new FileWriter(this.writeTo);
            Scanner scanner = new Scanner(new FileReader(this.readFrom));
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
