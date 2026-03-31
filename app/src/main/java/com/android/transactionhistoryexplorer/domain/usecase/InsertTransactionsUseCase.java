package com.android.transactionhistoryexplorer.domain.usecase;

import com.android.transactionhistoryexplorer.core.QualifierCore;
import com.android.transactionhistoryexplorer.data.local.entity.TransactionEntity;
import com.android.transactionhistoryexplorer.domain.mapper.ITransactionMapper;
import com.android.transactionhistoryexplorer.domain.model.TransactionDetail;
import com.android.transactionhistoryexplorer.domain.repository.ITransactionRepository;

import java.util.List;

import javax.inject.Inject;

public class InsertTransactionsUseCase {
    private final ITransactionRepository transactionRepository;
    private final ITransactionMapper transactionMapper;

    @Inject
    public InsertTransactionsUseCase(@QualifierCore.RoomTransactionRepo ITransactionRepository transactionRepository,@QualifierCore.RoomTransactionMapper ITransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    public void execute(List<TransactionDetail> transactionDetailList) throws InterruptedException {
        this.transactionRepository.insertTransactions(transactionMapper.mapDomainListToEntityList(transactionDetailList));

    }


}
