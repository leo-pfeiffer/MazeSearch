public class Experiment {
    String confName;
    String algorithmName;
    int exploredNodeCount;
    Double pathCost;

    public Experiment(String confName, String algorithmName, int exploredNodeCount, Double pathCost) {
        this.confName = confName;
        this.algorithmName = algorithmName;
        this.exploredNodeCount = exploredNodeCount;
        this.pathCost = pathCost;
    }
}