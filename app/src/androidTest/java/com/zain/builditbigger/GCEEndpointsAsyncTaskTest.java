package com.zain.builditbigger;

import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Zain on 08/01/2018.
 */
@RunWith(AndroidJUnit4.class)
public class GCEEndpointsAsyncTaskTest {
    CountDownLatch signal = null;
    String jokeString = null;

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

    @Test
    public void testgetJoke() throws InterruptedException {

        GCEEndpointsAsyncTask gceEndpointsAsyncTask = new GCEEndpointsAsyncTask(new GCEEndpointsAsyncTask.Listener() {
            @Override
            public void getData(String joke) {
                assertNotNull(joke);
            }
        });
        gceEndpointsAsyncTask.execute();
        signal.await();

    }
}
