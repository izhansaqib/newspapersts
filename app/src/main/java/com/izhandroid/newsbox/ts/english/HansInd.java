package com.izhandroid.newsbox.ts.english;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.webkit.WebSettingsCompat;
import androidx.webkit.WebViewFeature;

import android.os.Handler;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.izhandroid.newsbox.ts.R;
import com.izhandroid.newsbox.ts.ShareUtils;
import com.izhandroid.newsbox.ts.telugu.AndhraBhumi;

import static com.izhandroid.newsbox.ts.R.id.webviewtwo;

/**
 * Created by Izhan Ali on 8/16/2018.
 */

public class HansInd extends AppCompatActivity {
    /**
     * HANS
     * TEL TODY
     * FINANCIAL EX
     * Telegraph
     **/


    public WebView wv;
    Snackbar snacki;
    String lol = "Mozilla/5.0 (Linux; Android 5.1.1; Nexus 5 Build/LMY48B; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/43.0.2357.65 Mobile Safari/537.36";
    private FloatingActionButton floatingActionButton;
    private AppBarLayout appBarLayout;
    private RelativeLayout relativeLayout;
    private MaterialCardView cardView;
    FirebaseAnalytics firebaseAnalytics;
    private LinearLayout rootContent;
    String kit = "Mozilla/5.0 (Linux; Android 4.4; Nexus 5 Build/_BuildID_) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0 Mobile Safari/537.36";
    /**
     * NTel
     **/

