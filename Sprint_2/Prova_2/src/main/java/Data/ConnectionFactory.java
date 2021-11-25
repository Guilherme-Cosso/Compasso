package Data;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

    ComboPooledDataSource dataSources;

    public ConnectionFactory(){
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/filmes?useTimezone=true&serverTimezone=UTC");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("root");
        comboPooledDataSource.setMaxPoolSize(10);
        this.dataSources = comboPooledDataSource;
    }

    public Connection makeConnection() throws SQLException {
        return dataSources.getConnection();
    }

    void  closeConnection(Connection connection)throws SQLException{
        connection.close();
    }


}
