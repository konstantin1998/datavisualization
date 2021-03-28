import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.nio.*;
import org.jgrapht.nio.dot.*;
import org.jgrapht.nio.graphml.GraphMLExporter;
import org.jgrapht.nio.graphml.GraphMLImporter;
import org.jgrapht.traverse.*;
import org.jgrapht.util.SupplierUtil;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.function.Supplier;

public class App {

    public static void main(String[] args) throws URISyntaxException {
        Graph<URI, DefaultEdge> g = new DefaultDirectedGraph<>(DefaultEdge.class);

        URI google = new URI("http://www.google.com");
        URI wikipedia = new URI("http://www.wikipedia.org");
        URI jgrapht = new URI("http://www.jgrapht.org");

        // add the vertices
        g.addVertex(google);
        g.addVertex(wikipedia);
        g.addVertex(jgrapht);

        // add edges to create linking structure
        g.addEdge(jgrapht, wikipedia);
        g.addEdge(google, jgrapht);
        g.addEdge(google, wikipedia);
        g.addEdge(wikipedia, google);


        File file = new File( "src/main/resources/graph.graphml");
        GraphMLExporter<URI, DefaultEdge> exporter = new GraphMLExporter<>();
        exporter.exportGraph(g, file);

        Supplier<URI> vSupplier = new Supplier<URI>()
        {
            private int id = 0;

            @Override
            public URI get()
            {

                try {
                    return new URI("http://www.google.com" + id++);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        // Create the graph object
        Graph<URI, DefaultEdge> graph =
                new DefaultDirectedGraph<>(vSupplier, SupplierUtil.createDefaultEdgeSupplier(), false);

        GraphMLImporter<URI, DefaultEdge> importer = new GraphMLImporter<>();
        
        importer.importGraph(graph, file);
        System.out.println(graph);
    }
}
