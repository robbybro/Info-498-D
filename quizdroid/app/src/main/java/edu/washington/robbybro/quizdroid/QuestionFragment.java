package edu.washington.robbybro.quizdroid;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class QuestionFragment extends Fragment {
    private static final String ARG_PARAM1 = "question";
    private Question question;
    private TopicDetailActivity topicDetailActivity;


    public static  QuestionFragment newInstance(Question q) {
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
            question = (Question) getArguments().getSerializable(ARG_PARAM1);
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
        final RadioGroup choicesGroup = (RadioGroup) rootView.findViewById(R.id.choicesGroup);
        final Button submit = (Button) rootView.findViewById(R.id.submit_btn);

        // select a radio button
        View.OnClickListener selectAnswer = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit.setVisibility(View.VISIBLE);
            }
        };

        submit.setVisibility(View.INVISIBLE);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View radioButtonView = choicesGroup.findViewById(choicesGroup.getCheckedRadioButtonId());
                topicDetailActivity.showAnswer(choicesGroup.indexOfChild(radioButtonView));

            }
        });

        String[] choices = question.getAnswers();

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
        topicDetailActivity = (TopicDetailActivity) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
