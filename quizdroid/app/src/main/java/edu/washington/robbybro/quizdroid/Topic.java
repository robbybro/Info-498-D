package edu.washington.robbybro.quizdroid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robby on 2/11/2015.
 */
public class Topic implements Serializable {
    private String topic;
    private String descriptionShort;
    private String descriptionLong;
    private int currentQuestionUserIsOn;
    private List<Question> questions;
    private int imageResource;


    public Topic() {
        this("", "", "", new ArrayList<Question>());
    }

    public Topic(String topic, String descriptionShort, String descriptionLong, List<Question> questions) {
        this.topic = topic;
        this.descriptionShort = descriptionShort;
        this.descriptionLong = descriptionLong;
        this.questions = questions;

        this.currentQuestionUserIsOn = 0;
    }

    public Topic(String topic, String descriptionShort, String descriptionLong, List<Question> questions, int imageResource) {
        this.topic = topic;
        this.descriptionShort = descriptionShort;
        this.descriptionLong = descriptionLong;
        this.questions = questions;
        this.imageResource = imageResource;

        this.currentQuestionUserIsOn = 0;
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

    public List<Question> getQuestions() {
        return this.questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public void addQuestion(Question q) {
        questions.add(q);
    }

    public int totalQuestionCount () {
        return this.questions.size();
    }

    public int getCurrentQuestionUserIsOn() {
        return currentQuestionUserIsOn;
    }

    public Question getQuestion(int index) {
        return questions.get(index);
    }

    public int setCurrentQuestion(int index){
        currentQuestionUserIsOn = index;
        return currentQuestionUserIsOn;
    }

    public int getImageResource(){
        return this.imageResource;
    }
}
