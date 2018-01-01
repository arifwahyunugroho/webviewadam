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
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.StartAppSDK;

public class SurveyDuaActivity extends AppCompatActivity {

//    private InterstitialAd mInterstitialAd;
    private Boolean showProgress = false;
    private ProgressDialog pd;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        StartAppSDK.init(this, getResources().getString(R.string.startapp_id), true);


        setContentView(R.layout.activity_survey_dua);

        pd = new ProgressDialog(this);

//        // Create the InterstitialAd and set the adUnitId.
//        mInterstitialAd = new InterstitialAd(this);
//        // Defined in res/values/strings.xml
//        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
//        requestInterstitial();

        TextView under13 = (TextView) findViewById(R.id.under13);
        under13.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                intent = new Intent(SurveyDuaActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
//                showInterstitial();
                displayInterstitialStartapp();
            }
        });

        TextView olderThan13 = (TextView) findViewById(R.id.olderthan13);
        olderThan13.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                intent = new Intent(SurveyDuaActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
//                showInterstitial();
                displayInterstitialStartapp();
            }
        });

        // Banner Ads Admob
        AdView mAdViewban = (AdView) findViewById(R.id.adView_banner);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdViewban.loadAd(adRequest);

    }

//    private void requestInterstitial() {
//
//        if (showProgress) {
//
//            pd.setMessage("Please Wait...");
//            pd.setCancelable(false);
//            pd.show();
//        }
//
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mInterstitialAd.loadAd(adRequest);
//
//        mInterstitialAd.setAdListener(new AdListener() {
//
//            @Override
//            public void onAdLoaded() {
//                if (showProgress) {
//                    pd.dismiss();
//                    showInterstitial();
//                }
//            }
//
//            @Override
//            public void onAdFailedToLoad(int errorCode) {
//                if (showProgress) {
//
//                    requestInterstitial();
//                }
//            }
//
//            @Override
//            public void onAdClosed() {
//
//                startActivity(intent);
//            }
//        });
//    }
//
//    private void showInterstitial() {
//        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
//            mInterstitialAd.show();
//            showProgress = false;
//        } else {
//            showProgress = true;
//            requestInterstitial();
//        }
//    }

    // ini method untuk startapp interstitial
    public void displayInterstitialStartapp() {
        StartAppAd.showAd(this);
    }

}
