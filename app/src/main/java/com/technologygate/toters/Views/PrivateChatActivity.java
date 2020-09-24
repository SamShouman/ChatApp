package com.technologygate.toters.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.technologygate.toters.Adapters.MessagesAdapter;
import com.technologygate.toters.Models.DatabaseHelper;
import com.technologygate.toters.R;
import com.technologygate.toters.ViewModels.PrivateChatViewModel;

import java.util.Objects;

public class PrivateChatActivity extends AppCompatActivity {
    private Toolbar myToolbar;
    private ImageButton sendBtn;
    private EditText messageEt;
    private RecyclerView chatRv;
    private Intent intent;
    private PrivateChatViewModel viewModel;
    private int userId, profileId;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_chat);

        intent = getIntent();
        initializeComponents();


            viewModel.loadMessages(userId, chatRv, profileId);


        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    viewModel.sendMessage(messageEt, userId);
            }
        });
    }

    private void initializeComponents() {

        myToolbar = findViewById(R.id.myToolbar);
        sendBtn = findViewById(R.id.sendBtn);
        messageEt = findViewById(R.id.messageEt);
        chatRv = findViewById(R.id.chatRv);
        viewModel = new PrivateChatViewModel(this);

        userId = intent.getIntExtra("userId", 0);
        userName = intent.getStringExtra("userName");
        profileId = intent.getIntExtra("profileId", 0);


        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setTitle(userName);


    }
}