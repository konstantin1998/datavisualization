import java.io.File;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMxmlReader {

    public static void main(String[] args) {
        File file = new File("src/main/resources/graph.graphml");
        TreeParser parser = new TreeParser(file);
        parser.parse();
        System.out.println(parser.getEdges());
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
