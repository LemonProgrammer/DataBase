package controller;

import java.sql.*;
import java.util.Vector;

import javax.swing.JOptionPane;

import model.Person;

public class DatabaseController
{
	/**
	 * String that is used for the connection to the database.
	 */
	private String connectionString;
	/**
	 * Connection type that is useful to connect to the server.
	 */
	private Connection DatabaseConnection;

	private AppController baseController;
/**
	 * Constructor for the DatabaseController to instantiate.
	 * 
	 */
	public DatabaseController(AppController baseController)
	{
		this.baseController = baseController;
		connectionString = "jdbc:mysql://localhost/?user=root";
		
		checkDriver();
		setupConnection();
	}

	/**
	 * Checks that the driver for the MySQL is loaded properly.
	 */
	private void checkDriver()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}

		catch (Exception current)
		{
			System.err.println("unable to load the driver");
			System.err.println("Check that the ConnectorJ .jar file is loaded as an external JAR in the build path");
			System.err.println("The original .jar should be in the ~/MySQL/ folder");
			System.exit(1);
		}
	}

	public void createPeopleTable(String database)
	{
		try
		{
			Statement CreatePersonTableStatement = DatabaseConnection.createStatement();
			String createPersonTable = "CREATE TABLE IF NOT EXISTS `" + database + "`.`people`" +
			"(" + 
					"`person_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY," + 
					"`person_name` VARCHAR (50), " + 
					"`death_date` VARCHAR (30), " + 
					"`birth_date` VARCHAR (30), "+ 
					"`is_married` TINYINT (1), " + 
					"`has_children` TINYINT (1), " + 
					"`age` INT" + 
			")" + 
					"ENGINE = INNODB;";
			
			int result = CreatePersonTableStatement.executeUpdate(createPersonTable);
			CreatePersonTableStatement.close();
			JOptionPane.showMessageDialog(baseController.getMyAppFrame(), "Successfully created " + result + "table in the database");
		}
		catch (SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}
	}

	/**
	 * Sets up the connection to access the database server.
	 */
	private void setupConnection()
	{
		try
		{
			DatabaseConnection = DriverManager.getConnection(connectionString);
		}
		catch (SQLException current)
		{
			displaySQLErrors(current);
		}

	}

	/**
	 * Inserts the values and fields of Persons in the database table.
	 * 
	 * @param currentPerson
	 */
	public void insertPersonIntoTable(Person currentPerson)
	{

		try
		{
			Statement insertPersonStatement = DatabaseConnection.createStatement();

			int databaseIsMarried, databasehasChildren;

			if (currentPerson.getHasChildren())
			{
				databasehasChildren = 1;
			}
			else
			{
				databasehasChildren = 0;
			}
			if (currentPerson.getIsMarried())
			{
				databaseIsMarried = 1;
			}
			else
			{
				databaseIsMarried = 0;
			}

			String insertPersonString = "INSERT INTO `graveyard`.`people` " + 
					"(`person_name`, `death_date`, `birth_date`, `is_married`, `has_children`, `age`)" + 
					" VALUES ('" + currentPerson.getName() + "', '" + currentPerson.getDeathDate() + 
					"', '" + currentPerson.getBirthDate() + "', '" + 
					currentPerson.getIsMarried() + "', '" + currentPerson.getHasChildren() + "', '" + 
					currentPerson.getAge() + "');";

			int result = insertPersonStatement.executeUpdate(insertPersonString);
			insertPersonStatement.close();
			JOptionPane.showMessageDialog(null, "Successfully inserted " + result + " rows into the table.");

		}
		catch (SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}
	}

	/**
	 * Displays errors that would encounter if there is no drive of the database
	 * in java.
	 * 
	 * @param currentSQLException
	 */
	public void displaySQLErrors(SQLException currentSQLException)
	{
		JOptionPane.showMessageDialog(null, "SQL Exception: " + currentSQLException.getMessage() + "\n");
		JOptionPane.showMessageDialog(null, "SQL State: " + currentSQLException.getSQLState() + "\n");
		JOptionPane.showMessageDialog(null, "SQL error code: " + currentSQLException.getErrorCode() + "\n");

	}

	/**
	 * Closes the connection to the database server by preventing corruption and
	 * other security reasons.
	 */
	public void closeConnection()
	{
		try
		{
			DatabaseConnection.close();
		}
		catch (SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}
	}

	/**
	 * resets the connection to the database server.
	 */
	private void resetDataBaseConnection()
	{
		closeConnection();
		setupConnection();
	}

	/**
	 * Statements that creates the database to be used.
	 * @param string 
	 */
	public void createDatabase()
	{

		try
		{
			Statement coolStatement = DatabaseConnection.createStatement();
			
			String createSQL = "CREATE DATABASE IF NOT EXISTS `graveyard`";

			int result = coolStatement.executeUpdate("CREATE DATABASE graveyard;");
			coolStatement.close();
		}
		catch (SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}
	}
	
	/**
	 * Creates the database in the server.
	 * @param databaseName
	 */
	public void createDataBase(String databaseName)
	{
		try
		{
			Statement coolStatement = DatabaseConnection.createStatement();
			
			String createSQL = "CREATE DATABASE IF NOT EXISTS `" + databaseName + ";";
			
			int result = coolStatement.executeUpdate("CREATE DATABASE graveyard;");
			coolStatement.close();
		}
		catch (SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}
	}

	/**
	 * Creates a table within the existing database.
	 * 
	 * @param database
	 * @param tableName
	 */
	public void createTable(String database, String tableName)
	{

		String createTable = "CREATE TABLE`" + database + ",`" + tableName + "`";

		int queryIndex = connectionString.indexOf("?");
		String connectionStart = connectionString.substring(0, queryIndex);
		String connectionEnd = connectionString.substring(queryIndex);
		closeConnection();
		setupConnection();
		try
		{
			Statement createDatabaseTablelStatement = DatabaseConnection.createStatement();

			String creatTable = "CREAT TABLE`" + database + "`.`" + tableName + "`" + "(" + "`grave_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY , +" + "`grave_name` VARCHAR ( 20) NOT NULL" + ")" + "ENGINE = INNODB;";

			int result = createDatabaseTablelStatement.executeUpdate(createTable);

			createDatabaseTablelStatement.close();
		}
		catch (SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}

	}

	/**
	 * Drops the database and it's contents.
	 */
	public void dropTheBase()
	{
		resetDataBaseConnection();

		try
		{
			Statement coolStatement = DatabaseConnection.createStatement();

			int result = coolStatement.executeUpdate("DROP DATABASE graveyard;");
			coolStatement.close();
		}
		catch (SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}
	}

	/**
	 * Updates the data of a person to the table.
	 * Can modify data in a field.
	 * @param oldName
	 * @param newName
	 */
	
	public void updatePersonName(String oldName, String newName)
	{
		try
		{
			Statement updateStatement = DatabaseConnection.createStatement();

			String updateString = "UPDATE `graveyard`.`people`" +
					"SET `person_name` = '" + newName +"'" + 
					"WHERE `person_name` = ' " + oldName + 
					"' AND `age` = `0`";

			int result = updateStatement.executeUpdate(updateString);

			JOptionPane.showMessageDialog(baseController.getMyAppFrame(), "Successfully updated " + result + "rows(s).");

			updateStatement.close();
		}
		catch (SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}

	}

	/*
	 * Builds a Java connectionString for a MySQL database with the supplied fields for server path, database, username, and password to access the database.
	 * 
	*/
	public void buildConnectionString(String serverPath, String database, String userName, String password)
	{
		connectionString = "jdbc:mysql://" + serverPath + "/" + database + "?user=" + userName + "&password=" + password;
	}

	/**
	 * Connects to Mr.H's server.
	 */
	public void connectToExternalServer()
	{
		buildConnectionString("10.228.6.204", "graveyard", "ctec", "student");
		setupConnection();
		createDataBase("Fernando");
	}
	
	/**
	 * Selects the data from the person table in the external server.
	 * @param tableName
	 * @return
	 */
	public Vector<Person> selectDataFromTable(String tableName)
	{
		Vector<Person> personVector = new Vector <Person>();
		ResultSet seeDeadPeopleResults;
		String selectQuery = "SELECT person_age,person_name, person_has_children, person_is_married, " +
				"person_birth_date, person_death_date FROM " + tableName + ";";
		
		try 
		{
			PreparedStatement selectStatement = DatabaseConnection.prepareStatement(selectQuery);
			seeDeadPeopleResults = selectStatement.executeQuery();
			
			while(seeDeadPeopleResults.next())
			{
				Person tempPerson = new Person();
				
				int tempAge = seeDeadPeopleResults.getInt(1);
				String tempName = seeDeadPeopleResults.getString(2);
				boolean tempKids = seeDeadPeopleResults.getBoolean(3);
				boolean tempMarried = seeDeadPeopleResults.getBoolean(4);
				String tempBirthDate = seeDeadPeopleResults.getString(5);
				String tempDeathDate = seeDeadPeopleResults.getString(6);
				
				tempPerson.setAge(tempAge);
				tempPerson.setBirthDate(tempBirthDate);
				tempPerson.setDeathDate(tempDeathDate);
				tempPerson.setHasChildren(tempKids);
				tempPerson.setName(tempName);
				tempPerson.setIsMarried(tempMarried);
				
				
				personVector.add(tempPerson);
			}
			
			seeDeadPeopleResults.close();
			selectStatement.close();
		}
		
		catch(SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}
		return personVector;
	}
}
