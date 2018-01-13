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

    /* public GCEEndpointsAsyncTaskTest() {
         super();
     }*/
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testgetJoke() throws InterruptedException {
        final CountDownLatch signal = new CountDownLatch(1);
        GCEEndpointsAsyncTask gceEndpointsAsyncTask = new GCEEndpointsAsyncTask(new GCEEndpointsAsyncTask.Listener() {
            @Override
            public void getData(String joke) {

                assertNotNull(joke);
                signal.countDown();
            }
        });
        gceEndpointsAsyncTask.execute();
        signal.await();

    }
}
