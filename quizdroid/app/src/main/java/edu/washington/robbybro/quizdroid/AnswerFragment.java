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


public class AnswerFragment extends Fragment {
    private static final String ARG_PARAM1 = "yourAnswer";
    private static final String ARG_PARAM2 = "correctAnswer";
    private static final String ARG_PARAM3 = "numCorrect";
    private static final String ARG_PARAM4 = "numQuestions";
    private static final String ARG_PARAM5 = "moreQuestions";

    private String yourAnswer;
    private String correctAnswer;
    private int numCorrect;
    private int numQuestions;
    private boolean moreQuestions;
    private TopicDetailActivity topicDetailActivity;

    public static AnswerFragment newInstance(String yourAnswer, String correctAnswer, int numCorrect, int numQuestions, boolean moreQuestions) {
        AnswerFragment fragment = new AnswerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, yourAnswer);
        args.putString(ARG_PARAM2, correctAnswer);
        args.putInt(ARG_PARAM3, numCorrect);
        args.putInt(ARG_PARAM4, numQuestions);
        args.putBoolean(ARG_PARAM5, moreQuestions);
        fragment.setArguments(args);
        return fragment;
    }

    public AnswerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            yourAnswer = getArguments().getString(ARG_PARAM1);
            correctAnswer = getArguments().getString(ARG_PARAM2);
            numCorrect = getArguments().getInt(ARG_PARAM3);
            numQuestions = getArguments().getInt(ARG_PARAM4);
            moreQuestions = getArguments().getBoolean(ARG_PARAM5);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the answer fragment layout
        final View rootView = inflater.inflate(R.layout.fragment_answer, container, false);

        ((TextView) rootView.findViewById(R.id.correctAnswer)).setText(correctAnswer);
        ((TextView) rootView.findViewById(R.id.yourAnswer)).setText(yourAnswer);
        ((TextView) rootView.findViewById(R.id.correctCount)).setText(getString(R.string.correct_count_text, numCorrect, numQuestions));


        TextView correctCount = (TextView) rootView.findViewById(R.id.correctCount);
        correctCount.setText(getString(R.string.correct_count_text, numCorrect, numQuestions));

        Button next = (Button) rootView.findViewById(R.id.btn_next);
        next.setText((moreQuestions) ? "Next" : "Finish");
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (moreQuestions){
                    topicDetailActivity.showNextQuestion();
                } else {
                    topicDetailActivity.finish();
                }
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        topicDetailActivity = (TopicDetailActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
