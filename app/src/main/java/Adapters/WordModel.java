package Adapters;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Sheshnath on 10/9/2015.
 */
public class WordModel {
    private String mWord;
    private String mMeaning;
    private String mMnemonics;
    private String mExample;


    public WordModel(String mWord,String mMeaning,String mExample,String mMnemonics){
        this.mWord=mWord;
        this.mMeaning = mMeaning;
        this.mExample = mExample;
        this.mMnemonics = mMnemonics;
    }

    public void setmExample(String mExample) {
        this.mExample = mExample;
    }

    public void setmWord(String mWord) {
        this.mWord = mWord;
    }

    public void setmMeaning(String mMeaning) {
        this.mMeaning = mMeaning;
    }

    public void setmMnemonics(String mMnemonics) {
        this.mMnemonics = mMnemonics;
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
