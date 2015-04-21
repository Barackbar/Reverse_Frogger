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
import android.widget.ImageButton;
import android.widget.LinearLayout;

/**
 * Created by JDSS on 16/4/15.
 */
public class GameFragment extends Fragment {

    private View frogSpace;
    private View view;

    private Context context;

    private FrogController frogController;
    private CarController carController;

    private ImageButton lane0Button;
    private ImageButton lane1Button;
    private ImageButton lane2Button;
    private ImageButton lane3Button;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity.getApplicationContext();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view != null) {
            ((ViewGroup) view.getParent()).removeView(view);
            return view;
        }

        view = inflater.inflate(R.layout.game_layout, container, false);

        frogSpace = (FrogSpace) view.findViewById(R.id.frogSpace);

        lane0Button = (ImageButton) view.findViewById(R.id.lane0Button);
        lane1Button = (ImageButton) view.findViewById(R.id.lane1Button);
        lane2Button = (ImageButton) view.findViewById(R.id.lane2Button);
        lane3Button = (ImageButton) view.findViewById(R.id.lane3Button);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
