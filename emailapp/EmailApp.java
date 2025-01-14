package emailapp;

import java.util.ArrayList;
import java.util.Scanner;
import emailapp.AuthenticationException;

public class EmailApp {
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Email> emailList = new  ArrayList<Email>();
        /* FOR TESTS.
        addEmployee(emailList, "Eduardo Prado Morais");
        addEmployee(emailList, "John Smith");
        addEmployee(emailList, "Bruce Wayne"); */
        
        while(true){
            showMenu();
            int option = Email.getOption(scan,5);
            switch (option) {
                case 0 -> {
                    System.out.println("System closing...");
                    System.exit(0);
                    scan.close();
                }
                case 1 -> {
                    addEmployee(scan,emailList);
                }
                case 2 -> {
                    removeEmployee(scan, emailList);
                }
                case 3 -> {
                    printEmailList(emailList);
                }
                case 4 -> {
                    changePassword(scan, emailList);
                }
                case 5 -> {
                    setAlternativeEmail(scan, emailList);
                }
            }
            
        }
        
    }
    public static void showMenu(){
        System.out.println("\nX==================== MENU ====================X");
        System.out.println("1 - Add a new employee");
        System.out.println("2 - Remove a employee");
        System.out.println("3 - List all employees");
        System.out.println("4 - Change an employee's password");
        System.out.println("5 - Set an alternative email for an employee");
        System.out.println("0 - Close");
        System.out.println("X==============================================X");
        System.out.print("ENTER WITH THE DESIRED OPTION: ");
    }
    
    public static void addEmployee(Scanner scan, ArrayList<Email> emailList){
        
        try {
            System.out.print("EMPLOYEE'S NAME: ");
            String name = getValidName(scan);
            Email employee;
            employee = new Email(name);
            emailList.add(employee);

            System.out.println("EMPLOYEE ADDED SUCCESSFULLY!");
            System.out.println(name + " EMAIL IS: " + employee.getEmail());
            System.out.println(name + " PASSWORD IS: " + employee.getPassword());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    public static void addEmployee(ArrayList<Email> emailList, String name){
        Email employee;
        employee = new Email(name);
        emailList.add(employee);
        
        System.out.println("EMPLOYEE ADDED SUCCESSFULLY!");
        System.out.println(name + " EMAIL IS: " + employee.getEmail());
        System.out.println(name + " PASSWORD IS: " + employee.getPassword());
        
    }
    
    public static void removeEmployee(Scanner scan, ArrayList<Email> emailList){
        try {
            if(!emailList.isEmpty()){
                int index = getIndexByName(scan, emailList);
                emailList.remove(index);
                System.out.println("Employee removed successfully.");
            }else{
                throw new ArrayIndexOutOfBoundsException();
            }
            
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("List is empty");
        }
        
    }
    
    public static void printEmailList(ArrayList<Email> emailList){
        System.out.println("EMAIL LIST: \n");
        for(int i = 0; i < emailList.size();i++){
            System.out.println(emailList.get(i).toString() + "\n");
        }
    }
    
    public static void changePassword(Scanner scan, ArrayList<Email> emailList){
        try {
            int index = getIndexByName(scan, emailList);
            System.out.print("Enter the new password: ");
            String password = scan.next();
            scan.nextLine();
            emailList.get(index).changePassword(password);
            System.out.println("Password changed successfully!");
            
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    public static void setAlternativeEmail(Scanner scan, ArrayList<Email> emailList){
        try {
            int index = getIndexByName(scan, emailList);
            boolean validPassword = authenticatePassword(scan, emailList, index);
            if(validPassword){
                System.out.print("Enter with the new e-mail: ");
                String altEmail = scan.next();
                scan.nextLine();
                emailList.get(index).setAlternateEmail(altEmail);
                System.out.println("Alternative email added successfully");
            }else{
                throw new IllegalArgumentException();
            }
            
        } catch (IllegalArgumentException | AuthenticationException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    public static int getIndexByName(Scanner scan, ArrayList<Email> emailList){
        System.out.println("Enter with employee name.");
        String name = scan.nextLine();
        
        for(int i =0; i < emailList.size(); i++){
            if(emailList.get(i).getName().equalsIgnoreCase(name)){
                return i;
            }
        }
        throw new IllegalArgumentException("Name not found in the list.");
    }
    
    public static boolean authenticatePassword(Scanner scan, ArrayList<Email> emailList, int index){
        System.out.print("Enter with " + emailList.get(index).getName()+ " password: ");
        String password = scan.next();
        scan.nextLine();
        if(password.equals(emailList.get(index).getPassword())){
            return true;
        }
        
        throw new AuthenticationException("Incorrect password");
    }
    
    public static String getValidName(Scanner scan){
        boolean validEnter = false;
        String name = null;
        while(!validEnter){
            name = scan.nextLine();
            if(name.equals("")){
                throw new IllegalArgumentException("Invalid name");
            }else{
                validEnter = true;
            }
        }
        
        return name;
    }
}
