package com.github.gangz.emergentdesign.demo.tetris.genetic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.gangz.emergentdesign.demo.tetris.ai.Parameter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.IntStream;

class Data implements Serializable {
    Parameter parameter;
    Integer fitness=0;
    public Data(Parameter parameter) {
        this.parameter = parameter;
    }
}

class GAParameter{
    public int initSize = 4000;
    public int size = 500;
    public int maxBlocks = 3000;
    public int tryTimes = 5;
    public double mutationValue = 0.5;
    public double mutationProbability = 0.1;
    GAParameter(){

    }
}

public class GATuner {

    private static final String FILE_NAME = "tetris_parameter_data.dat";
    GAParameter parameter;

    public static void main(String[] arg){
        new GATuner().tune();
    }


    public GATuner(){
        readGAParameters();
    }

    private void readGAParameters() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            parameter = mapper.readValue(new File("./ga_parameters.json"), GAParameter.class);
        } catch (IOException e) {
            //use default value
        }
    }

    ArrayList<Data> population = new ArrayList<>();

    public Parameter tune() {
        /*
        1. create INIT_SIZE individuals first, with random parameter
        2. compute fitness: for each individual, run game TRY_TIMES times,
           each game with maximum MAX_BLOCK pieces; the score is the fitness.
        3. how to produce offspring:
           1) select the best 20% into next offspring
           2) cross over to produce 75% by roulette selected parent
           3) randomly mutate to generate 5% of population
        4. goto step 2; exit after 100 generation ;
        * */
        if (!readFromDataFile()){
            initPopulation();
        }

        for (int generation=0;generation<parameter.maxBlocks;generation++){
            computeFitness();
            Data best = population.get(0);
            System.out.println("score:"+best.fitness+"param:"+best.parameter);
            produceOffSpring();
            writeToDataFile();
        }
        return population.get(0).parameter;
    }

    private boolean readFromDataFile() {
        try {
            ObjectInputStream in = new ObjectInputStream(new
                    FileInputStream(FILE_NAME));
            population = (ArrayList<Data>)in.readObject();
            in.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void writeToDataFile(){
        try {
            FileOutputStream fos = new FileOutputStream(FILE_NAME,false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(population);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("write to data failed.");
        }
    }

    private void initPopulation() {
        population.clear();
        IntStream.range(0, parameter.initSize).forEach(i->{
            Parameter parameter = new Parameter(Math.random(),
                    Math.random(),
                    Math.random(),
                    Math.random(),
                    Math.random());
            population.add(new Data(parameter));});
    }

    private void computeFitness() {
        population.parallelStream().forEach(individual->{
            individual.fitness =
                IntStream.range(0, parameter.tryTimes).asLongStream().mapToInt(times->{
                    AutoGame game = new AutoGame(individual.parameter, parameter.maxBlocks);
                    game.play();
                    return game.score();
                }).sum()+1;//guarantee  fitness large than zero
        });
        population.sort(new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                return o2.fitness-o1.fitness;
            }
        });
    }

    private void produceOffSpring() {
        ArrayList<Data> next = new ArrayList<>();
        next.addAll(population.subList(0, (int) (parameter.size*0.2)));
        for (int i=0;i<parameter.size*0.8;i++){
            Data individual=crossOver();
            if (Math.random()< parameter.mutationProbability)
                mutation(individual);
            next.add(individual);
        }
        next.forEach(v->v.fitness=0);
        population = next;
    }

    private Data crossOver() {
        Data first = rouletteSelect();
        Data second = rouletteSelect();
        return new Data(new Parameter(
                first.fitness*first.parameter.heightWeight+second.fitness*second.parameter.heightWeight,
                first.fitness*first.parameter.removeLinesWeight+second.fitness*second.parameter.removeLinesWeight,
                first.fitness*first.parameter.holeCoverWeight +second.fitness*second.parameter.holeCoverWeight,
                first.fitness*first.parameter.holeAddingWeight +second.fitness*second.parameter.holeAddingWeight,
                first.fitness*first.parameter.bumpWellWeight +second.fitness*second.parameter.bumpWellWeight
        ));
    }

    private Data rouletteSelect() {
        long total = population.stream().mapToInt(v -> v.fitness).sum();
        long value = (long) (total*Math.random());
        long length = 0;
        for (int i=0;i<population.size();i++){
            length+=population.get(i).fitness;
            if (length>value)
                return population.get(i);
        }
        return population.get(0);
    }

    private void mutation(Data v) {
        int  select = (int) Math.floor(5.0*Math.random());
        switch (select){
            case 0:
                v.parameter.heightWeight+=mutationValue(v.parameter.heightWeight);
                break;
            case 1:
                v.parameter.removeLinesWeight+=mutationValue(v.parameter.removeLinesWeight);
                break;
            case 2:
                v.parameter.holeCoverWeight +=mutationValue(v.parameter.holeCoverWeight);
                break;
            case 3:
                v.parameter.holeAddingWeight +=mutationValue(v.parameter.holeAddingWeight);
                break;
            case 4:
                v.parameter.bumpWellWeight +=mutationValue(v.parameter.bumpWellWeight);
                break;
        }
    }

    private double mutationValue(double originValue) {
        double value = Math.random()*2* parameter.mutationValue -parameter.mutationValue;
        if (originValue+value<0){
            return 0-originValue*Math.random();
        }
        return value;
    }
}
