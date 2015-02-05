package edu.washington.robbybro.inclass02032015;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        // set up buttons
        final Button toast = (Button) findViewById(R.id.toast);
        toast.setOnClickListener(this);
        final Button notification = (Button) findViewById(R.id.notification);
        notification.setOnClickListener(this);
        final Button dialog = (Button) findViewById(R.id.dialog);
        dialog.setOnClickListener(this);

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

    // http://mobiledevtuts.com/android/android-notification-example-code/
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.toast:
                Context context = getApplicationContext();
                CharSequence text = "Robby Brosman's Toast!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                break;
            case R.id.notification:
                Intent intent = new Intent();
                PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
                Notification noti = new Notification.Builder(this)
                        .setTicker("Ticker Title")
                        .setContentTitle("Robby Brosman's Notification")
                        .setContentText("Noted.")
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentIntent(pIntent).getNotification();
                noti.flags=Notification.FLAG_AUTO_CANCEL;
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(0, noti);
                break;
            case R.id.dialog:
                AlertDialog alertDialog = new AlertDialog.Builder(
                        MainActivity.this).create();
                alertDialog.setTitle("Robby Brosman's Dialog");
                alertDialog.setMessage("Dialoged.");
                alertDialog.setIcon(R.drawable.ic_launcher);
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.show();
                break;
        }
    }
}
