package edu.washington.robbybro.quizdroid;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Robby on 2/11/2015.
 */
public class Topic implements Serializable {
    private String topic;
    private String descriptionShort;
    private String descriptionLong;
    private ArrayList<Question> questions;

    public Topic() {
        this("", "", "", new ArrayList<Question>());
    }

    public Topic(String topic, String descriptionShort, String descriptionLong, ArrayList<Question> questions) {
        this.topic = topic;
        this.descriptionShort = descriptionShort;
        this.descriptionLong = descriptionLong;
        this.questions = questions;
    }

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescriptionShort() {
        return this.descriptionShort;
    }

    public void setDescriptionShort(String descriptionShort) {
        this.descriptionShort = descriptionShort;
    }

    public String getDescriptionLong() {
        return this.descriptionLong;
    }

    public void setDescriptionLong(String descriptionLong) {
        this.descriptionLong = descriptionLong;
    }

    public ArrayList<Question> getQuestions() {
        return this.questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public int getQuestionSize () {
        return this.questions.size();
    }
}
