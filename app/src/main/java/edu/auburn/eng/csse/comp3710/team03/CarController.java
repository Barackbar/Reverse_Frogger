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

/**
 * Created by JDSS on 16/4/15.
 */
public class CarController implements Updateable {
    ArrayList<Car> cars;
    //endColumn and endLane are the number of columns and lanes
    //numbered from top left to bottom right in portrait mode
    private int endColumn;
    private int endLane;

    private String ID = "cars";


    private Bitmap carBitmapPortrait;
    private Bitmap carBitmapLandscape;

    public CarController(Context context) {
        setEndColumn(8);
        setEndLane(4);
        cars = new ArrayList<Car>();
        carBitmapPortrait = BitmapFactory.decodeResource(context.getResources(), R.drawable.red_car_vertical);
        carBitmapLandscape = BitmapFactory.decodeResource(context.getResources(), R.drawable.red_car_horizontal);
    }

    public CarController(Context context, int newEndColumn, int newEndLane) {
        setEndColumn(newEndColumn);
        setEndLane(newEndLane);
        cars = new ArrayList<Car>();
        carBitmapPortrait = BitmapFactory.decodeResource(context.getResources(), R.drawable.red_car_vertical);
        carBitmapLandscape = BitmapFactory.decodeResource(context.getResources(), R.drawable.red_car_horizontal);
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
        car.setPrevColumn(car.getColumn());
        car.setMoved(true);
        car.setColumn(car.getColumn() - 1);
    }

    public void restoreInstance(Bundle savedInstanceState) {
        //pull out car locations from serialized string
    }

    public void saveInstance (Bundle bundle) {
        Log.i("CarController","saveInstance");
        //store car locations as serialized string
        String serialized = "";
        for (int i = 0; i < cars.size(); i++) {
            serialized += Integer.toString(cars.get(i).getColumn()) + ",";
            serialized += Integer.toString(cars.get(i).getLane()) + ";";
        }
        bundle.putString(ID, serialized);
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
        //if in portrait mode
        if (canvas.getHeight() >= canvas.getWidth()) {
            for (Car car : cars) {
                canvas.drawBitmap(
                        carBitmapPortrait,
                        null,
                        new Rect(
                                (int) (((float) car.getLane() / endLane) * canvas.getWidth()),
                                (int) (((float) car.getColumn() / endColumn) * canvas.getHeight()),
                                (int) ((((float) car.getLane() + 1) / endLane) * canvas.getWidth()),
                                (int) ((((float) car.getColumn() + 1) / endColumn) * canvas.getHeight())
                        ),
                        paint
                );
            }
        }
        //else in landscape mode
        else {
            for (Car car : cars) {
                canvas.drawBitmap(
                        carBitmapLandscape,
                        null,
                        new Rect(
                                (int) (((float) car.getColumn() / endColumn) * canvas.getWidth()),
                                (int) ((((float) endLane - car.getLane() - 1) / endLane) * canvas.getHeight()),
                                (int) ((((float) car.getColumn() + 1) / endColumn) * canvas.getWidth()),
                                (int) ((((float) endLane - car.getLane()) / endLane) * canvas.getHeight())
                        ),
                        paint
                );
            }
        }
    }

    @Override
    public void minorDraw(Canvas canvas) {
        Paint paint = new Paint();
        //if in portrait mode
        if (canvas.getHeight() >= canvas.getWidth()) {
            for (Car car : cars) {
                if (car.getMoved()) {
                    int top = ((int) (((float) car.getPrevColumn() / endColumn) * canvas.getHeight())
                            + (int) (((float) car.getColumn() / endColumn) * canvas.getHeight()))
                            / 2;
                    int bottom = ((int) ((((float) car.getPrevColumn() + 1) / endColumn) * canvas.getHeight())
                            + (int) ((((float) car.getColumn() + 1) / endColumn) * canvas.getHeight()))
                            / 2;

                    canvas.drawBitmap(
                            carBitmapPortrait,
                            null,
                            new Rect(
                                    (int) (((float) car.getLane() / endLane) * canvas.getWidth()),
                                    top,
                                    (int) ((((float) car.getLane() + 1) / endLane) * canvas.getWidth()),
                                    bottom
                            ),
                            paint
                    );
                } else {
                    canvas.drawBitmap(
                            carBitmapPortrait,
                            null,
                            new Rect(
                                    (int) (((float) car.getLane() / endLane) * canvas.getWidth()),
                                    (int) (((float) car.getColumn() / endColumn) * canvas.getHeight()),
                                    (int) ((((float) car.getLane() + 1) / endLane) * canvas.getWidth()),
                                    (int) ((((float) car.getColumn() + 1) / endColumn) * canvas.getHeight())
                            ),
                            paint
                    );
                }
            }
        }
        //else in landscape mode
        else {
            for (Car car : cars) {
                if (car.getMoved()) {
                    int left = ((int) (((float) car.getPrevColumn() / endColumn) * canvas.getWidth())
                            + (int) (((float) car.getColumn() / endColumn) * canvas.getWidth()))
                            / 2;
                    int right = ((int) ((((float) car.getPrevColumn() + 1) / endColumn) * canvas.getWidth())
                            + (int) ((((float) car.getColumn() + 1) / endColumn) * canvas.getWidth()))
                            / 2;

                    canvas.drawBitmap(
                            carBitmapLandscape,
                            null,
                            new Rect(
                                    left,
                                    (int) ((((float) endLane - car.getLane() - 1) / endLane) * canvas.getHeight()),
                                    right,
                                    (int) ((((float) endLane - car.getLane()) / endLane) * canvas.getHeight())
                            ),
                            paint
                    );
                } else {
                    canvas.drawBitmap(
                            carBitmapLandscape,
                            null,
                            new Rect(
                                    (int) (((float) car.getColumn() / endColumn) * canvas.getWidth()),
                                    (int) ((((float) endLane - car.getLane() - 1) / endLane) * canvas.getHeight()),
                                    (int) ((((float) car.getColumn() + 1) / endColumn) * canvas.getWidth()),
                                    (int) ((((float) endLane - car.getLane()) / endLane) * canvas.getHeight())
                            ),
                            paint
                    );
                }
            }
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
