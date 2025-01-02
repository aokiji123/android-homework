package com.example.a05_homework;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends ArrayAdapter<UserModel> {
    private final LayoutInflater inflater;

    public UserAdapter(Context context, List<UserModel> users) {
        super(context, R.layout.user_list, users);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.user_list, parent, false);
        }

        UserModel user = getItem(position);

        ImageView avatar = view.findViewById(R.id.avatar);
        TextView name = view.findViewById(R.id.name);
        TextView location = view.findViewById(R.id.location);
        TextView age = view.findViewById(R.id.age);

        avatar.setImageResource(user.getAvatarId());
        name.setText(user.getFirstName() + " " + user.getLastName());
        location.setText(user.getCity() + ", " + user.getCountry());
        age.setText("Age: " + user.getAge());

        return view;
    }
}
