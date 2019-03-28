import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//Tests and times various database functions
//Andrew Stake

//Table Creation statement: CREATE TABLE TEST_STAKE_NOPK (Id INT, num2 INT, short CHAR(10), extended VARCHAR(30), exact DOUBLE);

public class DatabaseTest
{
	/**
     * You MUST change these values based on the DB you are assigned to work with.
     */
     public static final String DB_LOCATION = "jdbc:mysql://localhost/test";
     public static final String LOGIN_NAME = "andrew";
     public static final String PASSWORD = "an99Mi32st";   //TODO change to correct Database
     
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
    
      //Inserts rows into the no pk table
      public static long testInsertionStatementsNoPK() throws Exception
      {
          // Using a PreparedStatement to insert a value (best option when providing values
          // from variables).
          // Use place holders '?' to mark where I am going to provide the data.          
          // When I need to set a primitive type as null.
          //stmt2.setNull(2, java.sql.Types.INTEGER);
    	  
          int rowAdded;
          String insertString;
          PreparedStatement insertion;
          String condition1, condition2, condition3, condition4, condition5;
          
          long startTime = System.currentTimeMillis();
          
          for(int i = 0; i < 5000; i++) //TODO set to 500000
          {
        	  insertString = new String("INSERT INTO TEST_STAKE_NOPK (Id, num2, short, extended, exact) VALUES (?,?,?,?,?)");
        	  insertion = m_dbConn.prepareStatement(insertString);
        	  condition1 = Integer.toString(i+1);
        	  condition2 = Integer.toString(i);
        	  condition3 = "Check: " + i/1000;
        	  condition4 = "Extended Length " + i/10000;
        	  condition5 = Double.toString(i/1000 + (i/(double)10000 - i/10000));
        	  insertion.setString(1, condition1);
        	  insertion.setString(2, condition2);
        	  insertion.setString(3, condition3);
        	  insertion.setString(4, condition4);
        	  insertion.setString(5, condition5);
        	  
        	  insertion.executeUpdate();
          }
          
          long endTime = System.currentTimeMillis();
          long totalTime = endTime - startTime;
          return totalTime;
      }
      
      //Inserts rows into the pk table
      public static long testInsertionStatementsPK() throws Exception
      {
          // Using a PreparedStatement to insert a value (best option when providing values
          // from variables).
          // Use place holders '?' to mark where I am going to provide the data.          
          // When I need to set a primitive type as null.
          //stmt2.setNull(2, java.sql.Types.INTEGER);
    	  
          int rowAdded;
          String insertString;
          PreparedStatement insertion;
          String condition1, condition2, condition3, condition4, condition5;
          
          long startTime = System.currentTimeMillis();
          
          for(int i = 0; i < 5000; i++) //TODO set to 500000
          {
        	  insertString = new String("INSERT INTO TEST_STAKE_PK (Id, num2, short, extended, exact) VALUES (?,?,?,?,?)");
        	  insertion = m_dbConn.prepareStatement(insertString);
        	  condition1 = Integer.toString(i+1);
        	  condition2 = Integer.toString(i);
        	  condition3 = "Check: " + i/1000;
        	  condition4 = "Extended Length " + i/10000;
        	  condition5 = Double.toString(i/1000 + (i/(double)10000 - i/10000));
        	  insertion.setString(1, condition1);
        	  insertion.setString(2, condition2);
        	  insertion.setString(3, condition3);
        	  insertion.setString(4, condition4);
        	  insertion.setString(5, condition5);
        	  
        	  insertion.executeUpdate();
          }
          
          long endTime = System.currentTimeMillis();
          long totalTime = endTime - startTime;
          return totalTime;
      }
     
    public static void main(String args[]) throws Exception
    {
    	double[] insertNoPK = new double[10];
    	double[] insertPK = new double[10];
    	long time;
    	
    	/** 
    	    * Creates a connection to the database that you can then send commands to.
    	    */
    	    m_dbConn = DriverManager.getConnection(DB_LOCATION, LOGIN_NAME, PASSWORD);
    	    

    	   /**
    	    * To get the meta data for the DB.
    	    */
    	    DatabaseMetaData meta = m_dbConn.getMetaData();
    	    
//    	    /**
//    	     * Performs the insertion test on the no pk table 10 times
//    	     */
//    	    for(int i = 0; i < 10; i++)
//    	    {
//    	    	remakeNoPKTable();
//    	    	time = testInsertionStatementsNoPK();
//    	    	insertNoPK[i] = time / (double)1000;
//    	    	System.out.println("No PK insertion " + (i+1) + " complete.");
//    	    }
//    	    
//    	    for(int i = 0; i < 10; i++)
//    	    {
//    	    	System.out.println("Time for no PK insertion " + (i+1) + ": " + insertNoPK[i]);
//    	    }
//    	    
//    	    /**
//    	     * Performs the insertion test on the pk table 10 times
//    	     */
//    	    for(int i = 0; i < 10; i++)
//    	    {
//    	    	remakePKTable();
//    	    	time = testInsertionStatementsPK();
//    	    	insertPK[i] = time / (double)1000;
//    	    	System.out.println("PK insertion " + (i+1) + " complete.");
//    	    }
//    	    
//    	    for(int i = 0; i < 10; i++)
//    	    {
//    	    	System.out.println("Time for PK insertion " + (i+1) + ": " + insertPK[i]);
//    	    }
    	    
    	    prepareDatabaseCache();
    }

    private static void prepareDatabaseCache() throws Exception
    {
    	String select = "SELECT * FROM TEST_STAKE_PK WHERE Id = 4806";
        PreparedStatement statement = m_dbConn.prepareStatement(select);
        System.out.println("Time for select from Id (col 1) : " + timeSingleStatement(statement));	
	}

	//removes the no pk table if it exists and then recreates it as an empty table again
	private static void remakeNoPKTable() throws Exception
	{
		String string = "DROP TABLE IF EXISTS TEST_STAKE_NOPK;";
        PreparedStatement statement = m_dbConn.prepareStatement(string);
        statement.executeUpdate();
        string = "CREATE TABLE TEST_STAKE_NOPK (Id INT, num2 INT, short CHAR(10), extended VARCHAR(30), exact DOUBLE);";
        statement = m_dbConn.prepareStatement(string);
        statement.executeUpdate();
	}
	
    //removes the pk table if it exists and then recreates it as an empty table again	
	private static void remakePKTable() throws Exception
	{
		String string = "DROP TABLE IF EXISTS TEST_STAKE_PK;";
        PreparedStatement statement = m_dbConn.prepareStatement(string);
        statement.executeUpdate();
        string = "CREATE TABLE TEST_STAKE_PK (Id INT, num2 INT, short CHAR(10), extended VARCHAR(30), exact DOUBLE, PRIMARY KEY(Id));";
        statement = m_dbConn.prepareStatement(string);
        statement.executeUpdate();
	}
	
	private static double timeSingleStatement(PreparedStatement statement) throws Exception
	{
		long starttime = System.currentTimeMillis();
		statement.executeQuery();
		long endtime = System.currentTimeMillis();
		return (endtime-starttime) / (double) 1000;
	}
}
