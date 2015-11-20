package com.reeuse.component.intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.reeuse.component.R;


public class ShareIntentActivity extends AppCompatActivity {

    private TextView emailAddress;
    private TextView subject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_intent);
        emailAddress = (TextView) findViewById(R.id.share_email_txt);
        subject = (TextView) findViewById(R.id.share_subject_txt);
        // Get intent, action and MIME type
        Intent intent = getIntent();
        String action = intent.getAction();

        if (Intent.ACTION_SEND.equals(action)) {
            emailAddress.setText(intent.getStringExtra(Intent.EXTRA_EMAIL));
            subject.setText(intent.getStringExtra(Intent.EXTRA_SUBJECT));
        }
    }

}
