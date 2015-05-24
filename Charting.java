//Charting.java
//Extra Credit
//Marc-Eli Faldas

/**
 *
 * This program is a simple, online charting program for nurses.
 * The program accepts input from the nurse and outputs them to
 * text files. 
 *
 *
 * @author	Marc-Eli Faldas
 * @version	Last modified_5/7/14
 *
 */


import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Charting implements ActionListener
{
	private ArrayList<Patient> patientList;
	private Patient[] patientArray;


	//Login Frame
	private JFrame		loginFrame;      
	private JTextField	usernameField; 
	private JPasswordField	passwordField; //Is a passwordfield to block what the user types
	private JButton		loginButton;
	private JLabel		loginMTitle;
	private JLabel		loginSTitle;
	private String		nurseName;
	private String		nurseTitle;

	//Option Frame
	private JFrame		optionFrame;
	private JComboBox	currentPatients;
	private JButton		admitButton;
	private JButton		dischargeButton;
	private JButton		selectButton;
	private JButton		logoutButton;
	private String		selectedPatient;
	private Patient		patientToUpdate;

	//Admit Frame
	private JFrame		admitFrame;
	private JTextField	patientName;
	private JComboBox	month;
	private JComboBox	day;
	private JComboBox	year;
	private JTextArea	admitReason;
	private JButton		admitPatientButton;
	private JButton		cancelButtonA;

	//Discharge Frame
	private JFrame		dischargeFrame;
	private JComboBox	dischargePatients;
	private JTextArea	dischargeReason;
	private JButton		dischargePatientButton;
	private JButton		cancelButtonD;

	//Status Frame
	private JFrame		statusFrame;
	private JComboBox	hour;
	private JComboBox	minute;
	private JTextField	bloodPressureS;
	private JTextField	bloodPressureD;
	private JTextArea	commentBox;
	private JButton		updateButton;
	private JButton		cancelButtonS;

	public Charting()
	{
		/*
		    The following three patients:
		      -patientOne, Marc-Eli faldas
		      -patientTwo, Beyonce Knowles
		      -patientThree, Shakira

		    are a part of the Charting Gui.  They will automatically show up 
		    in the Charting Gui. 
		*/

		Patient defaultPatient = 
		new Patient("[Select a Patient]", "", "");

		Patient patient1 = 
		new Patient("Marc-Eli Faldas",
			    "May 3, 1995",
			    "Inflamed Appendix"
			   );
		patient1.createFile();

		patient1.setCurrentStatus("03:05", "120", "60", "NurseJackie", "Currently in bed.");

		Patient patient2 = 
		new Patient("Beyonce Knowles",
			    "September 4, 1981",
			    "Tangled hair to fan"
			   );
		patient2.createFile();
		
		patient2.setCurrentStatus("15:16", "123", "40", "NurseElinor", "Going crazy!");

		Patient patient3 =
		new Patient("Shakira",
			    "February 2, 1977",
			    "Broken hips"
			   );
		patient3.createFile();

		patient3.setCurrentStatus("17:18", "23","15", "NurseElinor", 
		"Trying to show her hips don't life.");

		patientList = new ArrayList<Patient>();
		patientList.add(defaultPatient); patientList.add(patient1); 
		patientList.add(patient2); patientList.add(patient3);

		patientArray = new Patient[ patientList.size() ];
		patientList.toArray(patientArray);

		//LOGIN FRAME

		loginFrame    = new JFrame();
		loginFrame.setResizable(false);
		
		usernameField = new JTextField();
		usernameField.setPreferredSize ( new Dimension(1, 30) );
		
		passwordField = new JPasswordField();
		passwordField.setPreferredSize ( new Dimension(1, 30) );

		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.setSize( new Dimension(350 , 180 ) );

		String hospitalTitle = "Hospital Charting";
		loginMTitle = new JLabel ( hospitalTitle, JLabel.CENTER );
		String instructions = "Input is case sensitive";
		loginSTitle = new JLabel( instructions, JLabel.CENTER );

		JPanel north = new JPanel( new GridLayout( 3,1) );
		north.add( loginMTitle );	
		north.add( loginSTitle );	
		north.add(new JLabel("") );

		JPanel center = new JPanel( new BorderLayout() );

		JPanel centerWest = new JPanel( new GridLayout(2,1) );
		centerWest.add( new JLabel("    Username:  ") );
		centerWest.add( new JLabel("    Password:  ") );

		JPanel centerCenter = new JPanel( new GridLayout(2,1) );
		centerCenter.add( usernameField );
		centerCenter.add( passwordField );
		
		center.add( centerCenter, BorderLayout.CENTER );
		center.add( centerWest, BorderLayout.WEST );
		
		JPanel south = new JPanel();
		loginButton  = new JButton("Login");
		loginButton.addActionListener(Charting.this);
		south.add( loginButton );

		loginFrame.add( north,  BorderLayout.NORTH  );
		loginFrame.add( center, BorderLayout.CENTER );
		loginFrame.add( south,  BorderLayout.SOUTH  ); 

		loginFrame.setVisible(true);

		//OPTION FRAME

		optionFrame      = new JFrame();
		optionFrame.setSize( new Dimension(400, 200) );
		optionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		optionFrame.setResizable(false);

		JPanel optionPanel    = new JPanel();

		JPanel northPanelO     = new JPanel( new GridLayout(2, 1) );

		JLabel optionMTitle = new JLabel("CHOOSE AN OPTION", JLabel.CENTER);
		String instructionsForOption = " 'Get Statuses' to get all current status" ;
		JLabel instructionsOption = new JLabel(instructionsForOption, JLabel.CENTER);

		northPanelO.add( optionMTitle );
		northPanelO.add( instructionsOption );

		JPanel centerPanelO = new JPanel( new GridLayout (2, 1) );

		admitButton	 = new JButton("Admit Patient");
		admitButton.addActionListener(Charting.this);
		dischargeButton  = new JButton("Discharge Patient");
		dischargeButton.addActionListener(Charting.this);
		
		centerPanelO.add(admitButton);
		centerPanelO.add(dischargeButton);

		JPanel southPanelO  = new JPanel( new GridLayout( 2, 1) );

		JPanel southPanelOF = new JPanel( new GridLayout( 1, 2) );

		currentPatients  = new JComboBox(patientArray);
		currentPatients.addActionListener(Charting.this);
		selectButton = new JButton("Get Statuses");
		selectButton.addActionListener(Charting.this);

		southPanelOF.add(currentPatients);
		southPanelOF.add(selectButton);

		JPanel southPanelOS = new JPanel();
		logoutButton = new JButton("Logout");
		logoutButton.addActionListener(Charting.this);
		southPanelOS.add(logoutButton);
		
		southPanelO.add(southPanelOF);
		southPanelO.add(southPanelOS);

		optionPanel.add( northPanelO, BorderLayout.NORTH );
		optionPanel.add( centerPanelO, BorderLayout.CENTER );
		optionPanel.add( southPanelO, BorderLayout.SOUTH );

		optionFrame.add(optionPanel);

		optionFrame.setVisible(false);

		//ADMIT FRAME
		
		final String[] months = 
		{"January", "February", "March",     "April"  , "May" ,    "June",
		 "July",    "August",   "September", "October", "November", "December" };


		ArrayList<String> listYears = new ArrayList<String>();
		int i = 2014;
		
		while ( i != 1899 )
		{
			String temp = "" + i;
			listYears.add(temp);
			i--;
		}

		String[] years = new String[ listYears.size() ];
		listYears.toArray(years);

		ArrayList<String> listDays = new ArrayList<String>();
		int j = 1;

		while ( j != 32 )
		{
			String temp = "" + j;
			listDays.add(temp);
			j++;
		}
		
		String[] days = new String[ listDays.size() ];
		listDays.toArray(days);

		admitFrame = new JFrame();
		admitFrame.setSize( new Dimension(400, 400) );
		admitFrame.setResizable(false);
		admitFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel northA  = new JPanel( new GridLayout(3,1) );

		JPanel northAN = new JPanel( new GridLayout(2,1) );
		

		JPanel patientNameP  = new JPanel( new GridLayout(1,2) );
		patientName = new JTextField();
		patientNameP.add( new JLabel("PATIENT NAME:") );
		patientNameP.add( patientName );

		northAN.add( patientNameP );
		northAN.add( new JLabel("DAY OF BIRTH", JLabel.CENTER) );
		northA.add( northAN );

		JPanel northAC = new JPanel( new GridLayout(2,3) );

		northAC.add( new JLabel("Month", JLabel.CENTER) );
		northAC.add( new JLabel("Day", JLabel.CENTER) );
		northAC.add( new JLabel("Year", JLabel.CENTER) );
		
		month  = new JComboBox(months);
		month.addActionListener(Charting.this);
		day    = new JComboBox(days);
		day.addActionListener(Charting.this);
		year   = new JComboBox(years);
		year.addActionListener(Charting.this);

		northAC.add( month );
		northAC.add( day );
		northAC.add( year );

		northA.add( BorderLayout.CENTER , northAC );

		northA.add
		( BorderLayout.SOUTH, new JLabel("REASON FOR ADMISSION", JLabel.CENTER) );

		admitReason = new JTextArea();
		
		JPanel southA = new JPanel( new GridLayout(1, 2) );
		admitButton = new JButton("Admit");
		admitButton.addActionListener(Charting.this);
		cancelButtonA = new JButton("Cancel");
		cancelButtonA.addActionListener(Charting.this);
		southA.add( admitButton );
		southA.add( cancelButtonA );
	
		admitFrame.add( BorderLayout.NORTH,  northA );
		admitFrame.add( BorderLayout.CENTER, admitReason );
		admitFrame.add( BorderLayout.SOUTH,  southA );

		admitFrame.setVisible(false);

		//DISCHARGE FRAME

		dischargeFrame  = new JFrame();
		dischargeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dischargeFrame.setSize( new Dimension(400, 400) );
		dischargeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel northD = new JPanel( new GridLayout(2,1) );
		
		dischargePatients = new JComboBox(patientArray);
		dischargePatients.addActionListener(Charting.this);
		JPanel northDF = new JPanel( new GridLayout(1,2) );
		northDF.add(new JLabel("PATIENT TO DISCHARGE") );
		northDF.add(dischargePatients);

		northD.add( northDF );
		northD.add( new JLabel("REASON FOR DISCHARGE", JLabel.CENTER) );

		dischargeReason = new JTextArea();

		dischargeButton = new JButton("Discharge");
		dischargeButton.addActionListener(Charting.this);
		cancelButtonD = new JButton("Cancel");
		cancelButtonD.addActionListener(Charting.this);
		JPanel southD = new JPanel( new GridLayout(1,2) );
		southD.add( dischargeButton );
		southD.add( cancelButtonD );
		
		dischargeFrame.add( BorderLayout.NORTH, northD );
		dischargeFrame.add( BorderLayout.CENTER, dischargeReason);
		dischargeFrame.add( BorderLayout.SOUTH, southD );	
		
		dischargeFrame.setVisible(false);

		//STATUS FRAME

		statusFrame = new JFrame();
		statusFrame.setSize( new Dimension(400, 400) );
		statusFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		statusFrame.setResizable(true);		
	

		ArrayList<String> listHours = new ArrayList<String>();
		listHours.add("00"); listHours.add("01"); listHours.add("02");
		listHours.add("03"); listHours.add("04"); listHours.add("05");
		listHours.add("06"); listHours.add("07"); listHours.add("08");
		listHours.add("09"); 
		
		int k = 10;		
		while ( k != 25 )
		{
			String temp = "" + k;
			listHours.add(temp);
			k++;
		}

		String[] hours = new String[ listHours.size() ];
		listHours.toArray(hours);

		ArrayList<String> listMinutes = new ArrayList<String>();
		listMinutes.add("00"); listMinutes.add("01"); listMinutes.add("02");
		listMinutes.add("03"); listMinutes.add("04"); listMinutes.add("05"); 
		listMinutes.add("06"); listMinutes.add("07"); listMinutes.add("08"); 
		listMinutes.add("09"); 
		
		int l = 10;	
	
		while ( l != 60 )
		{
			String temp = "" + l;
			listMinutes.add(temp);
			l++;
		}

		String[] minutes = new String[ listMinutes.size() ];
		listMinutes.toArray(minutes);

		JPanel northS = new JPanel( new GridLayout(5, 1) );

		JPanel northSS = new JPanel( new GridLayout(1, 4) );
		hour = new JComboBox( hours );
		hour.addActionListener(Charting.this);
		minute = new JComboBox( minutes );
		minute.addActionListener(Charting.this);
		northSS.add( new JLabel("Hour:", JLabel.CENTER) );
		northSS.add( hour );
		northSS.add( new JLabel("Minute:", JLabel.CENTER) );
		northSS.add( minute );

		JPanel northSF = new JPanel( new GridLayout( 2, 1) );
		
		JPanel bPS = new JPanel( new GridLayout(1,3) );
		bloodPressureS = new JTextField();
		bPS.add( new JLabel("Systolic:") );
		bPS.add( bloodPressureS );
		bPS.add( new JLabel("mmHG") );

		JPanel bPD = new JPanel( new GridLayout(1,3) );	
		bloodPressureD = new JTextField();
		bPD.add( new JLabel("Diastolic:") );
		bPD.add( bloodPressureD );
		bPD.add( new JLabel("mmHG") );

		northSF.add( bPS );
		northSF.add( bPD );
		
		northS.add( new JLabel("CURRENT TIME", JLabel.CENTER) );
		northS.add( northSS );
		northS.add( new JLabel("CURRENT BLOOD PRESSURE", JLabel.CENTER) );
		northS.add( northSF );
		northS.add( new JLabel("NURSE COMMENTS", JLabel.CENTER ) );


		commentBox = new JTextArea();
		
		JPanel southS = new JPanel( new GridLayout(1,2) );
		updateButton = new JButton("Update");
		updateButton.addActionListener(Charting.this);
		cancelButtonS = new JButton("Cancel");
		cancelButtonS.addActionListener(Charting.this);
		southS.add( updateButton );
		southS.add( cancelButtonS );

		statusFrame.add( BorderLayout.NORTH, northS );
		statusFrame.add( BorderLayout.CENTER, commentBox );
		statusFrame.add( BorderLayout.SOUTH, southS );
		
		statusFrame.setVisible(false);	
	}

	public void actionPerformed (ActionEvent ae)
	{
		String choice = "";
	
		//If a button was chosen;
		if ( ae.getSource() instanceof JButton )
		{ 
			if ( ae.getSource() == cancelButtonA )
			{ choice = "cancellation"; }

			else if ( ae.getSource() == cancelButtonD )
			{ choice = "cancellation"; }

			else if ( ae.getSource() == cancelButtonS )
			{ choice = "cancellation"; }

			else
			{
				JButton source = (JButton)ae.getSource();
				choice = source.getText();
			}
		}

		//If a patient was chosen from the currentPatients JComboBox
		else if ( ae.getSource() == currentPatients )
		{ choice = "Selection"; }

		if ( choice.equals("Login") )
		{ 
			String username = usernameField.getText();
			String passwordString = "";			

			char[] password = passwordField.getPassword();

			//Changes the password object from the passwordField
			// to a string.
			for ( int i = 0; i < password.length; i++)
			{ passwordString += password[i]; }

			boolean loginOrNot = checkLogin(username, passwordString);

			if (loginOrNot) 
			{ 
				JOptionPane.showMessageDialog(null, "Login Succesful!");
				loginFrame.setVisible(false);
				optionFrame.setTitle(nurseTitle);
				optionFrame.setVisible(true);
				usernameField.setText("");
				passwordField.setText("");
			}

			else 
			{
				JOptionPane.showMessageDialog(null,
				"Login was not succesful.  Please try again.");
			}
		}

		//Changes frames: option to admit
		if ( choice.equals("Admit Patient") )
		{	
			optionFrame.setVisible(false);
			admitFrame.setTitle(nurseTitle);
			admitFrame.setVisible(true);
		}

		//Changes frames: option to discharge
		if ( choice.equals("Discharge Patient") )
		{
			optionFrame.setVisible(false);
			dischargeFrame.setTitle(nurseTitle);
			dischargeFrame.setVisible(true);
		}

		//Admits a new patient
		if ( choice.equals("Admit") )
		{

			if ( admitAllFields() )
			{

			String name  = patientName.getText();

			String patientMonth = month.getSelectedItem().toString();
			String patientDay   = day.getSelectedItem().toString();
			String patientYear  = year.getSelectedItem().toString();
			String DofB         = patientMonth + " " + patientDay + ", " + patientYear;

			String admittanceR  = admitReason.getText();

			//Creation of a new Patient Object
			Patient addOn = new Patient( name, DofB, admittanceR);

			//Adds the Patient to the current list of patients
			patientList.add( addOn );
		
			//Adds the new Patient to the JComboBoxes
			currentPatients.addItem ( addOn );
			dischargePatients.addItem (addOn );

			//Creates a text file about this Patient
			addOn.createFile();

			continueOrLogout();

			//Clears whatever was written on this frame
			admitReason.setText("");
			patientName.setText("");
			month.setSelectedIndex( 0 );
			day.setSelectedIndex( 0 );
			year.setSelectedIndex( 0 );

			}
		}

		if ( choice.equals("Discharge") )
		{

			//ref is to get what has been selected
			Object ref = dischargePatients.getSelectedItem();
			String selectedPatient = ref.toString();
			String dR = dischargeReason.getText();

			if ( selectedPatient.equals("[Select a Patient]") ) 
			{ JOptionPane.showMessageDialog(null, "Please select a patient."); }
			
			else if ( dR.equals("") )
			{ JOptionPane.showMessageDialog(null, "Please input a reason for discharge."); }

			else 
			{
				int i = 0;
				boolean same = false;	

				while ( same == false )
				{
					Patient temp = patientList.get(i);
					String name = temp.getName();
	
					//Compares the strings
					// If same, the temp will choose that Patient Object
					if ( selectedPatient.equals(name) )
					{ 
						temp.dischargeEntry( dischargeReason.getText() );
						same = true; 
					}
					else { i++; }
				}

				//Removes that patient from the comboboxes
				patientList.remove(i);
				currentPatients.removeItemAt(i);
				dischargePatients.removeItemAt(i);

				continueOrLogout();
			
				dischargePatients.setSelectedIndex(0);
				dischargeReason.setText("");
			}
		}

		if ( choice.equals("Selection") )
		{
			String patient = currentPatients.getSelectedItem().toString();
			String nurseTitleOf = nurseTitle + " of Patient " + patient;

			if ( patient.equals("[Select a Patient]") )
			{}

			else 
			{
				selectedPatient =
				currentPatients.getSelectedItem().toString();
	
				boolean isPatient = false;
				int i = 0;

				while ( !isPatient)
				{
					String temp = patientList.get(i).toString();

					//Checks if the names are equal
					if ( temp.equals(selectedPatient) )
					{
						//Points patientToUpdate to that Patient
						patientToUpdate = patientList.get(i);
						isPatient = true;
					}
					
					i++;
				}

				optionFrame.setVisible(false);
				currentPatients.setSelectedIndex( 0 );
				statusFrame.setTitle(nurseTitleOf);
				statusFrame.setVisible(true);
			}
		}

		if ( choice.equals("Update") )
		{

			//Checks if all status fields are properly filled in
			if ( statusAllFields() )
			{

			String h = hour.getSelectedItem().toString();
			String m = minute.getSelectedItem().toString();
			String t = h + ":" + m;
			String s = bloodPressureS.getText();
			String d = bloodPressureD.getText();
			String n = nurseName;
			String c = commentBox.getText();

			patientToUpdate.setCurrentStatus( t, s, d, n, c );

			continueOrLogout();

			hour.setSelectedIndex( 0 );
			minute.setSelectedIndex( 0 );
			bloodPressureS.setText("");
			bloodPressureD.setText("");
			commentBox.setText("");

			}
		}

		//Gets the last entry of all the patients and
		// puts them to a text file
		if ( choice.equals("Get Statuses") )
		{
			try 
			{
				PrintWriter output = 
				new PrintWriter( new File("statusOfAll.txt") );


				for( int i = 1; i < patientList.size(); i++ )
				{
					Patient temp = patientList.get(i);
					String information = temp.getCurrentStatus();
					String tempName = temp.toString();
	
					output.println("");
					output.println("Patient: " + tempName);
					output.println( information );	
					output.println("");
					output.println("*****************************");
					output.println("");	
				}

				output.flush();

				JOptionPane.showMessageDialog(null,
				"Done! The text file is in the directory.");
			}
			catch (FileNotFoundException fnf) {}
		}

		//Reverts the program back to the optionFrame if they 
		// don't want to admit, discharge or update
		if ( choice.equals("cancellation") )
		{ cancelOptions(); }

		if ( choice.equals("Logout") )
		{
			optionFrame.setVisible(false);
			loginFrame.setVisible(true);
		}
	}

	/**
	 *
	 *  Asks the user if they want to logout or continue. 
	 *   If they press the YES button, they will be taken to the loginFrame.
	 *   If they press the NO  button, they will be taken to the optionFrame.
	 *   If they press the CANCEL button, they will stay on the current frame,
	 *    but they all the data that was inputted into that frame will be erased.
	 *
	 */
	public void continueOrLogout()
	{
		//If they are on the admitFrame.
		if ( admitFrame.isVisible() )
		{
			int choice = JOptionPane.showConfirmDialog
			(null, "Admission completed!  Would you like to logout?");

			if ( choice == JOptionPane.YES_OPTION )
			{
				admitFrame.setVisible(false);
				loginFrame.setVisible(true);
			}

			else if ( choice == JOptionPane.NO_OPTION )
			{
				admitFrame.setVisible(false);
				optionFrame.setVisible(true);
			}
		}

		//If they are on the dischargeFrame.
		if ( dischargeFrame.isVisible() )
		{
			int choice = JOptionPane.showConfirmDialog
			(null, "Discharge completed!  Would you like to logout?");

			if ( choice == JOptionPane.YES_OPTION )
			{
				dischargeFrame.setVisible(false);
				loginFrame.setVisible(true);
			}

			else if ( choice == JOptionPane.NO_OPTION )
			{
				dischargeFrame.setVisible(false);
				optionFrame.setVisible(true);
			}
		}

		//If they are on the statusFrame.
		if ( statusFrame.isVisible() )
		{
			int choice = JOptionPane.showConfirmDialog
			(null, "Update completed!  Would you like to logout?");

			if ( choice == JOptionPane.YES_OPTION )
			{
				statusFrame.setVisible(false);
				loginFrame.setVisible(true);
			}

			else if ( choice == JOptionPane.NO_OPTION )
			{
				statusFrame.setVisible(false);
				optionFrame.setVisible(true);
			}
		}
	}

	/**
	 *
	 *  Checks if the user's credentials to login are valid.  The
	 *  program takes the username and password and checks if they are
	 *  on the file with nurses' usernames and passwords.
	 *
	 *  @param	String username		The username from the
	 *					loginFrame.
	 *
	 *  @param	String password		The password from the 
	 * 					loginFrame.
 	 *
	 *  @return	boolean true		If the username and password
	 *					are valid.
	 *
	 * @return	boolean false		If the username and/or password
	 *					is/are not valid.
	 *
	 */
	public boolean checkLogin(String username, String password)
	{
		String nurse = username + " " + password;
		
		try
		{
			Scanner check = new Scanner( new File("Nurse.txt") );
	
			while ( check.hasNextLine() )
			{
				String nurseOnSystem = check.nextLine();
				
				if (  nurseOnSystem.equals(nurse) )
				{
					nurseName = username;
					nurseTitle = nurseName + "'s Charting";
					return true; 
				}
			}
		}

		catch (FileNotFoundException fnf) {}

		return false;
	}

	/**
	 *
	 *  If the user decides to cancels out from their current frame; they do not 
	 *  cancel out from the JPaneOption cancel button after admitting, discharging, or
	 *  updating a patient.  The program rev erases anything that was inputted 
	 *  into that current frame and reverts back to the optionFrame.
	 *
	 */
	public void cancelOptions()
	{

		//If  user was in the admitFrame.
		if ( admitFrame.isVisible() )
		{
			admitFrame.setVisible(false);
			patientName.setText("");
			month.setSelectedIndex(0);
			day.setSelectedIndex(0);
			year.setSelectedIndex(0);
			admitReason.setText("");
			optionFrame.setVisible(true);
		}

		//If the user was in the statusFrame.
		if ( statusFrame.isVisible() )
		{
			statusFrame.setVisible(false);
			month.setSelectedIndex( 0 );
			day.setSelectedIndex( 0 );
			year.setSelectedIndex( 0 );
			bloodPressureS.setText("");
			bloodPressureD.setText("");
			commentBox.setText("");
			optionFrame.setVisible(true);
		}

		//If the user was in the discharge frame.
		if ( dischargeFrame.isVisible() )
		{
			dischargeFrame.setVisible(false);
			dischargePatients.setSelectedIndex(0);
			dischargeReason.setText("");
			optionFrame.setVisible(true);
		}
	}

	/**
	 *
	 *  Checks if all the fields of the admitFrame have been 
	 *  inputted properly.  Will show messages telling the user
	 *  what input to fix.
	 *
	 *  @return	boolean true	If all fields have been completed correctly.
	 *
	 *  @return	boolean false	If fields were not inputted correctly.
	 *
	 */
	public boolean admitAllFields()
	{
		String aN = patientName.getText();
		String aR = admitReason.getText();

		if ( !aN.equals("") && !aR.equals("") )
		{ return true; }

		//If admitReason was not completed.
		else if ( !aN.equals("") && aR.equals("") )
		{
			JOptionPane.showMessageDialog(null, "Please enter a reason for admission.");
			return false;
		}

		//If patientName was not completed.
		else if ( aN.equals("") && !aR.equals("") )
		{
			JOptionPane.showMessageDialog(null, "Please enter the patient's name.");
			return false;
		}

		//If all information was not completed.
		else
		{
			JOptionPane.showMessageDialog(null, "Please enter information for all fields.");
			return false;
		}

	}

	/**
	 *
	 *  Checks if all fields of the status frame were inputted correctly.
	 *
	 *  @param	boolean true	If all fields of the status frame were
	 *				completed correctly.
	 *
	 * @param	boolean false	If the fields of the status frame were not completed
	 * 				correctly.
	 *
	 */
	public boolean statusAllFields()
	{
		String hS = hour.getSelectedItem().toString();
		String bPS = bloodPressureS.getText();
		String bPD = bloodPressureD.getText();
		Boolean s = checkS( bPS ); //Checks if systolic blood pressure input is a number.
		Boolean d = checkD( bPD ); //Checks if diastolic blood pressure input is a number.
		String cS = commentBox.getText();

		//If all fields are emtpy
		if ( hS.equals("")  && cS.equals("") && 
		     bPS.equals("") && bPD.equals("")    )
		{
			JOptionPane.showMessageDialog(null,
			"Please input all information.");
			return false;
		}

		//If systolic and diastolic blood pressures have been inputted properly.
		else if ( s && d )
		{
			//If hour and comment box have been filled.
			if ( !hS.equals("00") && !cS.equals("") )
			{ return true; }

			//If the hour was not changed.
			else if ( hS.equals("00") && !cS.equals("") )
			{
				JOptionPane.showMessageDialog(null, 
				"Please select an approrpiate hour.");
				return false;
			}
			
			//If the comment box was not filled.
			else if ( !hS.equals("00") && cS.equals("") )
			{
				JOptionPane.showMessageDialog(null, 
				"Please input comment updates");
				return false;
			}

			//If hour and comment box was not filled.
			else
			{
				JOptionPane.showMessageDialog(null,
				"Please input all information.");
				return false;
			}
		}
		
		return false;
	}

	/**
	 *
	 *  Checks if the systolic blood pressure input is a number.
	 *  Uses the parseInt method to see if it is.  Catches the NumberFormatException
	 *  when it isn't and prints an error message.
	 *
	 *  @return	boolean true	If the input is a number.
	 *
	 *  @return	boolean false	If the input is not a number.
	 *
	 */
	public boolean checkS( String bs )
	{
		try
		{
			int b = Integer.parseInt(bs);
			return true;

		}
		catch( NumberFormatException nf )
		{
			JOptionPane.showMessageDialog(null,
			"Please insert an appropriate input for the systolic blood pressure.");
			return false;
		}
	}

	/**
	 *
	 *  Checks if the diastolic blood pressure input is a number.
	 *  Uses the parseInt method to see if it is.  Catches the NumberFormatException
	 *  when it isn't and prints an error message.
	 *
	 *  @return	boolean true	If the input is a number.
	 *
	 *  @return	boolean false	If the input is not a number.
	 *
	 */
	public boolean checkD( String bd )
	{
		try
		{
			int b = Integer.parseInt(bd);
			return true;

		}
		catch( NumberFormatException nf )
		{
			JOptionPane.showMessageDialog(null,
			"Please insert an appropriate input for the diastolic blood pressure.");
			return false;
		}
	}

	public static void main (String [] args)
		throws FileNotFoundException
	{
		Charting gui = new Charting();
	}
}
