package com.technologygate.toters.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.technologygate.toters.Adapters.UsersAdapter;
import com.technologygate.toters.Models.User;
import com.technologygate.toters.R;
import com.technologygate.toters.ViewModels.MainActivityViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView chatLv;
    private ArrayList<User> users = new ArrayList<>();
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();


        boolean isGenerated = viewModel.checkIfGenerated();

        if (isGenerated) // which means that the user has already generated the users when he entered for the 1st time
            users = viewModel.loadUsers();
        else
            users = viewModel.generateUsers();


        setAdapter();


        chatLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent goToPrivateChat = new Intent(MainActivity.this, PrivateChatActivity.class);
                goToPrivateChat.putExtra("userId", users.get(position).getId());
                goToPrivateChat.putExtra("userName", users.get(position).getName());
                goToPrivateChat.putExtra("profileId", users.get(position).getProfile());
                startActivity(goToPrivateChat);
            }
        });

    }

    private void setAdapter() {
        UsersAdapter usersAdapter = new UsersAdapter(this.getApplicationContext(), users);
        chatLv.setAdapter(usersAdapter);
    }

    private void initializeComponents() {

        viewModel = new MainActivityViewModel(this);
        chatLv = findViewById(R.id.chatLv);
    }


    @Override
    protected void onStart() {
        super.onStart();
        users = viewModel.loadUsers();
        setAdapter();

    }
}