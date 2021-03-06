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
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by JDSS on 16/4/15.
 */
public class GameFragment extends Fragment {

    private ScoreView mCallback;

    private     FrogSpace           frogSpace;
    private     View                view;

    private     Timer               timer;
    private     Handler             handler;

    private     Context             context;

    private     ImageButton         lane0Button;
    private     ImageButton         lane1Button;
    private     ImageButton         lane2Button;
    private     ImageButton         lane3Button;

    private     TextView            countdown;

    private     Boolean             lane0SpawnCar;
    private     Boolean             lane1SpawnCar;
    private     Boolean             lane2SpawnCar;
    private     Boolean             lane3SpawnCar;

    private     static final int    FROG_SPAWN_DELAY    =   3;
    private     int                 frogSpawnCountdown;

    private     static final int    REFRESH_DELAY       =   300;
    private     static final int    GAME_DURATION       =   30000;
    private     int                 refreshCounter;

    private int getScore() {
        return 0;
    }

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);

        Log.i("GameFragment", "onAttach");

        try {
            mCallback = (ScoreView) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + "must implement ScoreShow");
        }

        context = activity.getApplicationContext();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Log.i("GameFragment", "onCreate");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        Log.i("GameFragment", "onCreateView");

        if (view != null) {
            ((ViewGroup) view.getParent()).removeView(view);
            return view;
        }

        view                =   inflater.inflate(R.layout.game_layout, container, false);

        frogSpace           =   (FrogSpace) view.findViewById(R.id.frogSpace);

        if (this.getArguments() != null) {
            Bundle arguments = this.getArguments();
            frogSpace.setDifficulty(arguments.getInt(getString(R.string.difficulty_id)));
        }

        lane0Button         =   (ImageButton) view.findViewById(R.id.lane0Button);
        lane1Button         =   (ImageButton) view.findViewById(R.id.lane1Button);
        lane2Button         =   (ImageButton) view.findViewById(R.id.lane2Button);
        lane3Button         =   (ImageButton) view.findViewById(R.id.lane3Button);

        countdown           =   (TextView) view.findViewById(R.id.countdown);

        lane0SpawnCar       =   false;
        lane1SpawnCar       =   false;
        lane2SpawnCar       =   false;
        lane3SpawnCar       =   false;

        frogSpawnCountdown  =   FROG_SPAWN_DELAY;
        refreshCounter      =   0;

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        Log.i("GameFragment", "onActivityCreated");

        if (savedInstanceState != null) {
            frogSpace.onRestoreInstanceState(savedInstanceState);
        }

        lane0Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lane0SpawnCar = true;
            }
        });

        lane1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lane1SpawnCar = true;
            }
        });

        lane2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lane2SpawnCar = true;
            }
        });

        lane3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lane3SpawnCar = true;
            }
        });

    }

    @Override
    public void onStart() {

        super.onStart();

        Log.i("GameFragment", "onStart");

    }

    @Override
    public void onResume() {

        super.onResume();

        Log.i("GameFragment", "onResume");

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {

                super.handleMessage(msg);

                //spawn cars
                if (lane0SpawnCar) {
                    frogSpace.spawnCar(0);
                    lane0SpawnCar = false;
                }
                if (lane1SpawnCar) {
                    frogSpace.spawnCar(1);
                    lane1SpawnCar  = false;
                }
                if (lane2SpawnCar) {
                    frogSpace.spawnCar(2);
                    lane2SpawnCar = false;
                }
                if (lane3SpawnCar) {
                    frogSpace.spawnCar(3);
                    lane3SpawnCar = false;
                }

                //spawn frogs
                if (frogSpawnCountdown == 0) {
                    frogSpace.spawnFrog();
                    frogSpawnCountdown = FROG_SPAWN_DELAY;
                }
                else
                    frogSpawnCountdown--;

                //update and redraw
                Log.i("GameFragment", "frogSpace.invalidate()");
                //TODO: add countdown timer to landscape layout
                //countdown.setText("Time Left: " + Integer.toString((GAME_DURATION - (REFRESH_DELAY * refreshCounter))/ 1000));
                frogSpace.invalidate();

                //check game timer
                if (refreshCounter == GAME_DURATION / REFRESH_DELAY) {
                    Log.i("GameFragment", "mCallback.StartScoreView()");
                    Bundle bundle = new Bundle();
                    bundle.putInt(getString(R.string.score_id), frogSpace.getFrogsHit() - frogSpace.getFrogsEscaped());
                    mCallback.StartScoreView(bundle);
                }
                else
                    refreshCounter++;
            }
        };

        timer = new Timer("timer", false);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                handler.obtainMessage(1).sendToTarget();
            }
        }, 0, REFRESH_DELAY);

    }

    @Override
    public void onPause() {

        super.onPause();

        Log.i("GameFragment", "onPause");

        timer.cancel();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.i("GameFragment", "onSaveInstanceState");

        frogSpace.onSaveInstanceState(outState);

    }
}
