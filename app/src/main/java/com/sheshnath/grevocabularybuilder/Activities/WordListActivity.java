package com.sheshnath.grevocabularybuilder.Activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import com.sheshnath.grevocabularybuilder.R;

import Fragments.WordListFragment;

public class WordListActivity extends AppCompatActivity {

    SharedPreferences preferences =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container,WordListFragment.newInstance())
                    .commit();
        }

        preferences = getSharedPreferences("com.sheshnath.GREVocabularyBuilder",MODE_PRIVATE);

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (preferences.getBoolean("firstrun", true)) {
            // Do first run stuff here then set 'firstrun' as false
            // using the following line to edit/commit prefs
            Toast.makeText(getApplicationContext(),"This is First RUN",Toast.LENGTH_LONG);
            preferences.edit().putBoolean("firstrun", false).commit();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_word_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
