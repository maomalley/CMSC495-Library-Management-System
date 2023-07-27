/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cmsc495_project;

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ryanb
 */
public class UserFunctions {

    private static String userName = "";
    private static String pass = "";
    // this arraylist will hold all the user data from UserData.txt so that
    // the file only has to be read once for functions
    // TODO: Use this variable for authentication login
    private static List<String> allUserData = new ArrayList<String>();
    private static List<String> allBooksData = new ArrayList<String>();

    /**
     * TODO: Code the actual login which reads in UserData.txt
     *
     * @param user the username entered
     * @param pword the password entered
     * @return isValid true for authenticated Validates whether the user entered
     * values for username and password are valid. If they are, the graphical
     * user interface should return that the user has been successfully
     * authenticated and should be taken to the login screen. If the credentials
     * are deemed invalid, the authentication should fail. This function, along
     * with the others below, will also be used to evaluate the proper
     * functionality of the unit tests as well.
     */
    public static boolean authenticate(String user, String pword) {
        userName = user;
        pass = pword;
        try {
            readUserData();
            readBookData();
        } catch (IOException ex) {
            //TODO: this should be a popup GUI message box (e.g. throw the error then catch in CMSC495_Project class).
            Logger.getLogger(UserFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean isValid = false;
        if (user.equalsIgnoreCase("BobJones1123!")
                && pword.equals("IloveBooks%123")) {
            isValid = true;
        }

        return isValid;
    }

    /**
     * Check current loans function for user. Created by Maureen O'Malley on
     * June 23, 2022 for CMSC 495
     *
     * @return String of current loans
     */
    public static String checkCurrentLoans() {
        String[] stringArr = allUserData.toArray(new String[0]);
        String[] thisUsersData = new String[4];
        StringTokenizer st;
        for (int i = 0; i < stringArr.length; i++) {
            st = new StringTokenizer(stringArr[i], ",");
            if (st.nextToken().equalsIgnoreCase(userName)) {
                int num = 0;
                while (st.hasMoreTokens()) {
                    thisUsersData[num] = st.nextToken();
                    num++;
                }
                return "The book you have checked out is: " + thisUsersData[3];
            }
        }
        return "error: could not find the username " + userName + " in the database.";
    }

    public static String checkOutBookData() {
        String out = "";
        String[] stringArr = allBooksData.toArray(new String[0]);
        String[] thisBooksData = new String[5];
        StringTokenizer st;
        for (int i = 0; i < stringArr.length; i++) {
            st = new StringTokenizer(stringArr[i], ",");
            int num = 0;
            while (st.hasMoreTokens()) {
                thisBooksData[num] = st.nextToken().trim();
                num++;
            }
            //true = avail to be checked out
            if (thisBooksData[4].equalsIgnoreCase("true")) {
                out += thisBooksData[0] + ", " + thisBooksData[1] + ", " + thisBooksData[2] + ", " + thisBooksData[3] + "\n";
            }
//            if (st.nextToken().equalsIgnoreCase(userName)) {
//                int num = 0;
//                while (st.hasMoreTokens()) {
//                    thisUsersData[num] = st.nextToken();
//                    num++;
//                }
//                return "The book you have checked out is: " + thisUsersData[3];
//            }
        }
        if (out.equals("")) {
            return "Could not find any books available to be checked out.";
        }
        return out;
    }

    public static String returnAllBooksData() {
        //TODO:
        //tokenize the arraylist
        //use check current loans to find the book you can return
        //put all into an output string
        //go to main and put output string in the window
        String[] stringArr = allBooksData.toArray(new String[0]);
        String[] thisBooksData = new String[4];
        StringTokenizer st;
        for (int i = 0; i < stringArr.length; i++) {
            st = new StringTokenizer(stringArr[i], ",");
//            if (st.nextToken().equalsIgnoreCase(userName)) {
//                int num = 0;
//                while (st.hasMoreTokens()) {
//                    thisUsersData[num] = st.nextToken();
//                    num++;
//                }
//                return "The book you have checked out is: " + thisUsersData[3];
//            }
        }
        return "Could not find an books available for return.";

//        String out = "";
//        for (String s : allBooksData) {
//         out += s + ", ";
//      }
//        if (out.equals("")) {
//            out = "No books could be found.";
//        }
//        return out;
    }

    /**
     * to read the user info file Created by Maureen O'Malley on June 23, 2022
     * for CMSC 495
     *
     * @throws FileNotFoundException file could not be found
     * @throws IOException error to be displayed in dialog box GUI
     */
    private static void readUserData() throws FileNotFoundException, IOException {
        BufferedReader in = new BufferedReader(new FileReader("UserData.txt"));
        String str;
        while ((str = in.readLine()) != null) {
            allUserData.add(str);
        }
    }

    private static void readBookData() throws FileNotFoundException, IOException {
        BufferedReader in = new BufferedReader(new FileReader("BooksData.txt"));
        String str;
        while ((str = in.readLine()) != null) {
            allBooksData.add(str);
        }
    }

}
