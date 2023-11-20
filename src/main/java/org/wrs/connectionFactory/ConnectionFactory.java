package org.wrs.connectionFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;

public class ConnectionFactory {

    private DataSource poolConnection;


    public ConnectionFactory(){
        ComboPooledDataSource poolDataSource = new ComboPooledDataSource();
        //holasasasdfasldfjlaskdjfklasdf

        String url = propertiesUtil.getProperty("db.url");
        String user = propertiesUtil.getProperty("db.username");
        String password = propertiesUtil.getProperty("db.password");

        poolDataSource.setJdbcUrl(url);
        poolDataSource.setUser(user);
        poolDataSource.setPassword(password);
        poolDataSource.setMinPoolSize(3);
        poolDataSource.setMaxPoolSize(3);
        this.poolConnection = poolDataSource;

    }

    public DataSource getConnection (){
        return this.poolConnection;
    }

}
