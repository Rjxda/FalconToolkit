package com.acharluk.falcontoolkit.GUI;

import com.acharluk.falcontoolkit.Command;
import com.acharluk.falcontoolkit.Main;
import com.acharluk.falcontoolkit.Reference;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

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
    private JButton eraseButton;
    private JTabbedPane tabbedPane3;
    private JButton selectFileButton;
    private JTextField textField2;
    private JButton flashButton;
    private JButton factoryResetButton;
    private JComboBox comboBox1;
    private JComboBox downloadComboBox;
    private JButton downloadButton;
    private JButton downloadAndFlashButton;
    private JComboBox eraseComboBox;
    private JButton installAPKButton;
    private JButton selectImageButton;
    private JTextField bootImageTextField;
    private JButton bootImageButton;
    private JButton rootButton;
    private JTabbedPane tabbedPane4;

    public FalconTGui() {
        super("FalconToolkit " + Reference.VERSION);
        setContentPane(mainPanel);
        requestFocus();
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textField1.setText("/sdcard/");
        log("FalconToolkit " + Reference.VERSION);

        loadMainButtons();
        loadRebootButtons();
        loadADBButtons();
        loadFastbootButtons();
        loadOtherButtons();
        loadEasyButtons();
        loadDownloadButtons();

        setVisible(true);


    }

    void loadMainButtons() {
        ADBDevicesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log(Command.getDevices());
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
                log(Command.rebootToNormalFromADB());
            }
        });
        normalFastbootButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log(Command.rebootToNormalFromFastboot());
            }
        });
        fastbootButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log(Command.rebootToFastboot());
            }
        });
        recoveryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log(Command.rebootToRecovery());
            }
        });
    }

    void loadADBButtons() {
        ADBSideloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log("Select zip to sideload");
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    log(Command.ADBSideload(selectedFile.getAbsolutePath()));
                    log("Sideload successful");
                } else {
                    log("Sideload aborted");
                }

            }
        });

        ADBPushButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log("Select file to push");
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    log(Command.ADBPush(selectedFile.getAbsolutePath(), textField1.getText()));
                    log("Push successful");
                } else {
                    log("Push aborted");
                }

            }
        });
    }

    void loadFastbootButtons() {
        eraseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log(Command.erase(eraseComboBox.getSelectedItem().toString()));
            }
        });

        selectFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log("Select file to flash");
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    log("Selected file:" + selectedFile.getName());
                    textField2.setText(selectedFile.getAbsolutePath());
                } else {
                    log("Flash aborted");
                }
            }
        });
        flashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log(Command.flash(comboBox1.getSelectedItem().toString(), textField2.getText()));
            }
        });
        selectImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    log("Selected file:" + selectedFile.getName());
                    bootImageTextField.setText(selectedFile.getAbsolutePath());
                } else {
                    log("Boot aborted");
                }
            }
        });
        bootImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log(Command.rebootToFastboot());
                log("Booting selected image");
                Command.boot(bootImageTextField.getText());
            }
        });
    }

    void loadOtherButtons() {
        killADBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log(Command.killADBServer());
            }
        });
        startADBServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log(Command.startADBServer());
            }
        });
    }

    void loadEasyButtons() {
        factoryResetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogResult = JOptionPane.showConfirmDialog(null, "This will wipe ALL YOUR DATA AND CACHE!", "Warning", JOptionPane.YES_NO_OPTION);
                if(dialogResult == JOptionPane.YES_OPTION) {
                    log("Rebooting into fastboot...");
                    log(Command.rebootToFastboot());

                    log("Wiping data...");
                    log(Command.erase("userdata"));
                    log("Data wiped.");

                    log("Wiping cache...");
                    log(Command.erase("cache"));
                    log("Cache wiped.");

                    log("Rebooting...");
                    log(Command.rebootToNormalFromFastboot());
                } else {
                    log("Factory reset aborted.");
                }
            }
        });
        installAPKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log("Select APK to install");
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    log("Installing:" + selectedFile.getName());
                    log(Command.installAPK(selectedFile.getAbsolutePath()));
                } else {
                    log("Installation aborted");
                }
            }
        });
        rootButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                downloadFile(Reference.CFRoot, "CF-Auto-Root-falconumts-falconreteu-xt1032.zip");
                log(Command.rebootToFastboot());
                log("Wait for download to finish and then");
                log("extract zip and run root-windows.bat");
            }
        });
    }

    void loadDownloadButtons() {
        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                downloadRecovery(downloadComboBox.getSelectedItem().toString());
            }
        });
        downloadAndFlashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                downloadRecovery(downloadComboBox.getSelectedItem().toString());

                Command.rebootToFastboot();
                log("Flashing recovery...");
                Command.flash("recovery", downloadComboBox.getSelectedItem().toString() + ".img");
                log("Flash successful, now enter recovery!");
            }
        });
    }

    void downloadRecovery(String selected) {
        if (selected.equals("CWM")) {
            downloadFile(Reference.CWM_dl, selected + ".img");
            return;
        }
        if (selected.equals("CWM Touch")) {
            downloadFile(Reference.CWM_Touch_dl, selected + ".img");
            return;
        }
        if (selected.equals("Philz")) {
            downloadFile(Reference.Philz_dl, selected + ".img");
            return;
        }
        if (selected.equals("TWRP")) {
            downloadFile(Reference.TWRP_dl, selected + ".img");
            return;
        }
        if (selected.equals("TWRP GPE")) {
            downloadFile(Reference.TWRP_GPE_dl, selected + ".img");
            return;
        }
    }

    void downloadFile(String fileUrl, String fileName) {
        Thread dlThread = new Thread() {
            @Override
            public void run() {
                URL website = null;
                        try {
                            website = new URL(fileUrl);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        try {
                            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
                            FileOutputStream fos = new FileOutputStream(fileName);
                            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                            fos.close();
                            log("Download finished");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
            }
        };
        log("Downloading from:" + fileUrl);
        log("Please wait until download finishes...");
        dlThread.start();
    }

    void log(String msg) {
        tx.append(msg + "\n");
    }
    
}
