package Adapters;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Sheshnath on 10/9/2015.
 */
public class WordModel {
    private final String mWord;
    private final String mMeaning;
    private final String mMnemonics;
    private final String mExample;

    //TODO use getter and setters.

    public WordModel(String word, String meaning, String mMnemonics, String mExample) {
        mWord = word;
        mMeaning = meaning;
        this.mMnemonics = mMnemonics;
        this.mExample = mExample;
    }


    //This is to initialize single word usingJSON array.
    public WordModel(JSONArray word) throws JSONException {
        mWord = word.getString(0);
        mMeaning = word.getString(1);
        mExample = word.getString(2);
        mMnemonics = word.getString(3);
    }

    public String getWord() {
        return mWord;
    }

    public String getMeaning() {
        return mMeaning;
    }

    public String getMnemonics() {
        return mMnemonics;
    }

    public String getExample() {
        return mExample;
    }


}
