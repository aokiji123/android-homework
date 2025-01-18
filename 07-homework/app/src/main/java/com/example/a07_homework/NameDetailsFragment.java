package com.example.a07_homework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NameDetailsFragment extends Fragment {

    private static final String ARG_NAME = "name";

    private String name;

    public static NameDetailsFragment newInstance(String name) {
        NameDetailsFragment fragment = new NameDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(ARG_NAME);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_name_details, container, false);

        TextView nameTextView = view.findViewById(R.id.name_text_view);
        TextView nameDaysTextView = view.findViewById(R.id.name_days_text_view);
        TextView descriptionTextView = view.findViewById(R.id.description_text_view);

        MainActivity activity = (MainActivity) getActivity();
        NameModel model = activity.getNameDetails(name);

        if (model != null) {
            nameTextView.setText(model.getName());
            nameDaysTextView.setText(model.getNameDays());
            descriptionTextView.setText(model.getDescription());
        }

        return view;
    }
}

