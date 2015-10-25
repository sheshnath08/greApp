package com.sheshnath.grevocabularybuilder.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

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
    private WordModel mModel;


    //TODO get Data from server and parse it.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getSharedPreferences("com.sheshnath.grevocabularybuilder",MODE_PRIVATE); // Get preferences file (0 = no option flags set)
        Log.w("WordListActivity", "second time");
        setContentView(R.layout.activity_word_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mRecyclerView = (RecyclerView) findViewById(R.id.wordListView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Words.initialize(preferences);
        mModels = new ArrayList<>();
        for(int i=0;i<Words.WORDS.size();i++)
            {
                mModel = new WordModel(Words.WORDS.get(i),Words.MEANING.get(i),
                        Words.EXAMPLE.get(i),Words.MNEMONICS.get(i));
                mModels.add(mModel);
            }

            mAdapter = new WordListAdapter(this, mModels);
            mRecyclerView.setAdapter(mAdapter);

            mRecyclerView.addOnItemTouchListener(
                    new RecyclerItemClickListner(this, new RecyclerItemClickListner.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Intent intent = new Intent(getBaseContext(), WordCardActivity.class);
                            intent.putExtra("wordNumber",position);
                            startActivity(intent);
                        }
                    })
            );


    }

    @Override
    protected void onResume() {
        super.onResume();
        //startActivity(new Intent(this, LauncherActivity.class));
        preferences = getApplicationContext().getSharedPreferences("com.sheshnath.GREVocabBuilder",MODE_PRIVATE); // Get preferences file (0 = no option flags set)
        Log.w("WordListActivity", "second time");
        setContentView(R.layout.activity_word_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mRecyclerView = (RecyclerView) findViewById(R.id.wordListView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mModels = new ArrayList<>();
        for(int i=0;i<Words.WORDS.size();i++)
        {
            mModel = new WordModel(Words.WORDS.get(i),Words.MEANING.get(i),
                    Words.EXAMPLE.get(i),Words.MNEMONICS.get(i));
            mModels.add(mModel);
        }

        mAdapter = new WordListAdapter(this, mModels);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListner(this, new RecyclerItemClickListner.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getBaseContext(), WordCardActivity.class);
                        intent.putExtra("wordNumber",position);
                        startActivity(intent);
                    }
                })
        );
    }

    /* To create option menu */
    @Override
      public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_word_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.update_menu:
                preferences = getSharedPreferences("com.sheshnath.grevocabularybuilder",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit(); // Open the editor for our settings
                editor.putBoolean("firstRun", true); // It is no longer the first run
                editor.commit(); // Save all changed settings
                Intent intent = new Intent(this,LauncherActivity.class);
                startActivity(intent);
        }
        return true;
    }

}
