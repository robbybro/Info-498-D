package edu.washington.robbybro.quizdroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Robby on 2/24/2015.
 */
public class QuestionDownloadAlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String url = intent.getStringExtra("url");
        Toast.makeText(context, "Downloading New Questions from " + url, Toast.LENGTH_SHORT).show();
    }
}
