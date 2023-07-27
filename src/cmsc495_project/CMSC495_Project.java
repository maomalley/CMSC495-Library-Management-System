/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package cmsc495_project;

//Below are the modules imported for use in this assignment
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ryanb
 */
public class CMSC495_Project extends Application {
    
    /**
     * Declaration of variables to be called throughout the program
     * @param attempt Counts the failed logon attempts per AC-7
     * @param writer Writes audit logs to separate file per AU-3
     * @param username Used to get current username for audit logs
     * @param scan Used to scan file for multi-factor authentication per IA-2(1)
     */
    int attempt = 1;
    BufferedWriter writer = null;
    Scanner scan = new Scanner(System.in);
    Scene scene1;
    Scene scene2;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        /**
         * Creates the logon page, including its grid, panels, text boxes, and buttons for the user
         * @param primaryStage used to print out stage for user to interact with
         * @param grid grid pane which text boxes, labels, and buttons are placed within
         * @param warntitle welcome page title
         * @param userName username label for text box
         * @param userTextField field in which user enters input for username
         * @param pw password label
         * @param pwbox text field in which user enters input for password
         * @param btn button used to login after entering credentials
         */
        //ok button is pressed
        primaryStage.setTitle("Library Management System");
        // Grid Pane divides your window into grids
        GridPane grid = new GridPane();
        // Align to Center
        // Note Position is geometric object for alignment
        grid.setAlignment(Pos.CENTER);
        // Set gap between the components
        // Larger numbers mean bigger spaces
        grid.setHgap(10);
        grid.setVgap(10);

        // Create some text to place in the scene
        Text warntitle = new Text("Welcome. Login to access your library account."
                + "\nIf you do not have an account, please register first.");
        // Add text to grid 0,0 span 2 columns, 1 row
        grid.add(warntitle, 0, 0, 2, 1);

        // Create Label
        Label userName = new Label("User Name:");
        // Add label to grid 0,1
        grid.add(userName, 0, 1);

        // Create Textfield
        TextField userTextField = new TextField();
        // Add textfield to grid 1,1
        grid.add(userTextField, 1, 1);

        // Create Label
        Label pw = new Label("Password:");
        // Add label to grid 0,2
        grid.add(pw, 0, 2);

        // Create Passwordfield
        PasswordField pwBox = new PasswordField();
        // Add Password field to grid 1,2
        grid.add(pwBox, 1, 2);

        // Create Login Button
        Button btn = new Button("Login");
        // Add button to grid 1,4
        grid.add(btn, 1, 4);
        
        //Create Clear Button
        Button cbtn = new Button("Clear");
        //Add button to grid 1,5
        grid.add(cbtn, 1, 5);
         
        //Create Registration Button
        Button rbtn = new Button("Registration");
        //Add button to grid 1,7
        grid.add(rbtn, 1, 7);
        
        //Create Admin or Employee Login Button
        Button empbtn = new Button("Employee Login");
        //Add button to grid 1,9
        grid.add(empbtn, 1, 9);
        
        // Set the size of Scene
        scene1 = new Scene(grid, 500, 400);
        primaryStage.setScene(scene1);
        primaryStage.show();
        
        rbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                GridPane grid3 = new GridPane();
                // Align to Center
                    // Note Position is geometric object for alignment
                    grid3.setAlignment(Pos.CENTER);
                     // Set gap between the components
                    // Larger numbers mean bigger spaces
                    grid3.setHgap(10);
                    grid3.setVgap(10);
                    
                    Text scenetitle = new Text("Please register your account information.");
                    // Add text to grid 0,0 span 2 columns, 1 row
                    grid3.add(scenetitle, 0, 0, 2, 1);
                    
                    // Create Label
                    Label userName = new Label("User Name:");
                    // Add label to grid 0,1
                    grid3.add(userName, 0, 1);

                    // Create Textfield
                    TextField userTextField = new TextField();
                    // Add textfield to grid 1,1
                    grid3.add(userTextField, 1, 1);

                    // Create Label
                    Label pw = new Label("Password:");
                    // Add label to grid 0,2
                    grid3.add(pw, 0, 2);

