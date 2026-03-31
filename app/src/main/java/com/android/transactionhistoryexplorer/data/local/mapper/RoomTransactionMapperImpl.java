package com.android.transactionhistoryexplorer.data.local.mapper;

import com.android.transactionhistoryexplorer.data.local.entity.TransactionEntity;
import com.android.transactionhistoryexplorer.domain.mapper.ITransactionMapper;
import com.android.transactionhistoryexplorer.domain.model.TransactionDetail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoomTransactionMapperImpl implements ITransactionMapper {
    @Override
    public TransactionEntity mapDomainToEntity(TransactionDetail transactionDetail) {
        if(transactionDetail==null) return null;
        return new TransactionEntity(transactionDetail.getId(), transactionDetail.getTransactionCode(),
                transactionDetail.getTransactionHash(), transactionDetail.getAmount(),
                transactionDetail.getCurrency(), transactionDetail.getFee(),
                transactionDetail.getBalanceAfter(), transactionDetail.getSenderName(),
                transactionDetail.getReceiverName(), transactionDetail.getProvider(),
                transactionDetail.getTimestamp(), transactionDetail.getNote(),
                transactionDetail.getStatus(), transactionDetail.getCategory(),
                transactionDetail.getDeviceId(), transactionDetail.getIpAddress(), transactionDetail.getLocation());
    }

    @Override
    public List<TransactionEntity> mapDomainListToEntityList(List<TransactionDetail> transactionDetailList) {
        if(transactionDetailList==null) return null;
        List<TransactionEntity> transactionEntityList = new ArrayList<>();
        for(TransactionDetail transactionDetail : transactionDetailList) {
            transactionEntityList.add(mapDomainToEntity(transactionDetail));
        }
        return transactionEntityList;
    }
}
