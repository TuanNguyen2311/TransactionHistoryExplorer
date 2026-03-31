package com.android.transactionhistoryexplorer.presentation.view.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.transactionhistoryexplorer.R;
import com.android.transactionhistoryexplorer.domain.model.TransactionDetail;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class TransactionBottomSheet extends BottomSheetDialogFragment {

    public static TransactionBottomSheet newInstance(TransactionDetail transactionDetail) {
        return new TransactionBottomSheet(transactionDetail);
    }
    private final TransactionDetail transactionDetail;

    public TransactionBottomSheet(TransactionDetail transactionDetail) {
        this.transactionDetail = transactionDetail;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bottom_sheet_transaction_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvTransactionCode = view.findViewById(R.id.tvTransactionCode);
        TextView tvAmount = view.findViewById(R.id.tvAmount);
        TextView tvDate = view.findViewById(R.id.tvDate);
        TextView tvStatus = view.findViewById(R.id.tvStatus);
        TextView tvSender = view.findViewById(R.id.tvSender);
        TextView tvReceiver = view.findViewById(R.id.tvReceiver);
        TextView tvProvider = view.findViewById(R.id.tvProvider);
        TextView tvCategory = view.findViewById(R.id.tvCategory);
        TextView tvNote = view.findViewById(R.id.tvNote);

        if(transactionDetail!=null) {
            tvTransactionCode.setText("Transaction Code: " + transactionDetail.getTransactionCode());
            tvAmount.setText("Amount: " + transactionDetail.getAmount());
            tvDate.setText("Date: " + transactionDetail.getTimestamp());
            String status = "Pending";
            if(transactionDetail.getStatus()==1) {
                status = "Success";
            } else if(transactionDetail.getStatus()==2) {
                status = "Failed";
            }
            tvStatus.setText("Status: " + status);
            tvSender.setText("Sender: " + transactionDetail.getSenderName());
            tvReceiver.setText("Receiver: " + transactionDetail.getReceiverName());
            tvProvider.setText("Provider: " + transactionDetail.getProvider());
            tvCategory.setText("Category: " + transactionDetail.getCategory());
            tvNote.setText("Note: " + transactionDetail.getNote());
        }

    }
}
