package com.example.mxhung.pandabook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mxhung.pandabook.R;
import com.example.mxhung.pandabook.model.Book;

import java.util.ArrayList;

/**
 * Created by MXHung on 2/20/2017.
 */

public class BookAdapter  extends RecyclerView.Adapter<BookAdapter.BookViewHolder>{
    private ArrayList<Book> list;
    private Context context;

    public BookAdapter(ArrayList<Book> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_menu, parent, false);

        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        Book book = list.get(position);
        holder.tvNameBook.setText(book.getName());
    }

    @Override
    public int getItemCount() {
        return (list != null ? list.size() : 0);
    }

    public class BookViewHolder extends RecyclerView.ViewHolder{
        TextView tvNameBook;
        public BookViewHolder(View itemView) {
            super(itemView);
            tvNameBook = (TextView) itemView.findViewById(R.id.tvNameBook);
        }
    }
}
