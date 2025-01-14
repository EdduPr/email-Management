package emailapp;

import java.util.ArrayList;
import java.util.Scanner;

public class EmailApp {
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Email> emailList = new  ArrayList<Email>();
        
        // FOR TESTS.
        Email em1 = new Email("Eduardo","Prado");
        emailList.add(em1);
        Email em2 = new Email("John","Smith");
        emailList.add(em2);
        Email em3 = new Email("Bruce","Wayne");
        emailList.add(em3);
        
        while(true){
            showMenu();
            int option = getOption(scan);
            switch (option) {
                case 0 -> {
                    System.out.println("System closing...");
                    System.exit(0);
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
        System.out.println("2 - Rempve a employee");
        System.out.println("3 - List all employees");
        System.out.println("4 - Change an employee's password");
        System.out.println("5 - Set an alternative email for an employee");
        System.out.println("0 - Close");
        System.out.println("X==============================================X");
        System.out.print("ENTER WITH THE DESIRED OPTION: ");
    }
    
    public static int getOption(Scanner scan){
        int option = 0;
        String in;
        boolean validEnter = false;
        while(!validEnter){
            try {
                in = scan.nextLine();
                option = Integer.parseInt(in);
                if(option < 0 || option > 5){
                    throw new Exception();
                }
                
                validEnter = true;
            } catch (Exception e) {
                System.out.println("Invalid option, enter again");
            }
        }
        
        return option;
    }
    
    public static void addEmployee(Scanner scan, ArrayList<Email> emailList){
        
        System.out.print("FIRST NAME: ");
        String firstName = scan.next();
        System.out.print("LAST NAME: ");
        String lastName = scan.next();
        scan.nextLine();
        Email employee;
        employee = new Email(firstName,lastName);
        emailList.add(employee);
        
        System.out.println(firstName + " " + lastName + " ADDED SUCCESSFULLY!");
    }
    
    public static void removeEmployee(Scanner scan, ArrayList<Email> emailList){
        
        emailList.remove(emailList.size()-1);
        System.out.println("Employee Removed.");
        
    }
    
    public static void printEmailList(ArrayList<Email> emailList){
        System.out.println("EMAIL LIST: \n");
        for(int i = 0; i < emailList.size();i++){
            System.out.println(emailList.get(i).toString() + "\n");
        }
    }
    
    public static void changePassword(Scanner scan, ArrayList<Email> emailList){
        System.out.print("Enter the new password: ");
        String password = scan.next();
        scan.nextLine();
        emailList.get(emailList.size()-1).changePassword(password);
        System.out.println("Password changed successfully!");
    }
    
    public static void setAlternativeEmail(Scanner scan, ArrayList<Email> emailList){
        System.out.print("Enter with the new e-mail: ");
        String altEmail = scan.next();
        scan.nextLine();
        emailList.get(emailList.size()-1).setAlternateEmail(altEmail);
        System.out.println("Alternative email added successfully");
    }
}
