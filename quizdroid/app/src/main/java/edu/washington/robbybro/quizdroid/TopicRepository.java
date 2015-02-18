package edu.washington.robbybro.quizdroid;

import java.util.ArrayList;

/**
 * Created by Robby on 2/17/2015.
 */
public interface TopicRepository {
    /**
     * get/set question user is on
     * get/set correct answer
     * get answer list
     * get/set topic
     * get/set description
     * get total questions count
     * get/add question
     * get question list
      */
    public int getCurrentQuestion();
    public void setCurrentQuestion(int index);

    public String getCorrectAnswer();
    public void setCorrectAnswer(int index);
    public ArrayList<String> getAnswerList();

    public String getTopic();
    public String getTopicAt(int index);
    public void setTopic(int index);

    public String getDescriptionShort();
    public void setDescriptionShort(String description);
    public String getDescriptionLong();
    public void setDescriptionLong(String description);

    public int totalQuestionCount();
    public Question getQuestion(int index);
    public ArrayList<Question> getQuestionList();
    public void addQuestion(Question question);
}

