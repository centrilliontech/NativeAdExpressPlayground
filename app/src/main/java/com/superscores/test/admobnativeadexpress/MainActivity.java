package com.superscores.test.admobnativeadexpress;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.FrameLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.NativeExpressAdView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "NativeAdMainActivity";

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        setContentView(R.layout.activity_main);

        FrameLayout bannerContainer = (FrameLayout) findViewById(R.id.bannerContainer);
        CardView nativeAdContainer1 = (CardView) findViewById(R.id.nativeAdContainer1);
        CardView nativeAdContainer2 = (CardView) findViewById(R.id.nativeAdContainer2);
        CardView nativeAdContainer3 = (CardView) findViewById(R.id.nativeAdContainer3);

        AdRequest adRequest =
                new AdRequest.Builder().addTestDevice("52E674BE351E9AA60E5765B92896B436").build();

        mAdView = new AdView(this);
        mAdView.setAdSize(AdSize.SMART_BANNER);
        mAdView.setAdUnitId(getString(R.string.banner_ad_unit_id));
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                Log.d(TAG, "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                Log.d(TAG, "onAdFailedToLoad: " + i);
            }
        });

        bannerContainer.addView(mAdView);

        NativeExpressAdView nativeExpressAdView1 = new NativeExpressAdView(this);
        nativeExpressAdView1.setAdUnitId(getString(R.string.native_ad_unit_id));
        nativeExpressAdView1.setAdSize(new AdSize(320, 300));
        nativeExpressAdView1.loadAd(adRequest);
        nativeAdContainer1.addView(nativeExpressAdView1);

        NativeExpressAdView nativeExpressAdView2 = new NativeExpressAdView(this);
        nativeExpressAdView2.setAdUnitId(getString(R.string.native_ad_unit_id));
        nativeExpressAdView2.setAdSize(new AdSize(AdSize.FULL_WIDTH, 300));
        nativeExpressAdView2.loadAd(adRequest);
        nativeAdContainer2.addView(nativeExpressAdView2);

        NativeExpressAdView nativeExpressAdView3 = new NativeExpressAdView(this);
        nativeExpressAdView3.setAdUnitId(getString(R.string.native_ad_unit_id));
        nativeExpressAdView3.setAdSize(new AdSize(getScreenWidthInDp() - 32, 300));
        nativeExpressAdView3.loadAd(adRequest);
        nativeAdContainer3.addView(nativeExpressAdView3);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mAdView != null) {
            mAdView.pause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mAdView != null) {
            mAdView.destroy();
            mAdView = null;
        }
    }

    public int getScreenWidthInDp() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float density = displayMetrics.density;
        return (int) (displayMetrics.widthPixels / density);
    }
}
