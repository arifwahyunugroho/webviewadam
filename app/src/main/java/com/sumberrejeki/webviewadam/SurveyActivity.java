package com.sumberrejeki.webviewadam;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class SurveyActivity extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;
    private Boolean showProgress = false;
    private ProgressDialog pd;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        pd = new ProgressDialog(this);

        // Create the InterstitialAd and set the adUnitId.
        mInterstitialAd = new InterstitialAd(this);
        // Defined in res/values/strings.xml
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        requestInterstitial();

        TextView nextWelcoming = (TextView) findViewById(R.id.yessure);
        nextWelcoming.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                intent = new Intent(SurveyActivity.this, SurveyDuaActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                showInterstitial();
            }
        });

        TextView nothingWelcoming = (TextView) findViewById(R.id.nothing);
        nothingWelcoming.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                intent = new Intent(SurveyActivity.this, SurveyDuaActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                showInterstitial();
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
