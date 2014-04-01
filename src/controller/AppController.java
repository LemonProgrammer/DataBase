package controller;

import java.util.ArrayList;

import model.GraveMarker;
import model.Person;
import view.DataBaseFrame;

public class AppController
{

	private DatabaseController myDataController;
	private DataBaseFrame myAppFrame;
	private ArrayList<GraveMarker> graveMarkerList;
	private ArrayList<Person> graveyardPersons;

	/**
	 * Constructor for the AppController.
	 */
	public AppController()
	{
		myDataController = new DatabaseController(this);

		graveyardPersons = new ArrayList<Person>();
		graveMarkerList = new ArrayList<GraveMarker>();

	}

	public DataBaseFrame getMyAppFrame()
	{
		return getMyAppFrame();
	}

	public ArrayList<GraveMarker> getGraveMarkerList()
	{
		return graveMarkerList;
	}

	public ArrayList<Person> getGraveyardPersons()
	{
		return graveyardPersons;
	}

	/**
	 * The start method that runs the program.
	 */
	public void start()
	{

		myAppFrame = new DataBaseFrame(this);
	}

	/**
	 * Getter for the DataController and returns the DataController.
	 * 
	 * @return
	 */
	public DatabaseController getMyDataController()
	{

		return myDataController;
	}

	/**
	 * Adds a person to the graveyard.
	 * 
	 * @param currentDeadPerson
	 */
	public void addDeadPerson(Person currentDeadPerson)
	{
		graveyardPersons.add(currentDeadPerson);
	}

}
