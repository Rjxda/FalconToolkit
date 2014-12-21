package com.acharluk.falcontoolkit.GUI;

import com.acharluk.falcontoolkit.Command;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
    private JButton killADBButton;
    private JButton startADBServerButton;
    private JButton ADBSideloadButton;
    private JButton ADBPushButton;
    private JTextField textField1;
    private JButton eraseCacheButton;
    private JButton eraseUserdataButton;
    private JButton eraseModemst1Button;
    private JButton eraseModemst2Button;
    private JTabbedPane tabbedPane3;
    private JButton selectFileButton;
    private JTextField textField2;
    private JTextField textField3;
    private JButton flashButton;

    public FalconTGui() {
        super("MGTool");
        setContentPane(mainPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textField1.setText("/sdcard/");

        loadMainButtons();
        loadRebootButtons();
        loadADBButtons();
        loadFastbootButtons();
        loadOtherButtons();
        loadEasyButtons();

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

    void loadADBButtons() {
        ADBSideloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tx.append("Select zip to sideload\n");
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    tx.append(Command.ADBSideload(selectedFile.getAbsolutePath()));
                    tx.append("Sideload successful\n");
                } else {
                    tx.append("Sideload aborted\n");
                }

            }
        });

        ADBPushButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tx.append("Select file to push\n");
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    tx.append(Command.ADBPush(selectedFile.getAbsolutePath(), textField1.getText()));
                    tx.append("Push successful\n");
                } else {
                    tx.append("Push aborted\n");
                }

            }
        });
    }

    void loadFastbootButtons() {
        eraseCacheButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tx.append(Command.erase("cache"));
            }
        });
        eraseUserdataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tx.append(Command.erase("userdata"));
            }
        });
        eraseModemst1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tx.append(Command.erase("modemst1"));
            }
        });
        eraseModemst2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tx.append(Command.erase("modemst2"));
            }
        });

        selectFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tx.append("Select file to flash\n");
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    tx.append("Selected file:" + selectedFile.getName());
                    textField2.setText(selectedFile.getAbsolutePath());
                } else {
                    tx.append("Push aborted\n");
                }
            }
        });
        flashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tx.append(Command.flash(textField3.getText(), textField2.getText()));
            }
        });
    }

    void loadOtherButtons() {
        killADBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tx.append(Command.killADBServer());
            }
        });
        startADBServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tx.append(Command.startADBServer());
            }
        });
    }

    void loadEasyButtons() {

    }

}
