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
        super.onCreateView(inflater, container, savedInstanceState);


        return layout;

    }


}
