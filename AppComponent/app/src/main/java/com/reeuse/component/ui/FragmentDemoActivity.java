package com.reeuse.component.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.reeuse.component.R;
import com.reeuse.component.ui.fragment.ContactUsFragment;
import com.reeuse.component.ui.fragment.HomeFragment;
import com.reeuse.component.ui.fragment.OnFragmentInteractionListener;


public class FragmentDemoActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    private static final int HOME_FRAGMENT = 0;
    private static final int CONTACT_US_FRAGMENT = 1;

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

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
        }else if (id == R.id.action_home){
            displayView(HOME_FRAGMENT);
            return true;
        }else if (id == R.id.action_onePlus){
            displayView(CONTACT_US_FRAGMENT);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(String fragmentName) {
        toolbar.setTitle(fragmentName);
    }
    /**
     * Displaying fragment view for selected navigation drawer list item
     */
    private void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        String fragmentName = null;
        switch (position) {
            case HOME_FRAGMENT:
                fragment = new HomeFragment();
                fragmentName = HomeFragment.class.getSimpleName();
                break;
            case CONTACT_US_FRAGMENT:
                fragment = new ContactUsFragment();
                break;
            default:
                break;
        }
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_container, fragment);
            fragmentTransaction.addToBackStack(fragmentName);
            fragmentTransaction.commit();
        }
    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }


}
