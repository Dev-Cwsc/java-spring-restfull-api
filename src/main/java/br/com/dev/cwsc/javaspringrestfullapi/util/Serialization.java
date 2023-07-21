package br.com.dev.cwsc.javaspringrestfullapi.util;

import br.com.dev.cwsc.javaspringrestfullapi.model.Device;
import br.com.dev.cwsc.javaspringrestfullapi.model.User;

import java.io.*;

public class Serialization {
    public static void serializeUser(User user) {
        try {
            FileOutputStream fileOut = new FileOutputStream("/tmp/user.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(user);
            out.close();
            fileOut.close();
            System.out.print("Serialized data is saved in /tmp/user.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void serializeDevice(Device device) {
        try {
            FileOutputStream fileOut = new FileOutputStream("/tmp/device.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(device);
            out.close();
            fileOut.close();
            System.out.print("Serialized data is saved in /tmp/device.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static User deserializeUser(String path) {
        User user = null;
        try {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            user = (User) in.readObject();
            in.close();
            fileIn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static Device deserializeDevice(String path) {
        Device device = null;
        try {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            device = (Device) in.readObject();
            in.close();
            fileIn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return device;
    }
}
