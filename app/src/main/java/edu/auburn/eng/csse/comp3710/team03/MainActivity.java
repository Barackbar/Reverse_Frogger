package edu.auburn.eng.csse.comp3710.team03;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;


public class MainActivity extends FragmentActivity  implements GameView, ScoreView, MenuView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment startFragment = fragmentManager.findFragmentById(R.id.main_layout);

        setContentView(R.layout.main_layout);

        if (startFragment == null) {
            startFragment = new StartFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.main_layout, startFragment)
                    .commit();
        }
    }

    public void StartGameView() {
        GameFragment gameFragment = (GameFragment)
                getSupportFragmentManager().findFragmentById(R.layout.game_layout);
        if (gameFragment == null) {
            GameFragment gFrag = new GameFragment();
            Bundle args = new Bundle();

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_layout, gFrag);
            transaction.addToBackStack(null);
            transaction.commit();
        }

    }

    public void StartScoreView(Bundle bundle) {
        ScoreFragment scoreFragment = (ScoreFragment)
                getSupportFragmentManager().findFragmentById(R.id.score_layout);
        if (scoreFragment == null) {
            ScoreFragment sFrag = new ScoreFragment();
            sFrag.setArguments(bundle);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_layout, sFrag);
            //TODO: make sure removing addToBackStack will prevent user from navigating back to GameFragment
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    public void StartMenuView() {
        StartFragment  startFrag = (StartFragment)
                getSupportFragmentManager().findFragmentById(R.id.start_fragment);

        if(startFrag == null) {
            StartFragment stFrag = new StartFragment();
            Bundle args = new Bundle();

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_layout, stFrag);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.i("MainActivity", "onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.i("MainActivity", "onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
    }

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment gameFragment = fragmentManager.findFragmentById(R.id.main_layout);

        setContentView(R.layout.main_layout);

        if (gameFragment == null) {
            gameFragment = new GameFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.main_layout, gameFragment)
                    .commit();
        }
    }*/
}
