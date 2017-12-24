package com.sumberrejeki.webviewadam;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class WelcomingActivity extends AppCompatActivity {

    SharedPreferences appPreferences;
    boolean isAppInstalled = false;
    boolean internetStatus;

    private InterstitialAd mInterstitialAd;
    private Boolean showProgress = false;
    private ProgressDialog pd;
    private static String LOG_TAG = "EXAMPLE";

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcoming);

        addShortcut();

        internetStatus=isOnline();
        if(!internetStatus){
            notifNoOnline();
        }else{
            Toast.makeText(this, "WELCOME.. :)", Toast.LENGTH_SHORT).show();
        }


        // editan solehudin
        pd = new ProgressDialog(this);

        // Create the InterstitialAd and set the adUnitId.
        mInterstitialAd = new InterstitialAd(this);
        // Defined in res/values/strings.xml
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        requestInterstitial();



        TextView nextWelcoming = (TextView) findViewById(R.id.nextwelcoming);
        nextWelcoming.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                intent = new Intent(WelcomingActivity.this, SurveyActivity.class);
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

    // ini method untuk membuat shortcut otomatis di desktop android setelah install apk
    private void addShortcut() {
        appPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        isAppInstalled = appPreferences.getBoolean("isAppInstalled", false);
        if (!isAppInstalled) {
            Intent shortcutIntent = new Intent(getApplicationContext(), SplashActivity.class);
            shortcutIntent.setAction(Intent.ACTION_MAIN);
            Intent intent = new Intent();
            intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
            intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, getString(R.string.app_name));
            intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                    Intent.ShortcutIconResource.fromContext(getApplicationContext(),
                            R.mipmap.ic_launcher));

            intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            getApplicationContext().sendBroadcast(intent);

            SharedPreferences.Editor editor = appPreferences.edit();
            editor.putBoolean("isAppInstalled", true);
            editor.commit();
        }

    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        else return false;
    }

    public void notifNoOnline() {
        new AlertDialog.Builder(this)
                .setMessage("Please connect to the Internet to continue!")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
//                        MainActivity.this.finish();

                        Intent intent = new Intent(WelcomingActivity.this, SplashActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                    }
                })
//                .setNegativeButton("No", null)
                .show();
    }

    // Method Override untuk pop-up exit confirmation
    @Override
    public void onBackPressed() {
        final Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        new AlertDialog.Builder(this)
                .setMessage("Are you sure to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent i = new Intent(WelcomingActivity.this, SurveyActivity.class);
                        finish();
                        startActivity(a);
                        startActivity(i);
                    }
                })
                .setNegativeButton("No", null)
                .show();
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
