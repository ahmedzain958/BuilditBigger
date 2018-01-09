package com.zain.builditbigger;

import android.app.Activity;
import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static junit.framework.Assert.assertFalse;

/**
 * Created by Zain on 08/01/2018.
 */
@RunWith(AndroidJUnit4.class)
public class GCEEndpointsAsyncTaskTest {
    CountDownLatch signal = null;
    String jokeString = null;
    Activity myActivity;

    /* public GCEEndpointsAsyncTaskTest() {
         super();
     }*/
    @Before
    public void setUp() throws Exception {
        signal = new CountDownLatch(1);
    }

    @After
    public void tearDown() throws Exception {
        signal.countDown();
    }

    public void testAlbumGetTask() throws InterruptedException {

         new GCEEndpointsAsyncTask(new GCEEndpointsAsyncTask.Listener() {
            @Override
            public void getData(String joke) {
                jokeString = joke;
                signal.countDown();
            }
        }).execute();

        signal.await();

        assertFalse(TextUtils.isEmpty(jokeString));

    }
}
