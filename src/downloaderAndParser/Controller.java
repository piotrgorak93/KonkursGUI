package downloaderAndParser;


import org.w3c.dom.Document;

import java.util.HashMap;

/**
 * Główny kontroler wywołujący DataDownloader() oraz wstawiający pobrany Document jako wartość do tabeli tableMap. Jako klucze zostały użyte nazwy tabeli podane jako String
 *
 * @author Piotr Górak dnia 2015-03-28.
 */
public class Controller {

    private final HashMap<String, Document> tableMap = new HashMap<>();

    public HashMap<String, Document> getTableMap() {
        return tableMap;
    }

    public void downloadData() {
        Document tabelaA = new DataDownloader().downloadData(new getXMLLink().getLinkToFile("http://www.nbp.pl/home.aspx?f=/kursy/kursya.html"));
        Document tabelaB = new DataDownloader().downloadData(new getXMLLink().getLinkToFile("http://www.nbp.pl/home.aspx?f=/kursy/kursyb.html"));
        Document tabelaC = new DataDownloader().downloadData(new getXMLLink().getLinkToFile("http://www.nbp.pl/home.aspx?f=/kursy/kursyc.html"));
        tableMap.put("Tabela A", tabelaA);
        tableMap.put("Tabela B", tabelaB);
        tableMap.put("Tabela C", tabelaC);
    }
}
