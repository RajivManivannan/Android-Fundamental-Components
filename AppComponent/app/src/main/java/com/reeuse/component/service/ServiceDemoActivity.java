package com.reeuse.component.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.reeuse.component.R;

public class ServiceDemoActivity extends AppCompatActivity implements View.OnClickListener {

    MyService myService;
    boolean isBound = false;

    private ServiceConnection myConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            MyService.MyBinder binder = (MyService.MyBinder) service;
            myService = binder.getService();
            isBound = true;
            showToast("Activity:: onServiceConnected(...)");
        }

        public void onServiceDisconnected(ComponentName arg0) {
            showToast("Activity:: onServiceDisconnected(...)");
            isBound = false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_demo);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        (findViewById(R.id.service_start_btn)).setOnClickListener(this);
        (findViewById(R.id.service_stop_btn)).setOnClickListener(this);
        (findViewById(R.id.service_start_bind_btn)).setOnClickListener(this);
        (findViewById(R.id.service_un_bind_btn)).setOnClickListener(this);
        (findViewById(R.id.service_bind_get_value_btn)).setOnClickListener(this);
        (findViewById(R.id.service_intent_service_btn)).setOnClickListener(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.service_start_btn:
                startService(new Intent(ServiceDemoActivity.this, MyService.class));
                break;
            case R.id.service_stop_btn:
                stopService(new Intent(ServiceDemoActivity.this, MyService.class));
                break;
            case R.id.service_start_bind_btn:
                Intent intent = new Intent(this, MyService.class);
                bindService(intent, myConnection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.service_un_bind_btn:
                unbindService(myConnection);
                break;
            case R.id.service_bind_get_value_btn:
                if (isBound)
                    showToast(myService.status);
                else
                    showToast(getString(R.string.service_bind_service_message));
                break;
            case R.id.service_intent_service_btn:
                startService(new Intent(ServiceDemoActivity.this, MyIntentService.class));
                break;
            default:
                break;
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


}
