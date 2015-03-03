package edu.washington.robbybro.quizdroid;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Robby on 3/1/2015.
 */
public class DownloadService extends IntentService {

    public static void setServiceAlarm(Context context, boolean on)
    {
        Intent i = new Intent(context, DownloadService.class);
        PendingIntent pi = PendingIntent.getService(context, 0, i, 0);

        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        if (on) {
            alarmManager.setRepeating(AlarmManager.RTC, System.currentTimeMillis(), 15 * 1000, pi);
        }
        else {
            alarmManager.cancel(pi);
            pi.cancel();
        }
    }
    public static boolean isServiceAlarmOn(Context context)
    {
        Intent i = new Intent(context, DownloadService.class);
        PendingIntent pi = PendingIntent.getService(context, 0, i, PendingIntent.FLAG_NO_CREATE);
        return pi != null;
    }

    public static final String TAG = IntentService.class.getSimpleName();

    public DownloadService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.v(TAG, "Received intent");

        try
        {
            Log.v(TAG, "Beginning download");
            URL downloadUrl = new URL("http://tednewardsandbox.site44.com/questions.json");

            BufferedReader in = null;
            PrintWriter out = null;
            try
            {
                in = new BufferedReader(new InputStreamReader(downloadUrl.openStream()));
                out = new PrintWriter(new FileOutputStream(getFilesDir() + "/questions.json"));

                String str;
                while ((str = in.readLine()) != null) {
                    Log.v(TAG, "Writing downloaded " + str);
                    out.println(str);
                }

                PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, SelectTopicActivity.class), 0);

                // Pop a notification
                Notification note = new Notification.Builder(this).
                        setTicker("Quiz content downloaded!").
                        setSmallIcon(android.R.drawable.ic_menu_report_image).
                        setContentTitle("QuizDroid").
                        setContentText("New questions downloaded").
                        setContentIntent(pi).
                        setAutoCancel(true).getNotification();
                NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(0, note);
            }
            finally
            {
                if (in != null)
                    in.close();
                if (out != null)
                    out.close();
            }
        }
        catch (MalformedURLException malURLEx)
        {
            Log.wtf(TAG, malURLEx);
        }
        catch (IOException ioEx)
        {
            Log.wtf(TAG, ioEx);
        }

    }
}
