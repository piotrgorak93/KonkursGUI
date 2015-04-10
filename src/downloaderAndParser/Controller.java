package downloaderAndParser;


import org.w3c.dom.Document;

import java.util.HashMap;

/**
 * @author Piotr Górak dnia 2015-03-28.
 */
public class Controller {

    private HashMap<String, Document> tableMap = new HashMap<>();

    public Controller() {
        Document tabelaA = new DataDownloader().downloadData("http://www.nbp.pl/kursy/xml/a060z150327.xml");
        Document tabelaB = new DataDownloader().downloadData("http://www.nbp.pl/kursy/xml/b012z150325.xml");
        Document tabelaC = new DataDownloader().downloadData("http://www.nbp.pl/kursy/xml/c060z150327.xml");
        this.tableMap.put("Tabela A", tabelaA);
        this.tableMap.put("Tabela B", tabelaB);
        this.tableMap.put("Tabela C", tabelaC);

    }

    public HashMap<String, Document> getTableMap() {
        return tableMap;
    }

}
