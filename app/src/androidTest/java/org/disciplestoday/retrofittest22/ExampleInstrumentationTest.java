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
                .setResponseCode(200).setBody(getSuccessTestBody()));
        server.play();
        AppConfig.serverBaseUrl = server.getUrl("/").toString();

        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);

        Espresso.onView(ViewMatchers.withText("SpoonMocked-Knife")).check(matches(isDisplayed()));
        //and check regular is not displayed.

        server.shutdown();

    }

    private String getSuccessTestBody() {
        //TODO: Read from file in assets or fixtures directory or similar
        // https://github.com/riggaroo/android-retrofit-test-examples/blob/master/RetrofitTestExample/app/src/androidTest/java/za/co/riggaroo/retrofittestexample/RestServiceTestHelper.java

        return "[\n" +
                "  {\n" +
                "    \"id\": 18221276,\n" +
                "    \"name\": \"git-consortium\",\n" +
                "    \"full_name\": \"octocat/git-consortium\",\n" +
                "    \"owner\": {\n" +
                "      \"login\": \"octocat\",\n" +
                "      \"id\": 583231,\n" +
                "      \"avatar_url\": \"https://avatars.githubusercontent.com/u/583231?v=3\",\n" +
                "      \"gravatar_id\": \"\",\n" +
                "      \"url\": \"https://api.github.com/users/octocat\",\n" +
                "      \"html_url\": \"https://github.com/octocat\",\n" +
                "      \"followers_url\": \"https://api.github.com/users/octocat/followers\",\n" +
                "      \"following_url\": \"https://api.github.com/users/octocat/following{/other_user}\",\n" +
                "      \"gists_url\": \"https://api.github.com/users/octocat/gists{/gist_id}\",\n" +
                "      \"starred_url\": \"https://api.github.com/users/octocat/starred{/owner}{/repo}\",\n" +
                "      \"subscriptions_url\": \"https://api.github.com/users/octocat/subscriptions\",\n" +
                "      \"organizations_url\": \"https://api.github.com/users/octocat/orgs\",\n" +
                "      \"repos_url\": \"https://api.github.com/users/octocat/repos\",\n" +
                "      \"events_url\": \"https://api.github.com/users/octocat/events{/privacy}\",\n" +
                "      \"received_events_url\": \"https://api.github.com/users/octocat/received_events\",\n" +
                "      \"type\": \"User\",\n" +
                "      \"site_admin\": false\n" +
                "    },\n" +
                "    \"private\": false,\n" +
                "    \"html_url\": \"https://github.com/octocat/git-consortium\",\n" +
                "    \"description\": \"This repo is for demonstration purposes only.\",\n" +
                "    \"fork\": false,\n" +
                "    \"url\": \"https://api.github.com/repos/octocat/git-consortium\",\n" +
                "    \"forks_url\": \"https://api.github.com/repos/octocat/git-consortium/forks\",\n" +
                "    \"keys_url\": \"https://api.github.com/repos/octocat/git-consortium/keys{/key_id}\",\n" +
                "    \"collaborators_url\": \"https://api.github.com/repos/octocat/git-consortium/collaborators{/collaborator}\",\n" +
                "    \"teams_url\": \"https://api.github.com/repos/octocat/git-consortium/teams\",\n" +
                "    \"hooks_url\": \"https://api.github.com/repos/octocat/git-consortium/hooks\",\n" +
                "    \"issue_events_url\": \"https://api.github.com/repos/octocat/git-consortium/issues/events{/number}\",\n" +
                "    \"events_url\": \"https://api.github.com/repos/octocat/git-consortium/events\",\n" +
                "    \"assignees_url\": \"https://api.github.com/repos/octocat/git-consortium/assignees{/user}\",\n" +
                "    \"branches_url\": \"https://api.github.com/repos/octocat/git-consortium/branches{/branch}\",\n" +
                "    \"tags_url\": \"https://api.github.com/repos/octocat/git-consortium/tags\",\n" +
                "    \"blobs_url\": \"https://api.github.com/repos/octocat/git-consortium/git/blobs{/sha}\",\n" +
                "    \"git_tags_url\": \"https://api.github.com/repos/octocat/git-consortium/git/tags{/sha}\",\n" +
                "    \"git_refs_url\": \"https://api.github.com/repos/octocat/git-consortium/git/refs{/sha}\",\n" +
                "    \"trees_url\": \"https://api.github.com/repos/octocat/git-consortium/git/trees{/sha}\",\n" +
                "    \"statuses_url\": \"https://api.github.com/repos/octocat/git-consortium/statuses/{sha}\",\n" +
                "    \"languages_url\": \"https://api.github.com/repos/octocat/git-consortium/languages\",\n" +
                "    \"stargazers_url\": \"https://api.github.com/repos/octocat/git-consortium/stargazers\",\n" +
                "    \"contributors_url\": \"https://api.github.com/repos/octocat/git-consortium/contributors\",\n" +
                "    \"subscribers_url\": \"https://api.github.com/repos/octocat/git-consortium/subscribers\",\n" +
                "    \"subscription_url\": \"https://api.github.com/repos/octocat/git-consortium/subscription\",\n" +
                "    \"commits_url\": \"https://api.github.com/repos/octocat/git-consortium/commits{/sha}\",\n" +
                "    \"git_commits_url\": \"https://api.github.com/repos/octocat/git-consortium/git/commits{/sha}\",\n" +
                "    \"comments_url\": \"https://api.github.com/repos/octocat/git-consortium/comments{/number}\",\n" +
                "    \"issue_comment_url\": \"https://api.github.com/repos/octocat/git-consortium/issues/comments{/number}\",\n" +
                "    \"contents_url\": \"https://api.github.com/repos/octocat/git-consortium/contents/{+path}\",\n" +
                "    \"compare_url\": \"https://api.github.com/repos/octocat/git-consortium/compare/{base}...{head}\",\n" +
                "    \"merges_url\": \"https://api.github.com/repos/octocat/git-consortium/merges\",\n" +
                "    \"archive_url\": \"https://api.github.com/repos/octocat/git-consortium/{archive_format}{/ref}\",\n" +
                "    \"downloads_url\": \"https://api.github.com/repos/octocat/git-consortium/downloads\",\n" +
                "    \"issues_url\": \"https://api.github.com/repos/octocat/git-consortium/issues{/number}\",\n" +
                "    \"pulls_url\": \"https://api.github.com/repos/octocat/git-consortium/pulls{/number}\",\n" +
                "    \"milestones_url\": \"https://api.github.com/repos/octocat/git-consortium/milestones{/number}\",\n" +
                "    \"notifications_url\": \"https://api.github.com/repos/octocat/git-consortium/notifications{?since,all,participating}\",\n" +
                "    \"labels_url\": \"https://api.github.com/repos/octocat/git-consortium/labels{/name}\",\n" +
                "    \"releases_url\": \"https://api.github.com/repos/octocat/git-consortium/releases{/id}\",\n" +
                "    \"deployments_url\": \"https://api.github.com/repos/octocat/git-consortium/deployments\",\n" +
                "    \"created_at\": \"2014-03-28T17:55:38Z\",\n" +
                "    \"updated_at\": \"2016-04-26T12:19:44Z\",\n" +
                "    \"pushed_at\": \"2015-10-28T23:30:54Z\",\n" +
                "    \"git_url\": \"git://github.com/octocat/git-consortium.git\",\n" +
                "    \"ssh_url\": \"git@github.com:octocat/git-consortium.git\",\n" +
                "    \"clone_url\": \"https://github.com/octocat/git-consortium.git\",\n" +
                "    \"svn_url\": \"https://github.com/octocat/git-consortium\",\n" +
                "    \"homepage\": null,\n" +
                "    \"size\": 190,\n" +
                "    \"stargazers_count\": 7,\n" +
                "    \"watchers_count\": 7,\n" +
                "    \"language\": null,\n" +
                "    \"has_issues\": true,\n" +
                "    \"has_downloads\": true,\n" +
                "    \"has_wiki\": true,\n" +
                "    \"has_pages\": false,\n" +
                "    \"forks_count\": 15,\n" +
                "    \"mirror_url\": null,\n" +
                "    \"open_issues_count\": 3,\n" +
                "    \"forks\": 15,\n" +
                "    \"open_issues\": 3,\n" +
                "    \"watchers\": 7,\n" +
                "    \"default_branch\": \"master\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 20978623,\n" +
                "    \"name\": \"hello-worId\",\n" +
                "    \"full_name\": \"octocat/hello-worId\",\n" +
                "    \"owner\": {\n" +
                "      \"login\": \"octocat\",\n" +
                "      \"id\": 583231,\n" +
                "      \"avatar_url\": \"https://avatars.githubusercontent.com/u/583231?v=3\",\n" +
                "      \"gravatar_id\": \"\",\n" +
                "      \"url\": \"https://api.github.com/users/octocat\",\n" +
                "      \"html_url\": \"https://github.com/octocat\",\n" +
                "      \"followers_url\": \"https://api.github.com/users/octocat/followers\",\n" +
                "      \"following_url\": \"https://api.github.com/users/octocat/following{/other_user}\",\n" +
                "      \"gists_url\": \"https://api.github.com/users/octocat/gists{/gist_id}\",\n" +
                "      \"starred_url\": \"https://api.github.com/users/octocat/starred{/owner}{/repo}\",\n" +
                "      \"subscriptions_url\": \"https://api.github.com/users/octocat/subscriptions\",\n" +
                "      \"organizations_url\": \"https://api.github.com/users/octocat/orgs\",\n" +
                "      \"repos_url\": \"https://api.github.com/users/octocat/repos\",\n" +
                "      \"events_url\": \"https://api.github.com/users/octocat/events{/privacy}\",\n" +
                "      \"received_events_url\": \"https://api.github.com/users/octocat/received_events\",\n" +
                "      \"type\": \"User\",\n" +
                "      \"site_admin\": false\n" +
                "    },\n" +
                "    \"private\": false,\n" +
                "    \"html_url\": \"https://github.com/octocat/hello-worId\",\n" +
                "    \"description\": \"My first repository on GitHub.\",\n" +
                "    \"fork\": false,\n" +
                "    \"url\": \"https://api.github.com/repos/octocat/hello-worId\",\n" +
                "    \"forks_url\": \"https://api.github.com/repos/octocat/hello-worId/forks\",\n" +
                "    \"keys_url\": \"https://api.github.com/repos/octocat/hello-worId/keys{/key_id}\",\n" +
                "    \"collaborators_url\": \"https://api.github.com/repos/octocat/hello-worId/collaborators{/collaborator}\",\n" +
                "    \"teams_url\": \"https://api.github.com/repos/octocat/hello-worId/teams\",\n" +
                "    \"hooks_url\": \"https://api.github.com/repos/octocat/hello-worId/hooks\",\n" +
                "    \"issue_events_url\": \"https://api.github.com/repos/octocat/hello-worId/issues/events{/number}\",\n" +
                "    \"events_url\": \"https://api.github.com/repos/octocat/hello-worId/events\",\n" +
                "    \"assignees_url\": \"https://api.github.com/repos/octocat/hello-worId/assignees{/user}\",\n" +
                "    \"branches_url\": \"https://api.github.com/repos/octocat/hello-worId/branches{/branch}\",\n" +
                "    \"tags_url\": \"https://api.github.com/repos/octocat/hello-worId/tags\",\n" +
                "    \"blobs_url\": \"https://api.github.com/repos/octocat/hello-worId/git/blobs{/sha}\",\n" +
                "    \"git_tags_url\": \"https://api.github.com/repos/octocat/hello-worId/git/tags{/sha}\",\n" +
                "    \"git_refs_url\": \"https://api.github.com/repos/octocat/hello-worId/git/refs{/sha}\",\n" +
                "    \"trees_url\": \"https://api.github.com/repos/octocat/hello-worId/git/trees{/sha}\",\n" +
                "    \"statuses_url\": \"https://api.github.com/repos/octocat/hello-worId/statuses/{sha}\",\n" +
                "    \"languages_url\": \"https://api.github.com/repos/octocat/hello-worId/languages\",\n" +
                "    \"stargazers_url\": \"https://api.github.com/repos/octocat/hello-worId/stargazers\",\n" +
                "    \"contributors_url\": \"https://api.github.com/repos/octocat/hello-worId/contributors\",\n" +
                "    \"subscribers_url\": \"https://api.github.com/repos/octocat/hello-worId/subscribers\",\n" +
                "    \"subscription_url\": \"https://api.github.com/repos/octocat/hello-worId/subscription\",\n" +
                "    \"commits_url\": \"https://api.github.com/repos/octocat/hello-worId/commits{/sha}\",\n" +
                "    \"git_commits_url\": \"https://api.github.com/repos/octocat/hello-worId/git/commits{/sha}\",\n" +
                "    \"comments_url\": \"https://api.github.com/repos/octocat/hello-worId/comments{/number}\",\n" +
                "    \"issue_comment_url\": \"https://api.github.com/repos/octocat/hello-worId/issues/comments{/number}\",\n" +
                "    \"contents_url\": \"https://api.github.com/repos/octocat/hello-worId/contents/{+path}\",\n" +
                "    \"compare_url\": \"https://api.github.com/repos/octocat/hello-worId/compare/{base}...{head}\",\n" +
                "    \"merges_url\": \"https://api.github.com/repos/octocat/hello-worId/merges\",\n" +
                "    \"archive_url\": \"https://api.github.com/repos/octocat/hello-worId/{archive_format}{/ref}\",\n" +
                "    \"downloads_url\": \"https://api.github.com/repos/octocat/hello-worId/downloads\",\n" +
                "    \"issues_url\": \"https://api.github.com/repos/octocat/hello-worId/issues{/number}\",\n" +
                "    \"pulls_url\": \"https://api.github.com/repos/octocat/hello-worId/pulls{/number}\",\n" +
                "    \"milestones_url\": \"https://api.github.com/repos/octocat/hello-worId/milestones{/number}\",\n" +
                "    \"notifications_url\": \"https://api.github.com/repos/octocat/hello-worId/notifications{?since,all,participating}\",\n" +
                "    \"labels_url\": \"https://api.github.com/repos/octocat/hello-worId/labels{/name}\",\n" +
                "    \"releases_url\": \"https://api.github.com/repos/octocat/hello-worId/releases{/id}\",\n" +
                "    \"deployments_url\": \"https://api.github.com/repos/octocat/hello-worId/deployments\",\n" +
                "    \"created_at\": \"2014-06-18T21:26:19Z\",\n" +
                "    \"updated_at\": \"2016-03-03T21:35:27Z\",\n" +
                "    \"pushed_at\": \"2015-06-17T09:48:41Z\",\n" +
                "    \"git_url\": \"git://github.com/octocat/hello-worId.git\",\n" +
                "    \"ssh_url\": \"git@github.com:octocat/hello-worId.git\",\n" +
                "    \"clone_url\": \"https://github.com/octocat/hello-worId.git\",\n" +
                "    \"svn_url\": \"https://github.com/octocat/hello-worId\",\n" +
                "    \"homepage\": null,\n" +
                "    \"size\": 160,\n" +
                "    \"stargazers_count\": 13,\n" +
                "    \"watchers_count\": 13,\n" +
                "    \"language\": null,\n" +
                "    \"has_issues\": true,\n" +
                "    \"has_downloads\": true,\n" +
                "    \"has_wiki\": true,\n" +
                "    \"has_pages\": false,\n" +
                "    \"forks_count\": 27,\n" +
                "    \"mirror_url\": null,\n" +
                "    \"open_issues_count\": 1,\n" +
                "    \"forks\": 27,\n" +
                "    \"open_issues\": 1,\n" +
                "    \"watchers\": 13,\n" +
                "    \"default_branch\": \"master\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 1296269,\n" +
                "    \"name\": \"Hello-World\",\n" +
                "    \"full_name\": \"octocat/Hello-World\",\n" +
                "    \"owner\": {\n" +
                "      \"login\": \"octocat\",\n" +
                "      \"id\": 583231,\n" +
                "      \"avatar_url\": \"https://avatars.githubusercontent.com/u/583231?v=3\",\n" +
                "      \"gravatar_id\": \"\",\n" +
                "      \"url\": \"https://api.github.com/users/octocat\",\n" +
                "      \"html_url\": \"https://github.com/octocat\",\n" +
                "      \"followers_url\": \"https://api.github.com/users/octocat/followers\",\n" +
                "      \"following_url\": \"https://api.github.com/users/octocat/following{/other_user}\",\n" +
                "      \"gists_url\": \"https://api.github.com/users/octocat/gists{/gist_id}\",\n" +
                "      \"starred_url\": \"https://api.github.com/users/octocat/starred{/owner}{/repo}\",\n" +
                "      \"subscriptions_url\": \"https://api.github.com/users/octocat/subscriptions\",\n" +
                "      \"organizations_url\": \"https://api.github.com/users/octocat/orgs\",\n" +
                "      \"repos_url\": \"https://api.github.com/users/octocat/repos\",\n" +
                "      \"events_url\": \"https://api.github.com/users/octocat/events{/privacy}\",\n" +
                "      \"received_events_url\": \"https://api.github.com/users/octocat/received_events\",\n" +
                "      \"type\": \"User\",\n" +
                "      \"site_admin\": false\n" +
                "    },\n" +
                "    \"private\": false,\n" +
                "    \"html_url\": \"https://github.com/octocat/Hello-World\",\n" +
                "    \"description\": \"My first repository on GitHub!\",\n" +
                "    \"fork\": false,\n" +
                "    \"url\": \"https://api.github.com/repos/octocat/Hello-World\",\n" +
                "    \"forks_url\": \"https://api.github.com/repos/octocat/Hello-World/forks\",\n" +
                "    \"keys_url\": \"https://api.github.com/repos/octocat/Hello-World/keys{/key_id}\",\n" +
                "    \"collaborators_url\": \"https://api.github.com/repos/octocat/Hello-World/collaborators{/collaborator}\",\n" +
                "    \"teams_url\": \"https://api.github.com/repos/octocat/Hello-World/teams\",\n" +
                "    \"hooks_url\": \"https://api.github.com/repos/octocat/Hello-World/hooks\",\n" +
                "    \"issue_events_url\": \"https://api.github.com/repos/octocat/Hello-World/issues/events{/number}\",\n" +
                "    \"events_url\": \"https://api.github.com/repos/octocat/Hello-World/events\",\n" +
                "    \"assignees_url\": \"https://api.github.com/repos/octocat/Hello-World/assignees{/user}\",\n" +
                "    \"branches_url\": \"https://api.github.com/repos/octocat/Hello-World/branches{/branch}\",\n" +
                "    \"tags_url\": \"https://api.github.com/repos/octocat/Hello-World/tags\",\n" +
                "    \"blobs_url\": \"https://api.github.com/repos/octocat/Hello-World/git/blobs{/sha}\",\n" +
                "    \"git_tags_url\": \"https://api.github.com/repos/octocat/Hello-World/git/tags{/sha}\",\n" +
                "    \"git_refs_url\": \"https://api.github.com/repos/octocat/Hello-World/git/refs{/sha}\",\n" +
                "    \"trees_url\": \"https://api.github.com/repos/octocat/Hello-World/git/trees{/sha}\",\n" +
                "    \"statuses_url\": \"https://api.github.com/repos/octocat/Hello-World/statuses/{sha}\",\n" +
                "    \"languages_url\": \"https://api.github.com/repos/octocat/Hello-World/languages\",\n" +
                "    \"stargazers_url\": \"https://api.github.com/repos/octocat/Hello-World/stargazers\",\n" +
                "    \"contributors_url\": \"https://api.github.com/repos/octocat/Hello-World/contributors\",\n" +
                "    \"subscribers_url\": \"https://api.github.com/repos/octocat/Hello-World/subscribers\",\n" +
                "    \"subscription_url\": \"https://api.github.com/repos/octocat/Hello-World/subscription\",\n" +
                "    \"commits_url\": \"https://api.github.com/repos/octocat/Hello-World/commits{/sha}\",\n" +
                "    \"git_commits_url\": \"https://api.github.com/repos/octocat/Hello-World/git/commits{/sha}\",\n" +
                "    \"comments_url\": \"https://api.github.com/repos/octocat/Hello-World/comments{/number}\",\n" +
                "    \"issue_comment_url\": \"https://api.github.com/repos/octocat/Hello-World/issues/comments{/number}\",\n" +
                "    \"contents_url\": \"https://api.github.com/repos/octocat/Hello-World/contents/{+path}\",\n" +
                "    \"compare_url\": \"https://api.github.com/repos/octocat/Hello-World/compare/{base}...{head}\",\n" +
                "    \"merges_url\": \"https://api.github.com/repos/octocat/Hello-World/merges\",\n" +
                "    \"archive_url\": \"https://api.github.com/repos/octocat/Hello-World/{archive_format}{/ref}\",\n" +
                "    \"downloads_url\": \"https://api.github.com/repos/octocat/Hello-World/downloads\",\n" +
                "    \"issues_url\": \"https://api.github.com/repos/octocat/Hello-World/issues{/number}\",\n" +
                "    \"pulls_url\": \"https://api.github.com/repos/octocat/Hello-World/pulls{/number}\",\n" +
                "    \"milestones_url\": \"https://api.github.com/repos/octocat/Hello-World/milestones{/number}\",\n" +
                "    \"notifications_url\": \"https://api.github.com/repos/octocat/Hello-World/notifications{?since,all,participating}\",\n" +
                "    \"labels_url\": \"https://api.github.com/repos/octocat/Hello-World/labels{/name}\",\n" +
                "    \"releases_url\": \"https://api.github.com/repos/octocat/Hello-World/releases{/id}\",\n" +
                "    \"deployments_url\": \"https://api.github.com/repos/octocat/Hello-World/deployments\",\n" +
                "    \"created_at\": \"2011-01-26T19:01:12Z\",\n" +
                "    \"updated_at\": \"2016-06-09T09:55:46Z\",\n" +
                "    \"pushed_at\": \"2016-05-12T07:20:54Z\",\n" +
                "    \"git_url\": \"git://github.com/octocat/Hello-World.git\",\n" +
                "    \"ssh_url\": \"git@github.com:octocat/Hello-World.git\",\n" +
                "    \"clone_url\": \"https://github.com/octocat/Hello-World.git\",\n" +
                "    \"svn_url\": \"https://github.com/octocat/Hello-World\",\n" +
                "    \"homepage\": \"\",\n" +
                "    \"size\": 578,\n" +
                "    \"stargazers_count\": 1405,\n" +
                "    \"watchers_count\": 1405,\n" +
                "    \"language\": null,\n" +
                "    \"has_issues\": true,\n" +
                "    \"has_downloads\": true,\n" +
                "    \"has_wiki\": true,\n" +
                "    \"has_pages\": false,\n" +
                "    \"forks_count\": 1124,\n" +
                "    \"mirror_url\": null,\n" +
                "    \"open_issues_count\": 164,\n" +
                "    \"forks\": 1124,\n" +
                "    \"open_issues\": 164,\n" +
                "    \"watchers\": 1405,\n" +
                "    \"default_branch\": \"master\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 17881631,\n" +
                "    \"name\": \"octocat.github.io\",\n" +
                "    \"full_name\": \"octocat/octocat.github.io\",\n" +
                "    \"owner\": {\n" +
                "      \"login\": \"octocat\",\n" +
                "      \"id\": 583231,\n" +
                "      \"avatar_url\": \"https://avatars.githubusercontent.com/u/583231?v=3\",\n" +
                "      \"gravatar_id\": \"\",\n" +
                "      \"url\": \"https://api.github.com/users/octocat\",\n" +
                "      \"html_url\": \"https://github.com/octocat\",\n" +
                "      \"followers_url\": \"https://api.github.com/users/octocat/followers\",\n" +
                "      \"following_url\": \"https://api.github.com/users/octocat/following{/other_user}\",\n" +
                "      \"gists_url\": \"https://api.github.com/users/octocat/gists{/gist_id}\",\n" +
                "      \"starred_url\": \"https://api.github.com/users/octocat/starred{/owner}{/repo}\",\n" +
                "      \"subscriptions_url\": \"https://api.github.com/users/octocat/subscriptions\",\n" +
                "      \"organizations_url\": \"https://api.github.com/users/octocat/orgs\",\n" +
                "      \"repos_url\": \"https://api.github.com/users/octocat/repos\",\n" +
                "      \"events_url\": \"https://api.github.com/users/octocat/events{/privacy}\",\n" +
                "      \"received_events_url\": \"https://api.github.com/users/octocat/received_events\",\n" +
                "      \"type\": \"User\",\n" +
                "      \"site_admin\": false\n" +
                "    },\n" +
                "    \"private\": false,\n" +
                "    \"html_url\": \"https://github.com/octocat/octocat.github.io\",\n" +
                "    \"description\": \"\",\n" +
                "    \"fork\": false,\n" +
                "    \"url\": \"https://api.github.com/repos/octocat/octocat.github.io\",\n" +
                "    \"forks_url\": \"https://api.github.com/repos/octocat/octocat.github.io/forks\",\n" +
                "    \"keys_url\": \"https://api.github.com/repos/octocat/octocat.github.io/keys{/key_id}\",\n" +
                "    \"collaborators_url\": \"https://api.github.com/repos/octocat/octocat.github.io/collaborators{/collaborator}\",\n" +
                "    \"teams_url\": \"https://api.github.com/repos/octocat/octocat.github.io/teams\",\n" +
                "    \"hooks_url\": \"https://api.github.com/repos/octocat/octocat.github.io/hooks\",\n" +
                "    \"issue_events_url\": \"https://api.github.com/repos/octocat/octocat.github.io/issues/events{/number}\",\n" +
                "    \"events_url\": \"https://api.github.com/repos/octocat/octocat.github.io/events\",\n" +
                "    \"assignees_url\": \"https://api.github.com/repos/octocat/octocat.github.io/assignees{/user}\",\n" +
                "    \"branches_url\": \"https://api.github.com/repos/octocat/octocat.github.io/branches{/branch}\",\n" +
                "    \"tags_url\": \"https://api.github.com/repos/octocat/octocat.github.io/tags\",\n" +
                "    \"blobs_url\": \"https://api.github.com/repos/octocat/octocat.github.io/git/blobs{/sha}\",\n" +
                "    \"git_tags_url\": \"https://api.github.com/repos/octocat/octocat.github.io/git/tags{/sha}\",\n" +
                "    \"git_refs_url\": \"https://api.github.com/repos/octocat/octocat.github.io/git/refs{/sha}\",\n" +
                "    \"trees_url\": \"https://api.github.com/repos/octocat/octocat.github.io/git/trees{/sha}\",\n" +
                "    \"statuses_url\": \"https://api.github.com/repos/octocat/octocat.github.io/statuses/{sha}\",\n" +
                "    \"languages_url\": \"https://api.github.com/repos/octocat/octocat.github.io/languages\",\n" +
                "    \"stargazers_url\": \"https://api.github.com/repos/octocat/octocat.github.io/stargazers\",\n" +
                "    \"contributors_url\": \"https://api.github.com/repos/octocat/octocat.github.io/contributors\",\n" +
                "    \"subscribers_url\": \"https://api.github.com/repos/octocat/octocat.github.io/subscribers\",\n" +
                "    \"subscription_url\": \"https://api.github.com/repos/octocat/octocat.github.io/subscription\",\n" +
                "    \"commits_url\": \"https://api.github.com/repos/octocat/octocat.github.io/commits{/sha}\",\n" +
                "    \"git_commits_url\": \"https://api.github.com/repos/octocat/octocat.github.io/git/commits{/sha}\",\n" +
                "    \"comments_url\": \"https://api.github.com/repos/octocat/octocat.github.io/comments{/number}\",\n" +
                "    \"issue_comment_url\": \"https://api.github.com/repos/octocat/octocat.github.io/issues/comments{/number}\",\n" +
                "    \"contents_url\": \"https://api.github.com/repos/octocat/octocat.github.io/contents/{+path}\",\n" +
                "    \"compare_url\": \"https://api.github.com/repos/octocat/octocat.github.io/compare/{base}...{head}\",\n" +
                "    \"merges_url\": \"https://api.github.com/repos/octocat/octocat.github.io/merges\",\n" +
                "    \"archive_url\": \"https://api.github.com/repos/octocat/octocat.github.io/{archive_format}{/ref}\",\n" +
                "    \"downloads_url\": \"https://api.github.com/repos/octocat/octocat.github.io/downloads\",\n" +
                "    \"issues_url\": \"https://api.github.com/repos/octocat/octocat.github.io/issues{/number}\",\n" +
                "    \"pulls_url\": \"https://api.github.com/repos/octocat/octocat.github.io/pulls{/number}\",\n" +
                "    \"milestones_url\": \"https://api.github.com/repos/octocat/octocat.github.io/milestones{/number}\",\n" +
                "    \"notifications_url\": \"https://api.github.com/repos/octocat/octocat.github.io/notifications{?since,all,participating}\",\n" +
                "    \"labels_url\": \"https://api.github.com/repos/octocat/octocat.github.io/labels{/name}\",\n" +
                "    \"releases_url\": \"https://api.github.com/repos/octocat/octocat.github.io/releases{/id}\",\n" +
                "    \"deployments_url\": \"https://api.github.com/repos/octocat/octocat.github.io/deployments\",\n" +
                "    \"created_at\": \"2014-03-18T20:54:39Z\",\n" +
                "    \"updated_at\": \"2016-05-17T18:21:17Z\",\n" +
                "    \"pushed_at\": \"2015-11-14T22:22:37Z\",\n" +
                "    \"git_url\": \"git://github.com/octocat/octocat.github.io.git\",\n" +
                "    \"ssh_url\": \"git@github.com:octocat/octocat.github.io.git\",\n" +
                "    \"clone_url\": \"https://github.com/octocat/octocat.github.io.git\",\n" +
                "    \"svn_url\": \"https://github.com/octocat/octocat.github.io\",\n" +
                "    \"homepage\": null,\n" +
                "    \"size\": 335,\n" +
                "    \"stargazers_count\": 12,\n" +
                "    \"watchers_count\": 12,\n" +
                "    \"language\": \"CSS\",\n" +
                "    \"has_issues\": true,\n" +
                "    \"has_downloads\": true,\n" +
                "    \"has_wiki\": true,\n" +
                "    \"has_pages\": true,\n" +
                "    \"forks_count\": 27,\n" +
                "    \"mirror_url\": null,\n" +
                "    \"open_issues_count\": 6,\n" +
                "    \"forks\": 27,\n" +
                "    \"open_issues\": 6,\n" +
                "    \"watchers\": 12,\n" +
                "    \"default_branch\": \"master\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 1300192,\n" +
                "    \"name\": \"SpoonMocked-Knife\",\n" +
                "    \"full_name\": \"octocat/Spoon-Knife\",\n" +
                "    \"owner\": {\n" +
                "      \"login\": \"octocat\",\n" +
                "      \"id\": 583231,\n" +
                "      \"avatar_url\": \"https://avatars.githubusercontent.com/u/583231?v=3\",\n" +
                "      \"gravatar_id\": \"\",\n" +
                "      \"url\": \"https://api.github.com/users/octocat\",\n" +
                "      \"html_url\": \"https://github.com/octocat\",\n" +
                "      \"followers_url\": \"https://api.github.com/users/octocat/followers\",\n" +
                "      \"following_url\": \"https://api.github.com/users/octocat/following{/other_user}\",\n" +
                "      \"gists_url\": \"https://api.github.com/users/octocat/gists{/gist_id}\",\n" +
                "      \"starred_url\": \"https://api.github.com/users/octocat/starred{/owner}{/repo}\",\n" +
                "      \"subscriptions_url\": \"https://api.github.com/users/octocat/subscriptions\",\n" +
                "      \"organizations_url\": \"https://api.github.com/users/octocat/orgs\",\n" +
                "      \"repos_url\": \"https://api.github.com/users/octocat/repos\",\n" +
                "      \"events_url\": \"https://api.github.com/users/octocat/events{/privacy}\",\n" +
                "      \"received_events_url\": \"https://api.github.com/users/octocat/received_events\",\n" +
                "      \"type\": \"User\",\n" +
                "      \"site_admin\": false\n" +
                "    },\n" +
                "    \"private\": false,\n" +
                "    \"html_url\": \"https://github.com/octocat/Spoon-Knife\",\n" +
                "    \"description\": \"This repo is for demonstration purposes only.\",\n" +
                "    \"fork\": false,\n" +
                "    \"url\": \"https://api.github.com/repos/octocat/Spoon-Knife\",\n" +
                "    \"forks_url\": \"https://api.github.com/repos/octocat/Spoon-Knife/forks\",\n" +
                "    \"keys_url\": \"https://api.github.com/repos/octocat/Spoon-Knife/keys{/key_id}\",\n" +
                "    \"collaborators_url\": \"https://api.github.com/repos/octocat/Spoon-Knife/collaborators{/collaborator}\",\n" +
                "    \"teams_url\": \"https://api.github.com/repos/octocat/Spoon-Knife/teams\",\n" +
                "    \"hooks_url\": \"https://api.github.com/repos/octocat/Spoon-Knife/hooks\",\n" +
                "    \"issue_events_url\": \"https://api.github.com/repos/octocat/Spoon-Knife/issues/events{/number}\",\n" +
                "    \"events_url\": \"https://api.github.com/repos/octocat/Spoon-Knife/events\",\n" +
                "    \"assignees_url\": \"https://api.github.com/repos/octocat/Spoon-Knife/assignees{/user}\",\n" +
                "    \"branches_url\": \"https://api.github.com/repos/octocat/Spoon-Knife/branches{/branch}\",\n" +
                "    \"tags_url\": \"https://api.github.com/repos/octocat/Spoon-Knife/tags\",\n" +
                "    \"blobs_url\": \"https://api.github.com/repos/octocat/Spoon-Knife/git/blobs{/sha}\",\n" +
                "    \"git_tags_url\": \"https://api.github.com/repos/octocat/Spoon-Knife/git/tags{/sha}\",\n" +
                "    \"git_refs_url\": \"https://api.github.com/repos/octocat/Spoon-Knife/git/refs{/sha}\",\n" +
                "    \"trees_url\": \"https://api.github.com/repos/octocat/Spoon-Knife/git/trees{/sha}\",\n" +
                "    \"statuses_url\": \"https://api.github.com/repos/octocat/Spoon-Knife/statuses/{sha}\",\n" +
                "    \"languages_url\": \"https://api.github.com/repos/octocat/Spoon-Knife/languages\",\n" +
                "    \"stargazers_url\": \"https://api.github.com/repos/octocat/Spoon-Knife/stargazers\",\n" +
                "    \"contributors_url\": \"https://api.github.com/repos/octocat/Spoon-Knife/contributors\",\n" +
                "    \"subscribers_url\": \"https://api.github.com/repos/octocat/Spoon-Knife/subscribers\",\n" +
                "    \"subscription_url\": \"https://api.github.com/repos/octocat/Spoon-Knife/subscription\",\n" +
                "    \"commits_url\": \"https://api.github.com/repos/octocat/Spoon-Knife/commits{/sha}\",\n" +
                "    \"git_commits_url\": \"https://api.github.com/repos/octocat/Spoon-Knife/git/commits{/sha}\",\n" +
                "    \"comments_url\": \"https://api.github.com/repos/octocat/Spoon-Knife/comments{/number}\",\n" +
                "    \"issue_comment_url\": \"https://api.github.com/repos/octocat/Spoon-Knife/issues/comments{/number}\",\n" +
                "    \"contents_url\": \"https://api.github.com/repos/octocat/Spoon-Knife/contents/{+path}\",\n" +
                "    \"compare_url\": \"https://api.github.com/repos/octocat/Spoon-Knife/compare/{base}...{head}\",\n" +
                "    \"merges_url\": \"https://api.github.com/repos/octocat/Spoon-Knife/merges\",\n" +
                "    \"archive_url\": \"https://api.github.com/repos/octocat/Spoon-Knife/{archive_format}{/ref}\",\n" +
                "    \"downloads_url\": \"https://api.github.com/repos/octocat/Spoon-Knife/downloads\",\n" +
                "    \"issues_url\": \"https://api.github.com/repos/octocat/Spoon-Knife/issues{/number}\",\n" +
                "    \"pulls_url\": \"https://api.github.com/repos/octocat/Spoon-Knife/pulls{/number}\",\n" +
                "    \"milestones_url\": \"https://api.github.com/repos/octocat/Spoon-Knife/milestones{/number}\",\n" +
                "    \"notifications_url\": \"https://api.github.com/repos/octocat/Spoon-Knife/notifications{?since,all,participating}\",\n" +
                "    \"labels_url\": \"https://api.github.com/repos/octocat/Spoon-Knife/labels{/name}\",\n" +
                "    \"releases_url\": \"https://api.github.com/repos/octocat/Spoon-Knife/releases{/id}\",\n" +
                "    \"deployments_url\": \"https://api.github.com/repos/octocat/Spoon-Knife/deployments\",\n" +
                "    \"created_at\": \"2011-01-27T19:30:43Z\",\n" +
                "    \"updated_at\": \"2016-06-10T23:48:50Z\",\n" +
                "    \"pushed_at\": \"2016-06-11T23:42:27Z\",\n" +
                "    \"git_url\": \"git://github.com/octocat/Spoon-Knife.git\",\n" +
                "    \"ssh_url\": \"git@github.com:octocat/Spoon-Knife.git\",\n" +
                "    \"clone_url\": \"https://github.com/octocat/Spoon-Knife.git\",\n" +
                "    \"svn_url\": \"https://github.com/octocat/Spoon-Knife\",\n" +
                "    \"homepage\": \"\",\n" +
                "    \"size\": 50574,\n" +
                "    \"stargazers_count\": 9944,\n" +
                "    \"watchers_count\": 9944,\n" +
                "    \"language\": \"HTML\",\n" +
                "    \"has_issues\": true,\n" +
                "    \"has_downloads\": true,\n" +
                "    \"has_wiki\": true,\n" +
                "    \"has_pages\": false,\n" +
                "    \"forks_count\": 82883,\n" +
                "    \"mirror_url\": null,\n" +
                "    \"open_issues_count\": 8074,\n" +
                "    \"forks\": 82883,\n" +
                "    \"open_issues\": 8074,\n" +
                "    \"watchers\": 9944,\n" +
                "    \"default_branch\": \"master\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 56271164,\n" +
                "    \"name\": \"test-repo1\",\n" +
                "    \"full_name\": \"octocat/test-repo1\",\n" +
                "    \"owner\": {\n" +
                "      \"login\": \"octocat\",\n" +
                "      \"id\": 583231,\n" +
                "      \"avatar_url\": \"https://avatars.githubusercontent.com/u/583231?v=3\",\n" +
                "      \"gravatar_id\": \"\",\n" +
                "      \"url\": \"https://api.github.com/users/octocat\",\n" +
                "      \"html_url\": \"https://github.com/octocat\",\n" +
                "      \"followers_url\": \"https://api.github.com/users/octocat/followers\",\n" +
                "      \"following_url\": \"https://api.github.com/users/octocat/following{/other_user}\",\n" +
                "      \"gists_url\": \"https://api.github.com/users/octocat/gists{/gist_id}\",\n" +
                "      \"starred_url\": \"https://api.github.com/users/octocat/starred{/owner}{/repo}\",\n" +
                "      \"subscriptions_url\": \"https://api.github.com/users/octocat/subscriptions\",\n" +
                "      \"organizations_url\": \"https://api.github.com/users/octocat/orgs\",\n" +
                "      \"repos_url\": \"https://api.github.com/users/octocat/repos\",\n" +
                "      \"events_url\": \"https://api.github.com/users/octocat/events{/privacy}\",\n" +
                "      \"received_events_url\": \"https://api.github.com/users/octocat/received_events\",\n" +
                "      \"type\": \"User\",\n" +
                "      \"site_admin\": false\n" +
                "    },\n" +
                "    \"private\": false,\n" +
                "    \"html_url\": \"https://github.com/octocat/test-repo1\",\n" +
                "    \"description\": \"\",\n" +
                "    \"fork\": false,\n" +
                "    \"url\": \"https://api.github.com/repos/octocat/test-repo1\",\n" +
                "    \"forks_url\": \"https://api.github.com/repos/octocat/test-repo1/forks\",\n" +
                "    \"keys_url\": \"https://api.github.com/repos/octocat/test-repo1/keys{/key_id}\",\n" +
                "    \"collaborators_url\": \"https://api.github.com/repos/octocat/test-repo1/collaborators{/collaborator}\",\n" +
                "    \"teams_url\": \"https://api.github.com/repos/octocat/test-repo1/teams\",\n" +
                "    \"hooks_url\": \"https://api.github.com/repos/octocat/test-repo1/hooks\",\n" +
                "    \"issue_events_url\": \"https://api.github.com/repos/octocat/test-repo1/issues/events{/number}\",\n" +
                "    \"events_url\": \"https://api.github.com/repos/octocat/test-repo1/events\",\n" +
                "    \"assignees_url\": \"https://api.github.com/repos/octocat/test-repo1/assignees{/user}\",\n" +
                "    \"branches_url\": \"https://api.github.com/repos/octocat/test-repo1/branches{/branch}\",\n" +
                "    \"tags_url\": \"https://api.github.com/repos/octocat/test-repo1/tags\",\n" +
                "    \"blobs_url\": \"https://api.github.com/repos/octocat/test-repo1/git/blobs{/sha}\",\n" +
                "    \"git_tags_url\": \"https://api.github.com/repos/octocat/test-repo1/git/tags{/sha}\",\n" +
                "    \"git_refs_url\": \"https://api.github.com/repos/octocat/test-repo1/git/refs{/sha}\",\n" +
                "    \"trees_url\": \"https://api.github.com/repos/octocat/test-repo1/git/trees{/sha}\",\n" +
                "    \"statuses_url\": \"https://api.github.com/repos/octocat/test-repo1/statuses/{sha}\",\n" +
                "    \"languages_url\": \"https://api.github.com/repos/octocat/test-repo1/languages\",\n" +
                "    \"stargazers_url\": \"https://api.github.com/repos/octocat/test-repo1/stargazers\",\n" +
                "    \"contributors_url\": \"https://api.github.com/repos/octocat/test-repo1/contributors\",\n" +
                "    \"subscribers_url\": \"https://api.github.com/repos/octocat/test-repo1/subscribers\",\n" +
                "    \"subscription_url\": \"https://api.github.com/repos/octocat/test-repo1/subscription\",\n" +
                "    \"commits_url\": \"https://api.github.com/repos/octocat/test-repo1/commits{/sha}\",\n" +
                "    \"git_commits_url\": \"https://api.github.com/repos/octocat/test-repo1/git/commits{/sha}\",\n" +
                "    \"comments_url\": \"https://api.github.com/repos/octocat/test-repo1/comments{/number}\",\n" +
                "    \"issue_comment_url\": \"https://api.github.com/repos/octocat/test-repo1/issues/comments{/number}\",\n" +
                "    \"contents_url\": \"https://api.github.com/repos/octocat/test-repo1/contents/{+path}\",\n" +
                "    \"compare_url\": \"https://api.github.com/repos/octocat/test-repo1/compare/{base}...{head}\",\n" +
                "    \"merges_url\": \"https://api.github.com/repos/octocat/test-repo1/merges\",\n" +
                "    \"archive_url\": \"https://api.github.com/repos/octocat/test-repo1/{archive_format}{/ref}\",\n" +
                "    \"downloads_url\": \"https://api.github.com/repos/octocat/test-repo1/downloads\",\n" +
                "    \"issues_url\": \"https://api.github.com/repos/octocat/test-repo1/issues{/number}\",\n" +
                "    \"pulls_url\": \"https://api.github.com/repos/octocat/test-repo1/pulls{/number}\",\n" +
                "    \"milestones_url\": \"https://api.github.com/repos/octocat/test-repo1/milestones{/number}\",\n" +
                "    \"notifications_url\": \"https://api.github.com/repos/octocat/test-repo1/notifications{?since,all,participating}\",\n" +
                "    \"labels_url\": \"https://api.github.com/repos/octocat/test-repo1/labels{/name}\",\n" +
                "    \"releases_url\": \"https://api.github.com/repos/octocat/test-repo1/releases{/id}\",\n" +
                "    \"deployments_url\": \"https://api.github.com/repos/octocat/test-repo1/deployments\",\n" +
                "    \"created_at\": \"2016-04-14T21:29:25Z\",\n" +
                "    \"updated_at\": \"2016-04-05T05:29:09Z\",\n" +
                "    \"pushed_at\": \"2016-04-05T05:31:50Z\",\n" +
                "    \"git_url\": \"git://github.com/octocat/test-repo1.git\",\n" +
                "    \"ssh_url\": \"git@github.com:octocat/test-repo1.git\",\n" +
                "    \"clone_url\": \"https://github.com/octocat/test-repo1.git\",\n" +
                "    \"svn_url\": \"https://github.com/octocat/test-repo1\",\n" +
                "    \"homepage\": null,\n" +
                "    \"size\": 1,\n" +
                "    \"stargazers_count\": 0,\n" +
                "    \"watchers_count\": 0,\n" +
                "    \"language\": null,\n" +
                "    \"has_issues\": false,\n" +
                "    \"has_downloads\": true,\n" +
                "    \"has_wiki\": true,\n" +
                "    \"has_pages\": false,\n" +
                "    \"forks_count\": 1,\n" +
                "    \"mirror_url\": null,\n" +
                "    \"open_issues_count\": 0,\n" +
                "    \"forks\": 1,\n" +
                "    \"open_issues\": 0,\n" +
                "    \"watchers\": 0,\n" +
                "    \"default_branch\": \"gh-pages\"\n" +
                "  }\n" +
                "]";
    }





}