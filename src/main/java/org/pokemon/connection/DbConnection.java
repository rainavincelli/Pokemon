package org.pokemon.connection;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class DbConnection {
    private final String dbUsername = "postgres";
    private final String dbPassword = "postgres1";
    private final String connectionString = "jdbc:postgresql://localhost:5432/Pokemon";
    private static DataSource dataSource;

    private DbConnection(){

        if(DbConnection.dataSource == null) {
            BasicDataSource basicDataSource = new BasicDataSource();
            basicDataSource.setUrl(connectionString);
            basicDataSource.setUsername(dbUsername);
            basicDataSource.setPassword(dbPassword);

            DbConnection.dataSource = basicDataSource;
        }
    }

    public static DataSource get(){

        if(DbConnection.dataSource == null){
            new DbConnection();
        }
        return DbConnection.dataSource;
    }
}
