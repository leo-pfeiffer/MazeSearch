public class Experiment {
    String conf;
    String algorithm;
    int exploredNodeCount;
    Double pathCost;

    public Experiment(String conf, String algorithm, int exploredNodeCount, Double pathCost) {
        this.conf = conf;
        this.algorithm = algorithm;
        this.exploredNodeCount = exploredNodeCount;
        this.pathCost = pathCost;
    }
}