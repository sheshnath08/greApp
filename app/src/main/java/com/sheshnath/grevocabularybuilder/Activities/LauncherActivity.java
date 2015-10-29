package com.sheshnath.grevocabularybuilder.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sheshnath.grevocabularybuilder.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import DataSource.Words;

/**
 * Created by Sheshnath on 10/25/2015.
 */

//TODO: Implement Splash Screen Activity

public class LauncherActivity extends AppCompatActivity {

    SharedPreferences preferences =null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getSharedPreferences("com.sheshnath.grevocabularybuilder",MODE_PRIVATE);         // Get preferences file (0 = no option flags set)
        boolean firstRun = preferences.getBoolean("firstRun", true); // Is it first run? If not specified, use "true"
        Log.d("Into Launcher Activity", firstRun + "");
        setContentView(R.layout.firstrun);
        AnalyticsTrackers.sInstance=null;
        if (firstRun) {
            Log.w("activity", "first time");
            getData();
            SharedPreferences.Editor editor = preferences.edit(); // Open the editor for our settings
            editor.putBoolean("firstRun", false); // It is no longer the first run
            editor.commit(); // Save all changed settings
        }
        else{
            //start wordListActivity
           startMainActivity();
        }
        AnalyticsTrackers.initialize(getApplicationContext());

    }

    private void getData() {
        // Downloading data from below url
        final String url = "http://sheshnath.com/gre-app/wordlist.php";
        new AsyncHttpTask().execute(url);

    }


    public class AsyncHttpTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected void onPreExecute() {
            setProgressBarIndeterminateVisibility(true);
        }

        @Override
        protected Integer doInBackground(String... params) {
            Integer result = 0;
            HttpURLConnection urlConnection;
            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                int statusCode = urlConnection.getResponseCode();
                Log.d("StatusCode", statusCode + "");
                // 200 represents HTTP OK
                if (statusCode == 200) {
                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        response.append(line);
                    }
                    parseResult(response.toString());
                    Log.d("Response String",response.toString());
                    result = 1; // Successful
                } else {
                    result = 0; //"Failed to fetch data!";
                }
            } catch (Exception e) {
                Log.d("Exception", e.getLocalizedMessage());
            }
            return result; //"Failed to fetch data!";
        }

        @Override
        protected void onPostExecute(Integer result) {
            // Download complete. Let us update UI
            if (result == 1) {
               startMainActivity();

            } else {
                Toast.makeText(LauncherActivity.this, "Failed to fetch data!", Toast.LENGTH_LONG).show();
            }
        }

        private void parseResult(String result) {
            SharedPreferences.Editor editor = preferences.edit(); // Open the editor for our settings
            editor.putString("WordJSON",result); // store JSON Array
            editor.commit(); // Save all changed settings
            Words.initialize(preferences);
        }
    }

    private void startMainActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run(){
                Intent intent = new Intent(getBaseContext(), WordListActivity.class);

                startActivity(intent);

                finish();
            }

        },1000);
    }

}
