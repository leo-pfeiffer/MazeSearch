import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ExperimentReaderWriter {

    private static final String EVALUATION_FOLDER = "evaluation/";
    private static final String OUT_FOLDER = "evaluation/out/";

    public static void main(String[] args) {
        ArrayList<String> algorithms = readJSON(EVALUATION_FOLDER + "configurations.json");
        Conf[] confArray = getEvalConfArray();
        regularTest(algorithms, confArray);
        bidirectionalTest(algorithms, confArray);
    }

    public static void regularTest(ArrayList<String> algorithms, Conf[] confArray) {
        try {
            ArrayList<Experiment> results = runExperiments(confArray, algorithms);
            writeJSON(OUT_FOLDER + "results.json", results);
        } catch (SearchFactory.InvalidSearch e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void bidirectionalTest(ArrayList<String> algorithms, Conf[] confArray) {
        try {
            ArrayList<Experiment> results = runBDSExperiments(confArray, algorithms);
            writeJSON(OUT_FOLDER + "bds-results.json", results);
        } catch (SearchFactory.InvalidSearch e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static ArrayList<Experiment> runExperiments(Conf[] confArray, ArrayList<String> algorithms) throws SearchFactory.InvalidSearch {
        String confName;
        ArrayList<Experiment> experiments = new ArrayList<>();

        for (Conf conf : confArray) {
            confName = conf.name();
            for (String algorithm : algorithms) {
                Search search = SearchFactory.makeSearch(algorithm, conf.getMap(), conf.getS(), conf.getG());
                search.runSearch();
                Experiment e = makeExperiment(confName, algorithm, search);
                experiments.add(e);
            }
        }
        return experiments;
    }

    public static ArrayList<Experiment> runBDSExperiments(Conf[] confArray, ArrayList<String> algorithms) throws SearchFactory.InvalidSearch {
        String confName;
        ArrayList<Experiment> experiments = new ArrayList<>();

        for (Conf conf : confArray) {
            confName = conf.name();
            for (String algorithm : algorithms) {
                if (algorithm.equals("BDS")) continue;
                Search search = SearchFactory.makeSearch("BDS", algorithm, conf.getMap(), conf.getS(), conf.getG());
                search.runSearch();
                Experiment e = makeExperiment(confName, "BDS-" + algorithm, search);
                experiments.add(e);
            }
        }
        return experiments;
    }

    public static Experiment makeExperiment(String confName, String algorithm, Search search) {
        int exploredNodeCount = search.getExploredNodeCount();
        Node solution = search.getSolution();
        Double pathCost;
        if (solution != null) {
            pathCost = search.getSolution().getCost();
        } else {
            pathCost = null;
        }
        return new Experiment(confName, algorithm, exploredNodeCount, pathCost);
    }

    /**
     * Read a JSON file containing the experiment settings.
     * */
    public static ArrayList<String> readJSON(String path) {
        try {
            Gson gson = new Gson();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            return gson.fromJson(bufferedReader, (Type) Object.class);

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Write a JSON file with the experiment results.
     * */
    public static void writeJSON(String path, ArrayList<Experiment> experiments) {
        try {

            FileWriter writer = new FileWriter(path);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(experiments, writer);
            writer.close();

            System.out.println("Experiment results written to " + path);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /*
    * Get the configurations for the evaluation.
    * */
    public static Conf[] getEvalConfArray() {
        Conf[] confArray = new Conf[25];
        int i=0;
        for (Conf conf : Conf.values()) {
            if (conf.name().startsWith("CONF")) {
                confArray[i] = conf;
                i++;
            }
        }
        return confArray;
    }
}