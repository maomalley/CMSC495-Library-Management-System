package cmsc495_project;

/*

 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ryanb
 */
public class Books {
    // Declare attributes
    private String book_id;
    private String title;
    private int year;
    private String author;
    private boolean stock;
    
    // Define constructor
    public Books(String book_id, String title, int year, String author, boolean stock) {
	this.book_id = book_id;
	this.title = title;
	this.year = year;
	this.author = author;
	this.stock = stock;
    }
    
    
    // Define get methods
    public String getBookId() {
        return book_id;
    }
	
    public String getTitle() {
        return title;
    }
	
    public int getYear() {
        return year;
    }
        
    public String getAuthor(){
       return author;     
    }
	
    public boolean getStock(){
       return stock;     
    }
		
    // Define set methods
    public void setBookId(String book_id) {
	this.book_id = book_id;
    }
	
    public void setTitle(String title) {
        this.title = title;
    }
	
    public void setYear(int year) {
	this.year = year;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
     
    public void setStock(boolean stock) {
        this.stock = stock;
    }
	
    //Begin method toString() which outputs different result based off of which child class is 
    //being initialized
    public String toString() {
        return getBookId() + "[ book id=" + getBookId() + ", title=" + getTitle()
        +  ", release year=" + getYear() + ", author=" + getAuthor() + ", stock=" +getStock() + "]";
    }     
}
