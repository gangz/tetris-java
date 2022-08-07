package com.github.gangz.emergentdesign.demo.tetris.genetic;

import com.github.gangz.emergentdesign.demo.tetris.ai.Parameter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.IntStream;

public class GATuner {

    public static final int SIZE = 1000;
    public static final int MAX_BLOCKS = 500;
    public static final int TRY_TIMES = 5;
    public static final double MUTATION_VAL = 0.2;

    public static void main(String[] arg){
        new GATuner().tune();
    }

    class Data{
        Parameter parameter;
        Integer fitness=0;

        public Data(Parameter parameter) {
            this.parameter = parameter;
        }
    }
    ArrayList<Data> population = new ArrayList<>();

    public Parameter tune() {
        /*
        1. create 1000 individuals first, with random parameter
        2. compute fitness: for each individual, run game 5 times,
           each game with maximum 500 pieces; the score is the fitness.
        3. how to produce offspring:
           1) select the best 20% into next offspring
           2) cross over to produce 75% by roulette selected parent
           3) randomly mutate to generate 5% of population
        4. goto step 2; exit after 100 generation ;
        * */
        initPopulation();
        for (int generation=0;generation<500;generation++){
            computeFitness();
            Data best = population.get(0);
            System.out.println("score:"+best.fitness+"param:"+best.parameter);
            produceOffSpring();
        }
        return population.get(0).parameter;
    }

    private void initPopulation() {
        population.clear();
        IntStream.range(0, SIZE).forEach(i->{
            Parameter parameter = new Parameter(Math.random(),
                    Math.random(),
                    Math.random(),
                    Math.random(),
                    Math.random(),
                    Math.random());
            population.add(new Data(parameter));});
    }

    private void computeFitness() {
        population.stream().forEach(individual->{
            individual.fitness =
                IntStream.range(0, TRY_TIMES).asLongStream().mapToInt(times->{
                    AutoGame game = new AutoGame(individual.parameter, MAX_BLOCKS);
                    //System.out.println("[game start] score:" + game.score + "parameter:" + individual.parameter);
                    game.play();
                    //System.out.println("[game end] score:" + game.score + "parameter:" + individual.parameter);
                    return game.score;
                }).sum();
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
        next.addAll(population.subList(0, (int) (SIZE*0.2)));
        for (int i=0;i<SIZE*0.8;i++){
            Data individual=crossOver();
            if (Math.random()<0.05)
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
                first.fitness*first.parameter.rowTransitionWeight+second.fitness*second.parameter.rowTransitionWeight,
                first.fitness*first.parameter.columnTransitionWeight+second.fitness*second.parameter.columnTransitionWeight,
                first.fitness*first.parameter.holeWeight+second.fitness*second.parameter.holeWeight,
                first.fitness*first.parameter.bumpWeight+second.fitness*second.parameter.bumpWeight
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
        double value = Math.random()*2* MUTATION_VAL -MUTATION_VAL;
        int  select = (int) Math.floor(6.0*Math.random());
        switch (select){
            case 0:
                v.parameter.heightWeight+=value;
                break;
            case 1:
                v.parameter.removeLinesWeight+=value;
                break;
            case 2:
                v.parameter.rowTransitionWeight+=value;
                break;
            case 3:
                v.parameter.columnTransitionWeight+=value;
                break;
            case 4:
                v.parameter.holeWeight+=value;
                break;
            case 5:
                v.parameter.bumpWeight+=value;
                break;
        }
    }
}
