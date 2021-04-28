package com.example.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWER_IS_TRUE =
            "com.example.geoquiz.answer_is_true";

    private static final String EXTRA_SCORE =
            "com.example.geoquiz.score";

    private static final String EXTRA_ANSWER_SHOWN =
            "com.example.geoquiz.answer_shown";

    private boolean mAnswerIsTrue;

    //private int mScore;

    private TextView mAnswerTextView;
    private TextView mQuestionTextView;
    private Button mCompareButton;
    private Button mScoreButton;

    public static Intent newIntent(Context packageContext, boolean answerIsTrue, String strScore) {
        Intent intent = new Intent(packageContext, ResultActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        intent.putExtra(EXTRA_SCORE, strScore);

        return intent;
    }

    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    //public static int whatIsScore(Intent result) {
    //    return result.getIntExtra(EXTRA_SCORE, 0);
    //}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        //mScore = getIntent().getIntExtra(EXTRA_SCORE, 0);

        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);

        mCompareButton = (Button) findViewById(R.id.compare_button);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setText(R.string.question_africa);



        mCompareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);

                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);

            }
        });

        mScoreButton = (Button) findViewById(R.id.score_button);
        mScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ResultActivity.this, EXTRA_SCORE, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }
}