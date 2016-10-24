package com.tom.atm;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/24.
 */

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder>{

    private final ArrayList<Transaction> trans;

    public TransactionAdapter(ArrayList<Transaction> trans) {
        this.trans = trans;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.trans_row, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return trans.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView dateTextView;
        private TextView amountTextView;
        private TextView typeTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            dateTextView = (TextView) itemView.findViewById(R.id.tran_date);
            amountTextView = (TextView) itemView.findViewById(R.id.tran_amount);
            typeTextView = (TextView) itemView.findViewById(R.id.tran_type);
        }
    }
}
