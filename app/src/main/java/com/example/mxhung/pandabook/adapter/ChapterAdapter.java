package com.example.mxhung.pandabook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mxhung.pandabook.R;
import com.example.mxhung.pandabook.model.Book;
import com.example.mxhung.pandabook.model.Chapter;

import java.util.ArrayList;

/**
 * Created by MXHung on 2/20/2017.
 */

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder>{
    private ArrayList<Chapter> list;
    private Context context;

    public ChapterAdapter(ArrayList<Chapter> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ChapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_chapter, parent, false);

        return new ChapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChapterViewHolder holder, int position) {
        Chapter chapter = list.get(position);
        holder.tvNameChapter.setText(chapter.getName());
    }

    @Override
    public int getItemCount() {
        return (list != null ? list.size() : 0);
    }

    public class ChapterViewHolder extends RecyclerView.ViewHolder{
        TextView tvNameChapter;

        public ChapterViewHolder(View itemView) {
            super(itemView);
            tvNameChapter = (TextView) itemView.findViewById(R.id.tvNameBook);
        }
    }
}
