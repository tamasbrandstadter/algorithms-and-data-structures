package problems.hamiltonian;

public class HamiltonianAlgorithm {
    private int numOfVertexes;
    private int[] hamiltonianPath;
    private int[][] adjacencyMatrix;

    public void hamiltonianCycle(int[][] adjacencyMatrix) {
        this.numOfVertexes = adjacencyMatrix[0].length;
        this.hamiltonianPath = new int[this.numOfVertexes];
        this.adjacencyMatrix = adjacencyMatrix;

        this.hamiltonianPath[0] = 0;

        if (!findFeasibleSolution(1)) {
            System.out.println("No feasible solution found...");
        } else {
            showHamiltonianPath();
        }
    }

    private boolean findFeasibleSolution(int position) {
        if (position == this.numOfVertexes) {
            return adjacencyMatrix[hamiltonianPath[position - 1]][hamiltonianPath[0]] == 1;
        }

        for (int vertexIndex = 1; vertexIndex < this.numOfVertexes; vertexIndex++) {
            if (isFeasible(vertexIndex, position)) {
                hamiltonianPath[position] = vertexIndex;
                if (findFeasibleSolution(position + 1)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isFeasible(int vertex, int actualPosition) {
        // first criteria: whether the two nodes are connected?
        if (adjacencyMatrix[hamiltonianPath[actualPosition - 1]][vertex] == 0) {
            return false;
        }

        // second criteria: whether we have already added this given node?
        for (int i = 0; i < actualPosition; i++) {
            if (hamiltonianPath[i] == vertex) {
                return false;
            }
        }

        return true;
    }

    private void showHamiltonianPath() {
        System.out.println("Hamiltonian cycle exists: ");

        for (int i = 0; i < numOfVertexes; i++) {
            System.out.print(hamiltonianPath[i] + " ");
        }

        System.out.println(hamiltonianPath[0]);
    }
}
