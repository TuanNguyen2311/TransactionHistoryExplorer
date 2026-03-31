package com.android.transactionhistoryexplorer.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagingSource;

import com.android.transactionhistoryexplorer.data.local.dao.TransactionDao;
import com.android.transactionhistoryexplorer.data.local.entity.TransactionEntity;
import com.android.transactionhistoryexplorer.domain.model.TransactionDetail;
import com.android.transactionhistoryexplorer.domain.model.TransactionSummary;
import com.android.transactionhistoryexplorer.domain.repository.ITransactionRepository;
import com.android.transactionhistoryexplorer.presentation.Resource;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class RoomTransactionRepoImpl implements ITransactionRepository {
    private TransactionDao transactionDao;

    @Inject
    public RoomTransactionRepoImpl(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @Override
    public void insertTransactions(List<TransactionEntity> transactionDetailList) {
        this.transactionDao.insertInTransaction(transactionDetailList);
    }

    @Override
    public PagingSource<Integer, TransactionSummary> getTransactionSummaries() {
        return transactionDao.getTransactionSummaries();
    }

    @Override
    public PagingSource<Integer, TransactionSummary> searchTransactionByNote(String note) {
        return transactionDao.searchTransactionByNote(note);
    }

    @Override
    public LiveData<Resource<TransactionDetail>> getTransactionDetail(int id) {
        MediatorLiveData<Resource<TransactionDetail>> result = new MediatorLiveData<>();
        result.setValue(Resource.loading());
        LiveData<TransactionDetail> source = this.transactionDao.getTransactionDetail(id);
        result.addSource(source, new Observer<TransactionDetail>() {
            @Override
            public void onChanged(TransactionDetail transactionDetail) {
                if(transactionDetail!=null) {
                    result.setValue(Resource.success(transactionDetail));
                } else {
                    result.setValue(Resource.error("Transaction not found"));
                }
            }
        });


        return result;
    }

    @Override
    public LiveData<Integer> getCount() {
        return transactionDao.getCount();
    }
}
