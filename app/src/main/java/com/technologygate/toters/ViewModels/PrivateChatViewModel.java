package com.technologygate.toters.ViewModels;

import android.app.Activity;
import android.os.CountDownTimer;
import android.widget.EditText;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.technologygate.toters.Adapters.MessagesAdapter;
import com.technologygate.toters.Models.DatabaseHelper;
import com.technologygate.toters.Models.Message;

import java.util.ArrayList;
import java.util.Calendar;

public class PrivateChatViewModel {
    private RecyclerView mChatRv;
    private Activity activity;
    private ArrayList<Message> messages = new ArrayList<>();
    private DatabaseHelper db;
    private int profileId;

    public PrivateChatViewModel(Activity activity) {
        this.activity = activity;
        db = new DatabaseHelper(activity);
    }


    public void sendMessage(EditText messageEt, int userId) {
        String message = messageEt.getText().toString().trim();

        if (!message.equals("")) {
            String timeStamp = Calendar.getInstance().getTime().toString();
            db.addMessage(message, userId, timeStamp);
            messageEt.setText("");

            loadMessages(userId, mChatRv, profileId);

            receiveMessage(userId, message, timeStamp);
        }
    }

    private void receiveMessage(final int userId, final String message, final String timeStamp) {

        new CountDownTimer(500, 1) {
            @Override
            public void onFinish() {
                db.receiveMessage(message, userId, timeStamp);
                loadMessages(userId, mChatRv, profileId);
            }

            @Override
            public void onTick(long millisUntilFinished) {

            }
        }.start();
    }

    public void loadMessages(final int userId, final RecyclerView chatRv, final int profileId) {

        mChatRv = chatRv;
        this.profileId = profileId;
        messages.clear();

        messages = db.getMessages(userId);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity.getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        chatRv.setLayoutManager(linearLayoutManager);

        MessagesAdapter adapter = new MessagesAdapter(activity.getApplicationContext(), messages, profileId);
        chatRv.setAdapter(adapter);


    }


}
