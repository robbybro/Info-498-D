package edu.washington.robbybro.quizdroid;

import android.app.Application;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Robby on 2/17/2015.
 */
public class QuizApp extends Application implements TopicRepository {

    private static QuizApp instance;
    public List<Topic> topics;
    private int currentTopic;

    public QuizApp() {
        if(instance == null) {
            instance = this;
        } else {
            Log.e("QuizApp", "There is already a quizapp built");
            throw new RuntimeException("Multiple App Exception");
        }
    }

    public static QuizApp getInstance() {
        return instance;
    }

//    public void initialize() {
//        Map<String, ArrayList<Question>> questions = new HashMap<>();
//        ArrayList<Question> questionTemp = new ArrayList<Question>();
//        ArrayList<String> answerTemp = new ArrayList<String>();
//        topics = new ArrayList<Topic>();
//
//        // Math
//        Topic math  = new Topic();
//        math.setTopic("Math");
//        math.setDescriptionLong("This is a really long math description");
//        math.setDescriptionShort("The universal language");
//        answerTemp.add("1");
//        answerTemp.add("2");
//        answerTemp.add("3");
//        answerTemp.add("69");
//        questionTemp.add(new Question("What is 1 + 1", answerTemp, 1));
//
//        answerTemp = new ArrayList<String>();
//        answerTemp.add("42");
//        answerTemp.add("that's not a math question...");
//        answerTemp.add("it kind of is");
//        answerTemp.add("2");
//        questionTemp.add(new Question("What is the answer to life, the universe, and everything?", answerTemp, 0));
//
//        answerTemp = new ArrayList<String>();
//        answerTemp.add("b");
//        answerTemp.add("0");
//        answerTemp.add("8");
//        answerTemp.add("9006");
//        questionTemp.add(new Question("What is the sum of the edges and corners of a quadrilateral?", answerTemp, 2));
//        questions.put("Math", questionTemp);
//        math.setQuestions(questionTemp);
//
//
//        // Physics
//        questionTemp = new ArrayList<Question>();
//        Topic physics = new Topic();
//        physics.setTopic("Physics");
//        physics.setDescriptionLong("This is a really long physics description");
//        physics.setDescriptionShort("math with application");
//
//        // same answers for all questions
//        answerTemp = new ArrayList<String>();
//        answerTemp.add("The total energy of an isolated system is constant; energy can be transformed from one form to another, but cannot be created or destroyed.");
//        answerTemp.add("Every object in a state of uniform motion tends to remain in that state of motion unless an external force is applied to it.");
//        answerTemp.add("For every action there is an equal and opposite reaction.");
//        answerTemp.add("The relationship between an object's mass m, its acceleration a, and the applied force F is F = ma. Acceleration and force are vectors (as indicated by their symbols being displayed in slant bold font); in this law the direction of the force vector is the same as the direction of the acceleration vector.");
//        questionTemp.add(new Question("What is Newton's first law of motion", answerTemp, 1));
//        questionTemp.add(new Question("What is Newton's second law of motion?", answerTemp, 3));
//        questionTemp.add(new Question("What is Newton's third law of motion?", answerTemp, 2));
//        questionTemp.add(new Question("What is the first law of thermodynamics?", answerTemp, 0));
//        questions.put("Physics", questionTemp);
//        physics.setQuestions(questionTemp);
//
//        // Marvel Superheroes
//        questionTemp = new ArrayList<Question>();
//        Topic marvel = new Topic();
//        marvel.setTopic("Marvel Super Heroes");
//        marvel.setDescriptionLong("really long marvel super hero description");
//        marvel.setDescriptionShort("Not DC");
//
//        answerTemp = new ArrayList<String>();
//        answerTemp.add("Robert Downey Jr.");
//        answerTemp.add("Jeff Bridges");
//        answerTemp.add("Ted Neward");
//        answerTemp.add("Emma Stone");
//        questionTemp.add(new Question("Who plays Obadiah Stane in Iron Man?", answerTemp, 1));
//
//        answerTemp = new ArrayList<String>();
//        answerTemp.add("Sour Apple");
//        answerTemp.add("Forest Green");
//        answerTemp.add("Mountain Dew Green");
//        answerTemp.add("Hulk Green");
//        questionTemp.add(new Question("What color is The Hulk?", answerTemp, 3));
//        questions.put("Marvel Super Heroes", questionTemp);
//        marvel.setQuestions(questionTemp);
//
//        topics.add(math);
//        topics.add(physics);
//        topics.add(marvel);
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("QuizApp", "getFilesDir() = " + getFilesDir().getAbsolutePath());
        FileInputStream fis = null;
        try
        {
            fis = openFileInput("questions.json");
            String json = readJSONFile(fis);
            JSONArray jsonTopics = new JSONArray(json);

            topics = new ArrayList<Topic>();
            for (int i=0; i<jsonTopics.length(); i++)
            {
                JSONObject topic = jsonTopics.getJSONObject(i);
                topics.add(loadTopic(topic));
            }
        }
        catch (JSONException jsonEx)
        {
            Log.e("QuizApp", "Exception in reading JSON file: " + jsonEx.getMessage());
            Log.wtf("QuizApp", jsonEx);
        }
        catch (IOException ioEx)
        {
            Log.e("QuizApp", "Exception in reading JSON file: " + ioEx.getMessage());
            Log.wtf("QuizApp", ioEx);
        }
        finally
        {
            try
            {
                if (fis != null)
                    fis.close();
            }
            catch (IOException ioEx)
            {
                // Not much we can do here....
            }
        }

