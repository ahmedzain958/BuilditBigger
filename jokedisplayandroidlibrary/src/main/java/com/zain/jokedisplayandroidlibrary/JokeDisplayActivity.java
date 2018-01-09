package com.zain.jokedisplayandroidlibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.ButterKnife;

public class JokeDisplayActivity extends AppCompatActivity {

    public static final String INTENT_EXTRA_NAME = "joke_value";
    TextView txt_jokeDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);
        ButterKnife.bind(this);
        if (getIntent().hasExtra(INTENT_EXTRA_NAME)) {
            txt_jokeDisplay = findViewById(R.id.txt_jokeDisplay);
            txt_jokeDisplay.setText(getIntent().getStringExtra(INTENT_EXTRA_NAME));
        }
    }
}
