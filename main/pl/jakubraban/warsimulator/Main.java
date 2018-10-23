package pl.jakubraban.warsimulator;

import java.io.IOException;
import java.util.Scanner;

public class Main {

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
        gameMenu();
    }

}