        // Now kick off the background service to download the new (?) file
        //Intent downloadIntent = new Intent(this, DownloadService.class);
        //startService(downloadIntent);
        DownloadService.setServiceAlarm(this, true);
    }

    private String readJSONFile(FileInputStream fis) throws IOException
    {
        int size = fis.available();
        byte[] buffer = new byte[size];
        fis.read(buffer);

        return new String(buffer, "UTF-8");
    }

    private Topic loadTopic(JSONObject topic)
            throws JSONException
    {
        JSONArray qs = topic.getJSONArray("questions");
        Log.d("QuizApp", "Topic " + topic.getString("title") + " has " + qs.length() + " questions.");
        List<Question> questions = new ArrayList<Question>();
        for (int j=0; j< qs.length(); j++)
        {
            Log.d("QuizApp", "Adding " + qs.getJSONObject(j).getString("text"));
            questions.add(loadQuestion(qs.getJSONObject(j)));
        }

        return new Topic(topic.getString("title"), topic.getString("desc"), topic.getString("desc"), questions);
    }

    private Question loadQuestion(JSONObject q)
            throws JSONException
    {
        return new Question(q.getString("text"),
                q.getJSONArray("answers").getString(0),
                q.getJSONArray("answers").getString(1),
                q.getJSONArray("answers").getString(2),
                q.getJSONArray("answers").getString(3),
                q.getInt("answer"));
    }


    @Override
    public void onTerminate() {
        super.onTerminate();

        if (DownloadService.isServiceAlarmOn(this))
            DownloadService.setServiceAlarm(this, false);
    }

    @Override
    public String getDescriptionShort() {
        return null;
    }

    @Override
    public String getTopicAt(int index){
        return topics.get(index).getTopic();

    }

    public List<Topic> getTopics() {
        return this.topics;
    }

    @Override
    public int getCorrectAnswer(){
        return getQuestion(topics.get(currentTopic).totalQuestionCount()).getCorrectAnswer();
    }

    @Override
    public void setCorrectAnswer(int index) {

    }

    @Override
    public ArrayList<String> getAnswerList(){
        return getAnswerList();
//        return getQuestion(getCurrentQuestionUserIsOn()).getAnswerList();
    }

    public String getTopic(){
        return topics.get(currentTopic).getTopic();
    }

    @Override
    public void setTopic(int index){
        topics.get(currentTopic).setTopic(getTopicAt(index));
    }

    @Override
    public String getDescriptionLong(){
        return topics.get(currentTopic).getDescriptionLong();
    }

    @Override
    public void setDescriptionLong(String description){
        topics.get(currentTopic).setDescriptionLong(description);
    }

    @Override
    public void setDescriptionShort(String descriptionShort){
        topics.get(currentTopic).setDescriptionShort(descriptionShort);
    }

    @Override
    public int getCurrentQuestion(){
        return topics.get(currentTopic).getCurrentQuestionUserIsOn();
    }

    public int getCurrentQuestionUserIsOn(){
        return topics.get(currentTopic).getCurrentQuestionUserIsOn();
    }

    @Override
    public void addQuestion(Question question){
        topics.get(currentTopic).addQuestion(question);
    }

    @Override
    public void setCurrentQuestion(int index){
        topics.get(currentTopic).setCurrentQuestion(index);
    }

    @Override
    public int totalQuestionCount(){
        return topics.get(currentTopic).totalQuestionCount();
    }

    @Override
    public Question getQuestion(int index){
        return topics.get(currentTopic).getQuestion(index);
    }

    public int getQuestionIndex(Question q) {
        return getQuestionList().indexOf(q);
    }

    @Override
    public ArrayList<Question> getQuestionList() {
        return getQuestionList();
//        return topics.get(currentTopic).getQuestions();
    }

}