    private String urla;
    private ProgressBar progressBarD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_two);
        Toolbar mTopToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mTopToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        if (!isConnected(HansInd.this))
            buildDialog(HansInd.this).show();
        else {
            Toast.makeText(getApplicationContext(), "Welcomr", Toast.LENGTH_SHORT);

        }
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);

        rootContent = findViewById(R.id.weblayouttwo);
        appBarLayout = findViewById(R.id.appbar);
        relativeLayout = findViewById(R.id.wtrmrkwebtwo);
        cardView = findViewById(R.id.web_two_txtbelow);
        floatingActionButton = findViewById(R.id.fabtwo);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtils.chkntake(HansInd.this, appBarLayout, rootContent, relativeLayout, cardView, floatingActionButton, "newsarticle-eng", firebaseAnalytics);
            }
        });
        this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        progressBarD = findViewById(R.id.progr);
        progressBarD.setMax(100);

        LoadWeb();


    }

    private boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if ((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting()))
                return true;
            else return false;
        } else
            return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setCancelable(true);
        builder.setTitle("No Internet Connection");
        builder.setMessage("Please turn on Wifi/Mobile Data and retry");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();

            }
        });
        builder.setNegativeButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (!isConnected(HansInd.this))
                    buildDialog(HansInd.this).show();
                else {
                    wv.reload();

                }
            }
        });
        return builder;
    }

    public void LoadWeb() {

        wv = (WebView) findViewById(webviewtwo);
        final WebSettings settings = wv.getSettings();
        settings.setJavaScriptEnabled(true);
        wv.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
//wv.loadUrl("http://epaper.thehansindia.com");
        wv.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        wv.getSettings().setAppCacheEnabled(true);
        wv.getSettings().getJavaScriptCanOpenWindowsAutomatically();
        //
        wv.getSettings().getDisplayZoomControls();
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(true);
        wv.getSettings().setSupportZoom(true);
        wv.getSettings().setLoadsImagesAutomatically(true);
        wv.getSettings().supportMultipleWindows();
        wv.getSettings().getAllowUniversalAccessFromFileURLs();
        wv.getSettings().getLoadWithOverviewMode();
        settings.getMediaPlaybackRequiresUserGesture();

        if (Build.VERSION.SDK_INT >= 21) {
            wv.getSettings().setUserAgentString(lol);
        } else {
            wv.getSettings().setUserAgentString(kit);
        }

        if (Build.VERSION.SDK_INT <= 18) {
            wv.getSettings().setBuiltInZoomControls(true);
            wv.getSettings().setDisplayZoomControls(true);
        } else {
            wv.getSettings().setBuiltInZoomControls(true);
            wv.getSettings().setDisplayZoomControls(true);
            wv.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
        settings.setAllowFileAccess(true);
        settings.setAllowContentAccess(true);


        settings.setDomStorageEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setUseWideViewPort(true);
        settings.setEnableSmoothTransition(true);
        if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
            WebSettingsCompat.setForceDark(settings, WebSettingsCompat.FORCE_DARK_ON);
        }

        //refreshLayout(this);
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBarD.setProgress(0);
                progressBarD.setVisibility(View.GONE);
                setTitle(view.getTitle());

                SharedPreferences settings = getSharedPreferences(Constanted.PREF_NAMEAB, 0);

                if (settings.getBoolean("my_first_time", true)) {
                    //the app is being launched for first time, do something
                    if (url.contains("deccanherald")) {
                        Toast t = Toast.makeText(HansInd.this, "Swipe to turn the pages", Toast.LENGTH_LONG);
                        t.setGravity(Gravity.CENTER, 0, 0);
                        t.show();
                    }
                    // first time task


                    // record the fact that the app has been started at least once
                    settings.edit().putBoolean("my_first_time", false).commit();
                }
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                progressBarD.setProgress(0);
                progressBarD.setVisibility(View.VISIBLE);
                setTitle("Loading...Please wait");
                super.onPageStarted(view, url, favicon);

            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

                wv.loadUrl("file:///android_asset/error.html");
                Alert(view);

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });


        wv.setWebChromeClient(new WebChromeClient() {

            public void onProgressChanged(WebView view, int newProgress) {
                // Update the progress bar with page loading progress
                progressBarD.setProgress(newProgress);
                if (newProgress == 100) {
                    // Hide the progressbar
                    progressBarD.setVisibility(View.GONE);
                }
            }
        });
        Intent intent = this.getIntent();

        if (intent != null) {

            Bundle data = getIntent().getExtras();


            if (data != null && data.containsKey("hans")) {
                urla = data.getString("hans");
                wv.loadUrl(urla);
                Bundle params = new Bundle();
                params.putString("msg", "hans paper loaded from HansAct");
                params.putString("title", "hans was called");
                firebaseAnalytics.logEvent("HansEn", params);
            }
            if (data != null && data.containsKey("teltod")) {
                urla = data.getString("teltod");
                wv.loadUrl(urla);
                Bundle params = new Bundle();
                params.putString("msg", "telangana today paper loaded from HansAct");
                params.putString("title", "teltod was called");
                firebaseAnalytics.logEvent("TelTodEn", params);
            }
            if (data != null && data.containsKey("finex")) {
                urla = data.getString("finex");
                wv.loadUrl(urla);
                Bundle params = new Bundle();
                params.putString("msg", "financial express loaded from HansAct");
                params.putString("title", "finex was called");
                firebaseAnalytics.logEvent("FinExEn", params);
            }
            if (data != null && data.containsKey("teleg")) {
                urla = data.getString("teleg");
                wv.loadUrl(urla);
            }
            if (data != null && data.containsKey("decher")) {
                urla = data.getString("decher");
                wv.loadUrl(urla);
                Bundle params = new Bundle();
                params.putString("msg", "Deccan Herald loaded from HansAct");
                params.putString("title", "decher was called");
                firebaseAnalytics.logEvent("DecHerEn", params);
            }
            if (data != null && data.containsKey("iexhy")) {
                urla = data.getString("iexhy");
                wv.loadUrl(urla);
                Bundle params = new Bundle();
                params.putString("msg", "Ind Express hyd loaded from HansAct");
                params.putString("title", "iexhy was called");
                firebaseAnalytics.logEvent("IndExpEn", params);
            }
            if (data != null && data.containsKey("iexwa")) {
                urla = data.getString("iexwa");
                wv.loadUrl(urla);
                Bundle params = new Bundle();
                params.putString("msg", "Ind Express wgl loaded from HansAct");
                params.putString("title", "iexwa was called");
                firebaseAnalytics.logEvent("IndExpEn", params);
            }
            if (data != null && data.containsKey("iexvj")) {
                urla = data.getString("iexvj");
                wv.loadUrl(urla);
                Bundle params = new Bundle();
                params.putString("msg", "Ind Express vijwd loaded from HansAct");
                params.putString("title", "iexvj was called");
                firebaseAnalytics.logEvent("IndExpEn", params);
            }
            if (data != null && data.containsKey("iexvs")) {
                urla = data.getString("iexvs");
                wv.loadUrl(urla);
                Bundle params = new Bundle();
                params.putString("msg", "Ind Express vishptnm loaded from HansAct");
                params.putString("title", "iexvs was called");
                firebaseAnalytics.logEvent("IndExpEn", params);
            }
            if (data != null && data.containsKey("iexti")) {
                urla = data.getString("iexti");
                wv.loadUrl(urla);
                Bundle params = new Bundle();
                params.putString("msg", "Ind Express trpt loaded from HansAct");
                params.putString("title", "iexti was called");
                firebaseAnalytics.logEvent("IndExpEn", params);
            }
            if (data != null && data.containsKey("iexan")) {
                urla = data.getString("iexan");
                wv.loadUrl(urla);
                Bundle params = new Bundle();
                params.putString("msg", "Ind Express antpr loaded from HansAct");
                params.putString("title", "iexan was called");
                firebaseAnalytics.logEvent("IndExpEn", params);
            }
            if (data != null && data.containsKey("iexta")) {
                urla = data.getString("iexta");
                wv.loadUrl(urla);
                Bundle params = new Bundle();
                params.putString("msg", "Ind Express tdpllgdm loaded from HansAct");
                params.putString("title", "iexta was called");
                firebaseAnalytics.logEvent("IndExpEn", params);
            }
            if (data != null && data.containsKey("iex")) {
                urla = data.getString("iex");
                wv.loadUrl(urla);
                Bundle params = new Bundle();
                params.putString("msg", "Ind Express all loaded from HansAct");
                params.putString("title", "iex was called");
                firebaseAnalytics.logEvent("IndExpEn", params);
            }


        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_web, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.reloadwv:
                LoadWeb();
                return true;
            case R.id.back:
                wv.goBack();
                return true;
            case R.id.cachecl:
                wv.clearCache(true);
                wv.reload();
                return true;
            case R.id.screenon:
                     /*isCheckeded = item.isChecked();
                     item.setChecked(isCheckeded);


                    this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);*/
                if (item.isChecked()) {
                    item.setChecked(false);
                    this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


                } else {
                    this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

                    item.setChecked(true);
                }
                return true;
            case R.id.darkmode:

                if (item.isChecked()) {

                    if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
                        item.setChecked(false);
                        WebSettingsCompat.setForceDark(wv.getSettings(), WebSettingsCompat.FORCE_DARK_OFF);
                    } else {
                        item.setChecked(false);
                        item.setEnabled(false);
                        Toast.makeText(this, "Dark Pages are not supported to your device", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
                        WebSettingsCompat.setForceDark(wv.getSettings(), WebSettingsCompat.FORCE_DARK_ON);
                        item.setChecked(true);
                    } else {
                        item.setChecked(false);
                        item.setEnabled(false);
                        Toast.makeText(this, "Dark Pages are not supported to your device", Toast.LENGTH_SHORT).show();
                    }
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void Alert(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(HansInd.this);
        builder.setCancelable(true);
        builder.setTitle("Sorry, an error occured");
        builder.setMessage("Please retry or check the internet connection");
        builder.setIcon(android.R.drawable.ic_dialog_info);


        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });
        builder.setNegativeButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LoadWeb();
            }
        });


    }

    @Override
    protected void onPause() {
        wv.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        wv.onResume();
        super.onResume();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (wv.canGoBack()) {

                        wv.goBack();
                        snacki = Snackbar.make(wv, "Press Again", Snackbar.LENGTH_SHORT);
                        snacki.show();

                        View sbarview = snacki.getView();
                        sbarview.setBackgroundColor(getResources().getColor(R.color.colorAccentDark));
                    } else {
                        finish();
                        wv.clearCache(true);


                    }

                    return true;


            }
        }
        return super.onKeyDown(keyCode, event);
    }
}

