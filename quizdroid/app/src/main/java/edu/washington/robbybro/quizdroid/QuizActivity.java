package edu.washington.robbybro.quizdroid;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class QuizActivity extends ActionBarActivity {

    private ArrayList<Question> questions;
    private int currentQuestionIndex;
    private Question currentQuestion;
    private int numCorrect;
    public int numQuestions;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_detail);

        // Get the Intent that opened this activity
        Intent launchedMe = getIntent();
        final String topic = launchedMe.getStringExtra("topic");
        setTitle(topic);

        questions = QuizApp.getInstance().getQuestionList();
        numQuestions = questions.size();
        fragmentManager = getFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        TopicOverviewFragment topicDetailFragment = TopicOverviewFragment.newInstance(topic, numQuestions);
        fragmentTransaction.replace(R.id.content_frame, topicDetailFragment);
        fragmentTransaction.commit();
    }

    public void showNextQuestion() {
        currentQuestionIndex = QuizApp.getInstance().getCurrentQuestion();
        currentQuestion = QuizApp.getInstance().getQuestionList().get(currentQuestionIndex);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_left);

        QuestionFragment questionFragment = QuestionFragment.newInstance(currentQuestionIndex, currentQuestion);
        fragmentTransaction.replace(R.id.content_frame, questionFragment);
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentTransaction.commit();
    }

    public void showAnswer(int guess) {
        if(currentQuestion.isCorrect(guess)){
            numCorrect++;
        }

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_left);
        boolean moreQuestions = numQuestions > currentQuestionIndex + 1;
        AnswerFragment answerFragment = AnswerFragment.newInstance(QuizApp.getInstance().getAnswerList().get(guess), currentQuestion.getCorrectAnswer(), numCorrect, numQuestions, moreQuestions);
        fragmentTransaction.replace(R.id.content_frame, answerFragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_topic_detail, menu);
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