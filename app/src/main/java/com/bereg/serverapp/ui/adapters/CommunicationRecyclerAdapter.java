package com.bereg.serverapp.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bereg.serverapp.R;
import com.bereg.serverapp.models.MessageModel;

import java.util.List;

/**
 * Created by 1 on 12.03.2018.
 */

public class CommunicationRecyclerAdapter extends RecyclerView.Adapter<CommunicationRecyclerAdapter.ViewHolder> {

    private static final String TAG = CommunicationRecyclerAdapter.class.getSimpleName();

    private List<MessageModel> items;

    public CommunicationRecyclerAdapter(List<MessageModel> list) {
        this.items = list;
        Log.e(TAG, "constructor:   ");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        Log.e(TAG, "onCreateViewHolder:  " + viewHolder);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        Log.e(TAG, "onBindViewHolder:  " + holder + "to" + position + "with" + items.get(position));
        holder.bind(items.get(position).getMessage(), position);
    }

    @Override
    public int getItemCount() {
        Log.e(TAG, "item count:   " + items.size());
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        View view;
        //private TextView senderTextView;
        private TextView messageTextView;

        ViewHolder(View v) {
            super(v);
            this.view = v;
            //senderTextView = v.findViewById(R.id.tv_sender);
            messageTextView = v.findViewById(R.id.tv_message);
            Log.e(TAG, "ViewHolder:   ");
        }

        void bind(final String message, int position) {

            messageTextView.setText(message);
            Log.e(TAG, "bind   :   " + message + "onPos   " + position);
        }
    }
}
