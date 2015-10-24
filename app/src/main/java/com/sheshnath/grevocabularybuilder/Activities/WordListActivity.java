package com.sheshnath.grevocabularybuilder.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import com.sheshnath.grevocabularybuilder.R;

import java.util.ArrayList;
import java.util.List;

import Adapters.WordListAdapter;
import Adapters.WordModel;
import DataSource.Words;
import Listners.RecyclerItemClickListner;

public class WordListActivity extends AppCompatActivity {

    SharedPreferences preferences =null;
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;
    private List<WordModel> mModels;

//TODO remove fragment and use only activity.
    //TODO get Data from server and parse it.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.wordListView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        preferences = getSharedPreferences("com.sheshnath.GREVocabularyBuilder",MODE_PRIVATE);
        mModels = new ArrayList<>();
        for (String word : Words.WORDLIST) {
            mModels.add(new WordModel(word,word, word, word));
        }

        mAdapter = new WordListAdapter(this, mModels);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListner(this, new RecyclerItemClickListner.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        Intent intent = new Intent(getBaseContext(), WordCardActivity.class);
                        intent.putExtra("wordNumber",position);
                        startActivity(intent);
                        //getFragmentManager().beginTransaction().replace(R.id.container, WordCardFragment.newInstance()).addToBackStack("word").commit();
                    }
                })
        );

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
