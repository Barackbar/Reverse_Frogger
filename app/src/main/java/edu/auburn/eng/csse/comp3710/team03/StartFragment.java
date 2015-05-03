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

    private static final int MIN_DIFFICULTY = 1;
    private static final int MAX_DIFFICULTY = 3;

    GameView mCallback;
    private View v;
    private Button startGameButton, difficultyButton;
    private int difficulty;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.start_layout, parent, false);

        startGameButton = (Button)v.findViewById(R.id.start_button);
        startGameButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle arguments = new Bundle();
                arguments.putInt(getString(R.string.difficulty_id), difficulty);
                mCallback.StartGameView(arguments);
            }
        });

        difficultyButton = (Button)v.findViewById(R.id.difficulty_button);
        difficultyButton.setOnClickListener(new Button.OnClickListener() {
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
            mCallback = (GameView) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + "must implement startGame");
        }
    }

    public void changeDifficulty() {
        if (difficulty < MAX_DIFFICULTY) {
            difficulty++;
        }
        else {
            difficulty = MIN_DIFFICULTY;
        }
        Toast.makeText(this.getActivity().getApplicationContext(), "Difficulty: " + Integer.toString(difficulty), Toast.LENGTH_SHORT).show();
    }
}
