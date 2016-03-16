package com.reeuse.component.activity;

import android.Manifest;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.reeuse.component.R;
import com.reeuse.component.datastorage.DataStorageActivity;
import com.reeuse.component.intent.IntentDemoActivity;
import com.reeuse.component.networking.ServerRequest;
import com.reeuse.component.service.ServiceDemoActivity;
import com.reeuse.component.thread.ThreadDemoActivity;
import com.reeuse.component.utils.NetworkUtils;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CODE_ASK_SMS_PERMISSIONS = 124;
    final private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        (findViewById(R.id.home_intent_btn)).setOnClickListener(this);
        (findViewById(R.id.home_activity_btn)).setOnClickListener(this);
        (findViewById(R.id.home_fragment_btn)).setOnClickListener(this);
        (findViewById(R.id.home_service_btn)).setOnClickListener(this);
        (findViewById(R.id.home_notification_btn)).setOnClickListener(this);
        (findViewById(R.id.home_thread_btn)).setOnClickListener(this);
        (findViewById(R.id.home_data_storage_btn)).setOnClickListener(this);
        (findViewById(R.id.home_networking_btn)).setOnClickListener(this);


        // Read SMS permission
        int smsPermissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_SMS);
        //To check the read sms permission granted
        if (smsPermissionCheck != PackageManager.PERMISSION_GRANTED) {
            // Request dialog and get permission from the user to read sms
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_SMS}, REQUEST_CODE_ASK_SMS_PERMISSIONS);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_intent_btn:
                //Go to intent Demo activity
                startActivity(new Intent(MainActivity.this, IntentDemoActivity.class));
                break;
            case R.id.home_activity_btn:
                //Go to demo activity
                startActivity(new Intent(MainActivity.this, DemoActivity.class));
                break;
            case R.id.home_fragment_btn:
                //Go to Fragment demo activity
                startActivity(new Intent(MainActivity.this, FragmentDemoActivity.class));
                break;
            case R.id.home_service_btn:
                //Go to Service demo activity
                startActivity(new Intent(MainActivity.this, ServiceDemoActivity.class));
                break;
            case R.id.home_notification_btn:
                sendNotification();
                break;
            case R.id.home_thread_btn:
                //Go to Service demo activity
                startActivity(new Intent(MainActivity.this, ThreadDemoActivity.class));
                break;
            case R.id.home_data_storage_btn:
                startActivity(new Intent(MainActivity.this, DataStorageActivity.class));
                break;
            case R.id.home_networking_btn:
                if (NetworkUtils.isConnected(MainActivity.this))
                    getValueFromNetWork();
                else
                    Toast.makeText(MainActivity.this, "No Internet connection.", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }

    }

    /**
     * Generate notification
     */
    private void sendNotification() {
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Intent resultIntent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setLargeIcon(largeIcon);
        notificationBuilder.setSmallIcon(R.drawable.ic_stat_name);
        notificationBuilder.setContentIntent(resultPendingIntent);
        notificationBuilder.setSound(defaultSoundUri);
        notificationBuilder.setContentTitle("App Component");
        notificationBuilder.setContentText("Android Notification from App Components app");

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        int notificationId = (int) System.currentTimeMillis();
        notificationManager.notify(notificationId, notificationBuilder.build());
    }


    private void getValueFromNetWork() {
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA";

        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                ServerRequest serverRequest = new ServerRequest();
                return serverRequest.httpGet(params[0]);
            }

            @Override
            protected void onPostExecute(String s) {
                Toast.makeText(MainActivity.this, "Response:: " + s, Toast.LENGTH_LONG).show();
                super.onPostExecute(s);
            }
        }.execute(url);
    }
}
