package com.karlo.bl;

import com.karlo.dal.repository.IRepository;
import com.karlo.dal.repository.RepositoryFactory;

public abstract class HandlerBase {
    final IRepository repo;

    public HandlerBase() {
        repo = RepositoryFactory.getRepository();
    }
    
}
