package com.karsonnichols.view;

import com.karsonnichols.model.replace.GetFilePath;
import com.karsonnichols.model.replace.MakeChanges;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GUI {
    JFrame mainFrame;
    JPanel mainPanel;

    String filePath;

    public GUI (String name){
        this.mainFrame = new JFrame(name);
        this.mainPanel  = new JPanel(new BorderLayout());
    }

    public void build() {

        // numbers add padding
        this.mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        // text
        JPanel nested = new JPanel(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        JLabel inputFile = new JLabel("Insert location of project", SwingConstants.CENTER);
        // black border
        inputFile.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2, true));

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;

        nested.add(inputFile, gridBagConstraints);

        Button findFile = new Button("Find File");

        FindProjectPath findProjectPath = new FindProjectPath();
        findFile.addActionListener(findProjectPath);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;

        nested.add(findFile, gridBagConstraints);

        this.mainPanel.add(BorderLayout.CENTER, nested);
        this.mainFrame.getContentPane().add(BorderLayout.CENTER, mainPanel);

        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setSize(1280, 780);
        this.mainFrame.setVisible(true);
    }

    public class FindProjectPath implements ActionListener {
        private String path;
        public void actionPerformed(ActionEvent event){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int option = fileChooser.showOpenDialog(mainFrame);
            if(option == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                String path = file.getAbsolutePath();

                GetFilePath filePath = new GetFilePath(path + "/");
                System.out.println(filePath.getStartPath());
                filePath.setPaths();

                if(filePath.getPaths().size() == 0){
                    // searches a specific file
                    new MakeChanges(filePath.getStartPath());
                }else {
                    // searches array of paths
                    new MakeChanges(filePath.getPaths());
                }


            }else{
                System.out.println("Could not open file");
            }
        }

    }

}