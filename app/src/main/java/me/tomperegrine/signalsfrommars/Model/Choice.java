package me.tomperegrine.signalsfrommars.Model;

/**
 * Created by Tom on 03/02/2015.
 */
public class Choice {
    private String mText;
    private int mNextPage;

    public Choice(String text, int nextPage) {
        mText = text;
        mNextPage = nextPage;
    }

    public int getNextPage() {
        return mNextPage;
    }

    public void setNextPage(int nextPage) {
        mNextPage = nextPage;
    }

    public String getText() {

        return mText;
    }

    public void setText(String text) {
        mText = text;
    }
}
