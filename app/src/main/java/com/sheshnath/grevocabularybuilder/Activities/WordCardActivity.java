package com.sheshnath.grevocabularybuilder.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.sheshnath.grevocabularybuilder.R;
import Adapters.WordModel;
import DataSource.Words;

/**
 * Created by Sheshnath on 10/11/2015.
 */
public class WordCardActivity extends AppCompatActivity
{

    private TextView mWord;
    private TextView mWordMeaning;
    private TextView mWordExample;
    private TextView mWordMnemonics;
    private WordModel mModel;
    private RelativeLayout mContainer;
    private static int wordPosition;
    private float width;
    private float velocityMin;
    private float flingMin;


    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wordcardview);
        mWord =(TextView)findViewById(R.id.wordCardWord);
        mWordMeaning = (TextView)findViewById(R.id.wordCardmeaning);
        mWordExample = (TextView)findViewById(R.id.wordCardExample);
        mWordMnemonics = (TextView)findViewById(R.id.wordCardMnemonics);
        mContainer = (RelativeLayout)findViewById(R.id.container);
        Intent intent = getIntent();
        wordPosition = intent.getIntExtra("wordNumber",0);
        Log.i("Wordposition from list:",wordPosition+"");
        setData();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // to show back arrow on action bar
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());
        flingMin = 100;
        velocityMin=100;
        trackScreen();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event){
            this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }


    @Override
    protected void onResume() {
        super.onResume();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_word_list, menu);
        return super.onCreateOptionsMenu(menu);
    }*/

    private void setData(){
        mModel = new WordModel(Words.WORDS.get(wordPosition),Words.MEANING.get(wordPosition),
                Words.EXAMPLE.get(wordPosition),Words.MNEMONICS.get(wordPosition));
        //Change above line for JSON data
        mWord.setText(mModel.getWord());
        mWordMeaning.setText(mModel.getMeaning());
        mWordExample.setText(mModel.getExample());
        mWordMnemonics.setText(mModel.getMnemonics());
    }

    private void nextWord(){
        wordPosition++;
        if(wordPosition==Words.WORDS.size()){
            wordPosition=0;
        }

        setData();
    }

    private void previousWord(){
        wordPosition--;
        if(wordPosition<0){
            wordPosition=Words.WORDS.size()-1;
        }
        setData();
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";

        @Override
        public boolean onDown(MotionEvent event) {
            Log.d(DEBUG_TAG,"onDown: " + event.toString());
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            Log.d(DEBUG_TAG, "onFling: " + event1.toString()+event2.toString());
            //calculate the change in X position within the fling gesture
            float horizontalDiff = event2.getX() - event1.getX();
            //calculate the change in Y position within the fling gesture
            float verticalDiff = event2.getY() - event1.getY();

            float absHDiff = Math.abs(horizontalDiff);
            float absVDiff = Math.abs(verticalDiff);
            float absVelocityX = Math.abs(velocityX);
            float absVelocityY = Math.abs(velocityY);

            if(absHDiff>absVDiff && absHDiff>flingMin && absVelocityX>velocityMin){
            //move forward or backward
                if(horizontalDiff>0) {
                    previousWord();
                }
                else {
                    nextWord();
                }
            }
            else if(absVDiff>flingMin && absVelocityY>velocityMin){
                if(verticalDiff>0) {
                    previousWord();
                }
                else {
                    nextWord();
                }
            }
            return true;
        }
    }

    private void trackScreen(){
        final Tracker tracker = AnalyticsTrackers.getInstance().get(AnalyticsTrackers.Target.SCREEN);
        if(tracker != null){
            tracker.setScreenName(getClass().getSimpleName());
            tracker.send(new HitBuilders.ScreenViewBuilder().build());
        }
    }

}
