package com.android.transactionhistoryexplorer.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;

import com.android.transactionhistoryexplorer.core.QualifierCore;
import com.android.transactionhistoryexplorer.domain.model.TransactionDetail;
import com.android.transactionhistoryexplorer.domain.model.TransactionSummary;
import com.android.transactionhistoryexplorer.domain.usecase.GetTransactionsUseCase;
import com.android.transactionhistoryexplorer.domain.usecase.InsertTransactionsUseCase;
import com.android.transactionhistoryexplorer.presentation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;
import javax.inject.Qualifier;

import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlin.jvm.functions.Function1;

@HiltViewModel
public class TransactionViewModel extends ViewModel {
    private final GetTransactionsUseCase getTransactionsUseCase;
    private final InsertTransactionsUseCase insertTransactionsUseCase;
    private final ExecutorService executorService;

    private LiveData<PagingData<TransactionSummary>> transactionSummaryPagingData;

    public LiveData<PagingData<TransactionSummary>> getTransactionSummaryPagingData() {
        return transactionSummaryPagingData;
    }

    private final MutableLiveData<Integer> _transactionId = new MutableLiveData<>();
    private final MutableLiveData<String> _transactionQuery = new MutableLiveData<>();
    public final LiveData<Resource<TransactionDetail>> transactionDetail;
    public LiveData<Resource<Integer>> transactionCount;

    public LiveData<Resource<Integer>> getTransactionCount() {
        return transactionCount;
    }

    public LiveData<Resource<TransactionDetail>> getTransactionDetail() {
        return transactionDetail;
    }

    @Inject
    public TransactionViewModel(GetTransactionsUseCase getTransactionsUseCase, InsertTransactionsUseCase insertTransactionsUseCase,@QualifierCore.BackgroundTasks ExecutorService executorService) {
        this.getTransactionsUseCase = getTransactionsUseCase;
        this.insertTransactionsUseCase = insertTransactionsUseCase;
        this.executorService = executorService;

        this.transactionCount = Transformations.map(this.getTransactionsUseCase.getCount(), Resource::success);

        transactionSummaryPagingData = Transformations.switchMap(_transactionQuery, query -> {
            Pager<Integer, TransactionSummary> pager = new Pager<>(new PagingConfig(40, 15, false, 20), ()-> {
                if(query==null || query.isEmpty()) {
                    return this.getTransactionsUseCase.getTransactionList();
                } else {
                    return this.getTransactionsUseCase.searchTransactionByNote(query);
                }
            });
            return PagingLiveData.getLiveData(pager);
        });
        transactionDetail = Transformations.switchMap(_transactionId, getTransactionsUseCase::getTransactionDetail);
    }

    public void addTransactionDataForTesting(){
        executorService.execute(() -> {
            int dataLength = 2000000;
            List<TransactionDetail> transactionDetailList = new ArrayList<>();
            for(int i=1;i<=dataLength;i++){
                TransactionDetail transactionDetail = new TransactionDetail(i, "TC_"+i, "TH_"+i, 1000000, "VND", 10000, 200000000, "Sender_"+i, "Receiver_"+i, "Provider_"+i, System.currentTimeMillis(), "Note_"+i, 1, "Category_"+i, "Device_"+i, "IP_"+i, "Location_"+i);
                transactionDetail.setTransactionCode(String.valueOf(i));
                transactionDetailList.add(transactionDetail);
                if(transactionDetailList.size()==50000 || i==dataLength) {
                    try {
                        insertTransactionsUseCase.execute(transactionDetailList);
                        transactionDetailList.clear();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }


    public void searchTransactionByNote(String note){
        if(!note.equals(_transactionQuery.getValue())) {
            _transactionQuery.setValue(note);
        }
    }

    public void loadData(){
        _transactionQuery.setValue("");
    }

    public void selectTransaction(int id){
        _transactionId.setValue(id);

    }




}
