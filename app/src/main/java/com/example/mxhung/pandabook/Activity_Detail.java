package com.example.mxhung.pandabook;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ZoomControls;

import com.example.mxhung.pandabook.model.Chapter;


/**
 * Created by MXHung on 2/20/2017.
 */

public class Activity_Detail extends AppCompatActivity {
    TextView tvChaper;
    TextView tvDetail;
    private static int id;
    private String name;
    DBManager db;
    Chapter chapter;
    ZoomControls zoomControls;
    private static int size = 18;
    private ImageView imNext;
    private ImageView imBack;
    private ScrollView scrollView;
    private ViewGroup rlView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvChaper = (TextView) findViewById(R.id.tvChaper);
        tvDetail = (TextView) findViewById(R.id.tvDetail);
        zoomControls = (ZoomControls) findViewById(R.id.zoom);
        scrollView = (ScrollView) findViewById(R.id.scroll);

        rlView = (ViewGroup) findViewById(R.id.rlView);
        imNext = (ImageView) findViewById(R.id.imNext);
        imBack = (ImageView) findViewById(R.id.imBack);
        db = new DBManager(this);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        id = getIntent().getIntExtra("id", 1);
        name = getIntent().getStringExtra("name");
        getSupportActionBar().setTitle(name);

        chapter = db.getChapterDetail(id);

        scrollView.post(new Runnable() {
            @Override
            public void run() {
            scrollView.scrollTo(0, 2041);
            }
        });
        tvDetail.setText(Html.fromHtml(chapter.getDetail()));
        tvDetail.scrollTo(0, getApplicationContext().getSharedPreferences("myData", 0).getInt("lastpos", 0));
        zoomControls.setOnZoomInClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (size < 36) {
                    size += 2;
//                        wv.getSettings().setDefaultFontSize(this.mfontsize);
                    tvDetail.setTextSize(size);
                    return;
                }
            }
        });

        zoomControls.setOnZoomOutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (wv != null) {
//                    size = tvDetail.getSettings().getDefaultFontSize();
//                    this.btnZoomOut.setEnabled(true);
                    if (size > 11) {
                        size -= 2;
//                        wv.getSettings().setDefaultFontSize(this.mfontsize);
                        tvDetail.setTextSize(size);
                        return;
                    }
                    tvDetail.setTextSize(TypedValue.COMPLEX_UNIT_SP, size + 2);
                }
            }

            );

            imNext.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View v){
                chapter = db.getChapterDetail(id++);
                getSupportActionBar().setTitle(chapter.getName());

                tvDetail.setText(Html.fromHtml(chapter.getDetail()));
            }
            }

            );

            imBack.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View v){
//                chapter = db.getChapterDetail(id--);
//                getSupportActionBar().setTitle(chapter.getName());
//
//                tvDetail.setText(Html.fromHtml(chapter.getDetail()));
//                    float position = tvDetail.get();
                    int position = scrollView.getScrollX();
                    int position1 = scrollView.getScrollY();
//                    int position = tvDetail
                    Log.d("pos", String.valueOf(position) + " " + position1);
            }
            }

            );


        }


    @Override
    public void onBackPressed() {
        getApplicationContext().getSharedPreferences("myData", 0).edit().putInt("lastpos", tvDetail.getScrollY());

        super.onBackPressed();
    }

    @Override
        public boolean onOptionsItemSelected (MenuItem item){
            int id = item.getItemId();
            if (id == android.R.id.home) {
                finish();
            }
            return true;
        }
    }
