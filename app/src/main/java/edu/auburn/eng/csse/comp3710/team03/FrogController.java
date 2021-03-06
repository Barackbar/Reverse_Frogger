package edu.auburn.eng.csse.comp3710.team03;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by JDSS on 16/4/15.
 */
public class FrogController implements Updateable {

    private     static final int        MAX_DIFFICULTY  =   3;
    private     static final int        MIN_DIFFICULTY  =   1;

    private     Context                 context;

    private     ArrayList<Frog>         frogs;
    private     ArrayList<Integer>      freeColumns;

    private     int                     endColumn;
    private     int                     endLane;
    private     int                     difficulty;
    private     int[][]                 carLocations;

    private     BitmapFactory.Options   options;
    private     Bitmap                  frogSitBitmap;
    private     Paint                   paint;

    private     Random                  RNGesus;

    private     int                     frogsEscaped;
    private     int                     frogsHit;

    public FrogController(Context newContext) {

        context                 =   newContext;

        frogs                   =   new ArrayList<Frog>();

        endColumn               =   8;
        endLane                 =   4;

        difficulty              =   MIN_DIFFICULTY;

        RNGesus                 =   new Random();

        freeColumns             =   new ArrayList<Integer>();

        for (int i = 0; i < endColumn; i++)
            freeColumns.add(i);

        frogsEscaped            =   0;
        frogsHit                =   0;

        options                 =   new BitmapFactory.Options();
        options.inSampleSize    =   1;
        frogSitBitmap           =   BitmapFactory.decodeResource(context.getResources(), R.drawable.frog, options);
        paint                   =   new Paint();

    }

    public FrogController(Context newContext, int newEndColumn, int newEndLane, int newDifficulty) {

        context                 =   newContext;

        frogs                   =   new ArrayList<Frog>();

        endColumn               =   newEndColumn;
        endLane                 =   newEndLane;

        if (!setDifficulty(newDifficulty))
            difficulty = MIN_DIFFICULTY;

        RNGesus                 =   new Random();

        freeColumns             =   new ArrayList<Integer>();

        for (int i = 0; i < endColumn; i++)
            freeColumns.add(i);

        frogsEscaped            =   0;
        frogsHit                =   0;

        options                 =   new BitmapFactory.Options();
        options.inSampleSize    =   1;
        frogSitBitmap           =   BitmapFactory.decodeResource(context.getResources(), R.drawable.frog);
        paint                   =   new Paint();

    }

    public int getEndColumn() {
        return endColumn;
    }

    private void setEndColumn(int newEndColumn) {
        endColumn = newEndColumn;
    }

    public int getFrogsHit() {
        return frogsHit;
    }

