package com.shiv.purplleassessment.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.shiv.purplleassessment.model.Article;
import com.shiv.purplleassessment.utils.Constants;
import com.shiv.purplleassessment.view.adapter.HeadLineAdapter;
import com.shiv.purplleassessment.viewmodel.NewsViewModel;
import com.shiv.purplleassessment.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements HeadLineAdapter.IClick {
    ArrayList<Article> articleArrayList = new ArrayList<>();
    HeadLineAdapter headLineAdapter;
    RecyclerView rvHeadline;
    private SwipeRefreshLayout pullToRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvHeadline = findViewById(R.id.rcv);
        setupRecyclerView();
        initViewModel();
        pullToRefresh = findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(() -> {
            initViewModel();
            pullToRefresh.setRefreshing(false);
        });

    }

    private void initViewModel() {
        final NewsViewModel viewModel = ViewModelProviders.of(MainActivity.this).get(NewsViewModel.class);
        observeViewModel(viewModel);
    }

    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        if (headLineAdapter == null) {
            headLineAdapter = new HeadLineAdapter(articleArrayList, this, this);
            rvHeadline.setLayoutManager(layoutManager);
            rvHeadline.setAdapter(headLineAdapter);
            rvHeadline.setItemAnimator(new DefaultItemAnimator());
            rvHeadline.setNestedScrollingEnabled(true);
        } else {
            headLineAdapter.notifyDataSetChanged();
        }
    }

    private void observeViewModel(NewsViewModel viewModel) {

        viewModel.getNewsResponseObservable().observe(this, newsResponse -> {
            if (newsResponse != null) {
                List<Article> articles = newsResponse.getArticle();
                articleArrayList.addAll(articles);
                headLineAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onItemClick(Article article) {
        Intent intent = new Intent(this, NewsDetailActivity.class);
        intent.putExtra(Constants.ARTICLE_DATA, article);
        startActivity(intent);
    }
}
