package downloaderAndParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import sample.GuiController;


/** Kontroler odpowiedzialnych za przeparsowanie pobranych danych
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

    /**
     * Główna metoda, która wywołuje metody pobierające poszczególne części pliku XML
     * @param isTabelaC parametr ten decyduje, czy pobrane zostaną dane znajdujące się w węzłach "Kurs sprzedaży" oraz "Kurs kupna" zamiast "Kurs średni"
     * @return zwraca pobrane dane w postaci String
     */
    public String parseData(Boolean isTabelaC) {
        String resultToReturn = getTableName();
        if (isTabelaC) {
            resultToReturn += getRatingDate();
        }
        resultToReturn += getDate();
        resultToReturn += getTheValues(isTabelaC);
        return resultToReturn;
    }

    /**
     * Funkcja pobiera nagłówek pliku, tj. nazwę tabeli oraz datę notowania
     * @param label Opis pobranych danych np. "Data notowania"
     * @param elementToGet Określa czy pobrać nazwę czy datę
     * @return Zwraca dane w postaci String
     */
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

    /**
     * Funkcja wywołuje funkcję getHeader
     * @return zwraca numer tabeli
     */
    private String getTableName() {
        return getHeader("Tabela", "numer_tabeli");
    }

    /**
     * Funkcja wywołuje funkcję getHeader
     * @return zwraca datę publikacji
     */
    private String getDate() {
        return getHeader("Data publikacji", "data_publikacji");
    }

    /**
     * Funkcja wywołuje funkcję getHeader
     * @return zwraca datę notowania
     */
    private String getRatingDate() {
        return getHeader("Data notowania", "data_notowania");
    }

    /**
     *
     * @param label Etykieta prezentowanych danych
     * @param elementToGet Węzeł pliku XML
     * @param i Numer węzła
     * @return zwraca dane w wersji String
     */
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

    /**
     * Funkcja pobierająca nazwy waluty poprzez wywołanie funkcji getValue()
     * @param i Numer węzła
     * @return zwraca nazwy walut w postaci String
     */
    private String getCurrency(int i) {
        controller.setResult("");
        return getValue("Waluta", "nazwa_waluty", i);
    }

    /**
     * Funkcja pobierająca przelicznik waluty poprzez wywołanie funkcji getValue()
     * @param i Numer węzła
     * @return zwraca przelicznik (1 dla większości walut, lub 100 dla np. węgierskiego forinta) w postaci String
     */
    private String getConversion(int i) {
        return getValue("Przelicznik", "przelicznik", i);
    }

    /**
     * Funkcja pobierająca kod waluty poprzez wywołanie funkcji getValue()
     * @param i Numer węzła
     * @return zwraca kod waluty np. PLN w postaci String
     */
    private String getCurrencyCode(int i) {
        return getValue("Kod waluty", "kod_waluty", i);
    }

    /**
     * Funkcja pobierająca kurs średni danej waluty poprzez wywołanie funkcji getValue()
     * @param i Numer węzła
     * @return Kurs średni w postaci String
     */
    private String getAverageExchangeRate(int i) {
        return getValue("Kurs średni", "kurs_sredni", i);
    }
    /**
     * Funkcja pobierająca kurs kupna danej waluty poprzez wywołanie funkcji getValue()
     * @param i Numer węzła
     * @return Kurs kupna w postaci String
     */
    private String getPurchaseRate(int i) {
        return getValue("Kurs kupna", "kurs_kupna", i);
    }
    /**
     * Funkcja pobierająca kurs sprzedaży danej waluty poprzez wywołanie funkcji getValue()
     * @param i Numer węzła
     * @return Kurs sprzedaży w postaci String
     */
    private String getSellRate(int i) {
        return getValue("Kurs sprzedaży", "kurs_sprzedazy", i);
    }
    /**
     * Funkcja będąca główną pętlą, pobiera wszystkie dane od początku do końca pliku XML
     * @param isTabelaC parametr określający, czy pobieranę będą dane kursu sprzedaży oraz kupna zamiast kursu średniego
     * @return Kurs średni w postaci String
     */
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
