/**
 * 
 */


import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Scott
 *
 */
public class EmpDataGUI extends Application {
	private BorderPane view = new BorderPane();
	private ScrollPane scroll = new ScrollPane();
    private GridPane main = new GridPane();
    private BorderPane entryPane = new BorderPane();
    private ListController controller = new ListController();
    // TODO #1:
    // create private TextField variables for Name, SSN, Salary and Years
    // so that they can be accessed directly by methods inside this class.
    private TextField lastField;
    private TextField firstField;
    private TextField SSNField;
    private TextField ageField;
    private TextField pronounsField;
    private TextField salaryField;
    private TextField yearsField;
   

    @Override
    public void start(Stage primaryStage) {
    
    	Scene scene = new Scene(entryPane, 400, 500);
    	Scene scene2 = new Scene(view, 400, 500);

	// TODO #2:
    	// create Labels for Name, SSN, Salary and Years
    	Label titleLabel = new Label("EMPLOYEE DATA ENTRY");
    	Label lastLabel = new Label("Last Name: ");
    	Label firstLabel = new Label("First Name: ");
    	Label SSNLabel = new Label("SSN: ");
    	Label ageLabel = new Label("Age: ");
    	Label pronounsLabel = new Label("Pronouns: ");
    	Label salaryLabel = new Label("Salary: ");
    	Label yearsLabel = new Label("Years: ");
    	Label deptLabel = new Label("Dept: ");
    	
    	Label title = new Label("Employees");
    	Button backButton = new Button("Back");
    	Button nameButton = new Button("Sort By Name");
    	Button IDButton = new Button("Sort By ID");
    	Button salaryButton = new Button("Sort By Salary");
    	backButton.setOnAction(e -> primaryStage.setScene(scene));
    	nameButton.setOnAction(e -> {controller.sortByName(); viewEmployeeDB();});
    	IDButton.setOnAction(e -> {controller.sortByID(); viewEmployeeDB();});
    	salaryButton.setOnAction(e -> {controller.sortBySalary(); viewEmployeeDB();});
    	
    	GridPane viewButtons = new GridPane();
    	viewButtons.add(backButton, 0, 0);
    	viewButtons.add(nameButton, 1, 0);
    	viewButtons.add(IDButton, 2, 0);
    	viewButtons.add(salaryButton, 3, 0);
    	
    	view.setTop(title);
    	view.setBottom(viewButtons);
    	view.setCenter(scroll);
    	
	// TODO #3
    	// instantiate (new) TextFields (already declared above) for Name, SSN, Salary and Years
    	lastField = new TextField();
    	firstField = new TextField();
    	SSNField = new TextField();
    	ageField = new TextField();
    	pronounsField = new TextField();
    	salaryField = new TextField();
    	yearsField = new TextField();
   
	// TODO #4
        // Create Add Employee Button, and write the setOnAction handler to call the controller
    	// to add the new Employee data
    	
    	
	// TODO #5
    	// add all the labels, textfields and button to gridpane main. refer to the slide
    	// for ordering...
    	
    	
    	VBox deptBox = new VBox();
    	RadioButton engineering = new RadioButton("Engineering");
    	RadioButton marketing = new RadioButton("Marketing/Sales");
    	RadioButton manufacturing = new RadioButton("Manufacturing");
    	RadioButton finance = new RadioButton("Finance");
    	RadioButton humanResources = new RadioButton("Human Resources");
    	RadioButton customerSupport = new RadioButton("Customer Support");
    	RadioButton management = new RadioButton("Management");
    	deptBox.getChildren().addAll(engineering, marketing, manufacturing, finance, humanResources, customerSupport, management);
    	ToggleGroup tg = new ToggleGroup();
    	
    	engineering.setToggleGroup(tg);
    	marketing.setToggleGroup(tg);
    	manufacturing.setToggleGroup(tg);
    	finance.setToggleGroup(tg);
    	humanResources.setToggleGroup(tg);
    	customerSupport.setToggleGroup(tg);
    	management.setToggleGroup(tg);
    	
    	engineering.setSelected(true);
    	
    	Button empButton = new Button("Add Employee");
    	Button viewButton = new Button("View Employees");
    	Button saveDB = new Button("Save Employee DB");
    	
		Alert error = new Alert(AlertType.ERROR);
		Alert warning = new Alert(AlertType.WARNING);

    	
    	empButton.setOnAction(e -> {
    		switch(controller.addEmployee(lastField.getText(), firstField.getText(), SSNField.getText(), ageField.getText(), 
    							pronounsField.getText(), salaryField.getText(), yearsField.getText(), ((RadioButton) tg.getSelectedToggle()).getText())) {
    		case "Ok":
	    		lastField.clear();
	    		firstField.clear();
	    		SSNField.clear();
	    		ageField.clear();
	    		pronounsField.clear();
	    		salaryField.clear();
	    		yearsField.clear();	
	    		tg.selectToggle(null);
	    		engineering.setSelected(true);
	    		break;
	    		
    		case "Missing Info":
    			error.setHeaderText("Missing Information");
    			error.setContentText("All information except for pronouns is required");
    			error.showAndWait();
    			break;
    			
    		case "SSN Format":
    			error.setHeaderText("Invalid SSN Format");
    			error.setContentText("SSN must follow the format xxx-xx-xxxx");
    			error.showAndWait();
    			break;
    			
    		case "Duplicate SSN":
    			error.setHeaderText("Duplicate SSN");
    			error.setContentText("Employee with this SSN already exists");
    			error.showAndWait();
    			break;
    			
    		case "Invalid Age":
    			error.setHeaderText("Invalid Age");
    			error.setContentText("Please enter a valid number");
    			error.showAndWait();
    			break;
    			
    		case "Too Young":
    			warning.setHeaderText("Invalid Age");
    			warning.setContentText("Age must be 16 or above");
    			warning.showAndWait();
    			break;
    			
    		case "Invalid Years":
    			error.setHeaderText("Invalid Years");
    			error.setContentText("Please enter a valid number");
    			error.showAndWait();
    			break;
    			
    		case "Anomaly":
    			error.setHeaderText("Inconsistent Age and Years");
    			error.setContentText("Years must be at least 16 more than age");
    			error.showAndWait();
    			break;
    			
    		case "Invalid Salary":
    			error.setHeaderText("Invalid Salary");
    			error.setContentText("Please enter a valid number (decimals allowed)");
    			error.showAndWait();
    			break;
    			
    		case "Small Salary":
    			error.setHeaderText("Invalid Salary");
    			error.setContentText("Salary must be at least 31200");
    			error.showAndWait();
    			break;
    			
    		case "Big Salary":
    			error.setHeaderText("Invalid Salary");
    			error.setContentText("Salary cannot be greater than 100,000,000");
    			error.showAndWait();
    			break;
	    		
    		}
    	});
    	
    	viewButton.setOnAction(e -> {viewEmployeeDB(); primaryStage.setScene(scene2); });
    	saveDB.setOnAction(e -> {
			try {
				controller.saveData();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
    	
    	main.add(lastLabel, 0, 1);
    	main.add(firstLabel, 0, 2);
    	main.add(SSNLabel, 0, 3);
    	main.add(ageLabel, 0, 4);
    	main.add(pronounsLabel, 0, 5);
    	main.add(salaryLabel, 0, 6);
    	main.add(yearsLabel, 0, 7);
    	main.add(deptLabel, 0, 8);    	
    	
    	main.add(lastField, 1, 1);
    	main.add(firstField, 1, 2);
    	main.add(SSNField, 1, 3);
    	main.add(ageField, 1, 4);
    	main.add(pronounsField, 1, 5);
    	main.add(salaryField, 1, 6);
    	main.add(yearsField, 1, 7);
    	main.add(deptBox, 1, 8);
    	
    	GridPane buttons = new GridPane();
    	buttons.add(empButton, 0, 0);
    	buttons.add(viewButton, 1, 0);
    	buttons.add(saveDB, 2, 0);
    	
    	entryPane.setTop(titleLabel);
    	entryPane.setCenter(main);
    	entryPane.setBottom(buttons);
    	
    	
    	primaryStage.setTitle("Employees");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // don't worry about this yet - part of part2
    private void viewEmployeeDB() {
    	String[] empDataStr = controller.getEmployeeDataStr();
    	ListView<String> lv = new ListView<>(FXCollections.observableArrayList(empDataStr));
    	lv.setPrefWidth(400);
    	scroll.setContent(lv);
    }
    
  /**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}

} ;
