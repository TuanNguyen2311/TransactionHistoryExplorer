package com.android.transactionhistoryexplorer.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.android.transactionhistoryexplorer.R;
import com.android.transactionhistoryexplorer.domain.model.TransactionSummary;

public class TransactionAdapter extends PagingDataAdapter<TransactionSummary, TransactionAdapter.TransactionViewHolder> {

    private static final DiffUtil.ItemCallback<TransactionSummary> DIFF_CALLBACK = new DiffUtil.ItemCallback<TransactionSummary>() {
        @Override
        public boolean areItemsTheSame(@NonNull TransactionSummary oldItem, @NonNull TransactionSummary newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull TransactionSummary oldItem, @NonNull TransactionSummary newItem) {
            return oldItem.getTransactionCode().equals(newItem.getTransactionCode());
        }
    };
    public TransactionAdapter() {
        super(DIFF_CALLBACK);
    }

    public interface OnItemClickListener {
        void onItemClick(int transactionId);
    }
    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public TransactionAdapter.TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction_summary_layout, parent, false);

        return new TransactionViewHolder(view, listener, this);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.TransactionViewHolder holder, int position) {
        TransactionSummary transactionSummary = getItem(position);
        if(transactionSummary != null) holder.bind(transactionSummary);
    }

    public static class TransactionViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvTransactionCode, tvDate, tvAmount, tvStatus;
        public TransactionViewHolder(@NonNull View itemView, OnItemClickListener listener, TransactionAdapter adapter) {
            super(itemView);
            tvTransactionCode = itemView.findViewById(R.id.tvTransactionCode);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getBindingAdapterPosition();
                    if(position != RecyclerView.NO_POSITION && listener != null) {
                        TransactionSummary selectedTransaction = adapter.getItem(position);
                        if(selectedTransaction!=null) {
                            listener.onItemClick(selectedTransaction.getId());
                        }
                    }
                }
            });
        }
        public void bind(TransactionSummary transactionSummary) {
            if(transactionSummary==null) {
                tvTransactionCode.setText("XXXX");
                tvDate.setText("yyyy-MM-dd HH:mm:ss");
                tvAmount.setText("$");
                String status = "Pending";
                tvStatus.setText(status);
            } else {
                tvTransactionCode.setText(transactionSummary.getTransactionCode());
                tvDate.setText(String.valueOf(transactionSummary.getTimestamp()));
                tvAmount.setText(String.valueOf(transactionSummary.getAmount()));
                String status = "Pending";
                if(transactionSummary.getStatus()==1) {
                    status = "Success";
                } else if(transactionSummary.getStatus()==2) {
                    status = "Failed";
                }
                tvStatus.setText(status);
            }

        }
    }
}
