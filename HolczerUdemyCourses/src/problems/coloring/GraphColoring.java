package problems.coloring;

public class GraphColoring {
    private int numOfVertices;
    private int numOfColors;
    private int[] colors;
    private int[][] graphMatrix;

    public void graphColor(int[][] graph, int numOfColors) {
        this.numOfVertices = graph.length;
        this.numOfColors = numOfColors;
        this.colors = new int[numOfVertices];
        this.graphMatrix = graph;
    }

    public void solveColoringProblem() {
        if (solve(0)) {
            showResult();
        } else {
            System.out.println("No solution with the given parameters...");
        }
    }

    public boolean solve(int nodeIndex) {
        if (nodeIndex == numOfVertices) {
            return true;
        }

        // try all colours
        for (int colorIndex = 1; colorIndex <= numOfColors; colorIndex++) {
            if (isValidColor(nodeIndex, colorIndex)) {
                // assign and proceed with next vertex
                colors[nodeIndex] = colorIndex;
                if (solve(nodeIndex + 1)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isValidColor(int nodeIndex, int colorIndex) {
        for (int i = 0; i < numOfVertices; i++) {
            if (graphMatrix[nodeIndex][i] == 1 && colorIndex == colors[i]) {
                return false;
            }
        }

        return true;
    }

    private void showResult() {
        for (int i = 0; i < numOfVertices; i++) {
            System.out.println("Node " + (i + 1) + " has color index: " + colors[i] + " ");
        }
        System.out.println();
    }
}
