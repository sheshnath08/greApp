package com.sheshnath.grevocabularybuilder.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sheshnath.grevocabularybuilder.R;

import Fragments.WordListFragment;

public class WordListActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container,WordListFragment.newInstance())
                    .commit();
        }


    }

}
