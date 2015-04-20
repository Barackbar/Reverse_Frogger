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
    private int score;
    //endColumn and endLane are the first column and lane that are off screen
    //first column and lane are 0
    private int endColumn;
    private int endLane;
    private Random RNGesus;

    public FrogController() {
        endColumn = 8;
        endLane = 4;
        difficulty = 0;
        frogs = new ArrayList<Frog>();
        RNGesus = new Random();
        freeColumns  = new ArrayList<Integer>();
        for (int i = 0; i < endColumn; i++) {
            freeColumns.add(i);
        }
    }
    public FrogController(int newEndColumn, int newEndLane, int newDifficulty) {
        if (setDifficulty(newDifficulty))
            difficulty = 0;
        setEndColumn(newEndColumn);
        setEndLane(newEndLane);
        frogs = new ArrayList<Frog>();
        RNGesus = new Random();
        freeColumns = new ArrayList<Integer>();
        for (int i = 0; i < endColumn; i++) {
            freeColumns.add(i);
        }
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
        //if there are no empty columns, no frog spawned
        if (freeColumns.size() == 0) {
            return false;
        }
        else {
            //get a random number between [0, numFreeColumns)
            int randNum = RNGesus.nextInt(freeColumns.size());
            //add a frog to the column number stored in the randNum index of freeColumns
            frogs.add(
                    //add new frog into lane 0
                    new Frog(freeColumns.get(randNum), 0)
            );
            //that column is not longer empty
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
        //percentage probabilities of the frog's movement, out of 100
        int forward = 40;
        int stay = 30;
        int rearward = 30;
        int movement;
        //search through cars for nearby cars
        for (int i = 0; i < cars.length; i++) {
            //if a car 1 column away
            if (cars[i][0] == frog.getColumn() - 1) {
                //if the car is in the same lane
                if (cars[i][1] == frog.getLane()) {
                    stay        -=      15;
                    forward     +=      10;
                    rearward    +=      5;
                }
                //if the car is in the forward lane
                else if (cars[i][1] == frog.getLane() + 1) {
                    forward     -=      15;
                    stay        +=      10;
                    rearward    +=      5;
                }
                //if the car is in the rearward lane
                else if (cars[i][1] == frog.getLane() - 1) {
                    rearward    -=      15;
                    forward     +=      10;
                    stay        +=      5;
                }
                //else do nothing to the probabilities
            }
            //if a car is 2 columns away
            else if (cars[i][0] == frog.getColumn() - 2) {
                //if the car is in the same lane
                if (cars[i][1] == frog.getLane()) {
                    stay        -=      10;
                    forward     +=      7;
                    rearward    +=      3;
                }
                //if the car is in the forward lane
                else if (cars[i][1] == frog.getLane() + 1) {
                    forward     -=      10;
                    stay        +=      7;
                    rearward    +=      3;
                }
                //if the car is in the rearward lane
                else if (cars[i][1] == frog.getLane() - 1) {
                    rearward    -=      10;
                    forward     +=      7;
                    stay        +=      3;
                }
                //else do nothing to the probabilities
            }
        }
        //PRAISE RNGesus
        movement = RNGesus.nextInt(100);
        //move frog
        if (movement < forward) { //move forward
            frog.setLane(frog.getLane() + 1);
        }
        else if (movement < forward + rearward) { //move rearward
            frog.setLane(frog.getLane() - 1);
        }
        //otherwise, stay still

        //if the frog jumped into the endLane, return true
        return (frog.getLane() == endLane);
    }

    @Override
    public void Draw(Canvas canvas) {

    }

    @Override
    public void Update(int[][] cars) {
        for (int i = 0; i < frogs.size(); i++) {
            if (Jump(frogs.get(i), cars))
                score++;
        }
    }
}
