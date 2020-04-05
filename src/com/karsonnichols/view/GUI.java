package com.karsonnichols.view;

import com.karsonnichols.model.replace.GetFilePath;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GUI {
    JFrame mainFrame;
    JPanel mainPanel;

    private String filePath;

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
        findFile.addActionListener(new FindProjectPath());

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;

        nested.add(findFile, gridBagConstraints);

        this.mainPanel.add(BorderLayout.CENTER, nested);
        this.mainFrame.getContentPane().add(BorderLayout.CENTER, mainPanel);

        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setSize(1280, 780);
        this.mainFrame.setVisible(true);
    }

    class FindProjectPath implements ActionListener {
        private String filePath;
        public void actionPerformed(ActionEvent event){
            JFileChooser fileOpen = new JFileChooser();
            fileOpen.showOpenDialog(mainFrame);
            File myFile = fileOpen.getSelectedFile();
            this.filePath = myFile.getAbsolutePath();
        }
        public String getFilePath (){
            return this.filePath;
        }
    }

    public String getFile(){
        FindProjectPath path = new FindProjectPath();
        return path.getFilePath();
    }
}