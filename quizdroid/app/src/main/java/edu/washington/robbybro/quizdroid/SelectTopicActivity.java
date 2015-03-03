package edu.washington.robbybro.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class SelectTopicActivity extends ActionBarActivity {

    private List<Topic> topics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);
        QuizApp app = QuizApp.getInstance();
        topics = app.getTopics();
        TopicsListAdapter topicsAdapter = new TopicsListAdapter(this, R.layout.topics_list_layout, QuizApp.getInstance().getTopics());


        final ListView topicsView = (ListView) findViewById(R.id.topics);
        topicsView.setAdapter(topicsAdapter);

        topicsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent questionIntent = new Intent(SelectTopicActivity.this, QuizActivity.class);
                questionIntent.putExtra("topic", position);
                startActivity(questionIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.action_preferences){
            Intent nextActivity = new Intent(SelectTopicActivity.this, PreferencesActivity.class);
            startActivity(nextActivity);
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (DownloadService.isServiceAlarmOn(this))
            DownloadService.setServiceAlarm(this, false);
    }
}
