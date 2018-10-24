package pl.jakubraban.warsimulator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Settings {

    static File settingsFile = new File(System.getProperty("user.dir") + "/" + "War Simulator/settings.xml");
    public static Properties programProperties = new Properties();
    static String[] propertyNames = {"getPlayersNames", "enumerateTurns", "printGameToFile", "printStatsOnGameEnd"};
    static boolean[] propertyDefaults = {false, false, true, true};
    static String[] propertyDescriptions = {
            "Pobieraj imiona graczy na początku gry",
            "Numeruj tury",
            "Zapisuj gry do pliku",
            "Pokaż statystyki pod koniec gry"
    };

    static void setUpSettingsFile() throws IOException {
        if (settingsFile.exists()) programProperties.loadFromXML(new FileInputStream(settingsFile));
        else {
            settingsFile.getParentFile().mkdirs();
            for (int i = 0; i < propertyNames.length; i++) {
                programProperties.setProperty(propertyNames[i], Boolean.toString(propertyDefaults[i]));
            }
            programProperties.storeToXML(new FileOutputStream(settingsFile), "");
        }
    }

    static void changeSettingsMenu() throws IOException {
        System.out.println("\n" + "--- USTAWIENIA ---");
        for (int i = 0; i < propertyNames.length; i++) {
            System.out.print(propertyDescriptions[i] + ": " + settingStatus(propertyNames[i]) + " --- ");
            System.out.println((char) (i + 65) + " = " + getChangePropertyMessage(settingStatus(propertyNames[i])));
        }
        System.out.println("E = X = Wróć do menu");

        boolean validInput = false;
        Scanner sc = new Scanner(System.in);
        while(!validInput) {
            System.out.print("Wybór = _" + "\b");
            int choice = (int) (sc.nextLine().toUpperCase().charAt(0)) - 65;
            if(choice >= 0 && choice <= 3) {
                validInput = true;
                programProperties.setProperty(propertyNames[choice],
                        Boolean.toString(!Boolean.parseBoolean(programProperties.getProperty(propertyNames[choice]))));
                programProperties.storeToXML(new FileOutputStream(settingsFile), "");
            } else if(choice == 4 || choice == 24) {
                validInput = true;
                Main.gameMenu();
            }
            if(validInput) changeSettingsMenu();
        }
    }

    private static String settingStatus(String property) {
        if(programProperties
                .getProperty(property)
                .equals("true")) return "WŁĄCZONE";
        else return "WYŁĄCZONE";
    }

    private static String getChangePropertyMessage(String currentState) {
        if(currentState.equals("WŁĄCZONE")) return "Wyłącz";
        else return "Włącz";
    }

}
