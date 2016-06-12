package org.disciplestoday.retrofittest22;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;


import com.google.mockwebserver.MockResponse;
import com.google.mockwebserver.MockWebServer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


/*
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
*/

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.junit.Assert.*;


/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@MediumTest
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentationTest {

    @Rule
    public ActivityTestRule<ArticleListActivity> mActivityRule = new ActivityTestRule(ArticleListActivity.class, true, false);
    // Don't launch the activity right away, get our mock server set up first.

    private MockWebServer server;




    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("org.disciplestoday.retrofittest22", appContext.getPackageName());
    }

    @Test
    public void testOctoCat() {
        Espresso.onView(ViewMatchers.withText("Spoon-Knife")).check(matches(isDisplayed()));
    }

    @Test
    public void testMockOctoCat() throws Exception {
        // Grateful credit to https://riggaroo.co.za/retrofit-2-mocking-http-responses/ and others.


        // Start the server.
        server = new MockWebServer();
        server.enqueue(new MockResponse()
                .setResponseCode(200).setBody(StringUtils.getStringFromFile(InstrumentationRegistry.getContext(), "octocat_ok.json")));
        server.play();
        AppConfig.serverBaseUrl = server.getUrl("/").toString();

        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);

        Espresso.onView(ViewMatchers.withText("Spoon-AssetFile-Mocked-Knife")).check(matches(isDisplayed()));
        //and check regular is not displayed.

        server.shutdown();

    }

}