package com.reeuse.component.datastorage;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.reeuse.component.R;

/**
 * DataStorageActivity.java
 */
public class DataStorageActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String PREF_USER_DETAIL = "user_details";
    private EditText sharedPreferenceEt;

    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_storage_demo);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferenceEt = (EditText) findViewById(R.id.data_sp_et);
        (findViewById(R.id.data_store_in_sp_btn)).setOnClickListener(this);
        (findViewById(R.id.data_get_from_sp_btn)).setOnClickListener(this);


    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.data_store_in_sp_btn:
                if (!TextUtils.isEmpty(sharedPreferenceEt.getText().toString()))
                    sharedPreferences.edit().putString(PREF_USER_DETAIL, sharedPreferenceEt.getText().toString()).apply();
                break;
            case R.id.data_get_from_sp_btn:
                sharedPreferenceEt.setText(sharedPreferences.getString(PREF_USER_DETAIL, "null"));
                break;
            default:
                break;
        }

    }
}
