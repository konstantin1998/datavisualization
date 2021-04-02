import org.testng.annotations.Test;
import ru.mipt.parsing.TreeParser;
import ru.mipt.entities.Vertex;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
public class TreeParserTest {
    @Test
    void shouldParseFileCorrectly() {
        String filepath = "src/test/resources/graph.graphml";
        File file = new File(filepath);
        TreeParser treeParser = new TreeParser(file);
        treeParser.parse();
        Vertex root = treeParser.getRoot();
        boolean correctIdentifiers = (root.getId() == 1)
                && (root.getRight().getId() == 3)
                && (root.getLeft().getId() == 2)
                && (root.getRight().getRight().getId() == 7)
                && (root.getRight().getLeft().getId() == 6)
                && (root.getLeft().getLeft().getId() == 4)
                && (root.getLeft().getRight().getId() == 5);
        boolean correctParents = (root.getLeft().getLeft().getParent() == root.getLeft())
                && (root.getLeft().getRight().getParent() == root.getLeft())
                && (root.getRight().getRight().getParent() == root.getRight())
                && (root.getRight().getLeft().getParent() == root.getRight())
                && (root.getRight().getParent() == root)
                && (root.getLeft().getParent() == root);

        assertTrue(correctIdentifiers && correctParents);
    }
}
