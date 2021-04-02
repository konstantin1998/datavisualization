import java.io.File;

public class Main {
    public static void main(String[] args) {
        File input = new File("src/main/resources/bigGrapg.graphml");
        TreeParser treeParser = new TreeParser(input);
        treeParser.parse();
        Tree tree = new Tree(treeParser.getRoot(), treeParser.getVertices(), treeParser.getEdges());
        Artist artist = new Artist(tree);
        artist.draw();
    }
}
