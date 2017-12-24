package com.sumberrejeki.webviewadam;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class SplashActivity extends AppCompatActivity {

    private static int Durasi = 2000;

    private InterstitialAd mInterstitialAd;
    private Boolean showProgress = false;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        // Create the InterstitialAd and set the adUnitId.
//        mInterstitialAd = new InterstitialAd(this);
//        // Defined in res/values/strings.xml
//        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
//        requestInterstitial();

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
////                showInterstitial();
//            }
//        }, Durasi);
//
//        pd = new ProgressDialog(this);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, WelcomingActivity.class);
                startActivity(i);
                this.finish();
            }
            private void finish() {
            }
        }, Durasi);

    }


//    private void requestInterstitial(){
//
//
//        if (showProgress){
//
//            pd.setMessage("Please Wait...");
//            pd.setCancelable(false);
//            pd.show();
//        }
//
//
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mInterstitialAd.loadAd(adRequest);
//
//        mInterstitialAd.setAdListener(new AdListener() {
//
//
//            @Override
//            public void onAdLoaded() {
//                if (showProgress){
//                    pd.dismiss();
//                    showInterstitial();
//                }
//            }
//
//            @Override
//            public void onAdFailedToLoad(int errorCode) {
//                if (showProgress){
//
//                    requestInterstitial();
//                }
//            }
//
//
//            @Override
//            public void onAdClosed() {
//                Intent i = new Intent(SplashActivity.this, WelcomingActivity.class);
//                startActivity(i);
//                finish();
//            }
//        });
//    }
//
//    private void showInterstitial(){
//        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
//            mInterstitialAd.show();
//            showProgress = false;
//        } else {
//            showProgress = true;
//            requestInterstitial();
//        }
//    }
}


