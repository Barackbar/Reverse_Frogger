package edu.auburn.eng.csse.comp3710.team03;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by JDSS on 16/4/15.
 */
public class FrogSpace extends View {
    private FrogController frogController;
    private CarController carController;
    private Bitmap background;
    private BitmapFactory.Options options;
    private Rect rect;
    private Paint paint;

    private int frogsEscaped;
    private int frogsHit;

    public FrogSpace(Context context) {
        super(context);
        frogController = new FrogController(context, 8, 4, 0);
        carController = new CarController(context, 8, 4);
        options  = new BitmapFactory.Options();
        options.inSampleSize = 8;
        background = BitmapFactory.decodeResource(getResources(), R.drawable.background_road);
        paint = new Paint();
    }

    public FrogSpace(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        frogController = new FrogController(context, 8, 4, 0);
        carController = new CarController(context, 8, 4);
        options  = new BitmapFactory.Options();
        options.inSampleSize = 8;
        background = BitmapFactory.decodeResource(getResources(), R.drawable.background_road, options);
        paint = new Paint();
    }

    public int[][] getFrogLocations() {
        return frogController.getFrogLocations();
    }

    public int[][] getCarLocations() {
        return carController.getCarLocations();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i("FrogSpace", "onDraw");
        super.onDraw(canvas);

        if (rect == null) {
            rect = new Rect(0, 0, canvas.getWidth(), canvas.getHeight());
        }

        //update frogs
        frogController.setCarLocations(carController.getCarLocations());
        frogController.Update();
        //update carLocations
        carController.Update();

        //draw background
        canvas.drawBitmap(background, null, rect, paint);
        //draw frogs
        Log.i("GameFragment", "frogController.Draw");
        frogController.Draw(canvas);
        //draw cars
        Log.i("GameFragment", "carController.Draw");
        carController.Draw(canvas);
    }
}
