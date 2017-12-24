package com.sumberrejeki.webviewadam;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class Halaman01Activity extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;
    private Boolean showProgress = false;
    private ProgressDialog pd;

    private Intent intent;

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman01);

        pd = new ProgressDialog(this);

        // Create the InterstitialAd and set the adUnitId.
        mInterstitialAd = new InterstitialAd(this);
        // Defined in res/values/strings.xml
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        requestInterstitial();


        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        toolbar.setSubtitle(this.getIntent().getStringExtra("pesan2"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        Button btnPindahHal2 = (Button) findViewById(R.id.next_button);
        btnPindahHal2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                intent = new Intent(Halaman01Activity.this, Halaman02Activity.class);
                startActivity(intent);
                showInterstitial();

            }
        });

        Button btnPindahMain = (Button) findViewById(R.id.back_button);
        btnPindahMain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                intent = new Intent(Halaman01Activity.this, MainActivity.class);
                startActivity(intent);
//                showInterstitial();

            }
        });

        mWebView = (WebView) findViewById(R.id.activity_main_webview);
        mWebView.loadUrl(this.getIntent().getStringExtra("pesan"));

        // mengaktifkan webclient agar loadurl webview tampil di lokal apk
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return true;
            }
        });

        // Banner Ads Admob
        AdView mAdViewban = (AdView) findViewById(R.id.adView_banner);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdViewban.loadAd(adRequest);

    }


    private void requestInterstitial() {

        if (showProgress) {

            pd.setMessage("Please Wait...");
            pd.setCancelable(false);
            pd.show();
        }

        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
                if (showProgress) {
                    pd.dismiss();
                    showInterstitial();
                }
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                if (showProgress) {

                    requestInterstitial();
                }
            }

            @Override
            public void onAdClosed() {

                startActivity(intent);
            }
        });
    }

    private void showInterstitial() {
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            showProgress = false;
        } else {
            showProgress = true;
            requestInterstitial();
        }
    }



}
