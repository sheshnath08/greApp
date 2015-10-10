package Adapters;

/**
 * Created by Sheshnath on 10/9/2015.
 */
public class WordModel {
    private final String mWord;
    private final String mMeaning;

    public WordModel(String word, String meaning) {
        mWord = word;
        mMeaning = meaning;
    }

    public String getWord() {
        return mWord;
    }

    public String getMeaning(){
        return mMeaning;
    }
}
