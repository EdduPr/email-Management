package emailapp;

import java.util.Scanner;

public class Email {
    private String name;
    private final String firstName;
    private final String lastName;
    private String password;
    private final String department;
    private final String email;
    private int mailboxCapacity = 500;
    private final int defaultPasswordLenght = 10;
    private String alternateEmail;
    private final String companySuffix = "aeycompany.com";
    
    // Constructor to receive the first name and last name
    /*public Email(String firstName, String lastName){
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
    }*/
    
    public Email(String name){
        this.name = name;
        this.firstName = setFirstName(name);
        this.lastName = setLastName(name);
        this.department = setDepartment();
        this.email = setEmail(this.firstName, this.lastName, this.department);
        this.password = randomPassword(defaultPasswordLenght);
        
    }
    
    private String setEmail(String firstName, String lastName, String dep) {
    String domain = dep.equals("") ? "aeycompany.com" : dep + ".aeycompany.com";
    return firstName.toLowerCase() + "." + lastName.toLowerCase() + "@" + domain;
    }

    
    // Ask for the department
    public String setDepartment(){
        System.out.print("DEPARTMENT CODES\n1- for Sales\n2- for Development \n3- for Accounting\n0- for none\nEnter deparment code: ");
        Scanner in = new Scanner(System.in);
        int depChoice = getOption(in,3);
        switch (depChoice) {
            case 1:
                return "sales";
            case 2:
                return "dev";
            case 3:
                return "acct";
            default:
                return "";
        }
    }
    
    // Generate a random password
    private String randomPassword(int lenght){
        String passwordSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%";
        char[] password = new char[lenght];
        for(int i=0;i<lenght;i++){
            int rand = (int) (Math.random() * passwordSet.length());
            password[i] = passwordSet.charAt(rand);
            
        }
        //System.out.println("Your password is: " + password);
        return new String (password);
        
    }
    
    public String setFirstName(String name){
        String[] arrOfStr = name.split(" ");
        return arrOfStr[0];
    }
    
    public String setLastName(String name){
        String[] arrOfStr = name.split(" ");
        return arrOfStr[arrOfStr.length-1];
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
        return "DISPLAY NAME: " + this.name +
                "\nCOMPANY EMAIL: " + this.email +
                "\nMAILBOX CAPACITY: " + mailboxCapacity + "mb" +
                "\nALTERNATIVE E-MAIL: " + (this.alternateEmail == null ? "NONE" : getAlternativeEmail()) + "\n";
    }
    
    public String getName(){
        return this.name;
    }
    
    public static int getOption(Scanner scan, int limit){
        int option = 0;
        String in;
        boolean validEnter = false;
        while(!validEnter){
            try {
                in = scan.nextLine();
                option = Integer.parseInt(in);
                if(option < 0 || option > limit){
                    throw new Exception();
                }
                
                validEnter = true;
            } catch (Exception e) {
                System.out.println("Invalid option, enter again");
            }
        }
        
        return option;
    }
    
    public String getEmail(){
        return this.email;
    }
    
}