                    // Create Passwordfield
                    PasswordField pwBox = new PasswordField();
                    // Add Password field to grid 1,2
                    grid3.add(pwBox, 1, 2);
                    
                    //Create Registration Button
                    Button regbtn = new Button("Register");
                    //Add button to grid 1,7
                    grid3.add(regbtn, 1, 4);
                    
                    Scene scene2 = new Scene(grid3, 500, 400);
                    primaryStage.setScene(scene2);
                    primaryStage.show();
                    
                    
                    regbtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        

                        //Returns to login page after user data is registered
                        primaryStage.setScene(scene1);
                        primaryStage.show();
                        
                    }
                });
            }
        });


        cbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                userTextField.clear();
                pwBox.clear();
            }
        });

        // Set the Action when button is clicked to enter username and password
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                /**
                 * Authenticate the user
                 * Calls the function to validate that the user input for username and password is valid
                 * @param isValid calls the function authenticate to validate user input for first step of authentication
                 */
                boolean isValid = UserFunctions.authenticate(userTextField.getText(), pwBox.getText());
                // If valid clear the grid and proceed to the login page
                if (isValid) {
                    grid.setVisible(false);
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
                    LocalDateTime now = LocalDateTime.now();
                    GridPane grid2 = new GridPane();
                    // Align to Center
                    // Note Position is geometric object for alignment
                    grid2.setAlignment(Pos.CENTER);
                     // Set gap between the components
                    // Larger numbers mean bigger spaces
                    grid2.setHgap(10);
                    grid2.setVgap(10);

                    Text scenetitle = new Text("Welcome. Your identity has been verified!");
                    Text scenetitle2 = new Text("Timestamp: " + dtf.format(now));
                    Text scenetitle3 = new Text("Please select from one of the below options.");
                    // Add text to grid 0,0 span 2 columns, 1 row
                    grid2.add(scenetitle, 0, 0, 2, 1);
                    grid2.add(scenetitle2, 0, 2, 2, 1);
                    grid2.add(scenetitle3, 0, 4, 2, 1);

                    // Create Check Loans Button
                    Button loanbtn = new Button("Check Current Loans");
                    // Add button to grid 0,8
                    grid2.add(loanbtn, 0, 8);
                    
                    //Create Check Book Out Button
                    Button bookbtn = new Button("Check Book Out");
                    // Add button to grid 1,8
                    grid2.add(bookbtn, 1, 8);
                    
                    //Create Return Book Button
                    Button returnbtn = new Button("Return Book");
                    // Add button to grid 1,8
                    grid2.add(returnbtn, 1, 10);
                    
                    // Create Logout Button
                    Button lbtn = new Button("Logout");
                    // Add button to grid 0,10
                    grid2.add(lbtn, 0, 12);

                    scene2 = new Scene(grid2, 500, 400);
                    primaryStage.setScene(scene2);
                    primaryStage.show();
                    try {
                        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
                        LocalDateTime now2 = LocalDateTime.now();
                        writer = new BufferedWriter(new FileWriter("Log.txt", true));
                        writer.write("\n");
                        writer.write("\nEvent User: " + userTextField.getText());
                        writer.write("\nEvent time: " + dtf2.format(now2));
                        writer.write("\nEvent Outcome: Successful logon after valid credentials were entered.");
                    }
                    // print error message if there is one
                    catch (IOException io) {
                        System.out.println("File IO Exception" + io.getMessage());
                    }
                    //close the file
                    finally {
                        try {
                            if (writer != null) {
                            writer.close();
                        }
                    }
                        //print error message if there is one
                        catch (IOException io) {
                            System.out.println("Issue closing the File." + io.getMessage());
                        }
                    }
                    //Set action when logout button is clicked
                    loanbtn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            /**
                             * Will present the books currently checked out by the logged in user.
                             */
                            
                            GridPane loangrid = new GridPane();
                            // Align to Center
                            // Note Position is geometric object for alignment
                            loangrid.setAlignment(Pos.CENTER);
                             // Set gap between the components
                            // Larger numbers mean bigger spaces
                            loangrid.setHgap(10);
                            loangrid.setVgap(10);
                            
                            //edited this (Maureen).
                            Text scenetitle = new Text("Welcome. Your checked "
                                    + "out books will now be displayed.\nIf you "
                                    + "have not checked out a book it will "
                                    + "appear as null.\n" 
                                    + UserFunctions.checkCurrentLoans());
                            
                            loangrid.add(scenetitle, 0, 0, 2, 1);
                            
                            
                            
                            Scene scene = new Scene(loangrid, 500, 400);
                            primaryStage.setScene(scene);
                            primaryStage.show();
                            
                            // Create Button to return to main user account page if needed
                            Button mainbtn = new Button("Account Page");
                            // Add button to grid 0,10
                            loangrid.add(mainbtn, 0, 10);
                            
                            mainbtn.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent e) {
                                    /**
                                    * Returns user to main account page
                                    */
                                    primaryStage.setScene(scene2);
                                    primaryStage.show();
                                }                          
                            });
                            
                            
                        }                          
                    });
                    bookbtn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            /**
                             * Returns any books that are available to be checked out by a user.
                             */
                            
                            GridPane bookgrid = new GridPane();
                            // Align to Center
                            // Note Position is geometric object for alignment
                            bookgrid.setAlignment(Pos.CENTER);
                             // Set gap between the components
                            // Larger numbers mean bigger spaces
                            bookgrid.setHgap(10);
                            bookgrid.setVgap(10);
                            //edited by Maureen 6/23/2022
                            Text scenetitle = new Text("The books available to "
                                    + "be checked out will now be displayed.\n" 
                            + UserFunctions.checkOutBookData() + "\nPlease enter"
                                    + " the book ID which you want to check "
                                    + "out below.");
                            //TODO: Add a text field to enter the book ID in
                            //TODO: Add a button to let a user check out a book
                            bookgrid.add(scenetitle, 0, 0, 2, 1);
                            
                            
                            
                            Scene scene = new Scene(bookgrid, 500, 400);
                            primaryStage.setScene(scene);
                            primaryStage.show();
                            
                            // Create Button to return to main user account page if needed
                            Button mainbtn = new Button("Account Page");
                            // Add button to grid 0,10
                            bookgrid.add(mainbtn, 0, 10);
                            
                            mainbtn.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent e) {
                                    /**
                                    * Returns user to main account page
                                    */
                                    primaryStage.setScene(scene2);
                                    primaryStage.show();
                                }                          
                            });
                            
                        }                          
                    });
                    returnbtn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            /**
                             * Returns any books that are available to be checked out by a user.
                             */
                            
                            GridPane bookgrid = new GridPane();
                            // Align to Center
                            // Note Position is geometric object for alignment
                            bookgrid.setAlignment(Pos.CENTER);
                             // Set gap between the components
                            // Larger numbers mean bigger spaces
                            bookgrid.setHgap(10);
                            bookgrid.setVgap(10);
                            
                            //assumption: user can only check out one book
                            Text scenetitle = new Text("The books available to "
                                    + "be returned will now be displayed.\n" 
                                    + UserFunctions.checkCurrentLoans() + 
                                    "\nWould you like to return this book?");
                            //TODO: yes or no buttons
                            //TODO: function call which changes the book data if the 'yes' GUI button is clicked
        
                            bookgrid.add(scenetitle, 0, 0, 2, 1);
                            
                            
                            
                            Scene scene = new Scene(bookgrid, 500, 400);
                            primaryStage.setScene(scene);
                            primaryStage.show();
                            
                            // Create Button to return to main user account page if needed
                            Button mainbtn = new Button("Account Page");
                            // Add button to grid 0,10
                            bookgrid.add(mainbtn, 0, 10);
                            
                            mainbtn.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent e) {
                                    /**
                                    * Returns user to main account page
                                    */
                                    primaryStage.setScene(scene2);
                                    primaryStage.show();
                                }                          
                            });
                            
                        }                          
                    });
                    empbtn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            /**After the user signs in to their account, this button will take them
                             * through an additional authentication step. 
                             * This step represents the user entering their admin credentials, allowing them to
                             * modify user and book data
                             */
                            
                        }                          
                    });
                    //Set action when logout button is clicked
                    lbtn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
                            LocalDateTime now3 = LocalDateTime.now();
                            grid2.setVisible(false);
                            GridPane grid4 = new GridPane();
                            // Align to Center
                            // Note Position is geometric object for alignment
                            grid4.setAlignment(Pos.CENTER);
                            // Set gap between the components
                            // Larger numbers mean bigger spaces
                            grid4.setHgap(10);
                            grid4.setVgap(10);

                            Text scenetitle = new Text("You have been successfully logged out!");
                            Text scenetitle2 = new Text("Timestamp: " + dtf3.format(now3));
                            // Add text to grid 0,0 span 2 columns, 1 row
                            grid4.add(scenetitle, 0, 0, 2, 1);
                            grid4.add(scenetitle2, 0, 10, 2, 1);

                            Scene scene = new Scene(grid4, 500, 400);
                            primaryStage.setScene(scene);
                            primaryStage.show();

                            try {
                                DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
                                LocalDateTime now2 = LocalDateTime.now();
                                writer = new BufferedWriter(new FileWriter("Log.txt", true));
                                writer.write("\n");
                                writer.write("\nEvent User: " + userTextField.getText());
                                writer.write("\nEvent time: " + dtf2.format(now2));
                                writer.write("\nEvent Outcome: Logout successful.");
                            }
                            // print error message if there is one
                            catch (IOException io) {
                                System.out.println("File IO Exception" + io.getMessage());
                            }
                            //close the file
                            finally {
                                try {
                                    if (writer != null) {
                                        writer.close();
                                    }
                                }
                                //print error message if there is one
                                catch (IOException io) {
                                    System.out.println("Issue closing the File." + io.getMessage());
                                }
                            }
                        }                          
                    });
                }
                //Condition for if user has not exceeded maximum number of failed consecutive logon attempts for username and password
                else {                       
                    attempt++;
                    if (attempt <= 3){
                        final Text actiontarget = new Text();
                        grid.add(actiontarget, 1, 10);
                        actiontarget.setFill(Color.FIREBRICK);
                        actiontarget.setText("Please try again.");

                        final Text actiontarget2 = new Text();
                        actiontarget2.setFill(Color.FIREBRICK);
                        actiontarget2.setText("Current attempt is " + attempt);
                        grid.getChildren().removeIf(node->GridPane.getRowIndex(node)==8);
                        grid.add(actiontarget2, 1, 8);

                        try {
                            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
                            LocalDateTime now2 = LocalDateTime.now();
                            writer = new BufferedWriter(new FileWriter("Log.txt", true));
                            writer.write("\n");
                            writer.write("\nEvent User: " + userTextField.getText());
                            writer.write("\nEvent time: " + dtf2.format(now2));
                            writer.write("\nEvent Outcome: The credentials were not valid. "
                                    + "The user is currently on attempt: " + attempt);
                        }
                        // print error message if there is one
                        catch (IOException io) {
                            System.out.println("File IO Exception" + io.getMessage());
                        }
                        //close the file
                        finally {
                            try {
                                if (writer != null) {
                                writer.close();
                            }
                        }
                            //print error message if there is one
                            catch (IOException io) {
                                System.out.println("Issue closing the File." + io.getMessage());
                            }
                        }
                        }
                    //Condition for if user has exceeded maximum number of failed consecutive login attempts
                    else{
                        try {
                            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
                            LocalDateTime now2 = LocalDateTime.now();
                            writer = new BufferedWriter(new FileWriter("Log.txt", true));
                            writer.write("\n");
                            writer.write("\nEvent User: " + userTextField.getText());
                            writer.write("\nEvent time: " + dtf2.format(now2));
                            writer.write("\nEvent Outcome: User exceeded the maximum number of failed login attempts. The program promptly closed.");
                        }
                        // print error message if there is one
                        catch (IOException io) {
                            System.out.println("File IO Exception" + io.getMessage());
                        }
                        //close the file
                        finally {
                            try {
                                if (writer != null) {
                                writer.close();
                                }
                            }
                            //print error message if there is one
                            catch (IOException io) {
                                System.out.println("Issue closing the File." + io.getMessage());
                            }
                        }
                        //Program closes, simulating the authentication session ending because user exceeded the maximum number of failed login attempts
                        Platform.exit();
                    }
                }
            }
        });
        
        empbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                
            }
         });
}
        
        

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    
    
}
