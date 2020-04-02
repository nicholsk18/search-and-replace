import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class ReplaceLine {
    private String line;

    public ReplaceLine(String line){
        this.line = line;
    }

    public String getLine () {
        try (Scanner scanner = new Scanner(Paths.get("replace.csv").toAbsolutePath())){

            while (scanner.hasNextLine()){
                // scann line
                String line = scanner.nextLine();
                if(line.startsWith("//")){
                    continue;
                }

                // splits line in to two string one for what you searching for and what to replace the line with
                String[] parts = line.split(":");
                String searchFor = parts[0];
                String changeTo = parts[1];

                if(this.line.contains(searchFor)){
                    // trims string to match actual string and preserve white space
                    String trimmed = this.line.trim();
                    // replace the whole matched line
                    String changed = this.line.replace(trimmed,changeTo + "\n");

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

}
