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

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHoler>{
    private final ArrayList<Transaction> trans;

    public TransactionAdapter(ArrayList<Transaction> trans){
        this.trans = trans;
    }

    @Override
    public ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.trans_row, parent, false);
        ViewHoler viewHoler = new ViewHoler(view);
        return viewHoler;
    }

    @Override
    public void onBindViewHolder(ViewHoler holder, int position) {
        Transaction t = trans.get(position);
        holder.dateTextView.setText(t.getDate());
        holder.amountTextView.setText(t.getAmount()+"");
        holder.typeTextView.setText(t.getType()+"");
    }

    @Override
    public int getItemCount() {
        return trans.size();
    }

    public static class ViewHoler extends RecyclerView.ViewHolder{
        TextView dateTextView;
        TextView amountTextView;
        TextView typeTextView;

        public ViewHoler(View itemView) {
            super(itemView);
            dateTextView = (TextView) itemView.findViewById(R.id.tran_date);
            amountTextView = (TextView) itemView.findViewById(R.id.tran_amount);
            typeTextView = (TextView) itemView.findViewById(R.id.tran_type);
        }
    }

}
