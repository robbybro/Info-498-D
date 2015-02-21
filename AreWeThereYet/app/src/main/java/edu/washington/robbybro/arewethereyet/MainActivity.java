package edu.washington.robbybro.arewethereyet;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

import static java.lang.Integer.parseInt;


public class MainActivity extends ActionBarActivity implements TextWatcher {
    private TextView message;
    private TextView phoneNumber;
    private TextView minutes;
    private Button startStop;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set up UI elements
        message = (TextView) findViewById(R.id.message_text);
        phoneNumber = (TextView) findViewById(R.id.number_text);
        minutes = (TextView) findViewById(R.id.minutes_text);
        startStop = (Button) findViewById(R.id.start_stop_button);

        // add listeners
        message.addTextChangedListener(this);
        phoneNumber.addTextChangedListener(this);
        minutes.addTextChangedListener(this);

        /* Retrieve a PendingIntent that will perform a broadcast */
        Intent alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntent, 0);

        startStop.setText("Start");

        startStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = startStop.getText().toString();
                if(buttonText == "Start") {
                    // Start alarm manager
                    startStop.setText("Stop");
                    Log.i("awty", "Texting started");

                    start();
                } else {
                    // STop alarm manager
                    startStop.setText("Start");
                    Log.i("awty", "Texting Stopped");

                    cancel();
                }
            }
        });
    }

    public void start() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = parseInt(minutes.getText().toString());

        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * interval, pendingIntent);
        Toast.makeText(this, "Texting Set", Toast.LENGTH_SHORT).show();
    }

    public void cancel() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        Toast.makeText(this, "Texting Canceled", Toast.LENGTH_SHORT).show();
    }


    public class AlarmReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            final CharSequence text = message.getText();
            final int duration = Toast.LENGTH_LONG;
            Log.i("awty", "alarm fired");
            Toast.makeText(context, text, duration).show();
        }
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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        validateFields();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        validateFields();
    }

    @Override
    public void afterTextChanged(Editable s) {
        validateFields();
    }

    /**
     * Check:
     * Message is not empty
     * phone number is valid
     * minutes is valid - no 0's, no negatives, must be integer
     */
    private Boolean validateFields() {
        if(message.getText().length() > 0 && phoneNumber.getText().length() > 0 && minutes.getText().length() > 0) {
            startStop.setClickable(true);
            startStop.setEnabled(true);

            return true;
        } else {
            startStop.setClickable(false);
            startStop.setEnabled(false);
            return false;
        }
    }
}
