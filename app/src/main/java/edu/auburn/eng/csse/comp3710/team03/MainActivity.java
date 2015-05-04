package edu.auburn.eng.csse.comp3710.team03;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;


public class MainActivity extends FragmentActivity  implements GameView, ScoreView, MenuView {

    Highscores db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new Highscores(getApplicationContext());

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment splashFragment = fragmentManager.findFragmentById(R.id.main_layout);

        setContentView(R.layout.main_layout);

        if (splashFragment == null) {
            splashFragment = new SplashFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.main_layout, splashFragment)
                    .commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void StartGameView(Bundle arguments) {
        GameFragment gameFragment = (GameFragment)
                getSupportFragmentManager().findFragmentById(R.layout.game_layout);
        if (gameFragment == null) {
            GameFragment gFrag = new GameFragment();
            gFrag.setArguments(arguments);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_layout, gFrag);
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
            transaction.commit();
        }
    }

    @Override
    public Highscores getDatabase() {
        return db;
    }

    public void StartMenuView() {
        StartFragment  startFrag = (StartFragment)
                getSupportFragmentManager().findFragmentById(R.id.start_layout);

        if(startFrag == null) {
            StartFragment stFrag = new StartFragment();
            Bundle args = new Bundle();

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_layout, stFrag);
            transaction.commit();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
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

}
