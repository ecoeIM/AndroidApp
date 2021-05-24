package com.example.androidapp.ui.tasks;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidapp.R;

public class TasksFragment extends Fragment {

    private TasksViewModel tasksViewModel;



    private EditText title;
    private DatePicker date;
    private TimePicker time;
    private CheckBox lightCheck;
    private CheckBox ventCheck;
    private AppCompatButton createTask;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        tasksViewModel =
                new ViewModelProvider(this).get(TasksViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tasks, container, false);


        createTask = root.findViewById(R.id.createTaskFrgm);



        //show alert dialog
        createTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup viewGroup = root.findViewById(android.R.id.content);
                View view = LayoutInflater.from(root.getContext()).inflate(R.layout.alert_dialog, viewGroup, false);
                AppCompatButton create = view.findViewById(R.id.buttonCreateTaskAD);

                AlertDialog.Builder builder = new AlertDialog.Builder(root.getContext());
                builder.setView(view);

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                title = view.findViewById(R.id.taskTitleAD);
                date = view.findViewById(R.id.taskDateAD);
                time = view.findViewById(R.id.taskTimeAD);
                // CODE FOR CHECKBOXES
//                CheckBox lightOn = view.findViewById(R.id.checkboxLightOnAD);
//                CheckBox lightOff = view.findViewById(R.id.checkboxLightOffAD);
//                CheckBox ventOn = view.findViewById(R.id.checkboxVentOnAD);
//                CheckBox ventOff = view.findViewById(R.id.checkboxVentOffAD);
//
//                boolean checked = ((CheckBox) view).isChecked();
//
//                switch (view.getId())
//                {
//                    case R.id.checkboxLightOnAD:
//                        if(checked)
//                            //something
//                            else //something
//                        break;
//                    case R.id.checkboxLightOffAD:
//                        if(checked)
//                        //something
//                            else //something
//                        break;
//                    case R.id.checkboxVentOnAD:
//                        if(checked)
//                        //something
//                            else //something
//                        break;
//                    case R.id.checkboxVentOffAD:
//                        if(checked)
//                        //something
//                            else //something
//                        break;
//                }

                create.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //CODE TO STORE THE DATA SOMEWHERE
                    }
                });
            }
        });



        return root;
    }

}