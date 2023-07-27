package cmsc495_project;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ryanb
 */
public class Users {
    // Declare attributes
    private String username;
    private String password;
    private String account_type;
    private int fines;
    private String book_id;
    
    // Define constructor
    public Users(String username, String password, String account_type, int fines, String book_id) {
	this.username = username;
        this.password = password;
        this.account_type = account_type;
        this.fines = fines;
        this.book_id = book_id;
    }

    // Define get method
    public String getUsername() {
	return username;
    }
    
    public String getPassword() {
	return password;
    }
    
    public String getType() {
	return account_type;
    }
    
    public int getFines() {
	return fines;
    }
    
    public String getBookId() {
	return book_id;
    }

    // Define set method
    public void setUsername(String username) {
	this.username = username;
    }
    
    public void setPassword(String password) {
	this.password = password;
    }
    
    public void setType(String account_type) {
	this.account_type = account_type;
    }
    
    public void setFines(int fines) {
	this.fines = fines;
    }
    
    public void setBookId(String book_id) {
	this.book_id = book_id;
    }
    
    public String toString() {
	return getUsername() + "[username=" + getUsername() + ", password=" + getPassword() +  ", account_type=" + getType() + ",  fines=" + getFines() + ", Book ID=" + book_id + "]";
	
    } 
    
}
