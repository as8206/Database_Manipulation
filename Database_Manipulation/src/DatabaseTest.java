import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

//Tests and times various database functions
//Andrew Stake

public class DatabaseTest
{
	/**
     * This is the recommended way to activate the JDBC drivers, but is
     * only setup to work with one specific driver.  Setup to work with
     * a MySQL JDBC driver.
     *
     * If the JDBC Jar file is not in your build path this will not work.
     * I have the Jar file posted in D2L.
     * 
     * @return Returns true if it successfully sets up the driver.
     */
    public boolean activateJDBC()
    {
        try
        {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }

        return true;
    }
    
    /**
     * You MUST change these values based on the DB you are assigned to work with.
     */
     public static final String DB_LOCATION = "jdbc:mysql://localhost/TEST_STAKE_NOPK";
     public static final String LOGIN_NAME = "andrew";
     public static final String PASSWORD = "an99Mi32st";   //TODO these are what need to be changed
     
     // Make sure and use the java.sql imports.
     protected static Connection m_dbConn = null;
    
    public static void main(String args[]) throws SQLException
    {
    	System.out.println("Hello");
    	
    	/** 
    	    * Creates a connection to the database that you can then send commands to.
    	    */
    	    m_dbConn = DriverManager.getConnection(DB_LOCATION, LOGIN_NAME, PASSWORD);
    	    

    	   /**
    	    * To get the meta data for the DB.
    	    */
    	    DatabaseMetaData meta = m_dbConn.getMetaData();
    }
}
