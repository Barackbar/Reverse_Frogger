package edu.auburn.eng.csse.comp3710.team03;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by JDSS on 16/4/15.
 */
public class FrogSpace extends View {
    private FrogController frogController;
    private CarController carController;
    private Bitmap background;

    public FrogSpace(Context context) {
        super(context);
        background = BitmapFactory.decodeResource(getResources(), R.drawable.background_road);
    }



    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //update frogs
        frogController.Update(carController.getCarLocations());
        //update cars
        carController.Update(frogController.getFrogLocations());

        //draw background
        canvas.drawBitmap(background, ((float) canvas.getWidth())/2, ((float) canvas.getHeight())/2, new Paint());
        //draw frogs
        //draw cars
    }
}
