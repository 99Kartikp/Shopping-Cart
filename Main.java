package shopping;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        DriverClass driver = new DriverClass();
        

        Scanner sc = new Scanner(System.in);

        while (true) {
        	
            int choice;

            System.out.println("Choose option: ");
            System.out.println("1. Admin Login");
            System.out.println("2. Customer Login");
            System.out.println("3. Customer Register");
            System.out.println("4. Exit");

            try {
                choice = sc.nextInt();
                sc.nextLine(); 
            } catch (InputMismatchException e) {
                System.out.println("... Choose a valid option.. !!! \n");
                sc.nextLine(); 
                continue; 
            }

            switch (choice) {
                case 1:
                    driver.adminLogin();
                    break;
                case 2:
                    driver.customerLogin();
                    break;
                case 3:
                    driver.customerRegister();
                    break;
                case 4:
                    System.out.println("Exiting....");
                    System.exit(0);
                    break;
                default:
                    System.out.println("!... Choose a valid option...!!!\n");
            }
        }
        
        
    }
}
