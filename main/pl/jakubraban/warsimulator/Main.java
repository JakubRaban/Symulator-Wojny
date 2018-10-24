package pl.jakubraban.warsimulator;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    public static Properties programProperties = new Properties();

    private static void setUpSettingsFile() throws IOException {
        File settingsFile = new File(System.getProperty("user.dir") + "/" + "War Simulator/settings.xml");
        if (settingsFile.exists()) {
            programProperties.loadFromXML(new FileInputStream(settingsFile));
        } else {
            settingsFile.getParentFile().mkdirs();
            programProperties.setProperty("getPlayersNames", "false");
            programProperties.setProperty("enumerateTurns", "false");
            programProperties.setProperty("printStatsOnGameEnd", "true");
            programProperties.setProperty("printGameToFile", "true");
            programProperties.storeToXML(new FileOutputStream
                    (System.getProperty("user.dir") + "/" + "War Simulator/settings.xml"), "");
        }
    }

    public static void gameMenu() throws IOException {
        Scanner sc = new Scanner(System.in);
        Gameplay gameplay;
        System.out.println("\n" + "--- SYMULATOR WOJNY ---");
        System.out.println("1 = Zagraj w wojnę");
        System.out.println("2 = Wyjdź");
        boolean validInput = false;
        while(!validInput) {
            System.out.print("Wybór = _" + "\b");
            String choice = sc.nextLine();
            if(choice.equals("1")) {
                System.out.println();
                validInput = true;
                gameplay = new Gameplay();
                gameplay.playTheGame();
            } else if(choice.equals("2")) System.exit(0);
        }
    }

    public static void main(String ... args) throws IOException {
        setUpSettingsFile();
        gameMenu();
    }

}
