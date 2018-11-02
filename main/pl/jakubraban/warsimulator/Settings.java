package pl.jakubraban.warsimulator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Settings {

    public static final String GET_PLAYERS_NAMES = "getPlayersNames";
    public static final String ENUMERATE_TURNS = "enumerateTurns";
    public static final String PRINT_GAME_TO_FILE = "printGameToFile";
    public static final String PRINT_STATS_ON_GAME_END = "printStatsOnGameEnd";

    private static final File settingsFile = new File(System.getProperty("user.dir") + "/" + "War Simulator/settings.xml");
    private static final Properties programProperties = new Properties();
    private static final String[] propertyNames = {GET_PLAYERS_NAMES, ENUMERATE_TURNS, PRINT_GAME_TO_FILE, PRINT_STATS_ON_GAME_END};
    private static final boolean[] propertyDefaults = {false, false, true, true};
    private static final String[] propertyDescriptions = {
            "Pobieraj imiona graczy na początku gry",
            "Numeruj tury",
            "Zapisuj gry do pliku",
            "Pokaż statystyki pod koniec gry"
    };

    public Settings() throws IOException {
        setUpSettingsFile();
    }

    public boolean getSetting(String name) {
        return Boolean.parseBoolean(programProperties.getProperty(name));
    }

    void setUpSettingsFile() throws IOException {
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
            } else if(choice == 4 || choice == 23) {
                validInput = true;
                UI.gameMenu();
            }
            if(validInput) changeSettingsMenu();
        }
    }

    private static String settingStatus(String property) {
        if(programProperties
                .getProperty(property)
                .equals("true")) return "TAK";
        else return "NIE";
    }

    private static String getChangePropertyMessage(String currentState) {
        if(currentState.equals("TAK")) return "Wyłącz";
        else return "Włącz";
    }

}
