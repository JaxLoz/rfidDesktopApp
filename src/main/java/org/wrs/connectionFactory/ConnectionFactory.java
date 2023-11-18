package org.wrs.connectionFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;

public class ConnectionFactory {

    private DataSource poolConnection;

    public ConnectionFactory(){
        ComboPooledDataSource poolDataSource = new ComboPooledDataSource();
        poolDataSource.setJdbcUrl("jdbc:mysql://aws.connect.psdb.cloud/rfidschoolstore?sslMode=VERIFY_IDENTITY");
        poolDataSource.setUser("kdkt06oq3ce31q5mlrph");
        poolDataSource.setPassword("pscale_pw_Dy4tFqwBbqaB4UbHwxRcoAbqIm9Re2oEzg3U6KgxMK3");
        poolDataSource.setMinPoolSize(2);
        poolDataSource.setMaxPoolSize(2);
        this.poolConnection = poolDataSource;

    }

    public DataSource getConnection (){
        return this.poolConnection;
    }

}
