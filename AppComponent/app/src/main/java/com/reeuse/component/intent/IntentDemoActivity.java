package com.reeuse.component.intent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.reeuse.component.R;

public class IntentDemoActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int REQUEST_FOR_RESULT_VALUE = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_demo);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        (findViewById(R.id.intent_implicit_intent_btn)).setOnClickListener(this);
        (findViewById(R.id.intent_explicit_intent_btn)).setOnClickListener(this);
        (findViewById(R.id.intent_start_activity_for_result_btn)).setOnClickListener(this);
        (findViewById(R.id.intent_start_share_intent_btn)).setOnClickListener(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.intent_implicit_intent_btn:
                Intent implicitIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.co.in"));
                startActivity(implicitIntent);
                break;
            case R.id.intent_explicit_intent_btn:
                //Go to intent demo activity
                Intent exIntent = new Intent(IntentDemoActivity.this, ShareIntentActivity.class);
                exIntent.putExtra("explicit_intent", "Explicit Intent");
                startActivity(exIntent);
                break;
            case R.id.intent_start_activity_for_result_btn:
                Intent resultIntent = new Intent(IntentDemoActivity.this, IntentResultActivity.class);
                resultIntent.putExtra("data", "Activity for Result");
                startActivityForResult(resultIntent, REQUEST_FOR_RESULT_VALUE);
                break;
            case R.id.intent_start_share_intent_btn:
                String emailAddress = "test@gmail.com";
                String subject = "share Intent demo";
                composeEmail(emailAddress, subject);
                break;
            default:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {

            if (data.hasExtra("result")) {
                Toast.makeText(this, data.getStringExtra("result"), Toast.LENGTH_LONG).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


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
