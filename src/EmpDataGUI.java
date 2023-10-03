/**
 * 
 */


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * @author Scott
 *
 */
public class EmpDataGUI extends Application {
    private GridPane main = new GridPane();	
    private ListController controller = new ListController();
    // TODO #1:
    // create private TextField variables for Name, SSN, Salary and Years
    // so that they can be accessed directly by methods inside this class.
    private TextField nameField;
    private TextField SSNField;
    private TextField salaryField;
    private TextField yearsField;

    @Override
    public void start(Stage primaryStage) {
    
    	Scene scene = new Scene(main, 400, 400);

	// TODO #2:
    	// create Labels for Name, SSN, Salary and Years
    	Label nameLabel = new Label("Name: ");
    	Label SSNLabel = new Label("SSN: ");
    	Label salaryLabel = new Label("Salary: ");
    	Label yearsLabel = new Label("Years: ");
    	
	// TODO #3
    	// instantiate (new) TextFields (already declared above) for Name, SSN, Salary and Years
    	nameField = new TextField();
    	SSNField = new TextField();
    	salaryField = new TextField();
    	yearsField = new TextField();
   
	// TODO #4
        // Create Add Employee Button, and write the setOnAction handler to call the controller
    	// to add the new Employee data
    	Button empButton = new Button("Add Employee");
    	empButton.setOnAction(e -> controller.addEmployee(nameField.getText(), SSNField.getText(), salaryField.getText(), yearsField.getText()));
    	
	// TODO #5
    	// add all the labels, textfields and button to gridpane main. refer to the slide
    	// for ordering...
    	
    	main.add(nameLabel, 0, 0);
    	main.add(SSNLabel, 0, 1);
    	main.add(salaryLabel, 0, 2);
    	main.add(yearsLabel, 0, 3);
    	main.add(empButton, 0, 4);
    	main.add(nameField, 1, 0);
    	main.add(SSNField, 1, 1);
    	main.add(salaryField, 1, 2);
    	main.add(yearsField, 1, 3);
    	
    	primaryStage.setTitle("Employees");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // don't worry about this yet - part of part2
    private void viewEmployeeDB() {
    	String[] empDataStr = controller.getEmployeeDataStr();
    }
    
  /**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}

} ;
