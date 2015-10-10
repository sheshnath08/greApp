package Adapters;

/**
 * Created by Sheshnath on 10/9/2015.
 */
public class WordModel {
    private final String mWord;


    public WordModel(String text) {
        mWord = text;
    }

    public String getText() {
        return mWord;
    }
}
