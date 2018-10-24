package pl.jakubraban.warsimulator;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {

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

    private static void setUpSettingsFile() throws IOException {
        if (settingsFile.exists()) programProperties.loadFromXML(new FileInputStream(settingsFile));
        else {
            settingsFile.getParentFile().mkdirs();
            for (int i = 0; i < propertyNames.length; i++) {
                programProperties.setProperty(propertyNames[i], Boolean.toString(propertyDefaults[i]));
            }
            programProperties.storeToXML(new FileOutputStream(settingsFile), "");
        }
    }

    private static void changeSettingsMenu() throws IOException {
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
                gameMenu();
            }
            changeSettingsMenu();
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

    public static void gameMenu() throws IOException {
        Scanner sc = new Scanner(System.in);
        Gameplay gameplay;
        System.out.println("\n" + "--- SYMULATOR WOJNY ---");
        System.out.println("1 = Zagraj w wojnę");
        System.out.println("2 = Wyjdź");
        System.out.println("3 = Ustawienia");
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
            else if(choice.equals("3")) {
                validInput = true;
                changeSettingsMenu();
            }
        }
    }

    public static void main(String ... args) throws IOException {
        setUpSettingsFile();
        gameMenu();
    }

}
