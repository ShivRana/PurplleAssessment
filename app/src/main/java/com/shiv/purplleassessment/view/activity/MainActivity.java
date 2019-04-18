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
import android.widget.TextView;
import android.widget.Toast;

import com.shiv.purplleassessment.model.Article;
import com.shiv.purplleassessment.utils.Constants;
import com.shiv.purplleassessment.utils.Utils;
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
    private TextView tvLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pullToRefresh = findViewById(R.id.pull_to_refresh);
        tvLoading = findViewById(R.id.tv_loading);
        tvLoading.setVisibility(View.VISIBLE);
        pullToRefresh.setVisibility(View.GONE);
        rvHeadline = findViewById(R.id.rcv);
        setupRecyclerView();
        initViewModel();
        pullToRefresh.setOnRefreshListener(() -> {
            initViewModel();
            pullToRefresh.setRefreshing(false);
        });

    }

    private void initViewModel() {
        if (Utils.isNetworkAvailable(this)) {
            final NewsViewModel viewModel = ViewModelProviders.of(MainActivity.this).get(NewsViewModel.class);
            observeViewModel(viewModel);
        } else {
            tvLoading.setText(getString(R.string.no_internet));
            Toast.makeText(this, getString(R.string.toast_please_turn_data_on), Toast.LENGTH_SHORT).show();
        }


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
                tvLoading.setVisibility(View.GONE);
                pullToRefresh.setVisibility(View.VISIBLE);
                List<Article> articles = newsResponse.getArticle();
                articleArrayList.addAll(articles);
                headLineAdapter.notifyDataSetChanged();
            } else tvLoading.setText(getString(R.string.oops));
        });
    }

    @Override
    public void onItemClick(Article article) {
        Intent intent = new Intent(this, NewsDetailActivity.class);
        intent.putExtra(Constants.ARTICLE_DATA, article);
        startActivity(intent);
    }
}
