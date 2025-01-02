package com.example.a05_homework;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<UserModel> users = generateUsers();
        UserAdapter adapter = new UserAdapter(this, users);

        ListView listView = findViewById(R.id.user_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            UserModel user = users.get(position);
            String message = "Selected: " + user.getFirstName() + " " + user.getLastName() + ", Age: " + user.getAge();
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        });
    }

    private List<UserModel> generateUsers() {
        int[] avatars = {
                R.drawable.avatar1,
                R.drawable.avatar2,
                R.drawable.avatar3,
                R.drawable.avatar4,
                R.drawable.avatar5
        };

        List<String> firstNames = Arrays.asList("John", "Alice", "Emma", "Mike", "Sophia", "Liam", "Olivia", "Noah", "Isabella", "Lucas");
        List<String> lastNames = Arrays.asList("Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Martinez", "Hernandez");
        List<String> countries = Arrays.asList("USA", "Canada", "UK");
        List<List<String>> cities = Arrays.asList(
                Arrays.asList("New York", "Los Angeles", "Chicago", "Houston", "Phoenix"),
                Arrays.asList("Toronto", "Vancouver", "Montreal", "Calgary", "Ottawa"),
                Arrays.asList("London", "Manchester", "Birmingham", "Liverpool", "Edinburgh")
        );

        Random random = new Random();
        List<UserModel> users = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            int avatarId = avatars[random.nextInt(avatars.length)];
            String firstName = firstNames.get(random.nextInt(firstNames.size()));
            String lastName = lastNames.get(random.nextInt(lastNames.size()));
            int age = 14 + random.nextInt(86);
            int countryIndex = random.nextInt(countries.size());
            String country = countries.get(countryIndex);
            String city = cities.get(countryIndex).get(random.nextInt(cities.get(countryIndex).size()));

            users.add(new UserModel(avatarId, firstName, lastName, age, country, city));
        }

        return users;
    }
}
