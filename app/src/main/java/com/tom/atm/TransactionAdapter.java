package com.tom.atm;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/24.
 */

public class TransactionAdapter {

    private final ArrayList<Transaction> trans;

    public TransactionAdapter(ArrayList<Transaction> trans) {
        this.trans = trans;
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
