/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.Scanner;
import model.Fileconfig;

/**
 *
 * @author QuaVi
 */
public class Library {

    private final String pathConfig = "src//fileConfig//config.properties";
    private final File file = new File(pathConfig);

    Validate valid = new Validate();

    public void readConfig() {
        Properties properties = new Properties();
        if (file.exists()) {
            try {
                FileReader fileReader = new FileReader(file);
                properties.load(fileReader);
                Fileconfig config = new Fileconfig(properties.getProperty("COPY_FOLDER"), properties.getProperty("DATA_TYPE"), properties.getProperty("PATH"));
                if (Validate.checkConfig(config)) {
                    copyFolder(config);
                } else {
                    System.out.println("System shutdown");
                }
                copyFolder(config);
                fileReader.close();
            } catch (Exception e) {
                System.out.println("File Configure is not found!");
            }
        } else {
            createFileConfig();
            readConfig();
        }
    }

    public Fileconfig createConfig() {
        String copyFolder = valid.getFolder("COPY_FOlDER: ");
        String dataType = valid.getString("DATA_TYPE: ");
        String path = valid.getFolder("PATH: ");
        return new Fileconfig(copyFolder, dataType, path);
    }

    public void createFileConfig() {
        Properties properties = new Properties();
        FileOutputStream fileoutputStream = null;
        Fileconfig config = createConfig();
        try {
            fileoutputStream = new FileOutputStream(pathConfig);
            properties.setProperty("COPY_FOLDER", config.getCopyFolder());
            properties.setProperty("DATA_TYPE", config.getDataType());
            properties.setProperty("PATH", config.getPath());
            properties.store(fileoutputStream, null);
            System.out.println("Created File");
        } catch (IOException ex) {
            System.out.println("Cannot create Config File");
            System.out.println("System shutdown");
        } finally {
            if (fileoutputStream != null) {
                try {
                    fileoutputStream.close();
                } catch (Exception e) {
                    System.err.println("File cannot create");
                    System.out.println("System shutdown");
                }
            }
        }
    }

    public void copyFile(String file, String folder) {
        File f1 = new File(file);
        File f2 = new File(folder);
        if (f1.exists() && f1.isFile() && f2.exists() && f2.isDirectory()) {
            try {
                FileInputStream fis = new FileInputStream(f1);
                FileOutputStream fos = new FileOutputStream(folder + "/" + f1.getName());
                int b;
                while ((b = fis.read()) != -1) {
                    fos.write(b);
                }
                fis.close();
                fos.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void copyFolder(Fileconfig config) {
        File sourceFolder = new File(config.getCopyFolder());
        File dest = new File(config.getPath());
        if (Validate.checkFolder(sourceFolder, dest)) {
            File[] listOfFiles = sourceFolder.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    copyFile(config.getCopyFolder() + "\\" + listOfFiles[i].getName(), config.getPath());
                    System.out.println("File name: " + listOfFiles[i].getName());
                }
            }
        } else {
            System.err.println("System shutdown");
        }
    }
}
