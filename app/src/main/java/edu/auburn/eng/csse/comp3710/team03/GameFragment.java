package edu.auburn.eng.csse.comp3710.team03;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by JDSS on 16/4/15.
 */
public class GameFragment extends Fragment {

    private View frogSpace;
    private View view;

    private Timer timer;
    private Handler handler;

    private Context context;

    private FrogController frogController;
    private CarController carController;

    private ImageButton lane0Button;
    private ImageButton lane1Button;
    private ImageButton lane2Button;
    private ImageButton lane3Button;

    private int frogsEscaped;
    private int frogsHit;

    @Override
    public void onAttach(Activity activity) {
        Log.i("GameFragment", "onAttach");
        super.onAttach(activity);
        context = activity.getApplicationContext();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("GameFragment", "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("GameFragment", "onCreateView");

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
        Log.i("GameFragment", "onActivityCreated");

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.i("GameFragment", "onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.i("GameFragment", "onResume");
        super.onResume();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.i("GameFragment", "frogSpace.invalidate()");
                frogSpace.invalidate();

            }
        };

        timer = new Timer("timer", false);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                handler.obtainMessage(1).sendToTarget();
            }
        }, 0, 300);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.i("GameFragment", "onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }
}
