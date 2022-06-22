package ui;

import model.EventLog;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            new GUI();
        } catch (FileNotFoundException exception) {
            System.out.println("Unable to run application: file not found.");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
