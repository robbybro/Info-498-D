package edu.washington.robbybro.quizdroid;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class TopicDetailActivity extends ActionBarActivity {

    private String topic;
    private int questionCount;
    private ArrayList<Question> questions;
    private int numCorrect;
    private String guess;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_detail);

        // Get the Intent that opened this activity
        Intent launchedMe = getIntent();
        topic = launchedMe.getStringExtra("topic");
        final TextView title = (TextView) findViewById(R.id.topic_title);
        title.setText(topic);
        // quiz just started
        questionCount = 0;

        final TextView description = (TextView) findViewById(R.id.topic_description);
        description.setText(getTopicDetail(topic));

        final TextView question_count = (TextView) findViewById(R.id.question_count);
        question_count.setText(getString(R.string.num_questions, getQuestionCount(topic)));

        // set up fragments
        final Fragment questionFragment = new QuestionFragment();
        final Fragment answerFragment = new AnswerFragment();
        fragmentManager = getFragmentManager();

        final Button btn_begin = (Button) findViewById(R.id.btn_begin);
        btn_begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("quiz", "Starting quiz " + topic);
                // load questions into global variable
                questions = getQuestionsForTopic(topic);

                // hide everything from main activity
                title.setVisibility(v.INVISIBLE);
                description.setVisibility(v.INVISIBLE);
                question_count.setVisibility(v.INVISIBLE);
                btn_begin.setVisibility(v.INVISIBLE);
                // start Question Fragment up
                fragmentManager.beginTransaction().replace(R.id.content_frame, new QuestionFragment()).commit();

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

    public int getQuestionCount(String topic){
        return getQuestionsForTopic(topic).size();
    }

    public ArrayList<Question> getQuestionsForTopic(String topic){
        return getQuestions().get(topic);
    }

    public String getTopic() {
        return this.topic;
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

    public class QuestionFragment extends Fragment {
        public  QuestionFragment newInstance() {
            QuestionFragment fragment = new QuestionFragment();
            Bundle args = new Bundle();
            fragment.setArguments(args);
            return fragment;
        }

        public QuestionFragment() {
            // Required empty public constructor
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {

            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            final View rootView = inflater.inflate(R.layout.fragment_question, container, false);

            TextView questionText = (TextView) rootView.findViewById(R.id.question);
            RadioButton a1 = (RadioButton) rootView.findViewById(R.id.choice1);
            RadioButton a2 = (RadioButton) rootView.findViewById(R.id.choice2);
            RadioButton a3 = (RadioButton) rootView.findViewById(R.id.choice3);
            RadioButton a4 = (RadioButton) rootView.findViewById(R.id.choice4);
            final RadioGroup choicesGroup = (RadioGroup) findViewById(R.id.choicesGroup);



            final Button submit = (Button) rootView.findViewById(R.id.submit_btn);
            submit.setVisibility(View.INVISIBLE);
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // guess = (String) ((RadioButton) findViewById(choicesGroup.getCheckedRadioButtonId())).getText();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, new AnswerFragment()).commit();

                }
            });


            Question q = questions.get(questionCount);
            String[] choices = q.getAnswers();

            // select a radio button
            View.OnClickListener selectAnswer = new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.i("quizdroid", "radio button checked: " );
                    submit.setVisibility(View.VISIBLE);
                }
            };



            questionText.setText(q.getQuestion());

            //Buttons
            a1.setText(choices[0]);
            a1.setOnClickListener(selectAnswer);

            a2.setOnClickListener(selectAnswer);
            a2.setText(choices[1]);

            a3.setOnClickListener(selectAnswer);
            a3.setText(choices[2]);

            a4.setOnClickListener(selectAnswer);
            a4.setText(choices[3]);


            return rootView;
        }


        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            try {

            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString()
                        + " must implement OnFragmentInteractionListener");
            }
        }

        @Override
        public void onDetach() {
            super.onDetach();
        }
    }


    public class AnswerFragment extends Fragment {

        public  AnswerFragment newInstance() {
            AnswerFragment fragment = new AnswerFragment();
            return fragment;
        }

        public AnswerFragment() {
            // Required empty public constructor
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {


            // Inflate the answer fragment layout
            final View rootView = inflater.inflate(R.layout.fragment_answer, container, false);

            Question q = questions.get(questionCount);

            String[] choices = q.getAnswers();

            TextView correctAnswer = (TextView) findViewById(R.id.correctAnswer);
            correctAnswer.setText(q.getCorrectAnswer());

            TextView yourAnswer = (TextView) findViewById(R.id.yourAnswer);
            yourAnswer.setText(guess);

            TextView correctCount = (TextView) findViewById(R.id.correctCount);
            correctCount.setText(getString(R.string.correct_count_text, numCorrect, q.getAnswers().length));

            Button next_btn = (Button) findViewById(R.id.moveOn_btn);
            final boolean moreQuestions = (questions.size() > questionCount);
            next_btn.setText((moreQuestions) ? "Next" : "Finish");
            next_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (moreQuestions){
                        questionCount++;
                        fragmentManager.beginTransaction().replace(R.id.content_frame, new QuestionFragment()).commit();

                    } else {
                        Intent topic = new Intent(getActivity(), SelectTopicActivity.class);
                        startActivity(topic);
                    }
                    finish();
                }
            });

            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            try {
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString()
                        + " must implement OnFragmentInteractionListener");
            }
        }

        @Override
        public void onDetach() {
            super.onDetach();
        }
    }



}
