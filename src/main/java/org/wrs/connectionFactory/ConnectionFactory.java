package org.wrs.connectionFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;

public class ConnectionFactory {

    private DataSource poolConnection;

    public ConnectionFactory(){
        ComboPooledDataSource poolDataSource = new ComboPooledDataSource();
        poolDataSource.setJdbcUrl("jdbc:mysql://aws.connect.psdb.cloud/rfidschoolstore?sslMode=VERIFY_IDENTITY");
        poolDataSource.setUser("ptfvm8tjp8gdqbqzdb48");
        poolDataSource.setPassword("pscale_pw_szX3P8nNodz6LLsSJYBabb68SZcbRQOtfZNa0U1TI0");
        poolDataSource.setMaxPoolSize(2);
        this.poolConnection = poolDataSource;

    }

    public DataSource getConnection (){
        return this.poolConnection;
    }

}
