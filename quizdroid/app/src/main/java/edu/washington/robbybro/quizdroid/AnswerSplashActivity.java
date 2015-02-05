package edu.washington.robbybro.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class AnswerSplashActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_summary);

        //Get data from parent activity
        Intent launchedMe = getIntent();
        final ArrayList<Question> questions = (ArrayList<Question>) launchedMe.getSerializableExtra("questions");
        final int numQuestions = launchedMe.getIntExtra("numQuestions", 0);
        final int numCorrect = launchedMe.getIntExtra("numCorrect", 0);
        final String topic = launchedMe.getStringExtra("topic");
        final Question prevQuestion = (Question) launchedMe.getSerializableExtra("prevQuestion");
        final String guess = launchedMe.getStringExtra("guess");

        TextView correctAnswer = (TextView) findViewById(R.id.correctAnswer);
        correctAnswer.setText(prevQuestion.getCorrectAnswer());

        TextView yourAnswer = (TextView) findViewById(R.id.yourAnswer);
        yourAnswer.setText(guess);

        TextView correctCount = (TextView) findViewById(R.id.correctCount);
        correctCount.setText(getString(R.string.correct_count_text, numCorrect, numQuestions));

        Button next_btn = (Button) findViewById(R.id.moveOn_btn);
        Log.i("quiz", "Questions left: " + questions.size());
        final boolean moreQuestions = (questions.size() > 0);
        next_btn.setText((moreQuestions) ? "Next" : "Finish");
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (moreQuestions){
                    Intent nextQuestion = new Intent(AnswerSplashActivity.this, AskQuestionActivity.class);
                    nextQuestion.putExtra("questions", questions);
                    nextQuestion.putExtra("numCorrect", numCorrect);
                    nextQuestion.putExtra("numQuestions", numQuestions);
                    nextQuestion.putExtra("topic", topic);
                    startActivity(nextQuestion);
                } else {
                    Intent topic = new Intent(AnswerSplashActivity.this, SelectTopicActivity.class);
                    startActivity(topic);
                }
                finish();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_answer_summary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
