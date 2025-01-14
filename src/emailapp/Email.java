package emailapp;

import java.util.Scanner;

public class Email {
    private final String firstName;
    private final String lastName;
    private String password;
    private final String department;
    private final String email;
    private int mailboxCapacity = 500;
    private final int defaultPasswordLenght = 10;
    private String alternateEmail;
    private final String companySuffix = ".aeycompany.com";
    
    // Constructor to receive the first name and last name
    public Email(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
        
        // Call a method asking for the department - return the department
        this.department = setDepartment();
        System.out.println("Department: " + this.department);
        
        // Combine elements to generate email
        this.email = firstName.toLowerCase() + "." +lastName.toLowerCase() + "@" + department + companySuffix;
        System.out.println("EMAIL CREATED: " + this.email);
        
        // Call a method that returns a random password
        this.password = randomPassword(defaultPasswordLenght);
        System.out.println("Your password is: " + this.password);
    }
    
    
    // Ask for the department
    private String setDepartment(){
        System.out.print("DEPARTMENT CODES\n1- for Sales\n2- for Development \n3- for Accounting\n0- for none\nEnter deparment code: ");
        Scanner in = new Scanner(System.in);
        int depChoice = in.nextInt();
        if(depChoice == 1) { return "sales"; }
        else if(depChoice == 2) { return "dev"; }
        else if(depChoice == 3) { return "acct"; }
        else { return ""; }
    }
    
    // Generate a random password
    private String randomPassword(int lenght){
        String passwordSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%";
        char[] password = new char[lenght];
        for(int i=0;i<lenght;i++){
            int rand = (int) (Math.random() * passwordSet.length());
            password[i] = passwordSet.charAt(rand);
            
        }
        return new String (password);
        
    }
    
    // Set the mailbox capacity
    public void setMailBoxCapacity(int capacity) {
        this.mailboxCapacity = capacity;
    }
    
    public int getMailBoxCapacity(){
        return this.mailboxCapacity;
    }
    
    // Set the alternate email
    public void setAlternateEmail(String altEmail){
        this.alternateEmail = altEmail;
    }
    
    public String getAlternativeEmail(){
        return this.alternateEmail;
    }
    
    // Change the password
    public void changePassword(String password){
        this.password = password;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    @Override
    public String toString(){
        return "DISPLAY NAME: " + firstName + " " + lastName +
                "\nCOMPANY EMAIL: " + this.email +
                "\nMAILBOX CAPACITY: " + mailboxCapacity + "mb" +
                "\nALTERNATIVE E-MAIL: " + (this.alternateEmail == null ? "NONE" : getAlternativeEmail()) + "\n";
    }
    
}
