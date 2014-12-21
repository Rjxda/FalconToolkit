package com.acharluk.falcontoolkit;

import com.acharluk.falcontoolkit.GUI.FalconTGui;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        Thread thread = new Thread() {
            public void run() {
                new FalconTGui();
            }
        };
        thread.start();
        Command.rebootToFastboot();
    }

}
