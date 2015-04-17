package edu.auburn.eng.csse.comp3710.team03;

import android.graphics.Canvas;

import java.util.ArrayList;

/**
 * Created by JDSS on 16/4/15.
 */
public class FrogController implements Updateable {
    private ArrayList<Frog> frogs;
    private int difficulty;
    private int endLane;

    public int getDifficulty() {
        return difficulty;
    }

    public Boolean setDifficulty(int newDifficulty) {
        if (newDifficulty > 2 || newDifficulty < 0)
            return false;
        else {
            this.difficulty = newDifficulty;
            return true;
        }
    }

    public void spawnFrog() {
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

    private void Jump(Frog frog, int[][] cars) {

    }

    @Override
    public void Draw(Canvas canvas) {

    }

    @Override
    public void Update() {

    }
}
