/* This program is an example used to illustrate how JDBC works.
 ** It uses the JDBC driver for MySQL.
 **
 ** This program was originally written by nikos dimitrakas
 ** on 2007-08-31 for use in the basic database courses at DSV.
 **
 ** There is no error management in this program.
 ** Instead an exception is thrown. Ideally all exceptions
 ** should be caught and managed appropriately. But this
 ** program's goal is only to illustrate the basic JDBC classes.
 **
 ** Last modified by nikos on 2015-10-07
 */

import java.sql.*;

public class DBJDBCM
{

    // DB connection variable
    static protected Connection con;
    // DB access variables
    private String URL = "jdbc:mysql://localhost:8889/lab1";
    private String driver = "com.mysql.jdbc.Driver";
    private String userID = "root";
    private String password = "root";

    // method for establishing a DB connection
    public void connect()
    {
        try
        {
            // register the driver with DriverManager
            Class.forName(driver);
            //create a connection to the database
            con = DriverManager.getConnection(URL, userID, password);
            // Set the auto commit of the connection to false.
            // An explicit commit will be required in order to accept
            // any changes done to the DB through this connection.
            con.setAutoCommit(false);
				//Some logging
				System.out.println("Connected to " + URL + " using "+ driver);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void simpleselect() throws Exception
    {
        // CHANGE: Shows all car brands

        // Local variables
        String query;
        ResultSet rs;
        Statement stmt;

        // Set the SQL statement into the query variable
        query = "SELECT DISTINCT marke FROM bil";

        // Create a statement associated to the connection con.
        // The new statement is placed in the variable stmt.
        stmt = con.createStatement();

        // Execute the SQL statement that is stored in the variable query
        // and store the result in the variable rs.
        rs = stmt.executeQuery(query);

        System.out.println("Resultatet (alla bilmärken): ");

        // Loop through the result set and print the results.
        // The method next() returns false when there are no more rows.
        while (rs.next())
        {
            System.out.print(rs.getString("marke"));
            System.out.println();
        }

        // Close the variable stmt and release all resources bound to it
        // Any ResultSet associated to the Statement will be automatically closed too.
        stmt.close();
    }

    public void parameterizedselect() throws Exception
    {
        // CHANGE: Shows all cars owned by someone in a city provided by user input

        // Local variables
        String query;
        ResultSet rs;
        PreparedStatement stmt;
        String markeparam;

        // Create a Scanner in order to allow the user to provide input.
        java.util.Scanner in = new java.util.Scanner(System.in);

        // This is the old way (Java 1.4 or earlier) for reading user input:
        // Create a BufferedReader in order to allow the user to provide input.
        // java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));

        // Ask the user to specify a value for city.
        System.out.print("Ange en stad: ");
        // Retrieve the value and place it in the variable markeparam.
        markeparam = in.nextLine();

        // Set the SQL statement into the query variable
        //alternative with subquery: query = "SELECT regnr, marke, farg FROM bil WHERE agare IN (SELECT id FROM person WHERE stad = ?)";
        query = "SELECT regnr, marke, farg FROM bil, person WHERE agare = id AND stad = ?";

        // Create a statement associated to the connection and the query.
        // The new statement is placed in the variable stmt.
        stmt = con.prepareStatement(query);

        // Provide the value for the first ? in the SQL statement.
        // The value of the variable markeparam will be sent to the database manager
        // through the variables stmt and con.
        stmt.setString(1, markeparam);

        // Execute the SQL statement that is prepared in the variable stmt
        // and store the result in the variable rs.
        rs = stmt.executeQuery();

        System.out.println("Resultatet (Bilar med ägare i " + markeparam + "):");

        // Loop through the result set and print the results.
        // The method next() returns false when there are no more rows.
        while (rs.next())
        {
            System.out.println(rs.getString("regnr") + " " + rs.getString("marke") + " " + rs.getString("farg"));
        }

        // Close the variable stmt and release all resources bound to it
        // Any ResultSet associated to the Statement will be automatically closed too.
        stmt.close();
    }

    public void update() throws Exception
    {
        // CHANGE: Updates color on car with car identifier

        // Local variables
        String query;
        PreparedStatement stmt;
        String regnrParam;
        String newColorParam;

        // Create a Scanner in order to allow the user to provide input.
        java.util.Scanner in = new java.util.Scanner(System.in);

        // Ask the user to specify a value for förnamn.
        System.out.print("Ange regnr att uppdatera: ");
        // Retrieve the value and place it in the variable regnrParam.
        regnrParam = in.nextLine();
        // Ask the user to specify a value for efternamn.
        System.out.print("Ange nya färgen: ");
        // Retrieve the value and place it in the variable newColorParam.
        newColorParam = in.nextLine();

        // Set the SQL statement into the query variable
        query = "UPDATE bil SET farg = ? WHERE regnr = ?";

        // Create a statement associated to the connection and the query.
        // The new statement is placed in the variable stmt.
        stmt = con.prepareStatement(query);

        // Provide the values for the ?'s in the SQL statement.
        // The value of the variable regnrParam is the first,
        // newColorParam is second and stadparam is third.
        stmt.setString(1, newColorParam);
        stmt.setString(2, regnrParam);

        // Execute the SQL statement that is prepared in the variable stmt
        stmt.executeUpdate();

        // Close the variable stmt and release all resources bound to it
        stmt.close();
    }

    public static void main(String[] argv) throws Exception
    {
        // Create a new object of this class.
        DBJDBCM t = new DBJDBCM();

        // Call methods on the object t.
		  System.out.println("-------- connect() ---------");
        t.connect();
		  System.out.println("-------- simpleselect() ---------");
        t.simpleselect();
		  System.out.println("-------- parameterizedselect() ---------");
        t.parameterizedselect();
		  System.out.println("-------- update() ---------");
        t.update();

        // Commit the changes made to the database through this connection.
        con.commit();
        // Close the connection.
        con.close();
    }
}
