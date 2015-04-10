package downloaderAndParser;


import org.w3c.dom.Document;

import java.util.HashMap;

/**
 * @author Piotr Górak dnia 2015-03-28.
 */
public class Controller {

    private HashMap<String, Document> tableMap = new HashMap<>();

    public Controller() {
        Document tabelaA = new DataDownloader().downloadData(new getXMLLink().getLinkToFile("http://www.nbp.pl/home.aspx?f=/kursy/kursya.html"));
        Document tabelaB = new DataDownloader().downloadData(new getXMLLink().getLinkToFile("http://www.nbp.pl/home.aspx?f=/kursy/kursyb.html"));
        Document tabelaC = new DataDownloader().downloadData(new getXMLLink().getLinkToFile("http://www.nbp.pl/home.aspx?f=/kursy/kursyc.html"));
        this.tableMap.put("Tabela A", tabelaA);
        this.tableMap.put("Tabela B", tabelaB);
        this.tableMap.put("Tabela C", tabelaC);

    }

    public HashMap<String, Document> getTableMap() {
        return tableMap;
    }

}
