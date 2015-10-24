package com.sheshnath.grevocabularybuilder.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

import com.sheshnath.grevocabularybuilder.R;

import Adapters.WordModel;
import DataSource.Words;

/**
 * Created by Sheshnath on 10/11/2015.
 */
public class WordCardActivity extends AppCompatActivity {

    private TextView mWord;
    private TextView mWordMeaning;
    private TextView mWordExample;
    private TextView mWordMnemonics;
    private WordModel mModel;
    private int wordPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wordcardview);
        mWord =(TextView)findViewById(R.id.wordCardWord);
        mWordMeaning = (TextView)findViewById(R.id.wordCardmeaning);
        mWordExample = (TextView)findViewById(R.id.wordCardExample);
        mWordMnemonics = (TextView)findViewById(R.id.wordCardMnemonics);
        Intent intent = getIntent();
        wordPosition = intent.getIntExtra("wordNumber",0);
        mModel = new WordModel(Words.WORDLIST[wordPosition],Words.WORDLIST[wordPosition],
                Words.WORDLIST[wordPosition],Words.WORDLIST[wordPosition]);
        mWord.setText(mModel.getWord());
        mWordMeaning.setText(mModel.getMeaning());
        mWordExample.setText(mModel.getExample());
        mWordMnemonics.setText(mModel.getMnemonics());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
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
