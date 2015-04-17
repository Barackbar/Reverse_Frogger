package edu.auburn.eng.csse.comp3710.team03;

/**
 * Created by JDSS on 16/4/15.
 */
public class Car {
    private int lane;
    private int column;

    public Car(int newColumn, int newLane) {
        column = newColumn;
        lane = newLane;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int newColumn) {
        column = newColumn;
    }

    public int getLane() {
        return lane;
    }

    public void setLane(int newLane) {
        lane = newLane;
    }
}
