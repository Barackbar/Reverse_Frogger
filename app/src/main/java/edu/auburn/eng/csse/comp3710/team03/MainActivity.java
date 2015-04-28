package edu.auburn.eng.csse.comp3710.team03;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


public class MainActivity extends FragmentActivity  implements StartGame {

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

    public void gameStart() {
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
