package com.karlo.dal.repository;

public final class RepositoryFactory {
    public static IRepository getRepository(){
        return new SqlRepository();
    }
}
