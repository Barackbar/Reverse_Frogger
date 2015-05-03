package edu.auburn.eng.csse.comp3710.team03;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

    private static final String TEXTVIEW_TEXT_ID = "textview";
    private static final String NAME_ENTERED_ID = "nameentered";
    private static final String NEW_SCORE_ID = "newscore";

    private MenuView mCallback;

    private View rootView;

    private EditText editText;
    private TextView textView;
    private Button enterButton;
    private Button menuButton;

    private int newScore;
    private ArrayList<String> users;
    private ArrayList<Integer> scores;
    private Boolean nameEntered = false;

    private Highscores db;

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);

        try {
            mCallback = (MenuView) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + "must implement MenuView");
        }
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

        if (rootView != null) {
            ((ViewGroup) rootView.getParent()).removeView(rootView);
            return rootView;
        }

        db          =               mCallback.getDatabase();

        rootView    =               inflater.inflate(R.layout.score_layout, container, false);

        editText    =   (EditText)  rootView.findViewById(R.id.editText);
        textView    =   (TextView)  rootView.findViewById(R.id.textView);
        enterButton =   (Button)    rootView.findViewById(R.id.button);
        menuButton  =   (Button)    rootView.findViewById(R.id.menuButton);
        menuButton.setVisibility(View.INVISIBLE);

        enterButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.addInformation(editText.getText().toString(), newScore);

                editText.setVisibility(View.INVISIBLE);
                enterButton.setVisibility(View.INVISIBLE);

                users = db.pullUsers();
                scores = db.pullScores();
                String temp = "";

                for (int i = users.size() - 1; i >= 0; i--) {
                    temp += users.get(i) + " : " + scores.get(i) + "\n";
                }

                textView.setText(temp);
                nameEntered = true;
                menuButton.setVisibility(View.VISIBLE);

            }
        });

        menuButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.StartMenuView();
            }
        });


        if (savedInstanceState != null) {
            if (savedInstanceState.getBoolean(NAME_ENTERED_ID)) {
                nameEntered = true;
                editText.setVisibility(View.INVISIBLE);
                enterButton.setVisibility(View.INVISIBLE);
                textView.setText(savedInstanceState.getString(TEXTVIEW_TEXT_ID));
            }
            else {
                newScore = savedInstanceState.getInt(NEW_SCORE_ID);
                nameEntered = false;
            }
        }

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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TEXTVIEW_TEXT_ID, textView.getText().toString());
        outState.putBoolean(NAME_ENTERED_ID, nameEntered);
        outState.putInt(NEW_SCORE_ID, newScore);
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
