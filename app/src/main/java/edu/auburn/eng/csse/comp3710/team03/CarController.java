package edu.auburn.eng.csse.comp3710.team03;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by JDSS on 16/4/15.
 */
public class CarController implements Updateable {
    ArrayList<Car> cars;
    //endColumn and endLane are the first column and lane that are off screen
    private int endColumn;
    private int endLane;

    private Bitmap carBitmap;

    public CarController(Context context) {
        setEndColumn(8);
        setEndLane(4);
        cars = new ArrayList<Car>();
        carBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.temp_car);
    }

    public CarController(Context context, int newEndColumn, int newEndLane) {
        setEndColumn(newEndColumn);
        setEndLane(newEndLane);
        cars = new ArrayList<Car>();
        carBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.temp_car);
    }

    public int getEndColumn() {
        return endColumn;
    }

    public void setEndColumn(int endColumn) {
        this.endColumn = endColumn;
    }

    public int getEndLane() {
        return endLane;
    }

    private void setEndLane(int newEndLane) {
        endLane = newEndLane;
    }

    public int[][] getCarLocations() {
        //list of column,lane coord's for each car
        int[][] locations = new int[cars.size()][2];
        for (int i= 0; i < cars.size(); i++) {
            locations[i][0] = cars.get(i).getColumn();
            locations[i][1] = cars.get(i).getLane();
        }
        return locations;
    }

    public Boolean spawnCar(int lane) {
        return false;
    }

    //returns false if car will be moved off screen
    private Boolean move(Car car) {
        if (car.getColumn() + 1 == endColumn) {
            return false;
        }
        else {
            car.setColumn(car.getColumn() + 1);
            return true;
        }
    }

    @Override
    public void Draw(Canvas canvas) {
        Paint paint = new Paint();
        for (Car car : cars) {
            //change to drawBitmap(bitmap, Rect src, Rect dst, paint)
            canvas.drawBitmap(
                    carBitmap,
                    (((float) car.getLane())    * (canvas.getWidth()    / endLane)),
                    (((float) car.getColumn())  * (canvas.getHeight()   / endColumn)),
                    paint);
        }
    }

    @Override
    public void Update() {
        for (int i = 0; i < cars.size(); i++) {
            //remove car if it moves off screen
            if (!move(cars.get(i)))
               cars.remove(i);
        }
    }
}
