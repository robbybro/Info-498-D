package edu.washington.robbybro.quizdroid;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class TopicOverviewFragment extends Fragment {

    private static final String ARG_PARAM1 = "topic";
    private static final String ARG_PARAM2 = "numQuestions";

    private String topic;
    private int numQuestions;
    private QuizActivity quizActivity;

    public static TopicOverviewFragment newInstance(String topic, int numQuestions) {
        TopicOverviewFragment fragment = new TopicOverviewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, topic);
        args.putInt(ARG_PARAM2, numQuestions);
        fragment.setArguments(args);
        return fragment;
    }

    public TopicOverviewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            topic = getArguments().getString(ARG_PARAM1);
            numQuestions = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_topic_overview, container, false);
        ((TextView)rootView.findViewById(R.id.topic_title)).setText(topic);

        ((TextView)rootView.findViewById(R.id.topic_description)).setText(QuizApp.getInstance().getDescriptionLong());
        ((TextView)rootView.findViewById(R.id.question_count)).setText(getString(R.string.num_questions, numQuestions));

        Button begin = (Button) rootView.findViewById(R.id.btn_begin);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizActivity.showNextQuestion();
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        quizActivity = (QuizActivity) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
