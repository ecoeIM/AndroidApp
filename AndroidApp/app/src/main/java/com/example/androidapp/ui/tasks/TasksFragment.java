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
import android.widget.ImageButton;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.apache.commons.lang3.StringUtils;

public class TasksFragment extends Fragment {

    private TasksViewModel tasksViewModel;
    private FloatingActionButton fabCreateTask;
    private ImageButton imageButtonTasksHelp;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        tasksViewModel =
                new ViewModelProvider(this).get(TasksViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tasks, container, false);
        fabCreateTask = root.findViewById(R.id.fab_create_task);
        imageButtonTasksHelp = root.findViewById(R.id.image_button_tasks_help);

        //help button
        imageButtonTasksHelp.setOnClickListener(v -> {
            androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(getActivity());
            builder.setMessage("Here you can manage the tasks for the terrarium. Those can be tasks triggering actions in your terrarium or simple reminder tasks.");
            builder.setPositiveButton("Close", (dialog, id) -> {
                //close
            });
            androidx.appcompat.app.AlertDialog dialog = builder.create();
            dialog.show();
        });

        //add task
        fabCreateTask.setOnClickListener(v -> {
            ViewGroup viewGroup = root.findViewById(android.R.id.content);
            View view = LayoutInflater.from(root.getContext()).inflate(R.layout.alert_add_task, viewGroup, false);
            AlertDialog.Builder builder = new AlertDialog.Builder(root.getContext());
            builder.setView(view);

            EditText editTextTaskName = view.findViewById(R.id.edit_text_task_name);
            CheckBox checkBoxToggleLight = view.findViewById(R.id.check_box_toggle_light);
            CheckBox checkBoxToggleVent = view.findViewById(R.id.check_box_toggle_vent);
            EditText editTextMinutes = view.findViewById(R.id.edit_text_minutes);
            EditText editTextHours = view.findViewById(R.id.edit_text_hours);
            DatePicker datePicker = view.findViewById(R.id.date_picker);

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

            alertDialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE).setOnClickListener(v1 -> {
                if (StringUtils.isEmpty(editTextTaskName.getText().toString())) {
                    editTextTaskName.setError("Required field");
                } else {
                    alertDialog.dismiss();
                }
            });
        });
        return root;
    }

}