package com.karsonnichols.view;

import com.karsonnichols.model.replace.GetFilePath;
import com.karsonnichols.model.replace.MakeChanges;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
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

        // sets margin on top of the button
        gridBagConstraints.insets = new Insets(20, 0, 0, 0);

        JLabel inputFile = new JLabel("Insert location of project", SwingConstants.CENTER);

        // empty border for spacing
        EmptyBorder border = new EmptyBorder(10, 20, 5, 20);
        border.getBorderInsets();
        // line to create the outline of border
        LineBorder line = new LineBorder(Color.black, 2, true);
        // combine them together and put it in to label
        CompoundBorder compound = new CompoundBorder(line, border);
        inputFile.setBorder(compound);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;

        nested.add(inputFile, gridBagConstraints);

        JButton findFile = new JButton("Find File");

        findFile.addActionListener(new FindProjectPath());

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;

        nested.add(findFile, gridBagConstraints);

        this.mainPanel.add(BorderLayout.CENTER, nested);
        this.mainFrame.getContentPane().add(BorderLayout.CENTER, mainPanel);

        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setSize(1280, 780);
        this.mainFrame.setVisible(true);
    }

    // look in to implementing outside instead of inner class
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