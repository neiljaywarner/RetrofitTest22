package org.disciplestoday.retrofittest22;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import org.disciplestoday.retrofittest22.dummy.DummyContent;

import java.util.List;

/**
 * An activity representing a list of Articles. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ArticleDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ArticleListActivity extends AppCompatActivity  implements FeedLoaderAsyncTask.OnTaskCompleted {

    private static final String TAG = ArticleListActivity.class.getSimpleName();
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private List<Item> mItems;
    private FeedLoaderAsyncTask asyncTask;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("NJW", "oncreate");

        setContentView(R.layout.activity_article_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        Log.i("NJW", "Please execute task");
        asyncTask = new FeedLoaderAsyncTask(ArticleListActivity.this);
        asyncTask.execute();

        // RUN ASYNC TASK.


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

         recyclerView = (RecyclerView) findViewById(R.id.article_list);
        assert recyclerView != null;

        if (findViewById(R.id.article_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

    @Override
    public void onTaskCompleted() {
        mItems = asyncTask.getItems();
        Log.i(TAG, "NJW: + lastItem" +  mItems.get(mItems.size()-2).getTitle());
        setupRecyclerView(recyclerView);

    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(mItems));
    }



    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<Item> mValues;

        public SimpleItemRecyclerViewAdapter(List<Item> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.article_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            Item item = mItems.get(position);
            String name = item.getTitle();
            holder.mContentView.setText(name);
           // holder.mIdView.setText(mValues.get(position).id);
           // holder.mContentView.setText(mValues.get(position).content);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "IN ONCLICK LISTENER", Toast.LENGTH_LONG).show();
                    /*
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(ArticleDetailFragment.ARG_ITEM_ID, holder.mItem.id);
                        ArticleDetailFragment fragment = new ArticleDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.article_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, ArticleDetailActivity.class);
                        intent.putExtra(ArticleDetailFragment.ARG_ITEM_ID, holder.mItem.id);

                        context.startActivity(intent);
                    }
                    */
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public Item mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }




}
