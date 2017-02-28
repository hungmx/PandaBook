package com.example.mxhung.pandabook;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.mxhung.pandabook.adapter.BookAdapter;
import com.example.mxhung.pandabook.adapter.ChapterAdapter;
import com.example.mxhung.pandabook.model.Book;
import com.example.mxhung.pandabook.model.Chapter;

import java.util.ArrayList;

/**
 * Created by MXHung on 2/20/2017.
 */

public class Activity_Chapter extends AppCompatActivity {
    TextView tvPanda;
    RecyclerView rvBook;
    DBManager db;
    ArrayList<Chapter> listChapter;
    ChapterAdapter adapter;
    private  int id_book;
    private String name_book;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        tvPanda = (TextView) findViewById(R.id.tvPanda);
        tvPanda.setVisibility(View.GONE);
        rvBook = (RecyclerView) findViewById(R.id.rvBook);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DBManager(this);
        db.openDataBase();
        id_book = getIntent().getIntExtra("id_book", 1);
        name_book = getIntent().getStringExtra("name_book");
        getSupportActionBar().setTitle(name_book);

        initViews();
        listChapter = db.getChapterId(id_book);
        adapter = new ChapterAdapter(listChapter, getApplicationContext());
        rvBook.setAdapter(adapter);

        rvBook.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), rvBook, new RecyclerTouchListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent iDetail = new Intent(Activity_Chapter.this, Activity_Detail.class);
                iDetail.putExtra("id", listChapter.get(position).getId());
                iDetail.putExtra("name", listChapter.get(position).getName());
                startActivity(iDetail);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return true;
    }
    private void initViews() {
        rvBook.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvBook.setLayoutManager(manager);
    }
}
