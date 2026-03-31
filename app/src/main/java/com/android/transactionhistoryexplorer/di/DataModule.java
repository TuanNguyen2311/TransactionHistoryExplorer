package com.android.transactionhistoryexplorer.di;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.android.transactionhistoryexplorer.core.QualifierCore;
import com.android.transactionhistoryexplorer.data.local.AppDatabase;
import com.android.transactionhistoryexplorer.data.local.dao.TransactionDao;
import com.android.transactionhistoryexplorer.data.local.mapper.RoomTransactionMapperImpl;
import com.android.transactionhistoryexplorer.data.repository.RoomTransactionRepoImpl;
import com.android.transactionhistoryexplorer.domain.mapper.ITransactionMapper;
import com.android.transactionhistoryexplorer.domain.repository.ITransactionRepository;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DataModule {
    @Provides
    @Singleton
    public AppDatabase provideAppDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "Transaction_History_Explorer.db").build();
    }

    @Provides
    public TransactionDao provideTransactionDao(AppDatabase appDatabase) {
        return appDatabase.transactionDao();
    }

    @Provides
    @QualifierCore.RoomTransactionMapper
    public ITransactionMapper provideTransactionMapper(){
        return new RoomTransactionMapperImpl();
    }

    @Provides
    @QualifierCore.BackgroundTasks
    public ExecutorService provideBackgroundTaskES(){
        return Executors.newFixedThreadPool(4);
    }

    @Provides
    @QualifierCore.LightweightTasks
    public ExecutorService provideLightweightTaskES(){
        return Executors.newFixedThreadPool(2);
    }

    @Provides
    @QualifierCore.RoomTransactionRepo
    public ITransactionRepository provideTransactionRepository(TransactionDao transactionDao){
        return new RoomTransactionRepoImpl(transactionDao);
    }



}
