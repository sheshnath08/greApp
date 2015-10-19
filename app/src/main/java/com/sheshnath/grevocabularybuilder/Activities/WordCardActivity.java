package com.sheshnath.grevocabularybuilder.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;

import com.sheshnath.grevocabularybuilder.R;

import Fragments.WordCardFragment;

/**
 * Created by Sheshnath on 10/11/2015.
 */
public class WordCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wordcardfragment);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(WordCardFragment.newInstance(),"")
                    .commit();
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportFragmentManager().beginTransaction().add(WordCardFragment.newInstance(),"")
                    .commit();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_word_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
