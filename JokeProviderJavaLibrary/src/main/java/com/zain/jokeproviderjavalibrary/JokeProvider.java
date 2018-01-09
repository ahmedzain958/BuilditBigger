package com.zain.jokeproviderjavalibrary;

import java.util.Random;

public class JokeProvider {
    private static final String[] Jokes = {
            "JOKE1",
            "JOKE2",
            "JOKE3",
            "JOKE4",
            "JOKE5",
            "JOKE6",
            "JOKE7"

    };


    public static String getJoke() {
        return Jokes[(new Random()).nextInt(Jokes.length)];
    }

}
