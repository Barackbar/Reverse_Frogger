package edu.auburn.eng.csse.comp3710.team03;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by JDSS on 16/4/15.
 */
public class FrogController implements Updateable {
    private static final int MAX_DIFFICULTY = 2;
    private static final int MIN_DIFFICULTY = 0;
    private ArrayList<Frog> frogs;
    private ArrayList<Integer> freeColumns;
    private int difficulty;
    //endColumn and endLane are the first column and lane that are off screen
    private int endColumn;
    private int endLane;
    private Random RNGesus;

    public FrogController(int newEndColumn, int newEndLane, int newDifficulty) {
        if (setDifficulty(newDifficulty))
            difficulty = 0;
        setEndColumn(newEndColumn);
        setEndLane(newEndLane);
        frogs = new ArrayList<Frog>();
        freeColumns = new ArrayList<Integer>();
        RNGesus = new Random();
    }

    public int getEndColumn() {
        return endColumn;
    }

    private void setEndColumn(int newEndColumn) {
        endColumn = newEndColumn;
    }

    public int getEndLane() {
        return endLane;
    }

    private void setEndLane(int newEndLane) {
        endLane = newEndLane;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public Boolean setDifficulty(int newDifficulty) {
        if (newDifficulty > MAX_DIFFICULTY || newDifficulty < MIN_DIFFICULTY)
            return false;
        else {
            difficulty = newDifficulty;
            return true;
        }
    }

    public Boolean spawnFrog() {
        if (freeColumns.size() == 0) {
            return false;
        }
        else {
            int randNum = RNGesus.nextInt(freeColumns.size());

            frogs.add(
                    //add new frog in random free column into lane 0
                    new Frog(freeColumns.get(randNum), 0)
            );
            freeColumns.remove(randNum);
            return true;
        }
    }

    public int[][] getFrogLocations() {
        //list of column,lane coord's for each frog
        int[][] locations = new int[frogs.size()][2];
        for (int i= 0; i < frogs.size(); i++) {
            locations[i][0] = frogs.get(i).getColumn();
            locations[i][1] = frogs.get(i).getLane();
        }
        return locations;
    }

    private Boolean Jump(Frog frog, int[][] cars) {
        //apply RNG weights based on cars one space away
        //apply RNG weights based on cars two spaces away
        //PRAISE RNGesus
        //move frog
        if (frog.getLane() == endLane)
            return false;
        else
            return true;
    }

    @Override
    public void Draw(Canvas canvas) {

    }

    @Override
    public void Update() {

    }
}
