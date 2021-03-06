package org.disciplestoday.retrofittest22;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;


import com.google.mockwebserver.Dispatcher;
import com.google.mockwebserver.MockResponse;
import com.google.mockwebserver.MockWebServer;
import com.google.mockwebserver.RecordedRequest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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

    public static final String TAG = ExampleInstrumentationTest.class.getSimpleName();

    @Rule
    public ActivityTestRule<ArticleListActivity> mActivityRule = new ActivityTestRule(ArticleListActivity.class, true, false);
    // Don't launch the activity right away, get our mock server set up first.

    private MockWebServer server;

    @Test
    public void testOctoCat() {
        Intent intent = new Intent();
        Log.d(TAG, "--->About to launch activity and check for text;");
        mActivityRule.launchActivity(intent);
        Espresso.onView(ViewMatchers.withText("Spoon-Knife")).check(matches(isDisplayed()));
        Log.d(TAG, "--->No exception if this code is reached, test passed with no thread.sleep thanks to Espresso");

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
        Log.d(TAG, "--->About to launch activity and check for text;");
        mActivityRule.launchActivity(intent);

        Espresso.onView(ViewMatchers.withText("Spoon-AssetFile-Mocked-Knife")).check(matches(isDisplayed()));
        //and check regular is not displayed.
        Log.d(TAG, "--->simple Test passed from mock json, about to shutdown mock server.");

        server.shutdown();

    }

    @Test
    public void testMockOctocatDispatch() throws Exception {
        server = new MockWebServer();


        final Dispatcher dispatcher = new Dispatcher() {

            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
                // e.g.     @GET("users/{user}/repos")
                Log.d("NJW", "request.getPath()" + request.getPath());
                if (request.getPath().contains("/users/")){
                    try {
                        return new MockResponse()
                                .setResponseCode(200).setBody(StringUtils.getStringFromFile(InstrumentationRegistry.getContext(),
                                        "octocat_ok.json"));
                    } catch (Exception e) {
                        Log.e(TAG, "Exception:" + e.getMessage());
                        return new MockResponse().setResponseCode(500);
                    }
                } else if (request.getPath().equals("v1/check/version/")){
                    return new MockResponse().setResponseCode(200).setBody("version=9");
                } else if (request.getPath().equals("/v1/profile/info")) {
                    return new MockResponse().setResponseCode(200).setBody("{\\\"info\\\":{\\\"name\":\"Lucas Albuquerque\",\"age\":\"21\",\"gender\":\"male\"}}");
                }
                return new MockResponse().setResponseCode(404);
            }
        };
        server.setDispatcher(dispatcher);
        server.play();
        AppConfig.serverBaseUrl = server.getUrl("/").toString();

        Intent intent = new Intent();
        Log.d(TAG, "--->About to launch activity and check for text;");
        mActivityRule.launchActivity(intent);
        Espresso.onView(ViewMatchers.withText("Spoon-AssetFile-Mocked-Knife")).check(matches(isDisplayed()));
        Log.d(TAG, "--->test passed for text from mock");

        server.shutdown();
    }

}