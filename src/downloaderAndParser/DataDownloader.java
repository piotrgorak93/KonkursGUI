package downloaderAndParser;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

/** Główny kontroler odpowiedzialny za pobranie oraz przeparsowanie danych
 * @author Piotr Górak dnia 2015-03-28.
 */
class DataDownloader {
    private URL url;
    private Document doc;
    private DocumentBuilder db;

    /**
     * Funkcja łaczy się z adresem URL podanym w parametrze oraz pobiera z niego plik XML poprzez wywołanie funkcji retrieveDocument()
     * @param urlToParse przyjmuje adres URL w postaci Stringa
     * @return zwraca Document
     */
    public Document downloadData(String urlToParse) {

        url = connectToURL(urlToParse);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        db = createDocument(dbf);
        doc = retrieveDocument();

        return doc;
    }

    /**
     * Funkcja pobiera dane znajdujące się pod linkiem
     * @return Zwraca Document
     */
    private Document retrieveDocument() {
        try {
            doc = db.parse(url.openStream());
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            /**
             * Jeśli komputer nie jest podłączony do Internetu, to zostanie wyświetlony Alert z odpowiednią informacją.
             * Po jego zamknięciu program zostanie zamknięty.
             */
            System.err.println("Brak polaczenia");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("bin/icon.png"));
            alert.setTitle("Błąd");
            alert.setContentText("Problem z połączeniem sieciowym!");
            alert.showAndWait();
            System.exit(0);
        }catch (IOException e){
            e.printStackTrace();
            System.exit(0);
        }
        return doc;
    }

    /**
     * Funkcja tworzy Document za pomocą DocumentBuilder
     * @param dbf DocumentBuilderFactory
     * @return Zwraca obiekt DocumentBuilder
     */
    private DocumentBuilder createDocument(DocumentBuilderFactory dbf) {
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return db;
    }

    /**
     * Funkcja parsuje String do URL
     * @param urlToParse Adres URL w postaci String, który zostaje przeparsowany do URL
     * @return url Adres URL pliku docelowego
     */
    private URL connectToURL(String urlToParse) {
        try {
            url = new URL(urlToParse);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
}
