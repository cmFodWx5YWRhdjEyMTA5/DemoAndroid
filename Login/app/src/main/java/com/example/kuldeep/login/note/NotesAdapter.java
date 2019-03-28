package com.example.kuldeep.login.note;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.kuldeep.login.R;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.BeanHolder> {

    private final List<Note> list;
    private final LayoutInflater layoutInflater;
    private final OnNoteItemClick onNoteItemClick;

    NotesAdapter(List<Note> list, Context context) {
        layoutInflater = LayoutInflater.from(context);
        this.list = list;
        this.onNoteItemClick = (OnNoteItemClick) context;
    }


    @NonNull
    @Override
    public BeanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.note_list_item,parent,false);
        return new BeanHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BeanHolder holder, int position) {
        Log.e("bind", "onBindViewHolder: "+ list.get(position));
        holder.textViewTitle.setText(list.get(position).getTitle());
        holder.textViewContent.setText(list.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BeanHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView textViewContent;
        final TextView textViewTitle;
        BeanHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textViewContent = itemView.findViewById(R.id.item_text);
            textViewTitle = itemView.findViewById(R.id.tv_title);
        }

        @Override
        public void onClick(View view) {
            onNoteItemClick.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteItemClick{
        void onNoteClick(int pos);
    }
}