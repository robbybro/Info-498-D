package edu.washington.robbybro.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class SelectTopicActivity extends ActionBarActivity {

    private ArrayList<Topic> topics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);
        QuizApp app = QuizApp.getInstance();
        topics = new ArrayList<Topic>();
        topics = app.getTopics();
        TopicsListAdapter topicsAdapter = new TopicsListAdapter(this, R.layout.topics_list_layout, topics);


        final ListView topicsView = (ListView) findViewById(R.id.topics);
        topicsView.setAdapter(topicsAdapter);

        topicsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent nextActivity = new Intent(SelectTopicActivity.this, QuizActivity.class);
                Topic selectedFromList = (Topic) (topicsView.getItemAtPosition(position));
                nextActivity.putExtra("topic", selectedFromList);
                startActivity(nextActivity);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_topics, menu);
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
}
