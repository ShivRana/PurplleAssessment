package com.shiv.purplleassessment.view.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.shiv.purplleassessment.BR;
import com.shiv.purplleassessment.R;
import com.shiv.purplleassessment.model.Article;
import com.shiv.purplleassessment.utils.Constants;

public class NewsDetailActivity extends AppCompatActivity {
    private Article article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  setContentView(R.layout.activity_main);
        ViewDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_news_details);
        findViewById(R.id.iv_back).setVisibility(View.VISIBLE);
        findViewById(R.id.iv_back).setOnClickListener(view -> onBackPressed());
        if (getIntent().hasExtra(Constants.ARTICLE_DATA)) {
            article = getIntent().getParcelableExtra(Constants.ARTICLE_DATA);
        }
        binding.setVariable(BR.article, article);
    }

}
