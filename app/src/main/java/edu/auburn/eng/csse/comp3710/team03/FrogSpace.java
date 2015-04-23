package edu.auburn.eng.csse.comp3710.team03;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
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

    public FrogSpace(Context context) {
        super(context);
        frogController = new FrogController(context, 8, 4, 0);
        carController = new CarController(context, 8, 4);
        background = BitmapFactory.decodeResource(getResources(), R.drawable.background_road);
    }

    public FrogSpace(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        frogController = new FrogController(context, 8, 4, 0);
        carController = new CarController(context, 8, 4);
        options  = new BitmapFactory.Options();
        options.inSampleSize = 8;
        background = BitmapFactory.decodeResource(getResources(), R.drawable.background_road, options);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (rect == null) {
            rect = new Rect(0, 0, canvas.getWidth(), canvas.getHeight());
        }
        //update frogs
        //frogController.setCarLocations(carController.getCarLocations());
        //frogController.Update();
        //update carLocations
        //carController.Update();

        //draw background
        canvas.drawBitmap(background, null, rect, new Paint());
        //draw frogs
        frogController.Draw(canvas);
        //draw cars
        //carController.Draw(canvas);
    }
}
