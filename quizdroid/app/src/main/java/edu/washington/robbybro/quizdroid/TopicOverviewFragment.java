package edu.washington.robbybro.quizdroid;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class TopicOverviewFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "topic";
    private static final String ARG_PARAM2 = "numQuestions";

    private String topic;
    private int numQuestions;
    private TopicDetailActivity topicDetailActivity;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment TopicOverviewFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        ((TextView)rootView.findViewById(R.id.topic_description)).setText(getTopicDetails(topic));
        ((TextView)rootView.findViewById(R.id.question_count)).setText(getString(R.string.num_questions, numQuestions));

        Button begin = (Button) rootView.findViewById(R.id.btn_begin);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topicDetailActivity.showNextQuestion();
            }
        });

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

    private String getTopicDetails(String topic){
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

}
