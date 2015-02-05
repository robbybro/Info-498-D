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
import java.util.HashMap;
import java.util.Map;


public class TopicDetailActivity extends ActionBarActivity {

    private String topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_detail);

        // Get the Intent that opened this activity
        Intent launchedMe = getIntent();
        String timestamp = launchedMe.getStringExtra("timestamp");  // get data that was passed from first activity
        topic = launchedMe.getStringExtra("topic");
        TextView title = (TextView) findViewById(R.id.topic_title);
        title.setText(topic);

        TextView description = (TextView) findViewById(R.id.topic_description);
        description.setText(getTopicDetail(topic));

        TextView question_count = (TextView) findViewById(R.id.question_count);
        question_count.setText(getString(R.string.num_questions, getQuestionCount(topic)));

        Button btn_begin = (Button) findViewById(R.id.btn_begin);
        btn_begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("quiz", "Starting quiz " + topic);
                Intent nextActivity = new Intent(TopicDetailActivity.this, AskQuestionActivity.class);

                //Gather quiz questions
                ArrayList<Question> questions = getQuestionsForTopic(topic);
                nextActivity.putExtra("questions", questions);
                nextActivity.putExtra("numCorrect", 0);
                nextActivity.putExtra("numQuestions", questions.size());
                nextActivity.putExtra("topic", topic);
                startActivity(nextActivity);
            }
        });
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

    private String getTopicDetail(String topic){
        switch (topic){
            case "Math":
                return "The universal language";
            case "Physics":
                return "math with application";
            case "Marvel Super Heroes":
                return "Not DC";
            default:
                return "";
        }
    }

    private int getQuestionCount(String topic){
        return getQuestionsForTopic(topic).size();
    }

    private ArrayList<Question> getQuestionsForTopic(String topic){
        return getQuestions().get(topic);
    }

    private Map<String, ArrayList<Question>> getQuestions(){
        Map<String, ArrayList<Question>> questions = new HashMap<>();

        // Math
        ArrayList<Question> temp = new ArrayList<Question>();
        temp.add(new Question("What is 1 + 1", new String[]{"1", "2", "3", "69"}, 2));
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
