/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import java.io.File;
import java.util.Scanner;
import model.Fileconfig;

/**
 *
 * @author QuaVi
 */
public class Validate {

    private Scanner SCANNER;

    public Scanner getScanner() {
        if (SCANNER == null) {
            SCANNER = new Scanner(System.in);
        }
        return SCANNER;
    }

    public String getYesNo(String title, String error) {
        String input;
        while (true) {
            System.out.println(title);
            input = getScanner().nextLine();
            if (input.matches("^[YyNn]")) {
                break;
            }
            System.err.println(error);
        }
        return input;
    }

    public String getFolder(String title) {
        String input;
        String pattern = "([a-zA-Z]:)?(\\\\[a-zA-Z0-9_.-]+)+\\\\?";
        while (true) {
            System.out.println(title);
            input = getScanner().nextLine();
            if (input.matches(pattern)) {
                break;
            }
        }
        return input;
    }

    public int getInt(String title, String error) {
        String input;
        while (true) {
            System.out.println(title);
            input = getScanner().nextLine();
            if (input.matches("^[\\d]+")) {
                break;
            }
            System.err.println(error);
        }
        return Integer.parseInt(input);
    }

    public String getString(String title) {
        String pattern = "[a-zA-Z0-9\\s]+";
        String input;
        while (true) {
            System.out.println(title);
            input = getScanner().nextLine();
            if (input.matches(pattern)) {
                break;
            } else {
                System.out.println("Invalid input");
            }
        }
        return input;
    }

    public static boolean checkConfig(Fileconfig config) {
        if (config.getCopyFolder().isEmpty()) {
            System.out.println("Folder Source is not input");
        }
        if (config.getDataType().isEmpty()) {
            System.out.println("Data type is not input");
        }
        if (config.getPath().isEmpty()) {
            System.out.println("Folder Destination is not input");
        }
        return config.getCopyFolder().isEmpty()
                && config.getDataType().isEmpty()
                && config.getPath().isEmpty();
    }

    public static boolean checkFolder(File source, File dest) {
        if (!source.exists() || !dest.isDirectory()) {
            System.err.println("Can't find folder Source");
        }
        if (!dest.exists() || !dest.isDirectory()) {
            System.err.println("Can't make folder Destination");
        }
        return source.exists() && source.isDirectory()
                && dest.exists() && dest.isDirectory();
    }
}
