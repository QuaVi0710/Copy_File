/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import common.Library;
import view.Menu;

/**
 *
 * @author QuaVi
 */
public class Copy_File_Manager extends Menu {

    static String[] mc = {"Read File Configure", "Exit"};
    Library lib;

    public Copy_File_Manager() {
        super("Copy File Program", mc);
        lib = new Library();
    }

    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                lib.readConfig();
                break;
            case 2:
                System.exit(0);
        }
    }
}
