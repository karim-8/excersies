package com.example.myapplication;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;


public class DetailsActivity extends AppCompatActivity {

    int position;
    TextView textView1;
    TextView textView2;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_activty);
        // find views and set data to views.
        textView1 = (TextView) findViewById(R.id.video_title);
        textView2 = (TextView) findViewById(R.id.description);
        webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new MyBrowser());
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        // get data intent.
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            position = extras.getInt("position");
            switch (position) {

                case 0:
                    textView1.setText("Chest Workout");
                    textView2.setText(R.string.chest);
                    //textView2.setText(Html.fromHtml("<h2>Title</h2><br><p>Description here</p>"));
                    watchYoutubeVideo("https://www.youtube.com/watch?v=JBKej6n_A9I");
                    break;
                case 1:
                    textView1.setText("Biceps Workout");
                    textView2.setText(R.string.bicepes);
                    watchYoutubeVideo("https://www.youtube.com/watch?v=NftbyLKI0So");

                    break;
                case 2:
                    textView1.setText("Triceps Workout");
                    textView2.setText(R.string.tricepes);
                    watchYoutubeVideo("https://www.youtube.com/watch?v=s1CgCndoSYg");
                    break;

                case 3:
                    textView1.setText("Back Workout");
                    textView2.setText(R.string.back);
                    watchYoutubeVideo("https://www.youtube.com/watch?v=OXvQe9payHw");
                    break;

                case 4:
                    textView1.setText("Abs Workout");
                    textView2.setText(R.string.abs);
                    watchYoutubeVideo("https://www.youtube.com/watch?v=vkKCVCZe474");
                    break;


                case 5:
                    textView1.setText("Leg Workout");
                    textView2.setText(R.string.leg);
                    watchYoutubeVideo("https://www.youtube.com/watch?v=Apm7CdnsZNo");
                    break;


            }
        }
    }


    public void watchYoutubeVideo(String url) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            webView.loadUrl(url);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // int id = item.getItemId();
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.share:
                //implicent intents
                shareApp();
                return true;
            case R.id.rate:
                rateApp();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    // method to share app
    public void shareApp() {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        // Add data to the intent, the receiving app will decide
        // what to do with it.
        share.putExtra(Intent.EXTRA_TEXT,
                getString(R.string.app_name) + " \n" +
                        "market://details?id=" + getPackageName());
        startActivity(Intent.createChooser(share, getString(R.string.share_via)));
    }


    // method to rate app
    public void rateApp() {
        final String APP_IN_GOOGLE_PLAY_PAGE = "market://details?id=" + getPackageName();
        // get uri link for app in google play and open it to user
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, Uri.parse(APP_IN_GOOGLE_PLAY_PAGE));
        try {
            // start activity to rate app
            startActivity(goToMarket);
        } catch (Exception e) {
            e.printStackTrace();

            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }


    class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
