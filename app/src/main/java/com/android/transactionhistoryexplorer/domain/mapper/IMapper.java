package com.android.transactionhistoryexplorer.domain.mapper;

import java.util.List;

public interface IMapper <Entity, Domain>{
    Entity mapDomainToEntity(Domain domain);
    List<Entity> mapDomainListToEntityList(List<Domain> domainList);
}
