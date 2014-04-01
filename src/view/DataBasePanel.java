package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

import model.Person;
import controller.AppController;

public class DataBasePanel extends JPanel
{
	private AppController baseController;
	private JButton createDBButton;
	private JButton dropDBButton;
	private JButton updatePersonButton;
	private JButton selectButton;
	private SpringLayout baseLayout;
	private JButton tableButton;
	private JTextField nameField;
	private JTextField ageField;
	private JTextField deathDateField;
	private JButton insertPersonButton;
	private JButton createPeopleTableButton;

	private JTextArea resultsArea;
	private JScrollPane resultsPane;

	public DataBasePanel(AppController baseController)
	{
		this.baseController = baseController;
		createDBButton = new JButton("Create the Database");
		dropDBButton = new JButton("Become Galactus");
		baseLayout = new SpringLayout();

		tableButton = new JButton("Create the table");

		updatePersonButton = new JButton("update a person in the table");

		insertPersonButton = new JButton("Insert the human into the database");

		createPeopleTableButton = new JButton("Click to create the table for People");
		;

		ageField = new JTextField("age", 5);

		nameField = new JTextField("person name", 20);

		deathDateField = new JTextField("death date", 10);

		resultsArea = new JTextArea(5, 20);

		resultsPane = new JScrollPane(resultsArea);
		
		selectButton = new JButton("Select Person");
		
		setupPanel();
		setupLayout();
		setupListeners();

	}

	private void setupListeners()
	{
		createPeopleTableButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				baseController.getMyDataController().createPeopleTable("graveyard");
			}
		});

		updatePersonButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				 baseController.getMyDataController().updatePersonName(nameField.getText(),
				 deathDateField.getText());
				 baseController.getMyDataController().connectToExternalServer();

				
				
			}
		});
		
		
		selectButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				Vector<Person> temp = baseController.getMyDataController().selectDataFromTable("people");
				readFromVector(temp);
				
			}
		});
		
		

		insertPersonButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				Person currentPerson = new Person();
				currentPerson.setName(nameField.getText());
				currentPerson.setDeathDate(deathDateField.getText());
				if (checkParseInteger(ageField.getText()))

				{
					currentPerson.setAge(Integer.parseInt(ageField.getText()));

				}
				baseController.getGraveyardPersons().add(currentPerson);
				baseController.getMyDataController().insertPersonIntoTable(currentPerson);
			}
		});

		createDBButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{

				baseController.getMyDataController().createDatabase();

			}

		});

		dropDBButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{

				baseController.getMyDataController().dropTheBase();

			}

		});

		tableButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{

				baseController.getMyDataController().createTable("graveyard", "people");

			}

		});

	}
	
	private void clearFields()
	{
		nameField.setText("");
		ageField.setText("");
		deathDateField.setText("");
		
		
	}
	
	private void readFromVector (Vector <Person> currentPeople)
	{
		for(Person currentPerson : currentPeople)
		{
			resultsArea.append(currentPerson.toString() + "\n");
		}
	}

	private Person createPersonFromValues()
	{
		Person tempDeadPerson = null;
		int age = 0;

		if (checkParseInteger(ageField.getText()))
			;
		{
			age = Integer.parseInt(ageField.getText());

		}
		tempDeadPerson = new Person(nameField.getText(), deathDateField.getText());
		tempDeadPerson.setAge(age);
		return tempDeadPerson;
	}

	private boolean checkParseInteger(String current)
	{
		boolean isParseable = false;

		try
		{
			Integer.parseInt(current);
			isParseable = true;
		}
		catch (NumberFormatException currentError)
		{
			JOptionPane.showMessageDialog(null, " Try typing in an integer for the age.");
		}

		return isParseable;
	}

	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.NORTH, selectButton, 0, SpringLayout.NORTH, dropDBButton);
		baseLayout.putConstraint(SpringLayout.WEST, selectButton, 0, SpringLayout.WEST, updatePersonButton);
		baseLayout.putConstraint(SpringLayout.NORTH, updatePersonButton, 0, SpringLayout.NORTH, tableButton);
		baseLayout.putConstraint(SpringLayout.WEST, updatePersonButton, 37, SpringLayout.EAST, tableButton);
		baseLayout.putConstraint(SpringLayout.WEST, ageField, 0, SpringLayout.WEST, createDBButton);
		baseLayout.putConstraint(SpringLayout.NORTH, insertPersonButton, 0, SpringLayout.NORTH, createDBButton);
		baseLayout.putConstraint(SpringLayout.WEST, insertPersonButton, 10, SpringLayout.EAST, createDBButton);
		baseLayout.putConstraint(SpringLayout.WEST, createPeopleTableButton, 0, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, createPeopleTableButton, -6, SpringLayout.NORTH, createDBButton);
		baseLayout.putConstraint(SpringLayout.SOUTH, nameField, -6, SpringLayout.NORTH, createPeopleTableButton);
		baseLayout.putConstraint(SpringLayout.WEST, nameField, 17, SpringLayout.EAST, deathDateField);
		baseLayout.putConstraint(SpringLayout.SOUTH, ageField, 0, SpringLayout.NORTH, deathDateField);
		baseLayout.putConstraint(SpringLayout.WEST, deathDateField, 0, SpringLayout.WEST, createDBButton);
		baseLayout.putConstraint(SpringLayout.SOUTH, deathDateField, -6, SpringLayout.NORTH, createPeopleTableButton);
		baseLayout.putConstraint(SpringLayout.SOUTH, createDBButton, -6, SpringLayout.NORTH, tableButton);
		baseLayout.putConstraint(SpringLayout.WEST, tableButton, 0, SpringLayout.WEST, dropDBButton);
		baseLayout.putConstraint(SpringLayout.SOUTH, tableButton, -6, SpringLayout.NORTH, dropDBButton);
		baseLayout.putConstraint(SpringLayout.WEST, dropDBButton, 0, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.WEST, createDBButton, 0, SpringLayout.WEST, dropDBButton);
		baseLayout.putConstraint(SpringLayout.SOUTH, dropDBButton, -43, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, resultsArea, 8, SpringLayout.EAST, resultsPane);
		baseLayout.putConstraint(SpringLayout.SOUTH, resultsArea, -6, SpringLayout.NORTH, ageField);

	}

	private void setupPanel()
	{
		this.setLayout(baseLayout);
		this.add(createDBButton);
		this.add(dropDBButton);
		this.add(tableButton);
		this.add(updatePersonButton);
		this.add(createPeopleTableButton);
		this.add(ageField);
		this.add(nameField);
		this.add(deathDateField);
		this.add(insertPersonButton);
		this.add(selectButton);
		this.add(resultsPane);

		resultsArea.setWrapStyleWord(true);
		resultsArea.setLineWrap(true);

	}

}
