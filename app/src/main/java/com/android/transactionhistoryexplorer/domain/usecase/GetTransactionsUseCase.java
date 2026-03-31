package com.android.transactionhistoryexplorer.domain.usecase;

import androidx.lifecycle.LiveData;
import androidx.paging.PagingSource;

import com.android.transactionhistoryexplorer.core.QualifierCore;
import com.android.transactionhistoryexplorer.domain.mapper.ITransactionMapper;
import com.android.transactionhistoryexplorer.domain.model.TransactionDetail;
import com.android.transactionhistoryexplorer.domain.model.TransactionSummary;
import com.android.transactionhistoryexplorer.domain.repository.ITransactionRepository;
import com.android.transactionhistoryexplorer.presentation.Resource;

import javax.inject.Inject;
import javax.inject.Qualifier;

public class GetTransactionsUseCase {
    private final ITransactionRepository transactionRepository;

    @Inject
    public GetTransactionsUseCase(@QualifierCore.RoomTransactionRepo ITransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public PagingSource<Integer, TransactionSummary> getTransactionList() {
        return this.transactionRepository.getTransactionSummaries();
    }

    public PagingSource<Integer, TransactionSummary> searchTransactionByNote(String note) {
        return this.transactionRepository.searchTransactionByNote(note);
    }
    public LiveData<Resource<TransactionDetail>> getTransactionDetail(int id) {
        return this.transactionRepository.getTransactionDetail(id);
    }

    public LiveData<Integer> getCount() {
        return this.transactionRepository.getCount();
    }

}
