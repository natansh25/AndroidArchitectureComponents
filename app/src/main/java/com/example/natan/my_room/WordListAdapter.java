package com.example.natan.my_room;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.natan.my_room.Database.Word;

import java.util.List;

/**
 * Created by natan on 3/9/2018.
 */

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.MyViewHolder> {

    private LayoutInflater mInflater;
    private List<Word> mWords; // Cached copy of words

    WordListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.custom_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Word current = mWords.get(position);
        holder.wordItemView.setText(current.getWord());


    }

    void setWords(List<Word> words) {
        mWords = words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView wordItemView;


        public MyViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.txt_custom);
        }
    }
}
