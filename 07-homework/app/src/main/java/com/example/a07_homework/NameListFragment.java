package com.example.a07_homework;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class NameListFragment extends Fragment {

    private GridView gridView;
    private List<String> names;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_name_list, container, false);

        gridView = view.findViewById(R.id.grid_view);
        initializeNameList();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, names);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = names.get(position);

                NameDetailsFragment detailsFragment = NameDetailsFragment.newInstance(selectedName);
                ((MainActivity) getActivity()).loadFragment(detailsFragment);
            }
        });

        return view;
    }

    private void initializeNameList() {
        names = new ArrayList<>();
        names.add("Аркадій");
        names.add("Марія");
    }
}
