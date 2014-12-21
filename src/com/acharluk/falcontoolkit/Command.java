package com.acharluk.falcontoolkit;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ACharLuk on 21/12/2014.
 */
public class Command {

    static String adb = "adb.exe";
    static String fastboot = "fastboot.exe";

    public static String rebootToFastboot() {
        send(adb, "reboot bootloader");
        return "Rebooted into fastboot";
    }

    public static String rebootToRecovery() {
        send(adb, "reboot recovery");
        return "Rebooted into recovery";
    }

    public static String rebootToNormalFromADB() {
        send(adb, "reboot");
        return "Rebooted into android";
    }

    public static String rebootToNormalFromFastboot() {
        send(fastboot, "reboot");
        return "Rebooted into android";
    }
    public static String getDevices() {
        return send(adb, "devices");
    }

    private static String send(String mode, String arguments) {
        String output = "";
        try {
            Process process = Runtime.getRuntime().exec("cmd /c res\\" + mode + " " + arguments);
            InputStream in = process.getInputStream();
            int ch;
            while((ch = in.read()) != -1) {
                output+=((char)ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

}
