package pl.jakubraban.warsimulator;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void setUpSettingsFile() throws IOException {
        File settingsFile = new File(System.getProperty("user.dir") + "/" + "War Simulator/.settings");
        settingsFile.getParentFile().mkdirs();
        PrintWriter writer = new PrintWriter(settingsFile, "UTF-8");
        writer.write("XD");
        writer.close(); // TODO no generalnie to całość tej metody
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
