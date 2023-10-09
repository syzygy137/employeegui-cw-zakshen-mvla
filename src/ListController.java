import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import myfileio.MyFileIO;

public class ListController {
	private ArrayList<Employee> employees;
	private static final boolean DEBUG = true;
	private MyFileIO fileio = new MyFileIO();
	
	public ListController () {
		employees = new ArrayList<Employee>();
		try {
			loadData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// adds a new employee
	void addEmployee(String name, String SSN, String salary, String years) {
		// TODO #6
		// controller needs to convert the numeric string data -
		// use Integer.parseInt() or Double.parseDouble() for ints and doubles
		// years should be int, salary should be a double....
		// Then, add the new employee to the employees list!
		// for initial demo and debugging, set DEBUG to true;
		
		employees.add(new Employee(name, SSN, Double.parseDouble(salary), Integer.parseInt(years)));
		if (DEBUG) System.out.println(employees);

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
	
	public void saveData() throws IOException {
		File file = new File("empDB.dat");
		fileio.createEmptyFile("empDB.dat");
		BufferedWriter bw = fileio.openBufferedWriter(file);
		if (fileio.checkFileStatus(file, false) == 0 || fileio.checkFileStatus(file, false) == 7) {
			for (int i = 0; i < employees.size(); i++) {
				Employee emp = employees.get(i);
				bw.write(emp.getName() + "|,|" + emp.getSSN() + "|,|" + Double.toString(emp.getSalary()) + "|,|" + Integer.toString(emp.getYears()) + "\n");
			}
			fileio.closeFile(bw);
		}
		
	}
	
	public void loadData() throws IOException {
		File file = new File("empDB.dat");
		BufferedReader br = fileio.openBufferedReader(file);
		if (fileio.checkFileStatus(file, true) == 0) {
			String line = br.readLine();
			while (line != null) {
				String[] data = line.split("\\|,\\|");
				this.addEmployee(data[0], data[1], data[2], data[3]);
				line = br.readLine();
			}
			fileio.closeFile(br);
		}
	}
	
}
 
