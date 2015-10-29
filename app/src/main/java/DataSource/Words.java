package DataSource;

import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Amit-PC on 10/24/2015.
 */
public class Words {
        //TODO: get data from shared preference and server


    public static final ArrayList<String> WORDS = new ArrayList<String>();
    public static final ArrayList<String> MEANING = new ArrayList<String>();
    public static final ArrayList<String> EXAMPLE = new ArrayList<String>();
    public static final ArrayList<String> MNEMONICS = new ArrayList<String>();

    public static final int MAXWORD=WORDS.size();

    public static void initialize(SharedPreferences preferences){
        WORDS.clear();
        MEANING.clear();
        EXAMPLE.clear();
        MNEMONICS.clear();
        String jsonStringWord = preferences.getString("WordJSON","");
        try {
            JSONArray jsonArray = new JSONArray(jsonStringWord);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                WORDS.add(jsonObject.getString("word"));
                MEANING.add(jsonObject.getString("meaning"));
                EXAMPLE.add(jsonObject.getString("example"));
                MNEMONICS.add(jsonObject.getString("mnemonic"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
