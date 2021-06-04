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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidapp.R;
import com.example.androidapp.adapter.TaskListAdapter;
import com.example.androidapp.data.model.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TasksFragment extends Fragment implements TaskListAdapter.OnListDeleteTaskClickListener {

    private TasksViewModel tasksViewModel;
    private FloatingActionButton fabCreateTask;
    private ImageButton imageButtonTasksHelp;
    private RecyclerView recyclerView;
    private TaskListAdapter adapter;
    private List scopedTasks;
    private TextView emptyView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        tasksViewModel =
                new ViewModelProvider(this).get(TasksViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tasks, container, false);
        scopedTasks = new ArrayList<>();
        fabCreateTask = root.findViewById(R.id.fab_create_task);
        imageButtonTasksHelp = root.findViewById(R.id.image_button_tasks_help);
        emptyView = root.findViewById(R.id.empty_view);
        recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.hasFixedSize();

        tasksViewModel.getTasks();

        tasksViewModel.getTasks().observe(getViewLifecycleOwner(), tasks -> {
            scopedTasks = tasks;

            //adapter
            if (!tasks.isEmpty()) {
                recyclerView.setVisibility(View.VISIBLE);
                emptyView.setVisibility(View.GONE);
                adapter = new TaskListAdapter((ArrayList<Task>) scopedTasks, this);
                recyclerView.setAdapter(adapter);
            } else {
                recyclerView.setVisibility(View.GONE);
                emptyView.setVisibility(View.VISIBLE);
            }
        });

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

            editTextTaskName.requestFocus();
            AlertDialog alertDialog = builder.create();
            alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            alertDialog.show();

            alertDialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE).setOnClickListener(v1 -> {
                LocalDateTime dt = LocalDateTime.now();
                LocalDateTime dtFromFields;
                boolean isValidTimeSyntax = true;

                if (StringUtils.isEmpty(editTextTaskName.getText().toString())) {
                    editTextTaskName.setError("Required field");
                } else {
                    if (checkBoxToggleLight.isChecked() || checkBoxToggleVent.isChecked()) {
                        if (StringUtils.isEmpty(editTextMinutes.getText().toString())) {
                            editTextMinutes.setError("Required field");
                        }
                        if (StringUtils.isEmpty(editTextHours.getText().toString())) {
                            editTextHours.setError("Required field");
                        }
                        if (!StringUtils.isEmpty(editTextMinutes.getText().toString()) && !StringUtils.isEmpty(editTextHours.getText().toString())) {
                            if (Integer.parseInt(editTextMinutes.getText().toString()) > 59 || Integer.parseInt(editTextMinutes.getText().toString()) < 0) {
                                editTextMinutes.setError("Invalid value");
                                isValidTimeSyntax = false;
                            }
                            if (Integer.parseInt(editTextHours.getText().toString()) > 23 || Integer.parseInt(editTextHours.getText().toString()) < 0) {
                                editTextHours.setError("Invalid value");
                                isValidTimeSyntax = false;
                            }
                            if (isValidTimeSyntax) {
                                dtFromFields = LocalDateTime.now()
                                        .withMinute(Integer.parseInt(editTextMinutes.getText().toString()))
                                        .withHour(Integer.parseInt(editTextHours.getText().toString()))
                                        .withDayOfMonth(datePicker.getDayOfMonth())
                                        .withMonth(datePicker.getMonth() + 1)
                                        .withYear(datePicker.getYear());
                                if (dtFromFields.isBefore(dt) || dtFromFields.isEqual(dt)) {
                                    Snackbar snackbar = Snackbar
                                            .make(view, "Past dates are not allowed", Snackbar.LENGTH_LONG);
                                    snackbar.show();
                                } else {
                                    Task newTask = new Task();
                                    newTask.name = editTextTaskName.getText().toString();
                                    newTask.toggleLight = checkBoxToggleLight.isChecked();
                                    newTask.toggleVent = checkBoxToggleVent.isChecked();
                                    newTask.dateTime = dtFromFields.toString();
                                    newTask.terrariumId = 1;
                                    scopedTasks.add(newTask);
                                    tasksViewModel.addTask(newTask);
                                    adapter.notifyDataSetChanged();
                                    alertDialog.dismiss();
                                }
                            }
                        }
                    } else {
                        Task newTask = new Task();
                        newTask.name = editTextTaskName.getText().toString();
                        newTask.terrariumId = 1;
                        tasksViewModel.addTask(newTask);
                        adapter.notifyDataSetChanged();
                        alertDialog.dismiss();
                    }
                }
            });
        });
        return root;
    }

    @Override
    public void onDeleteClick(int index) {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(getActivity());
        builder.setMessage("Do you really want to delete this task?");
        builder.setPositiveButton("Delete", (dialog, id) -> {
            //scopedTasks.remove(index);
            Task selectedTask = (Task) scopedTasks.get(index);
            int taskId = selectedTask.id;
            tasksViewModel.deleteTask(taskId);
            adapter.notifyDataSetChanged();
        });
        builder.setNegativeButton("Cancel", (dialog, id) -> {
            //close
        });
        androidx.appcompat.app.AlertDialog dialog = builder.create();
        dialog.show();
    }
}