    public int getFrogsEscaped() {
        return frogsEscaped;
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

    public void setCarLocations(int[][] newCarLocations) {
        carLocations = newCarLocations;
    }

    private void Jump(Frog frog, int[][] cars) {

        //percentage probabilities of the frog's movement, out of 100
        int forward;
        int stay;
        int rearward;
        int movement;

        //if the frog is not in the starting lane
        if (frog.getLane() != 0) {
            forward = 40;
            stay = 30;
            rearward = 30;
            //search through carLocations for nearby carLocations
            for (int i = 0; i < cars.length; i++) {
                //if a car 1 column away
                if (cars[i][0] == frog.getColumn() + 1) {
                    //if the car is in the same lane
                    if (cars[i][1] == frog.getLane()) {
                        stay -= 7 * difficulty;
                        forward += 5 * difficulty;
                        rearward += 2 * difficulty;
                    }
                    //if the car is in the forward lane
                    else if (cars[i][1] == frog.getLane() + 1) {
                        forward -= 7 * difficulty;
                        stay += 5 * difficulty;
                        rearward += 2 * difficulty;
                    }
                    //if the car is in the rearward lane
                    else if (cars[i][1] == frog.getLane() - 1) {
                        rearward -= 7 * difficulty;
                        forward += 5 * difficulty;
                        stay += 2 * difficulty;
                    }
                    //else do nothing to the probabilities
                }
                //if a car is 2 columns away
                else if (cars[i][0] == frog.getColumn() + 2) {
                    //if the car is in the same lane
                    if (cars[i][1] == frog.getLane()) {
                        stay -= 3 * difficulty;
                        forward += 2 * difficulty;
                        rearward += 1 * difficulty;
                    }
                    //if the car is in the forward lane
                    else if (cars[i][1] == frog.getLane() + 1) {
                        forward -= 3 * difficulty;
                        stay += 2 * difficulty;
                        rearward += 1 * difficulty;
                    }
                    //if the car is in the rearward lane
                    else if (cars[i][1] == frog.getLane() - 1) {
                        rearward -= 3 * difficulty;
                        forward += 2 * difficulty;
                        stay += 1 * difficulty;
                    }
                    //else do nothing to the probabilities
                }
            }
            //PRAISE RNGesus
            movement = RNGesus.nextInt(100);
            //move frog
            if (movement < forward) { //move forward
                frog.setPrevLane(frog.getLane());
                frog.setLane(frog.getLane() + 1);
                frog.setMoved(true);
            }
            else if (movement < forward + rearward) { //move rearward
                frog.setPrevLane(frog.getLane());
                frog.setLane(frog.getLane() - 1);
                frog.setMoved(true);
            }
            //otherwise, stay still
            else
                frog.setMoved(false);
        }

        //else the frog is in the first lane
        else {
            //percentage probabilities of the frog's movement, out of 100
            forward = 60;
            stay = 40;
            //search through carLocations for nearby carLocations
            for (int i = 0; i < cars.length; i++) {

                //if a car 1 column away
                if (cars[i][0] == frog.getColumn() + 1) {
                    //if the car is in the same lane
                    if (cars[i][1] == frog.getLane()) {
                        stay -= 8 * difficulty;
                        forward += 8 * difficulty;
                    }
                    //if the car is in the forward lane
                    else if (cars[i][1] == frog.getLane() + 1) {
                        forward -= 8 * difficulty;
                        stay += 8 * difficulty;
                    }
                    //else do nothing to the probabilities
                }
                //if a car is 2 columns away
                else if (cars[i][0] == frog.getColumn() + 2) {
                    //if the car is in the same lane
                    if (cars[i][1] == frog.getLane()) {
                        stay -= 4 * difficulty;
                        forward += 4 * difficulty;
                    }
                    //if the car is in the forward lane
                    else if (cars[i][1] == frog.getLane() + 1) {
                        forward -= 4 * difficulty;
                        stay += 4 * difficulty;
                    }
                    //else do nothing to the probabilities
                }

            }

            //PRAISE RNGesus
            movement = RNGesus.nextInt(100);
            //move frog
            if (movement < forward) { //move forward
                frog.setPrevLane(frog.getLane());
                frog.setLane(frog.getLane() + 1);
                frog.setMoved(true);
            }
            //else, stay still
            else {
                frog.setMoved(false);
            }
        }
    }

    private Boolean isHit(Frog frog, int[][] cars) {

        for (int i = 0; i < carLocations.length; i++) {
            //if frog is in same location as car, frog was hit
            if (frog.getLane() == carLocations[i][1] && frog.getColumn() == carLocations[i][0]) {
                i = carLocations.length;
                return true;
            }
        }

        return false;

    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {

        //pull out frog locations from serialized string
        frogs = new ArrayList<Frog>();

        String serialized = savedInstanceState.getString(context.getString(R.string.frogs_id));

        //until the end of the string
        for (int i = 0; i < serialized.length();) {
            //add a new frog
            frogs.add(
                    new Frog(
                            //into column number denoted by string between ';' and ','
                            Integer.parseInt(
                                    serialized.substring(
                                            i,
                                            serialized.indexOf(
                                                    ',',
                                                    i))),
                            //into lane number denoted by string between ',' and ';'
                            Integer.parseInt(
                                    serialized.substring(
                                            serialized.indexOf(
                                                    ',',
                                                    i) + 1,
                                            serialized.indexOf(
                                                    ';',
                                                    i)
                                    )
                            )
                    )
            );

            //move the cursor to the next set of coordinates
            i = serialized.indexOf(';', i) + 1;
        }

        freeColumns = new ArrayList<Integer>();
        for (int i = 0; i < endColumn; i++)
            freeColumns.add(i);

        for (int i = 0; i < frogs.size(); i++) {
            if (freeColumns.contains(frogs.get(i).getColumn())) {
                freeColumns.remove((Integer) frogs.get(i).getColumn());
            }
        }

    }

    public void onSaveInstanceState(Bundle outState) {

        Log.i("FrogController", "onSaveInstanceState");

        //store frog locations as serialized string
        String serialized = "";

        for (int i = 0; i < frogs.size(); i++) {
            //frog locations will be "column,lane;column,lane;..."
            serialized += Integer.toString(frogs.get(i).getColumn()) + ",";
            serialized += Integer.toString(frogs.get(i).getLane()) + ";";
        }

        outState.putString(context.getString(R.string.frogs_id), serialized);

    }

    @Override
    public void Draw(Canvas canvas) {

        //if in portrait mode
        if (canvas.getHeight() >= canvas.getWidth()) {
            for (Frog frog : frogs) {
                canvas.drawBitmap(
                        frogSitBitmap,
                        null,
                        new Rect(
                                (int) (((float) frog.getLane() / endLane) * canvas.getWidth()),
                                (int) (((float) frog.getColumn() / endColumn) * canvas.getHeight()),
                                (int) ((((float) frog.getLane() + 1) / endLane) * canvas.getWidth()),
                                (int) ((((float) frog.getColumn() + 1) / endColumn) * canvas.getHeight())
                        ),
                        paint
                );
            }
        }
        //else in landscape mode
        else {
            for (Frog frog : frogs) {
                canvas.drawBitmap(
                        frogSitBitmap,
                        null,
                        new Rect(
                                (int) (((float) frog.getColumn() / endColumn) * canvas.getWidth()),
                                (int) ((((float) endLane - frog.getLane() - 1) / endLane) * canvas.getHeight()),
                                (int) ((((float) frog.getColumn() + 1) / endColumn) * canvas.getWidth()),
                                (int) ((((float) endLane - frog.getLane()) / endLane) * canvas.getHeight())
                        ),
                        paint
                );
            }
        }
    }

    @Override
    public void minorDraw(Canvas canvas) {

        //if in portrait mode
        if (canvas.getHeight() >= canvas.getWidth()) {
            for (Frog frog : frogs) {
                //if frog moved, draw intermediate location
                if (frog.getMoved()) {

                    int left = ((int) (((float) frog.getPrevLane() / endLane) * canvas.getWidth())
                            + (int) (((float) frog.getLane() / endLane) * canvas.getWidth()))
                            / 2;
                    int right = ((int) ((((float) frog.getPrevLane() + 1) / endLane) * canvas.getWidth())
                            + (int) ((((float) frog.getLane() + 1) / endLane) * canvas.getWidth()))
                            / 2;

                    canvas.drawBitmap(
                            frogSitBitmap,
                            null,
                            new Rect(
                                    left,
                                    (int) (((float) frog.getColumn() / endColumn) * canvas.getHeight()),
                                    right,
                                    (int) ((((float) frog.getColumn() + 1) / endColumn) * canvas.getHeight())
                            ),
                            paint
                    );
                }
                //else draw normal location
                else {
                    canvas.drawBitmap(
                            frogSitBitmap,
                            null,
                            new Rect(
                                    (int) (((float) frog.getLane() / endLane) * canvas.getWidth()),
                                    (int) (((float) frog.getColumn() / endColumn) * canvas.getHeight()),
                                    (int) ((((float) frog.getLane() + 1) / endLane) * canvas.getWidth()),
                                    (int) ((((float) frog.getColumn() + 1) / endColumn) * canvas.getHeight())
                            ),
                            paint
                    );
                }
            }
        }
        //else in landscape mode
        else {
            for (Frog frog : frogs) {
                //if frog moved, draw intermediate location
                if (frog.getMoved()) {

                    int top = ((int) ((((float) endLane - frog.getPrevLane() - 1) / endLane) * canvas.getHeight())
                            + (int) ((((float) endLane - frog.getLane() - 1) / endLane) * canvas.getHeight()))
                            / 2;

                    int bottom = ((int) ((((float) endLane - frog.getPrevLane()) / endLane) * canvas.getHeight())
                            + (int) ((((float) endLane - frog.getLane()) / endLane) * canvas.getHeight()))
                            / 2;

                    canvas.drawBitmap(
                            frogSitBitmap,
                            null,
                            new Rect(
                                    (int) (((float) frog.getColumn() / endColumn) * canvas.getWidth()),
                                    top,
                                    (int) ((((float) frog.getColumn() + 1) / endColumn) * canvas.getWidth()),
                                    bottom

                            ),
                            paint
                    );
                }
                //else draw normal location
                else {
                    canvas.drawBitmap(
                            frogSitBitmap,
                            null,
                            new Rect(
                                    (int) (((float) frog.getColumn() / endColumn) * canvas.getWidth()),
                                    (int) ((((float) endLane - frog.getLane() - 1) / endLane) * canvas.getHeight()),
                                    (int) ((((float) frog.getColumn() + 1) / endColumn) * canvas.getWidth()),
                                    (int) ((((float) endLane - frog.getLane()) / endLane) * canvas.getHeight())
                            ),
                            paint
                    );
                }
            }
        }
    }

    @Override
    public void Update() {

        //for all frogs
        for (Iterator<Frog> iterator = frogs.iterator(); iterator.hasNext();) {

            Frog frog = iterator.next();

            //if frog is in endLane, frog has escaped
            if (frog.getLane() >= endLane) {
                //column is now free
                freeColumns.add(frog.getColumn());
                iterator.remove();
                frogsEscaped++;
            }
            //else if frog is hit by car
            else if (isHit(frog, carLocations)) {
                //column is now free
                freeColumns.add(frog.getColumn());
                iterator.remove();
                frogsHit++;
            }
            //else frog can jump
            else {
                Jump(frog, carLocations);
            }
        }
    }
}
