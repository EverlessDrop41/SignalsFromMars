package me.tomperegrine.signalsfrommars.UI;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import me.tomperegrine.signalsfrommars.Model.Page;
import me.tomperegrine.signalsfrommars.Model.Story;
import me.tomperegrine.signalsfrommars.R;


public class StoryActivity extends ActionBarActivity {

    public static final String TAG = StoryActivity.class.getSimpleName();

    private Story mStory = new Story();

    private ImageView mImageView;
    private TextView mStoryTextView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private String mName;
    private Page mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        Intent intent = getIntent();
        mName = intent.getStringExtra(getString(R.string.nameIntentExtra));

        if (mName == null){
            mName = "NO NAME";
        }

        mImageView = (ImageView)findViewById(R.id.storyImageView);
        mStoryTextView = (TextView)findViewById(R.id.storyTextView);
        mButtonChoice1 = (Button)findViewById(R.id.choiceButton1);
        mButtonChoice2 = (Button)findViewById(R.id.choiceButton2);

        loadPage(0);
    }

    private void loadPage(int pageId) {
        mCurrentPage = mStory.getPage(pageId);
        mImageView.setImageDrawable(getResources().getDrawable(mCurrentPage.getImageId())  );

        String pageText = mCurrentPage.getText();
        pageText = String.format(pageText,mName);
        mStoryTextView.setText(pageText);
        if (!mCurrentPage.isFinal()) {
            mButtonChoice1.setText(mCurrentPage.getChoice1().getText());
            mButtonChoice2.setText(mCurrentPage.getChoice2().getText());

            mButtonChoice1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadPage(mCurrentPage.getChoice1().getNextPage());
                }
            });

            mButtonChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadPage(mCurrentPage.getChoice2().getNextPage());
                }
            });
        }
        else {
            mButtonChoice1.setVisibility(View.INVISIBLE);
            mButtonChoice2.setText("PLAY AGAIN");

            mButtonChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }
}
