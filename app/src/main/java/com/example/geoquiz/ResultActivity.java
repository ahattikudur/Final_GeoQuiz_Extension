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

    private double mScore;

    private TextView mAnswerTextView;
    private TextView mQuestionTextView;
    private Button mNumCorrectButton;
    private Button mNumIncorrectButton;
    private Button mScoreButton;

    public static Intent newIntent(Context packageContext, boolean answerIsTrue, double score) {
        Intent intent = new Intent(packageContext, ResultActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        intent.putExtra(EXTRA_SCORE, score);
        return intent;
    }

    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);

        mScore = getIntent().getDoubleExtra(EXTRA_SCORE, 0);
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setText(R.string.res_prompt);


        mNumCorrectButton = (Button) findViewById(R.id.num_correct_button);
        mNumCorrectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strScore = String.valueOf(mScore);
                mQuestionTextView.setText(R.string.num_correct_button);
                mAnswerTextView.setText(strScore);
            }
        });

        mNumIncorrectButton = (Button) findViewById(R.id.num_incorrect_button);
        mNumIncorrectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strScore = String.valueOf(6 - mScore);
                mQuestionTextView.setText(R.string.num_incorrect_button);
                mAnswerTextView.setText(strScore);
            }
        });

        mScoreButton = (Button) findViewById(R.id.score_button);
        mScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strScore = String.valueOf(Math.floor(mScore / 6.0 * 100));
                mQuestionTextView.setText(R.string.score_button);
                mAnswerTextView.setText(strScore + "%");
                Toast.makeText(ResultActivity.this, strScore + "%", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }
}