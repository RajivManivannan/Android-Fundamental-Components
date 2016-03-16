package com.reeuse.component.thread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.reeuse.component.R;

import java.io.InputStream;

/**
 * ThreadActivity.java
 */
public class ThreadDemoActivity extends AppCompatActivity implements View.OnClickListener {
    final private String TAG = ThreadDemoActivity.class.getSimpleName();

    private ImageView imageView;
    private String url = "https://www.android.com/static/img/logos-2x/android-wordmark-8EC047.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView = (ImageView) findViewById(R.id.thread_image_view);
        (findViewById(R.id.thread_run_on_ui_btn)).setOnClickListener(this);
        (findViewById(R.id.thread_view_post_btn)).setOnClickListener(this);
        (findViewById(R.id.thread_view_post_delay_btn)).setOnClickListener(this);
        (findViewById(R.id.thread_asyc_task_btn)).setOnClickListener(this);

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.thread_run_on_ui_btn:
                runOnUiThread();
                break;
            case R.id.thread_view_post_btn:
                post();
                break;
            case R.id.thread_view_post_delay_btn:
                postDelay();
                break;
            case R.id.thread_asyc_task_btn:
                new DownloadImageTask(imageView)
                        .execute(url);
                break;
            default:
                break;
        }
    }

    private Bitmap loadImageFromNetwork(String url) {
        try {
            InputStream in = new java.net.URL(url).openStream();
            return BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private void runOnUiThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = loadImageFromNetwork(url);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            imageView.setImageBitmap(bitmap);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();

    }

    private void post() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = loadImageFromNetwork(url);
                Log.i(TAG, "Image downloaded from network");
                Log.i(TAG, "Image will be set in ImageView after 5 seconds");
                imageView.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            imageView.setImageBitmap(bitmap);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();


    }

    private void postDelay() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = loadImageFromNetwork(url);
                Log.i(TAG, "Image downloaded from network");
                Log.i(TAG, "Image will be set in ImageView after 5 seconds");
                imageView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            imageView.setImageBitmap(bitmap);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 5000);
            }
        }).start();
    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public DownloadImageTask(ImageView imageView) {
            this.imageView = imageView;
        }

        protected Bitmap doInBackground(String... urls) {
            return loadImageFromNetwork(urls[0]);
        }

        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }


}
