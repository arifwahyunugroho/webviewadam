package com.sumberrejeki.webviewadam;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Adam> adamList = new ArrayList<>();
    private RecyclerView recyclerView;
    private AdamAdapter mAdapter;

    String UrltoLoadIntent, ButtonTitleIntent;

    private InterstitialAd mInterstitialAd;
    private Boolean showProgress = false;
    private ProgressDialog pd;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        pd = new ProgressDialog(this);

        // Create the InterstitialAd and set the adUnitId.
        mInterstitialAd = new InterstitialAd(this);
        // Defined in res/values/strings.xml
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        requestInterstitial();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new AdamAdapter(adamList);

        recyclerView.setHasFixedSize(true);

        // vertical RecyclerView
        // keep adam_list_rowxml width to `match_parent`
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        // horizontal RecyclerView
        // keep adam_list_rowxml width to `wrap_content`
        // RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(mLayoutManager);

        // adding inbuilt divider line
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // adding custom divider line with padding 16dp
        // recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.HORIZONTAL, 16));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(mAdapter);

        // row click listener
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {




                Adam adam = adamList.get(position);
//                Toast.makeText(getApplicationContext(), adam.getHtmlToLoad() + " is selected!", Toast.LENGTH_SHORT).show();


                intent = new Intent(MainActivity.this, Halaman01Activity.class);
                UrltoLoadIntent = adam.getHtmlToLoad();
                ButtonTitleIntent = adam.getButtonTitle();

                intent.putExtra("pesan", UrltoLoadIntent);
                intent.putExtra("pesan2", ButtonTitleIntent);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);
                showInterstitial();

            }




            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        prepareAdamData();

        // Banner Ads Admob
        AdView mAdViewban = (AdView) findViewById(R.id.adView_banner);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdViewban.loadAd(adRequest);


    }

    /**
     * Prepares sample data to provide data set to adapter
     */
    private void prepareAdamData() {
        Adam adam = new Adam("Title 1", "file:///android_asset/www/bagongkonten/halaman01.html");
        adamList.add(adam);

        adam = new Adam("Title 2", "file:///android_asset/www/bagongkonten/halaman02.html");
        adamList.add(adam);

        adam = new Adam("Title 3", "file:///android_asset/www/bagongkonten/halaman03.html");
        adamList.add(adam);

        adam = new Adam("Title 4", "file:///android_asset/www/bagongkonten/halaman04.html");
        adamList.add(adam);

        adam = new Adam("Title 5", "file:///android_asset/www/bagongkonten/halaman05.html");
        adamList.add(adam);

        adam = new Adam("Title 6", "file:///android_asset/www/bagongkonten/halaman06.html");
        adamList.add(adam);

        adam = new Adam("Title 7", "file:///android_asset/www/bagongkonten/halaman07.html");
        adamList.add(adam);







        // notify adapter about data set changes
        // so that it will render the list with new data
        mAdapter.notifyDataSetChanged();
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
                        finish();
                        startActivity(a);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}


