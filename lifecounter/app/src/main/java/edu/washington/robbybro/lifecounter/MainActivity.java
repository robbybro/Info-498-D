package edu.washington.robbybro.lifecounter;

import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    int[] players = new int[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null) {
            Arrays.fill(players, 20);
        } else {
            players = savedInstanceState.getIntArray("players");
        }
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putIntArray("players", players);
    }

     @Override
     protected void onStart() {
         // P1
         final Button p1plus1 = (Button) findViewById(R.id.p1plus1);
         p1plus1.setOnClickListener(this);
         final Button p1minus1 = (Button) findViewById(R.id.p1minus1);
         p1minus1.setOnClickListener(this);
         final Button p1minus5 = (Button) findViewById(R.id.p1minus5);
         p1minus5.setOnClickListener(this);
         final Button p1plus5 = (Button) findViewById(R.id.p1plus5);
         p1plus5.setOnClickListener(this);
         // P2
         final Button p2plus1 = (Button) findViewById(R.id.p2plus1);
         p2plus1.setOnClickListener(this);
         final Button p2minus1 = (Button) findViewById(R.id.p2minus1);
         p2minus1.setOnClickListener(this);
         final Button p2minus5 = (Button) findViewById(R.id.p2minus5);
         p2minus5.setOnClickListener(this);
         final Button p2plus5 = (Button) findViewById(R.id.p2plus5);
         p2plus5.setOnClickListener(this);
         // P3
         final Button p3plus1 = (Button) findViewById(R.id.p3plus1);
         p3plus1.setOnClickListener(this);
         final Button p3minus1 = (Button) findViewById(R.id.p3minus1);
         p3minus1.setOnClickListener(this);
         final Button p3minus5 = (Button) findViewById(R.id.p3minus5);
         p3minus5.setOnClickListener(this);
         final Button p3plus5 = (Button) findViewById(R.id.p3plus5);
         p3plus5.setOnClickListener(this);
         // P4
         final Button p4plus1 = (Button) findViewById(R.id.p4plus1);
         p4plus1.setOnClickListener(this);
         final Button p4minus1 = (Button) findViewById(R.id.p4minus1);
         p4minus1.setOnClickListener(this);
         final Button p4minus5 = (Button) findViewById(R.id.p4minus5);
         p4minus5.setOnClickListener(this);
         final Button p4plus5 = (Button) findViewById(R.id.p4plus5);
         p4plus5.setOnClickListener(this);

         {
             final TextView p1Life = (TextView) findViewById(R.id.p1Life);
             p1Life.setText(Integer.toString(players[0]));
             checkLoss(1);
             final TextView p2Life = (TextView) findViewById(R.id.p2Life);
             p2Life.setText(Integer.toString(players[1]));
             checkLoss(2);
             final TextView p3Life = (TextView) findViewById(R.id.p3Life);
             p3Life.setText(Integer.toString(players[2]));
             checkLoss(3);
             final TextView p4Life = (TextView) findViewById(R.id.p4Life);
             p4Life.setText(Integer.toString(players[3]));
             checkLoss(4);
         }

         super.onStart();

     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private void checkLoss(int p) {
        int resId = getResources().getIdentifier("p" + p + "Life", "id", getPackageName());
        TextView textView = (TextView) findViewById(resId);
        int life = Integer.parseInt(textView.getText().toString());
        if(life <= 0) {
            TextView loser = (TextView) findViewById(R.id.loser);
            loser.setText("Player " + p + " loses!");
        }

    }

    @Override
    public void onClick(View v) {
        final TextView p1Life = (TextView) findViewById(R.id.p1Life);
        final TextView p2Life = (TextView) findViewById(R.id.p2Life);
        final TextView p3Life = (TextView) findViewById(R.id.p3Life);
        final TextView p4Life = (TextView) findViewById(R.id.p4Life);


        switch(v.getId()) {
            // P1
            case R.id.p1plus1:
                players[0]++;
                p1Life.setText(Integer.toString(players[0]));
                checkLoss(1);
                break;
            case R.id.p1plus5:
                players[0] = players[0] + 5;
                p1Life.setText(Integer.toString(players[0]));
                checkLoss(1);
                break;
            case R.id.p1minus1:
                players[0]--;
                p1Life.setText(Integer.toString(players[0]));
                checkLoss(1);
                break;
            case R.id.p1minus5:
                players[0] = players[0] - 5;
                p1Life.setText(Integer.toString(players[0]));
                checkLoss(1);
                break;
            //P2
            case R.id.p2plus1:
                players[1]++;
                p2Life.setText(Integer.toString(players[1]));
                checkLoss(2);
                break;
            case R.id.p2plus5:
                players[1] = players[1] + 5;
                p2Life.setText(Integer.toString(players[1]));
                checkLoss(2);
                break;
            case R.id.p2minus1:
                players[1]--;
                p2Life.setText(Integer.toString(players[1]));
                checkLoss(2);
                break;
            case R.id.p2minus5:
                players[1] = players[1] - 5;
                p2Life.setText(Integer.toString(players[1]));
                checkLoss(2);
                break;
            //P3
            case R.id.p3plus1:
                players[2]++;
                p3Life.setText(Integer.toString(players[2]));
                checkLoss(3);
                break;
            case R.id.p3plus5:
                players[2] = players[2] + 5;
                p3Life.setText(Integer.toString(players[2]));
                checkLoss(3);
                break;
            case R.id.p3minus1:
                players[2]--;
                p3Life.setText(Integer.toString(players[2]));
                checkLoss(3);
                break;
            case R.id.p3minus5:
                players[2] = players[2] - 5;
                p3Life.setText(Integer.toString(players[2]));
                checkLoss(3);
                break;
            // P4
            case R.id.p4plus1:
                players[3]++;
                p4Life.setText(Integer.toString(players[3]));
                checkLoss(4);
                break;
            case R.id.p4plus5:
                players[3] = players[3] + 5;
                p4Life.setText(Integer.toString(players[3]));
                checkLoss(4);
                break;
            case R.id.p4minus1:
                players[3]--;
                p4Life.setText(Integer.toString(players[3]));
                checkLoss(4);
                break;
            case R.id.p4minus5:
                players[3] = players[3] - 5;
                p4Life.setText(Integer.toString(players[3]));
                checkLoss(4);
                break;
        }
    }
}
