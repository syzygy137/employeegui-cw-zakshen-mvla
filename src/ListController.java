import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import myfileio.MyFileIO;

public class ListController {
	public ArrayList<Employee> employees;
	private static final boolean DEBUG = true;
	private MyFileIO fileio = new MyFileIO();
	
	
	public ListController () {
		employees = new ArrayList<Employee>();
		loadData();
	}

	// adds a new employee
	String addEmployee(String firstName, String lastName, String SSN, String age, String pronouns, String salary, String years, String department) {
		// TODO #6
		// controller needs to convert the numeric string data -
		// use Integer.parseInt() or Double.parseDouble() for ints and doubles
		// years should be int, salary should be a double....
		// Then, add the new employee to the employees list!
		// for initial demo and debugging, set DEBUG to true;
		if (lastName.equals("") || firstName.equals("") || SSN.equals("") || age.equals("") || salary.equals("") || years.equals("") || department.equals("") || department == null)
			return "Missing Info";
		if(!(SSN.matches("\\d{3}-\\d{2}-\\d{4}")))
			return "SSN Format";
		for (Employee employee : employees) {
			if (employee.getSSN().equals(SSN))
				return "Duplicate SSN";
		}
		if (!(age.matches("^\\d+$")))
			return "Invalid Age";
		if (Integer.parseInt(age) < 16)
			return "Too Young";
		if (!(years.matches("^\\d+$")))
			return "Invalid Years";
		if (Integer.parseInt(age) < Integer.parseInt(years) + 16)
			return "Anomaly";
		if (!(salary.matches("\\d+\\.?\\d*")))
			return "Invalid Salary";
		if (Double.parseDouble(salary) < 31200)
			return "Small Salary";
		if (Double.parseDouble(salary) > 100000000)
			return "Big Salary";
		employees.add(new Employee(firstName, lastName, SSN, Integer.parseInt(age), pronouns, Double.parseDouble(salary), Integer.parseInt(years), department));
		if (employees.size() == 1) {
			employees.get(0).setID(1);
			employees.get(0).resetID();
		}
		//if (DEBUG) System.out.println(employees);
		return "";
	}
	
	
	
	
	// returns a string array of the employee information to be viewed
	public String[] getEmployeeDataStr() {
		// temporary placeholder for compilation reasons - will remove later...
		String[] strings = new String[employees.size()];
		int i = 0;
		for (Employee employee : employees) {
			strings[i] = employee.toString();
			i++;
		}
		return(strings);
		
	}
	
	public int getNumEmployees() {
		return employees.size();
	}
	
	public void saveEmployeeDB() {
		this.sortByID();
		File file = new File("empDB.dat");
		try {
			fileio.createEmptyFile("empDB.dat");
			BufferedWriter bw = fileio.openBufferedWriter(file);
			if (fileio.checkFileStatus(file, false) == 0 || fileio.checkFileStatus(file, false) == 7) {
				for (int i = 0; i < employees.size(); i++) {
					Employee emp = employees.get(i);
					
						bw.write(emp.getFirstName() + "|,|" + emp.getLastName() + "|,|" + emp.getSSN() + "|,|" + Integer.toString(emp.getAge()) + "|,|" + 
								 emp.getPronouns() + "|,|" + emp.getFixedPointSalary() + "|,|" + Integer.toString(emp.getYears()) + "|,|" + emp.getDepartment() + "|,|" + emp.getID() + "|,|\n");
					
				}
				fileio.closeFile(bw);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void loadData() {
		File file = new File("empDB.dat");
		try {
			BufferedReader br = fileio.openBufferedReader(file);
			if (fileio.checkFileStatus(file, true) == 0) {
				String line = br.readLine();
				while (line != null) {
					String[] data = line.split("\\|,\\|");
					this.addEmployee(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7]);
					line = br.readLine();
				}
				fileio.closeFile(br);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private class ByName implements Comparator<Employee> {
		public int compare(Employee e1, Employee e2) {
			if (e1.getLastName().compareTo(e2.getLastName()) == 0) {
				return e1.getFirstName().compareTo(e2.getFirstName());
			}
			return e1.getLastName().compareTo(e2.getLastName());
		}
	}
	
	private class ByID implements Comparator<Employee> {
		public int compare(Employee e1, Employee e2) {
			return Integer.compare(e1.getID(), e2.getID());
		}
	}
	
	public class BySalary implements Comparator<Employee> {
		public int compare(Employee e1, Employee e2) {
			return Double.compare(e1.getSalary(), e2.getSalary());
		}
	}
	
	public void sortByName() {
		Collections.sort(employees, new ByName());
	}
	
	public void sortByID() {
		Collections.sort(employees, new ByID());
	}
	
	public void sortBySalary() {
		Collections.sort(employees, new BySalary());
	}
	
}
 
