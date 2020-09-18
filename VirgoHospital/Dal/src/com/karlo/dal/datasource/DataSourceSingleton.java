package com.karlo.dal.datasource;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import javax.sql.DataSource;

public final class DataSourceSingleton {

    private static DataSource createInstance() {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName(SERVER_NAME);
        ds.setDatabaseName(DATABASE_NAME);
        ds.setUser(USER);
        ds.setPassword(PASSWORD);        
        return ds;
    }
    
    
    private static final String SERVER_NAME = "DESKTOP-NQRK4KJ";      
    private static final String DATABASE_NAME = "VirgoHospital";
    private static final String USER = "Karlo"; 
    private static final String PASSWORD = "SQL"; 

    
    private DataSourceSingleton(){}
    
    private static DataSource instance;

    public static DataSource getInstance() {
        if (instance == null) {
            instance = createInstance();
        }
        return instance;
    }
    
}
