package edu.auburn.eng.csse.comp3710.team03;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.lang.ClassCastException;
import java.lang.Override;

/**
 * Created by JDSS on 28/4/15.
 */
public class StartFragment extends Fragment {

    StartGame mCallback;
    private View v;
    private Button start_Game, sound, difficulty;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.start_fragment, parent, false);

        start_Game = (Button)v.findViewById(R.id.start_button);
        start_Game.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.gameStart();
            }
        });

        sound = (Button)v.findViewById(R.id.sound_button);
        sound.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeSound();
            }
        });

        difficulty = (Button)v.findViewById(R.id.difficulty_button);
        difficulty.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeDifficulty();
            }
        });

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (StartGame) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + "must implement startGame");
        }
    }

    public void changeSound() {
        Highscores HS = new Highscores(getActivity().getApplicationContext());
        HS.addInformation("MY DICK", 100);
        HS.addInformation("YOUR DICK", 50);
        HS.addInformation("HIS DICK", 0);
        String[] users = HS.pullUsers();
        int[] scores = HS.pullScores();
        Toast.makeText(getActivity().getApplicationContext(), users[0] + " " + Integer.toString(scores[0]), Toast.LENGTH_SHORT).show();
    }

    public void changeDifficulty() {

    }
}
