package com.android.transactionhistoryexplorer.domain.repository;

import androidx.lifecycle.LiveData;
import androidx.paging.PagingSource;

import com.android.transactionhistoryexplorer.data.local.entity.TransactionEntity;
import com.android.transactionhistoryexplorer.domain.model.TransactionDetail;
import com.android.transactionhistoryexplorer.domain.model.TransactionSummary;
import com.android.transactionhistoryexplorer.presentation.Resource;

import java.util.List;

public interface ITransactionRepository {
    void insertTransactions(List<TransactionEntity> transactionDetailList);
    PagingSource<Integer, TransactionSummary> getTransactionSummaries();
    PagingSource<Integer, TransactionSummary> searchTransactionByNote(String note);
    LiveData<Resource<TransactionDetail>> getTransactionDetail(int id);

    LiveData<Integer> getCount();

}
