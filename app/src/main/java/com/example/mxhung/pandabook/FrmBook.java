package com.example.mxhung.pandabook;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mxhung.pandabook.adapter.BookAdapter;
import com.example.mxhung.pandabook.model.Book;

import java.util.ArrayList;

/**
 * Created by MXHung on 2/20/2017.
 */

public class FrmBook extends Fragment {
    RecyclerView rvBook;
    DBManager db;
    ArrayList<Book> listBook;
    BookAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_book, container, false);
        rvBook = (RecyclerView) view.findViewById(R.id.rvBook);
        db = new DBManager(getActivity());
        db.openDataBase();
        initViews();
        listBook = db.getBook();
        adapter = new BookAdapter(listBook, getContext());
        rvBook.setAdapter(adapter);

        rvBook.addOnItemTouchListener(new RecyclerTouchListener(getContext(), rvBook, new RecyclerTouchListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent iChapter = new Intent(getActivity(), Activity_Chapter.class);
                iChapter.putExtra("id_book", listBook.get(position).getId_book());
                iChapter.putExtra("name_book", listBook.get(position).getName());
                startActivity(iChapter);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
        return view;
    }


    private void initViews() {
        rvBook.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvBook.setLayoutManager(manager);
    }
}
