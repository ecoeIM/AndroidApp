package com.example.androidapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidapp.R;
import com.example.androidapp.data.model.Task;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {

    ArrayList<Task> tasks;
    OnListDeleteTaskClickListener listener;
    private final static int FADE_DURATION = 250;

    public TaskListAdapter(ArrayList<Task> tasks, OnListDeleteTaskClickListener listener) {
        this.tasks = tasks;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TaskListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.task_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskListAdapter.ViewHolder holder, int position) {
        holder.textViewTaskItemTitle.setText(tasks.get(position).name);
        String desc = getDescription(position);
        if (StringUtils.isEmpty(desc)) {
            holder.textViewTaskItemDescription.setVisibility(View.GONE);
        } else {
            holder.textViewTaskItemDescription.setText(desc);
            holder.textViewTaskItemDescription.setVisibility(View.VISIBLE);
        }
        setFadeAnimation(holder.itemView);
    }

    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTaskItemTitle;
        private TextView textViewTaskItemDescription;
        private ImageButton imageButtonSetDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTaskItemTitle = itemView.findViewById(R.id.text_view_task_item_title);
            textViewTaskItemDescription = itemView.findViewById(R.id.text_view_task_item_description);
            imageButtonSetDelete = itemView.findViewById(R.id.image_button_set_delete);
            imageButtonSetDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onDeleteClick(getAdapterPosition());
                }
            });
        }
    }

    public interface OnListDeleteTaskClickListener {
        void onDeleteClick(int index);
    }

    private String getDescription(int i) {
        Task item = tasks.get(i);

        boolean toggleLight = item.toggleLight; //1
        boolean toggleVent = item.toggleVent; //2
        System.out.println(i);
        System.out.println(item.dateTime);
        LocalDateTime dateTime = LocalDateTime.parse(item.dateTime); //3

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = null;

        String description = "";
        if (dateTime != null) {
            formattedDateTime = dateTime.format(formatter);
        }

        if (!toggleLight && !toggleVent && dateTime == null) { //000
            description = "";
        } else if (!toggleLight && !toggleVent && dateTime != null) { //001
            description = "Scheduled for: " + formattedDateTime;
        } else if (!toggleLight && toggleVent && dateTime == null) { //010
            description = "Actions: VENT";
        } else if (!toggleLight && toggleVent && dateTime != null) { //011
            description = "Actions: VENT" + "   Scheduled for: " + formattedDateTime;
        } else if (toggleLight && !toggleVent && dateTime == null) { //100
            description = "Actions: LIGHT";
        } else if (toggleLight && toggleVent && dateTime == null) { //110
            description = "Actions: LIGHT, VENT";
        } else if (toggleLight && !toggleVent && dateTime != null) { //101
            description = "Actions: LIGHT" + "   Scheduled for: " + formattedDateTime;
        } else if (toggleLight && toggleVent && dateTime != null) { //111
            description = "Actions: LIGHT, VENT" + "   Scheduled for: " + formattedDateTime;
        }
        return description;
    }
}
