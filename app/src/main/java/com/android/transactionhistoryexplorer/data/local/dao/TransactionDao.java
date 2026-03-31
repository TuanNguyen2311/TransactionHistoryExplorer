package com.android.transactionhistoryexplorer.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.paging.PagingSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.android.transactionhistoryexplorer.data.local.entity.TransactionEntity;
import com.android.transactionhistoryexplorer.domain.model.TransactionDetail;
import com.android.transactionhistoryexplorer.domain.model.TransactionSummary;
import com.android.transactionhistoryexplorer.presentation.Resource;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface TransactionDao {
    int BATCH_SIZE = 50000;

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TransactionEntity transaction);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<TransactionEntity> transactionList);
    @Update
    void update(TransactionEntity transaction);
    @Delete
    void delete(TransactionEntity transaction);
    @Query("SELECT id, transactionCode, amount, currency, senderName, receiverName, timestamp, note, status FROM transaction_table")
    PagingSource<Integer, TransactionSummary> getTransactionSummaries();

    @Query("SELECT id, transactionCode, amount, currency, senderName, receiverName, timestamp, note, status FROM transaction_table WHERE note LIKE '%' || :note || '%'")
    PagingSource<Integer, TransactionSummary> searchTransactionByNote(String note);

    @Query("SELECT * FROM transaction_table WHERE id = :id")
    LiveData<TransactionDetail> getTransactionDetail(int id);
    @Query("SELECT COUNT(*) FROM transaction_table")
    LiveData<Integer> getCount();

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
