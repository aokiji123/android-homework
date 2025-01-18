package com.example.a07_homework;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<NameModel> nameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeNameList();

        if (savedInstanceState == null) {
            loadFragment(new NameListFragment());
        }
    }

    private void initializeNameList() {
        nameList = new ArrayList<>();
        nameList.add(new NameModel("Аркадій", "12.01, 8.02, 13.03, 6.07", "Опис для Аркадія..."));
        nameList.add(new NameModel("Марія", "15.01, 22.03", "Опис для Марії..."));
    }

    public NameModel getNameDetails(String name) {
        for (NameModel model : nameList) {
            if (model.getName().equals(name)) {
                return model;
            }
        }
        return null;
    }

    public void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
