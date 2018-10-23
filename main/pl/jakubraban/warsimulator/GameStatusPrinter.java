package pl.jakubraban.warsimulator;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

public class GameStatusPrinter {

    private PrintWriter printer;

    GameStatusPrinter() throws IOException {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        String filename = year + "-" + month + "-" + day + "_" + hour + "-" + minute + "-" + second + ".txt";
        File file = new File(System.getProperty("user.dir") + "/" + "War Simulator/games/" + filename);
        file.getParentFile().mkdirs();
        printer = new PrintWriter(file, "UTF-8");
    }

    void printToFileAndConsole(String text) {
        System.out.print(text);
        printer.print(text);
    }

    void close() {
        printer.close();
    }

}
