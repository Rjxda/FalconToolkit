package com.acharluk.falcontoolkit;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ACharLuk on 21/12/2014.
 */
public class Command {

    static String adb = "adb.exe";
    static String fastboot = "fastboot.exe";

    /* Main tab */
    public static String getDevices() {
        return send(adb, "devices");
    }

    /* Reboot tab */
    public static String rebootToFastboot() {
        send(adb, "reboot bootloader");
        return "Rebooted into fastboot\n";
    }
    public static String rebootToRecovery() {
        send(adb, "reboot recovery");
        return "Rebooted into recovery\n";
    }
    public static String rebootToNormalFromADB() {
        send(adb, "reboot");
        return "Rebooted into android\n";
    }
    public static String rebootToNormalFromFastboot() {
        send(fastboot, "reboot");
        return "Rebooted into android\n";
    }

    /* ADB tab */
    public static String ADBSideload(String path) {
        return send(adb,"sideload " + path);
    }
    public static String ADBPush(String path, String remotePath) {
        return send(adb,"push " + path + " " + remotePath);
    }

    /* Fastboot tab */

    /* Other tab */
    public static String killADBServer() {
        return send(adb, "kill-server");
    }
    public static String startADBServer() {
        return send(adb, "start-server");
    }

    /* Easy tab*/


    /**
     * sends a command through adb or fastboot and returns log
     * @param mode
     * @param arguments
     * @return
     */
    private static String send(String mode, String arguments) {
        String output = "";
        try {
            Process process = Runtime.getRuntime().exec("cmd /c res\\" + mode + " " + arguments);
            InputStream in = process.getInputStream();
            int ch;
            while((ch = in.read()) != -1) {
                output+=((char)ch);
            }
            output+="\n";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

}
