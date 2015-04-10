package downloaderAndParser;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


/**
 * @author Piotr Górak dnia 2015-04-10.
 */
public class getXMLLink {


    public String getLinkToFile(String url) {
        Document doc = null;
        String toReturn = "";
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements links = doc.select("a[href]:contains(xml)");

        for (Element link : links) {
            toReturn = print(link.attr("abs:href"), trim(link.text(), 35));
        }
        return toReturn;
    }

    private static String print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
        return (String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width - 1) + ".";
        else
            return s;
    }
}
