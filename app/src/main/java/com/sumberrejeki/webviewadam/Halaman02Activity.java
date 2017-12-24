package com.sumberrejeki.webviewadam;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Halaman02Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman02);

        TextView reviewApps = (TextView) findViewById(R.id.review_apps);
        reviewApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uriUrl = Uri.parse(getResources().getString(R.string.url_apps));
                Intent browse_intent = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(browse_intent);
            }
        });

        TextView shareApps = (TextView) findViewById(R.id.share_apps);
        shareApps.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "AndroidSolved");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,
                        "Grab for Free " + getResources().getString(R.string.app_name) + " Now! " + getResources().getString(R.string.url_apps));
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

        TextView moreApps = (TextView) findViewById(R.id.more_apps);
        moreApps.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Uri uriUrl3 = Uri.parse(getResources().getString(R.string.url_developer));
                Intent browse_intent3 = new Intent(Intent.ACTION_VIEW, uriUrl3);
                startActivity(browse_intent3);
            }
        });

//        Button btnRestart = (Button) findViewById(R.id.next_button);
//        btnRestart.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(Halaman2.this, SplashActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
//
//            }
//        });


        // Banner Ads Admob
        AdView mAdViewban = (AdView) findViewById(R.id.adView_banner);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdViewban.loadAd(adRequest);
    }



}
