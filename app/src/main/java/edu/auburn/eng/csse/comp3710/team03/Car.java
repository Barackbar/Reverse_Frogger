package edu.auburn.eng.csse.comp3710.team03;

/**
 * Created by JDSS on 16/4/15.
 */
public class Car {
    private int lane;
    private int column;
    private int prevColumn;
    private Boolean moved;

    public Car(int newColumn, int newLane) {
        column = newColumn;
        lane = newLane;
        prevColumn = 0;
        moved = false;
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

    public int getPrevColumn() {
        return prevColumn;
    }

    public void setPrevColumn(int prevColumn) {
        this.prevColumn = prevColumn;
    }

    public Boolean getMoved() {
        return moved;
    }

    public void setMoved(Boolean moved) {
        this.moved = moved;
    }
}
