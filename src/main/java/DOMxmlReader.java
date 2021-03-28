import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMxmlReader {

    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(100000, 50000);
        System.out.println(map.get(100000));

    }

    // создаем из узла документа объект Language
    private static Vertex getVertex(Node node) {
        Vertex v = new Vertex();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            v.setId(Integer.parseInt(element.getAttribute("id")));
        }

        return v;
    }

    // получаем значение элемента по указанному тегу
    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }

}
