package com.android.transactionhistoryexplorer.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.android.transactionhistoryexplorer.data.local.dao.TransactionDao;
import com.android.transactionhistoryexplorer.data.local.entity.TransactionEntity;

@Database(entities = {TransactionEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TransactionDao transactionDao();
}
