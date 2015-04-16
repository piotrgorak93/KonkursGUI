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
 * Obiekt parsuj�cy stron� i znajduj�cy link do pliku XML zawieraj�cego dane
 *
 * @author Piotr G�rak dnia 2015-04-10.
 */
class getXMLLink {
    /**
     * Funkcja pobieraj�ca link do pliku XML
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
     * Funkcja drukuj�ca pobrany link
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
     * Funkcja wycinaj�ca niepotrzebne znaki i wstawiaj�ca kropki do linku
     * @param s pobrany link
     * @param width liczba, do kt�rej ma by� przyci�ty
     * @return zwraca link
     */
    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width - 1) + ".";
        else
            return s;
    }
}