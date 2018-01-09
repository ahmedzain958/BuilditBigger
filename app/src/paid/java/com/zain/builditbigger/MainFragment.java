package com.zain.builditbigger;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zain.builditbigger.GCEEndpointsAsyncTask;
import com.zain.builditbigger.R;
import com.zain.jokedisplayandroidlibrary.JokeDisplayActivity;

/**
 * Created by Zain on 07/01/2018.
 */

public class MainFragment extends Fragment {
    private String myJoke = "";
    TextView txt_joke;
    Button getJoke;

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
                tellJoke(v);
            }
        });

        return rootView;
    }

    private void tellJoke(View view) {

        new GCEEndpointsAsyncTask(new GCEEndpointsAsyncTask.Listener() {
            @Override
            public void getData(final String joke) {
                if (!joke.equals("")) {
                    myJoke = joke;
                    startActivity(
                            new Intent(getActivity(), JokeDisplayActivity.class)
                                    .putExtra(JokeDisplayActivity.INTENT_EXTRA_NAME, joke));
                }

            }
        }).execute();


    }
}