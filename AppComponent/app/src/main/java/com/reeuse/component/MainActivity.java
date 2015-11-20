package com.reeuse.component;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.reeuse.component.intent.IntentDemoActivity;
import com.reeuse.component.service.ServiceDemoActivity;
import com.reeuse.component.thread.ThreadDemoActivity;
import com.reeuse.component.ui.DemoActivity;
import com.reeuse.component.ui.FragmentDemoActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        (findViewById(R.id.home_implicit_intent_btn)).setOnClickListener(this);
        (findViewById(R.id.home_explicit_intent_btn)).setOnClickListener(this);
        (findViewById(R.id.home_activity_btn)).setOnClickListener(this);
        (findViewById(R.id.home_fragment_btn)).setOnClickListener(this);
        (findViewById(R.id.home_service_btn)).setOnClickListener(this);
        (findViewById(R.id.home_intent_service_btn)).setOnClickListener(this);
        (findViewById(R.id.home_notification_btn)).setOnClickListener(this);
        (findViewById(R.id.home_thread_btn)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.home_implicit_intent_btn:
                String emailAddress = "test@gmail.com";
                String subject = "Implicit Intent demo";
                composeEmail(emailAddress,subject);
                break;
            case R.id.home_explicit_intent_btn:
                //Go to intent demo activity
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
            case R.id.home_intent_service_btn:
                //Go to Service demo activity
                startActivity(new Intent(MainActivity.this, ServiceDemoActivity.class));
                break;
            case R.id.home_notification_btn:
                break;
            case R.id.home_thread_btn:
                //Go to Service demo activity
                startActivity(new Intent(MainActivity.this, ThreadDemoActivity.class));
                break;
            default:
                break;
        }

    }


    /**
     * To share details with Intent.ACTION_SEND
     * @param address email address string
     * @param subject email subject
     */
    public void composeEmail(String address, String subject) {
        Intent intent = new Intent(Intent.ACTION_SEND); // implicit intent
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, address);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


}
