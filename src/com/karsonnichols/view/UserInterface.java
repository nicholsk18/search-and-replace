package com.karsonnichols.view;

/**
 * This is the GUI
 * on actionListener it will run all the code to make changes
 *
 * want to change to only run when user is ready
 * not when they select path
 */

import com.karsonnichols.model.createPath.CreateFilePath;
import com.karsonnichols.model.writeFile.WriteToSource;
import com.karsonnichols.model.searchForChange.SearchForChange;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class UserInterface {
    JFrame mainFrame;
    JPanel mainPanel;

    JTextArea inputSearchFor;
    JTextArea inputStringReplaceTo;

    JCheckBox isLine;

    
    public UserInterface(String name){
        this.mainFrame = new JFrame(name);
        this.mainPanel  = new JPanel(new BorderLayout());
    }

    public void build() {

        // numbers add padding
        this.mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        // text
        JPanel nested = new JPanel(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        // sets margin on top of the button
        gridBagConstraints.insets = new Insets(20, 0, 0, 0);

        // START BORDER STYLES
        // empty border for spacing
        EmptyBorder border = new EmptyBorder(10, 20, 5, 20);
        border.getBorderInsets();
        // line to create the outline of border
        LineBorder line = new LineBorder(Color.black, 2, true);
        // combine them together and put it in to label
        CompoundBorder compound = new CompoundBorder(line, border);

        // create a new font
        Font font = new Font("Verdana", Font.PLAIN, 20);

        // create buttons and labels
        JLabel inputSearchForText = new JLabel("What are you searching for", SwingConstants.CENTER);
        // ***grid bag needs to be above the file adding to nested to work correctly*** //
        // layout
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        nested.add(inputSearchForText, gridBagConstraints);

        this.inputSearchFor = new JTextArea("", 1, SwingConstants.CENTER);
        inputSearchFor.setColumns(10);
        inputSearchFor.setFont(font);
        // layout
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        nested.add(this.inputSearchFor, gridBagConstraints);

        JLabel inputStringReplaceToText = new JLabel("What do you want to replace with", SwingConstants.CENTER);
        // layout
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        nested.add(inputStringReplaceToText, gridBagConstraints);

        this.inputStringReplaceTo = new JTextArea("", 1, SwingConstants.CENTER);
        inputStringReplaceTo.setColumns(10);
        inputStringReplaceTo.setFont(font);
        // layout
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        nested.add(this.inputStringReplaceTo, gridBagConstraints);


        JLabel isFileText = new JLabel("Change the hole line? (Check for yes)", SwingConstants.CENTER);
        // layout
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        nested.add(isFileText, gridBagConstraints);

        this.isLine = new JCheckBox();
        // layout
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        nested.add(this.isLine, gridBagConstraints);


        JLabel inputFileText = new JLabel("Insert location of project", SwingConstants.CENTER);
        inputFileText.setBorder(compound);
        // layout
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        nested.add(inputFileText, gridBagConstraints);

        JButton findFile = new JButton("Find File");
        // layout
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        nested.add(findFile, gridBagConstraints);


        // file button that starts the search
        findFile.addActionListener(new FindProjectPath());

        this.mainPanel.add(BorderLayout.CENTER, nested);
        this.mainFrame.getContentPane().add(BorderLayout.CENTER, mainPanel);

        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setSize(1280, 780);
        this.mainFrame.setVisible(true);

    }

    // look in to implementing outside instead of inner class
    public class FindProjectPath implements ActionListener {

        public void actionPerformed(ActionEvent event){
            // need to figure out how to pass this data
//            test1 = inputSearchFor.getText();
//            test2 = inputStringReplaceTo.getText();

            // opens file window
            JFileChooser fileChooser = new JFileChooser();
            // allows for files and directories to be selected
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int option = fileChooser.showOpenDialog(mainFrame);

            // check to make sure right action was preformed
            if(option == JFileChooser.APPROVE_OPTION){
                // String to hold file data
                String afterLineRead = "";

                // gets file and turns it to string path
                File file = fileChooser.getSelectedFile();
                String userPathToFile = file.getAbsolutePath();

                // set the path
                CreateFilePath filePath = new CreateFilePath(userPathToFile);

                // if its file run as file
                // otherwise run though files in directory
                if(!file.isDirectory()){

                    String startPath = filePath.getStartPath();

                    try(Scanner scanner = new Scanner(new FileReader(startPath))) {

                        while (scanner.hasNextLine()){
                            String line = scanner.nextLine();

                            SearchForChange searchForChange = new SearchForChange(line, inputSearchFor.getText(), inputStringReplaceTo.getText());
                            if(searchForChange.changeHappened()){

                                System.out.println("In file " + startPath);

                                if(isLine.isSelected()){
                                    afterLineRead += searchForChange.getLineChange() + "\n";
                                    continue;
                                }
                                afterLineRead += searchForChange.getItemChange() + "\n";
                                continue;
                            }

                            // returns old line w/o change
                            afterLineRead += line + "\n";

                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    WriteToSource writeToSource = new WriteToSource(afterLineRead, startPath);
                    writeToSource.writeFiles();

                } else {
                    filePath.setPaths();

                    for (String path : filePath.getPaths()){

                        try(Scanner scanner = new Scanner(new FileReader(path))) {
                            while (scanner.hasNextLine()){
                                String line = scanner.nextLine();

                                SearchForChange searchForChange = new SearchForChange(line, inputSearchFor.getText(), inputStringReplaceTo.getText());
                                if(searchForChange.changeHappened()){

                                    System.out.println("In file " + path);

                                    if(isLine.isSelected()){
                                        afterLineRead += searchForChange.getLineChange() + "\n";
                                        continue;
                                    }
                                    afterLineRead += searchForChange.getItemChange() + "\n";
                                    continue;
                                }

                                // returns old line w/o change
                                afterLineRead += line + "\n";

                                // if didnt happen write same line "line + "\n""
                            }
                            WriteToSource writeToSource = new WriteToSource(afterLineRead, path);
                            writeToSource.writeFiles();
                            afterLineRead = "";
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }

                }

            }else{
                System.out.println("Could not open file");
            }

            // clear input
            inputSearchFor.setText("");
            inputStringReplaceTo.setText("");
        }

    }

}