package com.example.androidapp.ui.tasks;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TasksFragment extends Fragment {

    private TasksViewModel tasksViewModel;

    private EditText title;
    private DatePicker date;
    private TimePicker time;
    private CheckBox lightCheck;
    private CheckBox ventCheck;
    private FloatingActionButton fabCreateTask;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        tasksViewModel =
                new ViewModelProvider(this).get(TasksViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tasks, container, false);


        fabCreateTask = root.findViewById(R.id.fab_create_task);


        //show alert dialog
        fabCreateTask.setOnClickListener(v -> {
            ViewGroup viewGroup = root.findViewById(android.R.id.content);
            View view = LayoutInflater.from(root.getContext()).inflate(R.layout.alert_add_task, viewGroup, false);
            AlertDialog.Builder builder = new AlertDialog.Builder(root.getContext());
            builder.setView(view);

            EditText editTextTaskName = view.findViewById(R.id.edit_text_task_name);

            builder.setTitle("Add task");
            builder.setPositiveButton("Add",
                    (dialog, which) -> {
                        //Do nothing here because we override this button later to change the close behaviour.
                    });

            builder.setNegativeButton("Cancel", (dialog, id) -> {
            });

            builder.setNeutralButton("Delete", (dialog, id) -> {
            });

            editTextTaskName.requestFocus();
            AlertDialog alertDialog = builder.create();
            alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            alertDialog.show();
        });


        return root;
    }

}