package edu.washington.robbybro.quizdroid;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class TopicDetailActivity extends ActionBarActivity {

    private ArrayList<Question> questions;
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


        questions = getQuestionsForTopic(topic);
        numQuestions = questions.size();
        fragmentManager = getFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        TopicOverviewFragment topicDetailFragment = TopicOverviewFragment.newInstance(topic, numQuestions);
        fragmentTransaction.replace(R.id.content_frame, topicDetailFragment);
        fragmentTransaction.commit();
    }

    public void showNextQuestion() {
        currentQuestion = questions.remove(0);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_left);

        QuestionFragment questionFragment = QuestionFragment.newInstance(currentQuestion);
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
        AnswerFragment answerFragment = AnswerFragment.newInstance(currentQuestion.getAnswers()[guess], currentQuestion.getCorrectAnswer(), numCorrect, numQuestions, !questions.isEmpty());
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

    public ArrayList<Question> getQuestionsForTopic(String topic){
        return getQuestions().get(topic);
    }

    private Map<String, ArrayList<Question>> getQuestions(){
        Map<String, ArrayList<Question>> questions = new HashMap<>();

        // Math
        ArrayList<Question> temp = new ArrayList<Question>();
        temp.add(new Question("What is 1 + 1", new String[]{"1", "2", "3", "69"}, 1));
        temp.add(new Question("What is the answer to life, the universe, and everything?", new String[]{"42", "that's not a math question...", "it kind of is", "2"}, 0));
        temp.add(new Question("What is the sum of the edges and corners of a quadrilateral?", new String[]{"b", "0", "8", "9006"}, 2));
        questions.put("Math", temp);

        // Physics
        temp = new ArrayList<Question>();
        temp.add(new Question("What is Newton's first law of motion?", new String[]{"The total energy of an isolated system is constant; energy can be transformed from one form to another, but cannot be created or destroyed.", "Every object in a state of uniform motion tends to remain in that state of motion unless an external force is applied to it.", "For every action there is an equal and opposite reaction.", "The relationship between an object's mass m, its acceleration a, and the applied force F is F = ma. Acceleration and force are vectors (as indicated by their symbols being displayed in slant bold font); in this law the direction of the force vector is the same as the direction of the acceleration vector."}, 1));
        temp.add(new Question("What is Newton's second law of motion?", new String[]{"The total energy of an isolated system is constant; energy can be transformed from one form to another, but cannot be created or destroyed.", "Every object in a state of uniform motion tends to remain in that state of motion unless an external force is applied to it.", "For every action there is an equal and opposite reaction.", "The relationship between an object's mass m, its acceleration a, and the applied force F is F = ma. Acceleration and force are vectors (as indicated by their symbols being displayed in slant bold font); in this law the direction of the force vector is the same as the direction of the acceleration vector."}, 3));
        temp.add(new Question("What is Newton's third law of motion?", new String[]{"The total energy of an isolated system is constant; energy can be transformed from one form to another, but cannot be created or destroyed.", "Every object in a state of uniform motion tends to remain in that state of motion unless an external force is applied to it.", "For every action there is an equal and opposite reaction.", "The relationship between an object's mass m, its acceleration a, and the applied force F is F = ma. Acceleration and force are vectors (as indicated by their symbols being displayed in slant bold font); in this law the direction of the force vector is the same as the direction of the acceleration vector."}, 2));
        temp.add(new Question("What is the first law of thermodynamics?", new String[]{"The total energy of an isolated system is constant; energy can be transformed from one form to another, but cannot be created or destroyed.", "Every object in a state of uniform motion tends to remain in that state of motion unless an external force is applied to it.", "For every action there is an equal and opposite reaction.", "The relationship between an object's mass m, its acceleration a, and the applied force F is F = ma. Acceleration and force are vectors (as indicated by their symbols being displayed in slant bold font); in this law the direction of the force vector is the same as the direction of the acceleration vector."}, 0));
        questions.put("Physics", temp);

        // Marvel Superheroes
        temp = new ArrayList<Question>();
        temp.add(new Question("Who plays Obadiah Stane in Iron Man?", new String[]{"Robert Downey Jr.", "Jeff Bridges", "Ted Neward", "Emma Stone"}, 1));
        temp.add(new Question("What color is The Hulk?", new String[]{"Sour Apple", "Forest Green", "Mountain Dew Green", "Hulk Green"}, 3));
        questions.put("Marvel Super Heroes", temp);
        return questions;
    }

}
