package com.rooksoto.parallel.viewwidgets.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rooksoto.parallel.R;
import com.rooksoto.parallel.network.objects.Events;

import java.util.ArrayList;
import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter {
    List <Events> listofEvents = new ArrayList <>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View eventView = inflater.inflate(R.layout.fragment_login_wait_viewholder, parent, false);
        EventsViewholder viewHolder = new EventsViewholder(eventView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder (RecyclerView.ViewHolder holder, int position) {
        EventsViewholder eventsViewholder = (EventsViewholder) holder;
        eventsViewholder.bind(position);
    }

    @Override
    public int getItemCount () {
        return listofEvents.size();
    }
}
