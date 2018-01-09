package com.zain.builditbigger;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.zain.jokedisplayandroidlibrary.JokeDisplayActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    private String myJoke = "";
    TextView txt_joke;
    Button getJoke;
    private InterstitialAd mInterstitialAd;
    private AdView mAdView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        getJoke = rootView.findViewById(R.id.getJoke);
        txt_joke = rootView.findViewById(R.id.joke);
        getJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tellJoke();
            }
        });
     /*   mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.AdUnitId));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());*/
        mAdView = rootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        mAdView.loadAd(adRequest);

        return rootView;
    }

    private void tellJoke() {

        new GCEEndpointsAsyncTask(new GCEEndpointsAsyncTask.Listener() {
            @Override
            public void getData(final String joke) {
                if (!myJoke.equals("") && !myJoke.equals(null))
                    startActivity(
                            new Intent(getActivity(), JokeDisplayActivity.class)
                                    .putExtra(JokeDisplayActivity.INTENT_EXTRA_NAME, joke));
                /*if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {

                }*/


              /*  mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdLoaded() {
                        // Code to be executed when an ad finishes loading.
                    }

                    @Override
                    public void onAdFailedToLoad(int errorCode) {
                        // Code to be executed when an ad request fails.
                    }

                    @Override
                    public void onAdOpened() {
                        // Code to be executed when the ad is displayed.

                    }

                    @Override
                    public void onAdLeftApplication() {
                        // Code to be executed when the user has left the app.
                    }

                    @Override
                    public void onAdClosed() {
                        if (!myJoke.equals("") && !myJoke.equals(null)) {

                        }
                    }
                });*/


            }
        }).execute();

    }
}
