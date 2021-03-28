import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TreeTest {

    @Test
    public void shouldSetCorrectInitialCoordinates() {
        TreeParser treeParser = getTreeParser();
        Vertex root = treeParser.getRoot();
        Tree tree = new Tree(root, treeParser.getVertices(), treeParser.getEdges());

        Map<Integer, Integer> xCoordinates = new HashMap<>();
        xCoordinates.put(1, 0);
        xCoordinates.put(2, 0);
        xCoordinates.put(3, 1);
        xCoordinates.put(4, 0);
        xCoordinates.put(5, 1);
        xCoordinates.put(6, 1);
        xCoordinates.put(7, 2);

        Collection<Vertex> vertices = new ArrayList<>(treeParser.getVertices().values());
        boolean correctXCoordinates = isCorrectXCoordinates(xCoordinates, vertices);

        Map<Integer, Integer> yCoordinates = new HashMap<>();
        yCoordinates.put(1, 0);
        yCoordinates.put(2, 1);
        yCoordinates.put(3, 0);
        yCoordinates.put(4, 2);
        yCoordinates.put(5, 1);
        yCoordinates.put(6, 1);
        yCoordinates.put(7, 0);

        boolean correctYCoordinates = isCorrectYCoordinates(yCoordinates, vertices);

        assertTrue(correctXCoordinates && correctYCoordinates);
    }

    @Test
    public void shouldExpandTreeCorrectly() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        TreeParser treeParser = getTreeParser();
        Vertex root = treeParser.getRoot();
        Tree tree = new Tree(root, treeParser.getVertices(), treeParser.getEdges());

        Method m = tree.getClass().getDeclaredMethod("setFinalCoordinates");
        m.setAccessible(true);
        m.invoke(tree);

        Map<Integer, Integer> xCoordinates = new HashMap<>();
        xCoordinates.put(1, 0);
        xCoordinates.put(2, 0);
        xCoordinates.put(3, 200);
        xCoordinates.put(4, 0);
        xCoordinates.put(5, 100);
        xCoordinates.put(6, 200);
        xCoordinates.put(7, 300);

        Collection<Vertex> vertices = new ArrayList<>(treeParser.getVertices().values());
        boolean correctXCoordinates = isCorrectXCoordinates(xCoordinates, vertices);

        Map<Integer, Integer> yCoordinates = new HashMap<>();
        yCoordinates.put(1, 0);
        yCoordinates.put(2, 200);
        yCoordinates.put(3, 0);
        yCoordinates.put(4, 300);
        yCoordinates.put(5, 200);
        yCoordinates.put(6, 100);
        yCoordinates.put(7, 0);

        boolean correctYCoordinates = isCorrectYCoordinates(yCoordinates, vertices);

        assertTrue(correctXCoordinates && correctYCoordinates);
    }

    @Test
    void shouldTurnCorrectly() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        TreeParser treeParser = getTreeParser();
        Vertex root = treeParser.getRoot();
        Tree tree = new Tree(root, treeParser.getVertices(), treeParser.getEdges());

        Method m = tree.getClass().getDeclaredMethod("setFinalCoordinates");
        m.setAccessible(true);
        m.invoke(tree);

        m = tree.getClass().getDeclaredMethod("turnBy45Degrees");
        m.setAccessible(true);
        m.invoke(tree);

        assertEquals(0, root.getX());
        assertEquals(0, root.getY());

        assertEquals((int) (200 * 0.707), root.getRight().getX());
        assertEquals((int) (200 * 0.707), root.getRight().getY());
    }

    private boolean isCorrectXCoordinates(Map<Integer, Integer> expectedCoordinates, Collection<Vertex> vertices) {
        boolean correctXCoordinates = true;
        for (Vertex v : vertices) {
            correctXCoordinates = correctXCoordinates && (v.getX() == expectedCoordinates.get(v.getId()));
        }
        return correctXCoordinates;
    }

    private boolean isCorrectYCoordinates(Map<Integer, Integer> expectedCoordinates, Collection<Vertex> vertices) {
        boolean correctYCoordinates = true;
        for (Vertex v : vertices) {
            correctYCoordinates = correctYCoordinates && (v.getY() == expectedCoordinates.get(v.getId()));
        }
        return correctYCoordinates;
    }

    private TreeParser getTreeParser() {
        String filepath = "src/test/resources/graph.graphml";
        File file = new File(filepath);
        TreeParser treeParser = new TreeParser(file);
        treeParser.parse();
        return treeParser;
    }

}
