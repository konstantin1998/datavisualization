import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.*;

public class TreeParser {
    private final Map<Integer, Vertex> vertices;
    private final List<Edge> edges;
    private  Vertex root;
    private final File graphFile;
    public TreeParser(File file) {
        graphFile = file;
        vertices = new HashMap<>();
        edges = new ArrayList<>();
    }
    public void parse() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(graphFile);
            document.getDocumentElement().normalize();

            parseVertices(document);
            parseEdges(document);
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        root = findRoot();
    }
    private void parseVertices(Document document) {
        NodeList nodeList = document.getElementsByTagName("node");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Vertex v = getVertex(nodeList.item(i));
            vertices.put(v.getId(), v);
        }
    }

    private void parseEdges(Document document) {
        NodeList edges = document.getElementsByTagName("edge");
        for (int i = 0; i < edges.getLength(); i++) {
            Edge edge = getEdge(edges.item(i));
            Vertex source = vertices.get(edge.getSource());
            Vertex target = vertices.get(edge.getTarget());
            String direction = edge.getDirection();
            target.setParent(source);
            if(direction.equals("right")) {
                source.setRight(target);
            } else {
                source.setLeft(target);
            }
            this.edges.add(edge);
        }
    }

    private Vertex getVertex(Node node) {
        Vertex v = new Vertex();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            v.setId(Integer.parseInt(element.getAttribute("id")));
        }
        return v;
    }

    private Edge getEdge(Node node) {
        Edge edge = new Edge();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            edge.setSource(Integer.parseInt(element.getAttribute("source")));
            edge.setTarget(Integer.parseInt(element.getAttribute("target")));
            edge.setDirection(element.getAttribute("direction"));
        }
        return edge;
    }

    private Vertex findRoot() {
        for(Map.Entry<Integer, Vertex> entry : vertices.entrySet()) {
            Vertex v = entry.getValue();
            if (v.getParent() == null) {
                return v;
            }
        }
        return null;
    }

    public Vertex getRoot() {
        return root;
    }

    public Map<Integer, Vertex> getVertices() {
        return vertices;
    }
    public Collection<Edge> getEdges() {
        return edges;
    }
}
