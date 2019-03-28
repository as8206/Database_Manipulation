import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

//Tests and times various database functions
//Andrew Stake

public class DatabaseTest
{
	/**
     * You MUST change these values based on the DB you are assigned to work with.
     */
     public static final String DB_LOCATION = "jdbc:mysql://localhost/test";
     public static final String LOGIN_NAME = "andrew";
     public static final String PASSWORD = "an99Mi32st";   //TODO these are what need to be changed
     
     // Make sure and use the java.sql imports.
     protected static Connection m_dbConn = null;
	
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
      * To execute an SQL statement that is not a SELECT statement.
      */
      public void testNonSelectStatements() throws Exception
      {
          // Using a PreparedStatement to insert a value (best option when providing values
          // from variables).
          // Use place holders '?' to mark where I am going to provide the data.
    	  
          String insertData = new String("INSERT INTO TEST_STAKE_NOPK (Id, num2, short, extended, exact) VALUES (?,?,?,?,?)");
          PreparedStatement stmt2 = m_dbConn.prepareStatement(insertData);
          stmt2.setString(1, "1");
          stmt2.setString(2, "2");
          stmt2.setString(3, "'hello'");
          stmt2.setString(4, "'This is a longer string'");
          stmt2.setString(5, '55.55');
          
          // When I need to set a primitive type as null.
          //stmt2.setNull(2, java.sql.Types.INTEGER);
          int rowsAdded = stmt2.executeUpdate();
          if (rowsAdded == 1)
          {
          	System.out.println("Added");
          }
      }
     
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
