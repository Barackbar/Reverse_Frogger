package edu.auburn.eng.csse.comp3710.team03;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;


public class MainActivity extends FragmentActivity {

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
    }
}
