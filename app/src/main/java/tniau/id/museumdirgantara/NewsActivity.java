package tniau.id.museumdirgantara;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class NewsActivity extends AppCompatActivity {
    String TAG = "Dirgantara";
    private ProgressBar spinner;
    private WebView webview;
    private SwipeRefreshLayout swipe;
    private String currentURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        checkNetworkConnection();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Berita Museum");

        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        String str_ScreenSize = "The Android Screen is: "
                + dm.widthPixels
                + " x "
                + dm.heightPixels;

        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        int dpHeight = (int)(displayMetrics.heightPixels / displayMetrics.density + 0.5);
        int dpWidth = (int)(displayMetrics.widthPixels / displayMetrics.density + 0.5);

        currentURL = "https://www.facebook.com/plugins/page.php?href=https%3A%2F%2Fwww.facebook.com%2FMuspusdirlaYogyakarta%2F&tabs=timeline&width="+ dpWidth +"&height="+ dpHeight +"&small_header=true&adapt_container_width=true&hide_cover=true&show_facepile=false&appId=2257166317875821";

        webview = findViewById(R.id.web_timelinefb);
        spinner = findViewById(R.id.loading_timelinefb);
        swipe = findViewById(R.id.swipe);

        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        webview.loadUrl(currentURL);
        webview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webview.getSettings().setAppCacheEnabled(true);
        webview.setWebViewClient(new MyWebViewClient());

        webview.setOnKeyListener(new View.OnKeyListener(){

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK
                        && event.getAction() == MotionEvent.ACTION_UP
                        && webview.canGoBack()) {
                    webview.goBack();
                    return true;
                }

                return false;
            }

        });
    }

    public class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            swipe.setRefreshing(false);
            currentURL = url;
            super.onPageFinished(view, url);
            spinner.setVisibility(View.GONE);
        }
    }

    public boolean checkNetworkConnection() {
        ConnectivityManager connMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        boolean isConnected = false;
        if (networkInfo != null && (isConnected = networkInfo.isConnected())) {
        } else {
            Toast.makeText(this, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
        }

        return isConnected;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
