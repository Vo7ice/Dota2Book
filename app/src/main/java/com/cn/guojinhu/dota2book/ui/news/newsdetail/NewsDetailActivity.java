package com.cn.guojinhu.dota2book.ui.news.newsdetail;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cn.guojinhu.dota2book.R;
import com.cn.guojinhu.dota2book.base.BaseActivity;

import static com.google.common.base.Preconditions.checkNotNull;

public class NewsDetailActivity extends BaseActivity implements NewsDetailContact.View {

    private NewsDetailPresenter mPresenter;
    private String docid;

    private static final String KEY_DETAIL = "key_detail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mPresenter = new NewsDetailPresenter(this);
        mPresenter.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        docid = getIntent().getStringExtra(KEY_DETAIL);
        mPresenter.loadNews(docid);
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void setPresenter(NewsDetailContact.Presenter p) {
        mPresenter = (NewsDetailPresenter) checkNotNull(p);
    }
}
