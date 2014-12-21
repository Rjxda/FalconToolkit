package com.acharluk.falcontoolkit.GUI;

import com.acharluk.falcontoolkit.Command;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ACharLuk on 21/12/2014.
 */
public class FalconTGui extends JFrame{
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JTextArea tx;
    private JButton exitButton;
    private JButton ADBDevicesButton;
    private JTabbedPane tabbedPane2;
    private JButton normalADBButton;
    private JButton normalFastbootButton;
    private JButton fastbootButton;
    private JButton recoveryButton;

    public FalconTGui() {
        super("MGTool");
        setContentPane(mainPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loadMainButtons();
        loadRebootButtons();

        setVisible(true);
    }

    void loadMainButtons() {
        ADBDevicesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tx.append(Command.getDevices());
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    void loadRebootButtons() {
        normalADBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tx.append(Command.rebootToNormalFromADB());
            }
        });
        normalFastbootButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tx.append(Command.rebootToNormalFromFastboot());
            }
        });
        fastbootButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tx.append(Command.rebootToFastboot());
            }
        });
        recoveryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tx.append(Command.rebootToRecovery());
            }
        });
    }

}
