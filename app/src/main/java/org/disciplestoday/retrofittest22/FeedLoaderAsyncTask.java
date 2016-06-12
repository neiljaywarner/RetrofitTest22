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

public class FeedLoaderAsyncTask extends AsyncTask<Void,Void, List<Repo>> {


    public interface OnTaskCompleted{
        void onTaskCompleted();
    }

    private OnTaskCompleted listener;

    private List<Repo> mRepos;


    public FeedLoaderAsyncTask(OnTaskCompleted listener){
        super();
        Log.e("NJW", "in constructor");
        this.listener = listener;
    }

    @Override
    protected List<Repo> doInBackground(Void... params) {
        Log.i("NJW", "in doonbackgrond");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.serverBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);
        Call<List<Repo>> call = service.listRepos("octocat");
        try {
            Response<List<Repo>> repos = call.execute();
            Log.i("NJW", "code=" + repos.code());
            List<Repo> reposList= repos.body();
            return reposList;
        } catch (IOException e) {
            Log.e("NJW", e.getMessage());
            e.printStackTrace();
            return null;

        }
    }

    protected void onPostExecute(List<Repo> repos   ) {
        Log.i("NJW", "onPOstExecute: repoCount=" + repos.size());
        Repo repo = repos.get(0);
        Log.i("NJW", "repoItem1 name" + repo.getName());
        //Toast.makeText(this.g)
        mRepos = repos;
        listener.onTaskCompleted();
    }

    public List<Repo> getRepos() {
        return mRepos;
    }
}
