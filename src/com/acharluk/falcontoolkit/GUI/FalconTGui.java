package com.acharluk.falcontoolkit.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ACharLuk on 21/12/2014.
 */
public class FalconTGui extends JFrame{
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JTextArea textArea1;
    private JButton exitButton;

    public FalconTGui() {
        super("MGTool");
        setContentPane(mainPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loadMainButtons();

        setVisible(true);

    }

    void loadMainButtons() {
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

}
