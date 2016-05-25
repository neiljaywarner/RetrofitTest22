package org.disciplestoday.retrofittest22;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by neil on 5/25/16.
 */

public class FeedLoaderAsyncTask extends AsyncTask<Void,Void, Feed> {


    //public static final String BASE_URL = "https://api.github.com";
    public static final String BASE_URL = "http://www.disciplestoday.org";


    public interface OnTaskCompleted{
        void onTaskCompleted();
    }

    private OnTaskCompleted listener;

    private List<Item> mRepos;


    public FeedLoaderAsyncTask(OnTaskCompleted listener){
        super();
        Log.e("NJW", "in constructor");
        this.listener = listener;
    }

    @Override
    protected Feed doInBackground(Void... params) {
        Log.i("NJW", "in doonbackgrond");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DTService service = retrofit.create(DTService.class);
        Call<Feed> call = service.listHighlights();  //Featured News=353?
        try {
            Response<Feed> feedResponse = call.execute();
            Log.i("NJW", "code=" + feedResponse.code());
            Feed feed = feedResponse.body();
            return feed;
        } catch (IOException e) {
            Log.e("NJW", e.getMessage());
            e.printStackTrace();
            return null;

        }
    }

    protected void onPostExecute(Feed feed   ) {
        List<Item> items =  feed.getItems();
        Log.i("NJW", "onPOstExecute: repoCount=" + items.size());
        Item repo = items.get(0);
        Log.i("NJW", "repoItem1 name" + repo.getTitle());
        //Toast.makeText(this.g)
        mRepos = items;
        listener.onTaskCompleted();
    }

    public List<Item> getItems() {
        return mRepos;
    }
}
