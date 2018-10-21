
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

public class GameStatusPrinter {

    private String filename;
    private PrintWriter printer;

    GameStatusPrinter() throws IOException {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        filename = year + "-" + month + "-" + day + "_" + hour + "-" + minute + "-" + second;
        printer = new PrintWriter(filename, "UTF-8"); // TODO godzina w formacie 24h
    }

    void printToFileAndConsole(String text) {
        System.out.print(text);
        printer.print(text); // TODO printer nie drukuje całego tekstu do pliku
    }

}
