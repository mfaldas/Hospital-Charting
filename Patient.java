//Patient.java
//Marc-Eli Faldas
//Extra Credit

/**
 *
 *  This is the patient object that describes each patient.
 *
 *  @author	Marc-Eli Faldas
 *  @version	Last modified_5/7/14
 *
 */

import java.io.*;
import java.util.*;


public class Patient
{
	private String name;
	private String reason;
	private String DofB;
	private String time;
	private String systolic;
	private String diastolic;
	private String currentStatus;
	private String dischargeReason;
	private String nurse;
	private String comments;
	private String file;
	private PrintWriter output;
	private int entry = 1;
	
	/**
	 *
	 *  Creates the basic Patient object;
	 *
	 */
	public Patient()
	{
		this("","","");
	}

	/**
	 *
	 *  Creates the Patient object.
	 *
	 *  @param	String name	The name of the patient.
	 *
	 *  @param	String DofB	The day of birth of the patient.
	 *
	 *  @param	String reason	The reason of admission.
	 *
	 */
	public Patient(String name, String DofB, String reason)
	{
		this.name = name;
		this.DofB = DofB;
		this.reason = reason;
	}

	/**
	 *
	 *  The name of the patient is returned as the String representation
	 *  of the patient object.
	 *
	 *  @return	String name	The name of the patient.	
	 *
	 */
	public String toString()
	{
		return(name);
	}

	/**
 	 *
	 *  Sets the name of the patient.
	 * 
	 *  @param	String name	The name of the patient
	 * 
	 */
	public void setName(String name) { this.name = name; }
	
	/**
	 *
 	 *  Returns the name of the patient.
	 *
 	 *  @return	String name	The name of the patient.
	 *
	 */
	public String getName() { return name; }

	/**
 	 *
	 *  Sets the day of birth of the patient.
	 * 
	 *  @param	String DofB	The day of birth of the patient
	 * 
	 */
	public void setDofB(String DofB) { this.DofB = DofB; }

	/**
	 *
 	 *  Returns the day of birth of the patient.
	 *
 	 *  @return	String DofB	The day of birth of the patient.
	 *
	 */
	public String getDofB() { return DofB; }

	/**
	 *
 	 *  Sets the reason of admission of the patient.
	 *
 	 *  @param	String reason	The reason for admission.
	 *
	 */
	public void setReason(String reason) { this.reason = reason; }

	/**
	 *
 	 *  Returns the reason of admission of the patient.
	 *
 	 *  @return	String reason	The reason for admission.
	 *
	 */
	public String getReason() { return reason; }

	/**
 	 *
	 *  Sets the current status of the patient and prints into the
	 *  the patient's corresponding text file.
	 *
	 *  @param	String time		The time the update was filed.
	 *
	 *  @param	String systolic		The systolic blood pressure of the patient
	 *					at that time.
	 * 
	 *  @param	String diastolic	The diasolic blood pressure of the patient
	 *					at that time.
	 *
	 *  @param	String nurse		The nusre who filed the update.
	 *
	 *  @param	String comments		The comments the nurse made.
	 *
	 */
	public void setCurrentStatus
	( String time, String systolic, String diastolic, String nurse, String comments)
	{
		this.time = time;
		this.systolic = systolic;
		this.diastolic = diastolic;
		this.nurse = nurse;
		this.comments = comments;

		toFile( this.getCurrentStatus() );
	}

	/**
	 *
	 *  Returns the current status of the patient.  If no update
	 *  was made, then the fields will be declared as 'N/A' and the program will
	 *  return N/A.
	 *
 	 *  @return	String (indicated below)	The current status of the patient.
	 *
	 */
	public String getCurrentStatus()
	{
		if ( time == null )
		{
			time = "N/A";
			systolic = "N/A";
			diastolic = "N/A";
			comments = "N/A";
			nurse = "N/A";
		}

		return("Time: " + time +
		       "\n " +
		       "\nBlood Pressure" +
		       "\nSystolic: " + systolic + " mmHg" +
		       "\nDialostic: " + diastolic + " mmHg" +
		       "\n " +
		       "\nComments: " +
		       "\n" + comments +
		       "\n" +
		       "\nNurse that made update: " + nurse );
	}

	/**
 	 *  Returns the basic information about the patient (name,
	 *  day of birth, and reason for admittance) as a String.
	 *
 	 *  @return	String (indicate below)		Basic information about
	 * 						the patient
	 *
	 */
	public String newPatient()
	{
		return( "Patient Name: " + name + 
			"\nDay of Birth: " + DofB +
			"\nReason for Admittance: " +
			"\n" + reason );
	}

	/**
	 *
	 *  Creates a file about the patient when the are admitted.
	 *
	 */
	public void createFile()
	{
		String fileName = modifyName(name);
		file = fileName;

		try 
		{ 
			output = new PrintWriter( file ); 
			output.println( newPatient() );
			output.println("");
			output.println("*****************************");
			output.flush();
	
		}
		catch (FileNotFoundException fnf) {}
	}

	/**
	 *
	 *  Prints the current status of the patient to their corresponding text file.
	 *
	 *  @param	String print	Current information about the patient
	 * 				that was updated.
 	 * 
	 */
	public void toFile(String print)
	{

		output.append("\n");
		output.append("\nENTRY " + entry);
		output.append("\n" + print);
		output.append("\n");
		output.append("\n*****************************\n");
		output.append("");
		output.flush();
	
		entry++;
	}

	/**
	 *
	 *  Writes the reason on the discharge file of the patient's text file.
	 * 
	 *  @param	String reason	Reason for discharge.
	 *
	 */
	public void dischargeEntry (String reason)
	{
		output.append("\n");
		output.append("\nDISCHARGE ENTRY\n");
		output.append("\nReason for Discharge: \n" + reason);
		output.flush();
	}

	/**
	 *
 	 *  Modifies the file name of the patient's file.  If the name
	 *  of the patient is separated by spaces, this method will create
	 *  a new String name file based on the patient's name without
	 *  the spaces.
	 *
	 *  @param	String fileName		The name that will be modified.
	 *
 	 *  @return	String newFileName	The new file name without spaces.
	 *
	 */
	public String modifyName( String fileName )
	{
		StringBuilder sb = new StringBuilder();
		int length = fileName.length();

		for (int i = 0; i < length; i++ )
		{
			char c = name.charAt(i);

			if ( c == ' ' )
			{ }
			else { sb.append(c); };

		}

		String newFileName = sb.toString();

		newFileName += ".txt";
		return newFileName;
	}
}

