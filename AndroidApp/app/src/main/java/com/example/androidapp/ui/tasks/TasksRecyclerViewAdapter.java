package com.example.androidapp.ui.tasks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androidapp.R;

import java.util.List;

public class TasksRecyclerViewAdapter extends RecyclerView.Adapter<TasksRecyclerViewAdapter.ViewHolder>{

    private List<Task> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public TasksRecyclerViewAdapter(Context context, List<Task> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycler_view_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Task task = mData.get(position);
        holder.title.setText(task.getTitle());
        holder.date.setText((CharSequence) task.getDate());
        holder.time.setText((CharSequence) task.getTime());
        holder.light.setText(task.getLight());
        holder.vent.setText(task.getVent());

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }




    // stores and recycles views
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        TextView date;
        TextView time;
        TextView light;
        TextView vent;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textViewTask);
            date = itemView.findViewById(R.id.textViewDate);
            time = itemView.findViewById(R.id.textViewTime);
            light = itemView.findViewById(R.id.textViewLight);
            vent = itemView.findViewById(R.id.textViewVent);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public Task getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(TasksFragment itemClickListener) {
        this.mClickListener = (ItemClickListener) itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
