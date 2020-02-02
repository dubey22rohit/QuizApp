package com.skorpion.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.*;

public class MainActivity extends AppCompatActivity {
    ;
    public TextView mTxtQuestion;
    public Button btnTrue;
    public Button btnFalse;
    public int questionIndex;
    public int mQuizQuestion;
    public ProgressBar mProgressBar;
    public TextView mQuizStatsTextView;
    public int mUserScore;
    public QuizModel[] questionCollection = new QuizModel[]{
            new QuizModel(R.string.q1,true),
            new QuizModel(R.string.q2,false),
            new QuizModel(R.string.q3,true),
            new QuizModel(R.string.q4,false),
            new QuizModel(R.string.q5,true),
            new QuizModel(R.string.q6,false),
            new QuizModel(R.string.q7,true),
            new QuizModel(R.string.q8,false),
            new QuizModel(R.string.q9,true),
            new QuizModel(R.string.q10,false)
    };
    final int USER_PROGRESS = (int) Math.ceil(100.0 / questionCollection.length);

@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTxtQuestion = findViewById(R.id.testQuestions);
        QuizModel q1 = questionCollection[questionIndex];

        mTxtQuestion.setText(q1.getmQuestion());

        btnTrue = findViewById(R.id.btnTrue);

        mProgressBar = findViewById(R.id.quizPB);
        mQuizStatsTextView = findViewById(R.id.testQuizStatus);

        btnTrue.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                evaluateUserAnswer(true);
                changeQuestionOnButtonClick();

            }
        });

        btnFalse = findViewById(R.id.btnFalse);
        //btnFalse.setOnClickListener(myClickListener);

        btnFalse.setOnClickListener(new OnClickListener() {
            //@Override
            public void onClick(View view) {
                evaluateUserAnswer(false);
                changeQuestionOnButtonClick();
            }
        });

    }
    public void changeQuestionOnButtonClick(){
    questionIndex = (questionIndex + 1) % 10;
    if(questionIndex == 0){
        AlertDialog.Builder quizAlert = new AlertDialog.Builder(this);
        quizAlert.setCancelable(false);
        quizAlert.setTitle("The Quiz is Finished");
        quizAlert.setMessage("Your Score is : " + mUserScore);
        quizAlert.setPositiveButton("Finish The Quiz", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
             finish();
            }
        });
        quizAlert.show();
    }
    mQuizQuestion = questionCollection[questionIndex].getmQuestion();
    mTxtQuestion.setText(mQuizQuestion);
    mProgressBar.incrementProgressBy(USER_PROGRESS);
    mQuizStatsTextView.setText(mUserScore + "");
    }

    public void evaluateUserAnswer(boolean userGuess){
    boolean currentQuestionAnswer = questionCollection[questionIndex].ismAnswer();
    if(currentQuestionAnswer == userGuess){
        //Toast.makeText(getApplicationContext(),R.string.correct_toast_message,Toast.LENGTH_SHORT).show();
        mUserScore+=1;
    }else{
        //Toast.makeText(getApplicationContext(),R.string.incorrect_toast_message,Toast.LENGTH_SHORT).show();
    }
    }
}
