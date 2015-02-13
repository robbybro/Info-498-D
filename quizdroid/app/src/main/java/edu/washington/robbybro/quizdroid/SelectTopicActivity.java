package edu.washington.robbybro.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;


public class SelectTopicActivity extends ActionBarActivity {

    private ArrayList<String> topics = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);

        ArrayAdapter<String> topicsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, topics);
        topics.add("Math");
        topics.add("Physics");
        topics.add("Marvel Super Heroes");

        final ListView topicsView = (ListView) findViewById(R.id.topics);
        topicsView.setAdapter(topicsAdapter);

        topicsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent nextActivity = new Intent(SelectTopicActivity.this, TopicDetailActivity.class);
                nextActivity.putExtra("timestamp", new Date().toString());
                String selectedFromList = (String) (topicsView.getItemAtPosition(position));
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
