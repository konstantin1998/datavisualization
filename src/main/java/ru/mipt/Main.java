package ru.mipt;

import ru.mipt.drawing.Artist;
import ru.mipt.entities.Tree;
import ru.mipt.parsing.TreeParser;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File input = new File("src/main/resources/graph.graphml");
        TreeParser treeParser = new TreeParser(input);
        treeParser.parse();
        Tree tree = new Tree(treeParser.getRoot(), treeParser.getVertices(), treeParser.getEdges());
        Artist artist = new Artist(tree);
        artist.draw();
    }
}
