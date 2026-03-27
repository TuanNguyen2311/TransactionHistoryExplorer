package com.android.transactionhistoryexplorer.data.local.dao;

import androidx.paging.PagingSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.android.transactionhistoryexplorer.data.local.entity.TransactionEntity;
import com.android.transactionhistoryexplorer.domain.model.TransactionDetail;
import com.android.transactionhistoryexplorer.domain.model.TransactionSummary;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface TransactionDao {
    int BATCH_SIZE = 50000;

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TransactionEntity transaction);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<TransactionEntity> transactionList);
    @Query("SELECT id, transactionCode, amount, currency, senderName, receiverName, timestamp, note FROM transaction_table")
    PagingSource<Integer, TransactionSummary> getTransactionSummaries();

    @Transaction
    default void insertInTransaction(List<TransactionEntity> transactionList) {
        List<TransactionEntity> batch = new ArrayList<>();
        for (TransactionEntity transaction : transactionList) {
            batch.add(transaction);
            if (batch.size() >= BATCH_SIZE) {
                insertAll(batch);
                batch.clear();
            }
        }
    }

}
