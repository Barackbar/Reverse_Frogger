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
import android.widget.LinearLayout;

/**
 * Created by JDSS on 16/4/15.
 */
public class GameFragment extends Fragment {



    //Assume nothing in this file will work
    //not sure what we can do with respect to the whole View, ViewGroup, Layout stuff
    //basically need to make frogSpace "View" that we can draw onto,
    //but also need some buttons in the view, meaning it needs to be a ViewGroup of some kind
    //not sure what we can get away with



    //changing frogSpace to a LinearLayout might do away with the need for ViewGroup layout
    private ViewGroup layout;
    private LinearLayout frogSpace;
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
        if (frogSpace != null) {
            frogSpace.removeView(frogSpace);
            return frogSpace;
        }

        //not sure if below code will work

        //frogSpace.addView();
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
