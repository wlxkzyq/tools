package tools.mygenerator.internal.db;

import static tools.mygenerator.internal.util.StringUtility.stringHasValue;
import static tools.mygenerator.internal.util.messages.Messages.getString;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

import tools.mygenerator.config.JDBCConnectionConfiguration;



/** 
* 类说明 
* @author 作者 : zyq
* 创建时间：2016年12月13日 下午8:56:45 
* @version 
*/
public class ConnectionFactory {

    private static ConnectionFactory instance = new ConnectionFactory();

    public static ConnectionFactory getInstance() {
        return instance;
    }

    /**
	 *  
	 */
    private ConnectionFactory() {
        super();
    }

    public Connection getConnection(JDBCConnectionConfiguration config)
            throws SQLException {
        Driver driver = getDriver(config);

        Properties props = new Properties();

        if (stringHasValue(config.getUserId())) {
            props.setProperty("user", config.getUserId()); //$NON-NLS-1$
        }

        if (stringHasValue(config.getPassword())) {
            props.setProperty("password", config.getPassword()); //$NON-NLS-1$
        }

        //props.putAll(config.getProperties());

        Connection conn = driver.connect(config.getConnectionURL(), props);

        if (conn == null) {
            throw new SQLException(getString("RuntimeError.7")); //$NON-NLS-1$
        }

        return conn;
    }

    private Driver getDriver(JDBCConnectionConfiguration connectionInformation) {
        String driverClass = connectionInformation.getDriverClass();
        Driver driver;

        try {
            Class<?> clazz = Class.forName(driverClass);
            driver = (Driver) clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(getString("RuntimeError.8"), e); //$NON-NLS-1$
        }

        return driver;
    }
    
    public static void main(String[] args) throws SQLException {
		JDBCConnectionConfiguration c=new JDBCConnectionConfiguration();
		c.setConnectionURL("jdbc:mysql://120.25.220.63:3306/yibaodb");
		c.setDriverClass("com.mysql.jdbc.Driver");
		c.setPassword("life12345");
		c.setUserId("app_test");
		Driver d=new ConnectionFactory().getDriver(c);
		Connection cc=new ConnectionFactory().getConnection(c);
		System.out.println(cc);
	}
}
