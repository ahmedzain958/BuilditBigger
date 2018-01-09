package com.zain.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.zain.jokedisplayandroidlibrary.JokeDisplayActivity;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;



/**
 * Created by Zain on 06/01/2018.
 */

public class GCEEndpointsAsyncTask extends AsyncTask<Void, Void, String> {
    private final Listener listener;
    private static MyApi myApiService = null;


    //    private static MyApi myApiService = null;
    public GCEEndpointsAsyncTask(Listener listener) {
        this.listener = listener;

    }

    public interface Listener {
        void getData(String joke);
    }

    @Override
    protected String doInBackground(Void... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
//                     .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setRootUrl("http://192.168.1.6:8080/_ah/api/");
                    /*.setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });*/
            // end options for devappserver

            myApiService = builder.build();
        }


        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
        //return null;
    }

    @Override
    protected void onPostExecute(final String joke) {
        super.onPostExecute(joke);
        if (listener != null) {
            if (!joke.equals("")) {
                listener.getData(joke);
            }
        }
    }
}
