package com.rooksoto.parallel.fragments.activityHub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.pojos.Chat;

/**
 * Created by huilin on 3/2/17.
 */

public class FragmentChat extends Fragment {

    private ProgressBar progressBar;
    private EditText messageEditText;
    private Button sendButton;
    private ListView messageListView;
    private FirebaseListAdapter<Chat> messageListAdapter;

    public FragmentChat() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chatroom, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        messageEditText = (EditText) view.findViewById(R.id.messageEditText);
        sendButton = (Button) view.findViewById(R.id.sendButton);
        messageListView = (ListView) view.findViewById(R.id.messageListView);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // FIXME will need to expand and grab person name from FirebaseAuth and eventID/chatId for latter child method
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("chatIds").child("001");
        messageListAdapter = new FirebaseListAdapter<Chat>(getActivity(), Chat.class, R.layout.item_message, ref) {
            @Override
            protected void populateView(View view, Chat chatMessage, int position) {
                progressBar.setVisibility(View.INVISIBLE);
                ((TextView) view.findViewById(R.id.messageTextView)).setText(chatMessage.getText());
                ((TextView) view.findViewById(R.id.nameTextView)).setText(chatMessage.getName());

            }
        };
        messageListView.setAdapter(messageListAdapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref.push().setValue(new Chat("Lily", messageEditText.getText().toString()));
                messageEditText.setText("");
            }
        });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        messageListAdapter.cleanup();
    }
}
