package com.example.androidapp.ui.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidapp.R;
import com.example.androidapp.SettingsActivity;

public class TasksFragment extends Fragment {

    private TasksViewModel tasksViewModel;
    private ImageView imageViewAnime;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        tasksViewModel =
                new ViewModelProvider(this).get(TasksViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tasks, container, false);
        final TextView textView = root.findViewById(R.id.text_tasks);
        //tasksViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        imageViewAnime = root.findViewById(R.id.imageViewAnime);
        imageViewAnime.setOnClickListener(v -> {
            Toast toast = Toast.makeText(getContext(), "SHEEEEESH", Toast.LENGTH_LONG);
            toast.show();
        });

        return root;
    }
}