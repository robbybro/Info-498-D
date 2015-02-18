package edu.washington.robbybro.arewethereyet;

import android.content.BroadcastReceiver;
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
    private TextView message;
    private TextView phoneNumber;
    private TextView minutes;
    private Button startStop;

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

        // TODO: make
        startStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(startStop.getText().toString() == "Start") {
                    startStop.setText("Stop");
                    Log.i("arewethereyet", "Texting started");

//                    Context context = getApplicationContext();
//                    final CharSequence text = message.getText();
//                    final int duration = Toast.LENGTH_SHORT;
//                    // TODO: create intentBroadcast
//                    Intent i = new Intent();
//                    BroadcastReceiver br = new BroadcastReceiver() {
//                        @Override
//                        public void onReceive(Context context, Intent intent) {
//                            Toast toast = Toast.makeText(context, text, duration);
//                            toast.show();
//                        }
//                    };


                } else {
                    startStop.setText("Start");
                }
            }
        });


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
