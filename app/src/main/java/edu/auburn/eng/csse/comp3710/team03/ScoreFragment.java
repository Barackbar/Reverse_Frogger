package edu.auburn.eng.csse.comp3710.team03;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by JDSS on 16/4/15.
 */
public class ScoreFragment extends Fragment {

    private MenuView mCallback;

    private int newScore;

    private EditText user;
    TextView tx;
    Button b;
    Button menuButton;
    View rootView;
    ArrayList<String> users;
    ArrayList<Integer> scores;
    int i;
    Highscores db;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (MenuView) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + "must implement MenuView");
        }

        db = mCallback.getDatabase();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            Bundle arguments = this.getArguments();
            newScore = arguments.getInt(getString(R.string.score_id));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.score_layout, container, false);

        i = 0;
        user = (EditText)rootView.findViewById(R.id.editText);
        tx = (TextView) rootView.findViewById(R.id.textView);
        b = (Button)rootView.findViewById(R.id.button);
        menuButton = (Button) rootView.findViewById(R.id.menuButton);

        b.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addInformation(user.getText().toString(), newScore);

                user.setVisibility(View.INVISIBLE);
                b.setVisibility(View.INVISIBLE);

                users = db.pullUsers();
                scores = db.pullScores();
                String temp = "";
                while(i < users.size()) {
                    temp += users.get(i) + " : " + scores.get(i) + "\n";
                    i++;
                }
                tx.setText(temp);

            }
        });

        menuButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.StartMenuView();
            }
        });

        return rootView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
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
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
