package edu.auburn.eng.csse.comp3710.team03;

/**
 * Created by JDSS on 16/4/15.
 */
public class Frog {
    private int lane;
    private int column;
    private int prevLane;
    private Boolean moved;

    public Frog(int newColumn, int newLane) {
        column = newColumn;
        lane = newLane;
        prevLane = 0;
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

    public int getPrevLane() {
        return prevLane;
    }

    public void setPrevLane(int prevLane) {
        this.prevLane = prevLane;
    }

    public Boolean getMoved() {
        return moved;
    }

    public void setMoved(Boolean moved) {
        this.moved = moved;
    }
}
