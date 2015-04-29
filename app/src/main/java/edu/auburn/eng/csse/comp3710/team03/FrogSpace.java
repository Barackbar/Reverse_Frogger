package edu.auburn.eng.csse.comp3710.team03;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by JDSS on 16/4/15.
 */
public class FrogSpace extends View {
    private FrogController frogController;
    private CarController carController;
    private Bitmap backgroundVertical;
    private Bitmap backgroundHorizontal;
    private Bitmap background;
    private BitmapFactory.Options options;
    private Rect rect;
    private Paint paint;

    //major action
    private Boolean major = true;

    private int endLane = 4;
    private int endColumn = 8;
    private int difficulty = 1;
    private Boolean portrait;

    public FrogSpace(Context context) {
        super(context);
        frogController = new FrogController(context, endColumn, endLane, difficulty);
        carController = new CarController(context, endColumn, endLane);
        options  = new BitmapFactory.Options();
        options.inSampleSize = 8;
        backgroundHorizontal = BitmapFactory.decodeResource(getResources(), R.drawable.background_road_horizontal, options);
        backgroundVertical = BitmapFactory.decodeResource(getResources(), R.drawable.background_road_vertical, options);
        rect = null;
        paint = new Paint();
    }

    public FrogSpace(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        frogController = new FrogController(context, endColumn, endLane, difficulty);
        carController = new CarController(context, endColumn, endLane);
        options = new BitmapFactory.Options();
        options.inSampleSize = 8;
        backgroundHorizontal = BitmapFactory.decodeResource(getResources(), R.drawable.background_road_horizontal, options);
        backgroundVertical = BitmapFactory.decodeResource(getResources(), R.drawable.background_road_vertical, options);
        rect = null;
        paint = new Paint();
    }

    public int[][] getFrogLocations() {
        return frogController.getFrogLocations();
    }

    public int[][] getCarLocations() {
        return carController.getCarLocations();
    }

    public int getFrogsHit() {
        return frogController.getFrogsHit();
    }

    public int getFrogsEscaped() {
        return frogController.getFrogsEscaped();
    }

    public void spawnCar(int lane) {
        if (lane < endLane)
            carController.spawnCar(lane);
    }

    public void spawnFrog() {
        frogController.spawnFrog();
    }

    public void saveInstance(Bundle bundle) {
        frogController.saveInstance(bundle);
        carController.saveInstance(bundle);
    }

    public void restoreInstance(Bundle savedInstanceState) {
        frogController.restoreInstance(savedInstanceState);
        carController.restoreInstance(savedInstanceState);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i("FrogSpace", "onDraw");
        super.onDraw(canvas);

        //first time setup needs the canvas
        if (rect == null) {
            if (canvas.getHeight() <= canvas.getWidth()) {
                background = backgroundHorizontal;
            }
            else {
                background = backgroundVertical;
            }
            rect = new Rect(0, 0, canvas.getWidth(), canvas.getHeight());
        }

        if (major) {
            //draw background
            canvas.drawBitmap(background, null, rect, paint);
            //draw frogs
            Log.i("GameFragment", "frogController.Draw");
            frogController.Draw(canvas);
            //draw cars
            Log.i("GameFragment", "carController.Draw");
            carController.Draw(canvas);

            //update frogs
            frogController.setCarLocations(carController.getCarLocations());
            frogController.Update();
            //update carLocations
            carController.Update();
            major = false;
        }
        else {
            canvas.drawBitmap(background, null, rect, paint);
            frogController.minorDraw(canvas);
            carController.minorDraw(canvas);
            major = true;
        }

    }
}
