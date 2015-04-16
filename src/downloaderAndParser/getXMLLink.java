package downloaderAndParser;

import bin.AlertWindow;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


/**
 * Obiekt parsuj¹cy stronê i znajduj¹cy link do pliku XML zawieraj¹cego dane
 *
 * @author Piotr Górak dnia 2015-04-10.
 */
class getXMLLink {
    /**
     * Funkcja pobieraj¹ca link do pliku XML
     *
     * @param url Link do podstrony NBP w postaci String
     * @return zwraca link w postaci String
     */
    public String getLinkToFile(String url) {
        Document doc = null;
        String toReturn = "";
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            new AlertWindow().initialize();
            System.err.println("Brak polaczenia");
            System.exit(0);
        }
        Elements links = doc.select("a[href]:contains(xml)");

        for (Element link : links) {
            toReturn = print(link.attr("abs:href"), trim(link.text(), 35));
        }
        return toReturn;
    }

    /**
     * Funkcja drukuj¹ca pobrany link
     *
     * @param msg  Pobrany link
     * @param args Argument do funkcji format
     * @return zwraca link w postaci String
     */
    private static String print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
        return (String.format(msg, args));
    }

    /**
     * Funkcja wycinaj¹ca niepotrzebne znaki i wstawiaj¹ca kropki do linku
     * @param s pobrany link
     * @param width liczba, do której ma byæ przyciêty
     * @return zwraca link
     */
    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width - 1) + ".";
        else
            return s;
    }
}