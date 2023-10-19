
public class Employee {
	private int empID;
	private static int lastID = 0;
	private String lastName;
	private String firstName;
	private int age;
	private String pronouns;
	private String department;
	private double salary;
	private int years;
	private String SSN;
	


public Employee (String lastName, String firstName, String SSN, int age, String pronouns, double salary, int years, String department) {
	lastID++;
	empID = lastID;
	this.lastName = lastName;
	this.firstName = firstName;
	this.SSN = SSN;
	this.age = age;
	this.pronouns = pronouns;
	this.salary = salary;
	this.years = years;
	this.department = department;
}



public String getLastName() {
	return lastName;
}



public void setLastName(String lastName) {
	this.lastName = lastName;
}



public String getFirstName() {
	return firstName;
}



public void setFirstName(String firstName) {
	this.firstName = firstName;
}



public int getAge() {
	return age;
}



public void setAge(int age) {
	this.age = age;
}



public String getPronouns() {
	return pronouns;
}



public void setPronouns(String pronouns) {
	this.pronouns = pronouns;
}



public String getDepartment() {
	return department;
}



public void setDepartment(String department) {
	this.department = department;
}



public double getSalary() {
	return salary;
}

public String getFixedPointSalary() {
	return String.format(String.format("%.2f",((long) (salary*100)/100.0)));
}

public void setSalary(double salary) {
	this.salary = salary;
}


public int getYears() {
	return years;
}


public void setYears(int years) {
	this.years = years;
}


public String getSSN() {
	return SSN;
}


public void setSSN(String sSN) {
	SSN = sSN;
}

public int getID() {
	return empID;
}

public void setID(int id) {
	empID = id;
}


public String toString( ) {
	String str = String.format("%-8s\t","Name:")+firstName+" "+lastName;
	str += (!pronouns.isEmpty()) ? " ("+pronouns+")\n" : "\n";
	str = str +    String.format("%-8s\t", "SSN")+String.format("%-16s\t",SSN );
	str = str +    String.format("%-16s\t","Employee ID:")+empID+"\n";
	str = str +    String.format("%-8s\t","Age:")+String.format("%-16d\t\t", age)+String.format("%-20s\t","Years Exp:")+years+"\n";
	if(department.equals("Finance")) {
		str = str +    String.format("%-8s\t","Dept:")+String.format("%-16s\t\t",department);
	} else {
		str = str +    String.format("%-8s\t","Dept:")+String.format("%-16s\t",department);		
	}
	str = str +    String.format("%-20s\t\t","Salary:")+ getFixedPointSalary();
	return(str);
}

}
