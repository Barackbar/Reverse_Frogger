package edu.auburn.eng.csse.comp3710.team03;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by JDSS on 16/4/15.
 */
public class GameFragment extends Fragment {

    private ViewGroup layout;
    private View frogSpace;
    private Context context;
    private FrogController frogController;
    private CarController carController;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity.getApplicationContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //not sure if we need this, only way into this fragment is by starting a new game
        if (layout != null) {
            layout.removeView(layout);
            return layout;
        }

        //not sure if below code will work
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        layout = (ViewGroup) inflater.inflate(R.layout.game_layout, container, false);
        frogSpace = new FrogSpace(context);
        layout.addView(frogSpace);
        Button carButton1 = new Button(context);
        Button carButton2 = new Button(context);
        Button carButton3 = new Button(context);
        Button carButton4 = new Button(context);
        layout.addView(carButton1);
        layout.addView(carButton2);
        layout.addView(carButton3);
        layout.addView(carButton4);

        return layout;

    }


}
