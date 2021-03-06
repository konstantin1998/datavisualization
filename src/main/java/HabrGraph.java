import javax.swing.JFrame;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

public class HabrGraph extends JFrame {

    public static void main(String[] args) {
        HabrGraph frame = new HabrGraph();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 320);
        frame.setVisible(true);

    }

    public HabrGraph()
    {
        super("HabraGraph");

        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();
        try
        {
            Object v1 = graph.insertVertex(parent, null, "Habra", 20, 20, 80, 30);
            Object v2 = graph.insertVertex(parent, null, "Habr", 240, 150, 80, 30);
            graph.insertEdge(parent, null, "Дуга", v1, v2);
        }
        finally
        {
            graph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);
    }

}
