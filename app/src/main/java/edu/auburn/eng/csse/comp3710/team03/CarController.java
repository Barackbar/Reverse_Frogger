package edu.auburn.eng.csse.comp3710.team03;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by JDSS on 16/4/15.
 */
public class CarController implements Updateable {
    ArrayList<Car> cars;
    //endColumn and endLane are the number of columns and lanes
    //numbered from top left to bottom right in portrait mode
    private int endColumn;
    private int endLane;

    private Bitmap carBitmap;

    public CarController(Context context) {
        setEndColumn(8);
        setEndLane(4);
        cars = new ArrayList<Car>();
        carBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.red_car);
    }

    public CarController(Context context, int newEndColumn, int newEndLane) {
        setEndColumn(newEndColumn);
        setEndLane(newEndLane);
        cars = new ArrayList<Car>();
        carBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.red_car);
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
        //make sure there's no car where the new one will spawn
        for (Car car : cars) {
            if (car.getLane() == lane && car.getColumn() == endColumn - 1) {
                return false;
            }
        }
        cars.add(new Car(endColumn - 1, lane));
        return true;
    }

    private void move(Car car) {
        car.setColumn(car.getColumn() - 1);
    }

    @Override
    public void Draw(Canvas canvas) {
        Paint paint = new Paint();

/*        //      use for testing
        ArrayList<Car> tempCars = new ArrayList<>();
        tempCars.add(new Car(4, 0));
        tempCars.add(new Car(5, 1));
        tempCars.add(new Car(6, 2));
        tempCars.add(new Car(7, 3));

        for (Car car : tempCars) {
*/

        for (Car car : cars) {
            canvas.drawBitmap(
                    carBitmap,
                    null,
                    new Rect(
                            (int) (((float) car.getLane()/endLane) * canvas.getWidth()),
                            (int) (((float) car.getColumn()/endColumn) * canvas.getHeight()),
                            (int) ((((float) car.getLane() + 1)/endLane) * canvas.getWidth()),
                            (int) ((((float) car.getColumn() + 1)/endColumn) * canvas.getHeight())
                    ),
                    paint
            );
        }
    }

    @Override
    public void Update() {
        //move cars, remove off screen cars
        for (Iterator<Car> iterator = cars.iterator(); iterator.hasNext();) {
            Car car = iterator.next();
            move(car);
            if (car.getColumn() < 0)
                iterator.remove();
        }
    }
}
