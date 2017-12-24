package com.sumberrejeki.webviewadam;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdamAdapter extends RecyclerView.Adapter<AdamAdapter.MyViewHolder> {

    private List<Adam> adamList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mButtonTitle, mHtmlToLoad;

        public MyViewHolder(View view) {
            super(view);
            mButtonTitle = (TextView) view.findViewById(R.id.buttontitle);
        }
    }


    public AdamAdapter(List<Adam> adamList) {
        this.adamList = adamList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adam_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Adam adam = adamList.get(position);
        holder.mButtonTitle.setText(adam.getButtonTitle());
    }

    @Override
    public int getItemCount() {
        return adamList.size();
    }
}
