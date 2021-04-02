public class Artist {
    private final Tree tree;
    private final int minNodeDistance = 100;
    private final int vertexDistanceIncrement = 100;
    private final int vertexRadiusPixels = 40;
    private final int edgeThicknessPixels = 10;
    private final int paddingPixels = 40;

    Artist(Tree tree) {
        this.tree = tree;
    }

}
