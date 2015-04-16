package downloaderAndParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import sample.GuiController;


/**
 * @author Piotr Górak dnia 2015-03-28.
 */
public class DataParser {
    private final Document downloadedData;
    private final NodeList nList;
    private final GuiController controller;

    public DataParser(Document downloadedData, GuiController controller) {
        this.downloadedData = downloadedData;
        this.controller = controller;
        this.nList = downloadedData.getElementsByTagName("pozycja");
        this.controller.clearTextarea();
    }


    public String parseData(Boolean isTabelaC) {
        String resultToReturn = getTableName();
        if (isTabelaC) {
            resultToReturn += getRatingDate();
        }
        resultToReturn += getDate();
        resultToReturn += getTheValues(isTabelaC);
        return resultToReturn;
    }

    private String getHeader(String label, String elementToGet) {
        String dataToReturn = "";
        NodeList header = downloadedData.getElementsByTagName("tabela_kursow");
        for (int temp = 0; temp < header.getLength(); temp++) {
            Node node = header.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                dataToReturn = eElement.getElementsByTagName(elementToGet).item(0).getTextContent();
                System.out.println(label + " : " + dataToReturn);
                controller.setResult(label + " : " + dataToReturn);
            }
        }
        return dataToReturn;
    }

    private String getTableName() {
        return getHeader("Tabela", "numer_tabeli");
    }

    private String getDate() {
        return getHeader("Data publikacji", "data_publikacji");
    }

    private String getRatingDate() {
        return getHeader("Data notowania", "data_notowania");
    }


    private String getValue(String label, String elementToGet, int i) {
        String dataToReturn = "";

        Node nNode = nList.item(i);
        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) nNode;
            dataToReturn = eElement.getElementsByTagName(elementToGet).item(0).getTextContent();
            System.out.println(label + " : " + dataToReturn);
            controller.setResult(label + " : " + dataToReturn);
        }
        return dataToReturn;
    }

    private String getCurrency(int i) {
        controller.setResult("");
        return getValue("Waluta", "nazwa_waluty", i);
    }

    private String getConversion(int i) {
        return getValue("Przelicznik", "przelicznik", i);
    }

    private String getCurrencyCode(int i) {
        return getValue("Kod waluty", "kod_waluty", i);
    }

    private String getAverageExchangeRate(int i) {
        return getValue("Kurs średni", "kurs_sredni", i);
    }

    private String getPurchaseRate(int i) {
        return getValue("Kurs kupna", "kurs_kupna", i);
    }

    private String getSellRate(int i) {
        return getValue("Kurs sprzedaży", "kurs_sprzedazy", i);
    }

    private String getTheValues(Boolean isTabelaC) {
        String resultToReturn = "";
        System.out.println();
        for (int i = 0; i < nList.getLength(); i++) {
            resultToReturn += getCurrency(i);
            resultToReturn += getConversion(i);
            resultToReturn += getCurrencyCode(i);
            if (isTabelaC) {
                resultToReturn += getPurchaseRate(i);
                resultToReturn += getSellRate(i);
            } else
                resultToReturn += getAverageExchangeRate(i);
            System.out.println();
        }

        return resultToReturn;
    }
}
