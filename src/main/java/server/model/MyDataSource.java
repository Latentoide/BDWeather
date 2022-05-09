package server.model;

import com.mysql.cj.jdbc.MysqlDataSource;
import server.properties.MyConfig;

import javax.sql.DataSource;

public class MyDataSource {
    public static DataSource getMyMariaDBDataSource(){
        MysqlDataSource mysqlDataSource = new MysqlDataSource();

        String schema = MyConfig.getInstance().getDBSchema();
        String port = MyConfig.getInstance().getDBPort();
        String host = MyConfig.getInstance().getDBHost();
        String user = MyConfig.getInstance().getUsername();
        String password = MyConfig.getInstance().getPassword();

        mysqlDataSource.setURL("jdbc:mysql://"+ host + ":" + port +"/"+ schema);
        mysqlDataSource.setUser(user);
        mysqlDataSource.setPassword(password);

        return mysqlDataSource;
    }
}
