package edu.auburn.eng.csse.comp3710.team03;

import android.graphics.Canvas;

/**
 * Created by JDSS on 16/4/15.
 */
public interface Updateable {

    public void Update();

    public void Draw(Canvas canvas);

    public void minorDraw(Canvas canvas);
}
