package com.technologygate.toters.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.technologygate.toters.Models.Message;
import com.technologygate.toters.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessagesViewHolder> {

    private Context context;
    private List<Message> messages;
    private int profileId;

    public MessagesAdapter(Context context, List<Message> messages, int profileId) {
        this.context = context;
        this.messages = messages;
        this.profileId = profileId;
    }

    @NonNull
    @Override
    public MessagesAdapter.MessagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType % 2 == 0) {
            View v = LayoutInflater.from(context).inflate(R.layout.chat_right, parent, false);
            return new MessagesViewHolder(v);
        } else {
            View v = LayoutInflater.from(context).inflate(R.layout.chat_left, parent, false);
            return new MessagesViewHolder(v);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull MessagesAdapter.MessagesViewHolder holder, int position) {

        Message message = messages.get(position);
        holder.messageTv.setText(message.getContent());
        holder.profileIv.setImageResource(profileId);

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public static class MessagesViewHolder extends RecyclerView.ViewHolder {
        public TextView messageTv;
        public CircleImageView profileIv;

        public MessagesViewHolder(@NonNull View itemView) {
            super(itemView);

            messageTv = itemView.findViewById(R.id.messageTv);
            profileIv = itemView.findViewById(R.id.profileIv);
        }
    }
}
