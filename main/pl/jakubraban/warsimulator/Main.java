package pl.jakubraban.warsimulator;

import java.io.*;

public class Main {

    public static Gameplay gameplay;

    public static void main(String ... args) throws IOException {
        Settings.setUpSettingsFile();
        UI.gameMenu();
    }

}
