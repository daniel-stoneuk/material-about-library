package com.danielstone.materialaboutlibrary;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.danielstone.materialaboutlibrary.adapters.MaterialAboutListAdapter;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;
import com.danielstone.materialaboutlibrary.util.DefaultViewTypeManager;
import com.danielstone.materialaboutlibrary.util.ViewTypeManager;

public abstract class MaterialAboutFragment extends Fragment {

    private MaterialAboutList list = new MaterialAboutList.Builder().build();
    private RecyclerView recyclerView;
    private MaterialAboutListAdapter adapter;

    public static MaterialAboutFragment newInstance(MaterialAboutFragment fragment) {
        return fragment;
    }

    protected abstract MaterialAboutList getMaterialAboutList(Context activityContext);

    protected int getTheme() {
        return R.style.Theme_Mal_Light;
    }

    protected boolean shouldAnimate() {
        return true;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int style = getTheme();

        // create ContextThemeWrapper from the original Activity Context with the custom theme
        final Context contextThemeWrapper = new android.view.ContextThemeWrapper(getActivity(), style);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        View rootView = localInflater.inflate(R.layout.mal_material_about_fragment, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.mal_recyclerview);
        adapter = new MaterialAboutListAdapter(getViewTypeManager());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        RecyclerView.ItemAnimator animator = recyclerView.getItemAnimator();
        if (animator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) animator).setSupportsChangeAnimations(false);
        }

        recyclerView.setAlpha(0f);
        recyclerView.setTranslationY(20);

        ListTask task = new ListTask(getActivity());
        task.execute();

        return rootView;
    }

    protected ViewTypeManager getViewTypeManager() {
        return new DefaultViewTypeManager();
    }

    protected MaterialAboutList getList() {
        return list;
    }

    protected void setMaterialAboutList(MaterialAboutList materialAboutList) {
        list = materialAboutList;
        adapter.setData(list.getCards());
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    protected void refreshMaterialAboutList() {
        setMaterialAboutList(list);
    }

    private class ListTask extends AsyncTask<String, String, String> {

        Context fragmentContext;

        public ListTask(Context activityContext) {
            this.fragmentContext = activityContext;
        }

        @Override
        protected String doInBackground(String... params) {
            list = getMaterialAboutList(fragmentContext);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            adapter.setData(list.getCards());

            if (shouldAnimate()) {
                recyclerView.animate()
                        .alpha(1f)
                        .translationY(0f)
                        .setDuration(600)
                        .setInterpolator(new FastOutSlowInInterpolator())
                        .start();
            } else {
                recyclerView.setAlpha(1f);
                recyclerView.setTranslationY(0f);
            }

            super.onPostExecute(s);
            fragmentContext = null;
        }
    }
}
