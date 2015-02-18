package edu.washington.robbybro.quizdroid;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Robby on 2/4/2015.
 */
public class Question implements Serializable {
    private int correct;
    private String question;
    private ArrayList<String> answers;

    public Question(String question, ArrayList<String> answers, int correct) {
        this.correct = correct;
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return this.question;
    }

    public ArrayList<String> getAnswers(){
        return this.answers;
    }

    public String getCorrectAnswer(){
        Log.i("quiz", this.answers.get(this.correct));
        return this.answers.get(this.correct);
    }

    public boolean isCorrect(int guess){
        return guess == correct;
    }
}
