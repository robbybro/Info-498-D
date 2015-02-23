package edu.washington.robbybro.awty;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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

import static java.lang.Integer.parseInt;

public class MainActivity extends ActionBarActivity implements TextWatcher {
    public static final int INTENT_ID = 1;

    private TextView message;
    private TextView phoneNumber;
    private TextView minutes;
    private Button startStop;
    private PendingIntent pendingIntent;
//    private boolean started;


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

        // Retrieve a PendingIntent that will perform a broadcast
        // check to see if alarm already exists
//        started = (PendingIntent.getBroadcast(MainActivity.this, INTENT_ID, alarmIntent,
//                PendingIntent.FLAG_NO_CREATE) != null);

//        if (started) { //If alarm already exists
//            pendingIntent = PendingIntent.getBroadcast(MainActivity.this, MainActivity.INTENT_ID, alarmIntent, PendingIntent.FLAG_NO_CREATE);
//            Toast.makeText(MainActivity.this, "Alarm already exists", Toast.LENGTH_SHORT).show();
//            startStop.setText("Stop");
//        } else {
        startStop.setText("Start");
//        }

        startStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve a PendingIntent that will perform a broadcast
                Intent alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
                alarmIntent.putExtra("message", phoneNumber.getText() + ": " + message.getText());
                pendingIntent = PendingIntent.getBroadcast(MainActivity.this, INTENT_ID,
                        alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT);

                String buttonText = startStop.getText().toString();
                if(buttonText == "Start") {
                    // Start alarm
                    Log.i("awty", "Texting started");
                    startStop.setText("Stop");
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
        int interval = parseInt(minutes.getText().toString()) * 1000;

        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        Toast.makeText(this, "Texting Set", Toast.LENGTH_SHORT).show();
    }

    public void cancel() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        pendingIntent.cancel();
        Toast.makeText(this, "Texting Canceled", Toast.LENGTH_SHORT).show();
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


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * Check:
     * Message is not empty
     * phone number is valid
     * minutes is valid - no 0's, no negatives, must be integer
     */
    private Boolean validateFields() {
        if(message.getText().length() > 0 && phoneNumber.getText().length() > 0
                && minutes.getText().length() > 0 && parseInt(minutes.getText().toString()) > 0) {
            startStop.setClickable(true);
            startStop.setEnabled(true);

            return true;
        } else {
            startStop.setClickable(false);
            startStop.setEnabled(false);
            return false;
        }
    }

    //    TODO: gonna use this to validate phone numbers on the next assignment
    private static boolean validatePhoneNumber(String phoneNo) {
        //validate phone numbers of format "1234567890"
        if (phoneNo.matches("\\d{10}")) return true;
            //validating phone number with -, . or spaces
        else if(phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
            //validating phone number where area code is in braces ()
        else if(phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
            //return false if nothing matches the input
        else return false;
    }
}