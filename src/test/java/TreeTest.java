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
    public void shouldSetCorrectInitialCoordinates() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        TreeParser treeParser = getTreeParser();
        Vertex root = treeParser.getRoot();
        Tree tree = new Tree(root, treeParser.getVertices(), treeParser.getEdges());

        Class<?>[] paramTypes = new Class[] {Vertex.class};
        Method m = tree.getClass().getDeclaredMethod("setInitialCoordinates", paramTypes);
        m.setAccessible(true);
        Object[] args = new Object[1];
        args[0] = root;
        m.invoke(tree, args);

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

        Class<?>[] paramTypes = new Class[] {Vertex.class};
        Method m = tree.getClass().getDeclaredMethod("setInitialCoordinates", paramTypes);
        m.setAccessible(true);
        Object[] args = new Object[1];
        args[0] = root;
        m.invoke(tree, args);

        m = tree.getClass().getDeclaredMethod("setExpandedCoordinates");
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

        Class<?>[] paramTypes = new Class[] {Vertex.class};
        Method m = tree.getClass().getDeclaredMethod("setInitialCoordinates", paramTypes);
        m.setAccessible(true);
        Object[] args = new Object[1];
        args[0] = root;
        m.invoke(tree, args);

        m = tree.getClass().getDeclaredMethod("setExpandedCoordinates");
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

    @Test
    public void getCorrectLeftestNodeCoordinateWhenTreeWasTurned() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        TreeParser treeParser = getTreeParser();
        Vertex root = treeParser.getRoot();
        Tree tree = getTree(root, treeParser.getVertices(), treeParser.getEdges());

        assertEquals(tree.getLeftestCoord(), root.getLeft().getLeft().getX());
    }

    @Test
    public void getCorrectRightestNodeCoordinateWhenTreeWasTurned() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        TreeParser treeParser = getTreeParser();
        Vertex root = treeParser.getRoot();
        Tree tree = getTree(root, treeParser.getVertices(), treeParser.getEdges());

        assertEquals(tree.getRightestCoord(), root.getRight().getRight().getX());
    }

    @Test
    public void getCorrectHighestNodeCoordinateWhenTreeWasTurned() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        TreeParser treeParser = getTreeParser();
        Vertex root = treeParser.getRoot();
        Tree tree = getTree(root, treeParser.getVertices(), treeParser.getEdges());

        assertEquals(tree.getHighestCoord(), root.getY());
    }

    @Test
    public void getCorrectLowestNodeCoordinateWhenTreeWasTurned() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        TreeParser treeParser = getTreeParser();
        Vertex root = treeParser.getRoot();
        Tree tree = getTree(root, treeParser.getVertices(), treeParser.getEdges());

        assertEquals(tree.getLowestCoord(), root.getRight().getRight().getY());
    }

    @Test
    public void shouldShiftTreeCorrectly() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        TreeParser treeParser = getTreeParser();
        Vertex root = treeParser.getRoot();
        Tree tree = new Tree(root, treeParser.getVertices(), treeParser.getEdges());

        Class<?>[] paramTypes = new Class[] {Vertex.class};
        Method m = tree.getClass().getDeclaredMethod("setInitialCoordinates", paramTypes);
        m.setAccessible(true);
        Object[] args = new Object[1];
        args[0] = root;
        m.invoke(tree, args);

        tree.shift(1, 2);

        Map<Integer, Integer> xCoordinates = new HashMap<>();
        xCoordinates.put(1, 1);
        xCoordinates.put(2, 1);
        xCoordinates.put(3, 2);
        xCoordinates.put(4, 1);
        xCoordinates.put(5, 2);
        xCoordinates.put(6, 2);
        xCoordinates.put(7, 3);

        Collection<Vertex> vertices = new ArrayList<>(treeParser.getVertices().values());
        boolean correctXCoordinates = isCorrectXCoordinates(xCoordinates, vertices);



        Map<Integer, Integer> yCoordinates = new HashMap<>();
        yCoordinates.put(1, 2);
        yCoordinates.put(2, 3);
        yCoordinates.put(3, 2);
        yCoordinates.put(4, 4);
        yCoordinates.put(5, 3);
        yCoordinates.put(6, 3);
        yCoordinates.put(7, 2);

        boolean correctYCoordinates = isCorrectYCoordinates(yCoordinates, vertices);

        assertTrue(correctXCoordinates && correctYCoordinates);
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

    private Tree getTree(Vertex root, Map<Integer, Vertex> vertices, Collection<Edge> edges) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Tree tree = new Tree(root, vertices, edges);
        Class<?>[] paramTypes = new Class[] {Vertex.class};
        Method m = tree.getClass().getDeclaredMethod("setInitialCoordinates", paramTypes);
        m.setAccessible(true);
        Object[] args = new Object[1];
        args[0] = root;
        m.invoke(tree, args);

        m = tree.getClass().getDeclaredMethod("setExpandedCoordinates");
        m.setAccessible(true);
        m.invoke(tree);

        m = tree.getClass().getDeclaredMethod("turnBy45Degrees");
        m.setAccessible(true);
        m.invoke(tree);

        return tree;
    }

}
