package test;

/**
 * Mock test
 */

import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;


// test learning
// https://junit.org/junit4/javadoc/4.12/org/junit/rules/TemporaryFolder.html

public class CreateFilePathTest {

    @Rule
    private TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void testUsingTempFolder() throws IOException {
        File createdFile= folder.newFile("myfile.txt");
        File createdFolder= folder.newFolder("subfolder");
        // ...
    }

    @Test
    public void testMethod(){
        File tempFile = folder.newFile("myfile.txt");
        classUnderTest.changeFileToAnswer(file.getPath(), mediaType);
    }

}
