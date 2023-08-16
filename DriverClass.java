package shopping;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class DriverClass {
	
    private Map<String, String> adminCredentials;
    
    Shop shop = new Shop();
    
    Scanner scanner = new Scanner(System.in);
    
    public DriverClass() {
        adminCredentials = new HashMap<>();
        adminCredentials.put("a", "a");
    }

    

    
    public void customerRegister() {

    	 Scanner sc = new Scanner(System.in);
    	 System.out.print("Enter customer id: ");
         String id = sc.nextLine();
         System.out.print("Enter name: ");
         String name = sc.nextLine();
         System.out.print("Enter contact: ");
         String contact = sc.nextLine();
         System.out.print("Enter address: ");
         String address = sc.nextLine();

         boolean passwordMatch = false;
         String password = null;

         while (!passwordMatch) {
             System.out.print("Enter password: ");
             password = sc.nextLine();
             System.out.print("Confirm password: ");
             String confirmPassword = sc.nextLine();
             if (password.equals(confirmPassword)) {
                 passwordMatch = true;
             } else {
                 System.out.println("Password and confirmation do not match.");
             }
         }

         Customer customer = new Customer(id, name, contact, address, password);
         shop.addCustomer(customer);
    }

    public void adminLogin() {
         boolean success=false;
        while(!success){
        System.out.print("Enter admin username: ");
        String adminUsername = scanner.nextLine();

        System.out.print("Enter admin password: ");
        String adminPassword = scanner.nextLine();

        if (authenticate(adminCredentials, adminUsername, adminPassword)) {
        	success=true;
            System.out.println("Admin logged in successfully\n");

            while (true) {
                System.out.println("1. Add Product");
                System.out.println("2. Delete Product");
                System.out.println("3. List all Products");
                System.out.println("4. List all Customers");
                System.out.println("5. Back\n");

                int choice;
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline left after nextInt()
                } catch (Exception e) {
                    System.out.println("... Choose  a valid opstion..!!!\n");
                    scanner.nextLine(); // Consume the invalid input
                    continue; // Skip the rest of the loop and start from the beginning
                }

                switch (choice) {
                    case 1:
                        // add product
                        System.out.print("Enter Product id: ");
                        String pid = scanner.nextLine();
                        System.out.print("Enter Product name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Product price: ");
                        float price = 0;
                        boolean validPrice = false;
                        while (!validPrice) {
                            try {
                                price = Float.parseFloat(scanner.nextLine());
                                if (price <= 0) {
                                    System.out.println("Enter a valid positive price.");
                                } else {
                                    validPrice = true;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Enter a valid numerical price.");
                            }
                        }
                        Product product = new Product(pid, name, price);
                        shop.addProduct(pid,product);
                        System.out.println();
                        break;
                    case 2:
                        // deleting product
                        System.out.println("Enter Product id:");
                        String id = scanner.nextLine();
                        shop.deleteProduct(id);
                        System.out.println();
                      
                        break;
                    case 3:
                        // Logic for listing all products
                    	System.out.println();
                        System.out.println("Available Products: ");
                        shop.allProducts();
                        System.out.println();
                        break;
                    case 4:
                        // Logic for listing all customers
                        System.out.println("Available Customers: ");
                        shop.allCustomers();
                        System.out.println();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("!!!...Invalid input ...!!!");
                }
            }
        } else {
            System.out.println("!!!...Admin login failed...Try again !!!");
        }
        
        }

       
    }

    public void customerLogin() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter customer userid: ");
        String customerid = scanner.nextLine();

        System.out.print("Enter customer password: ");
        String customerpassword = scanner.nextLine();
  
        shop.customerLogin(customerid,customerpassword);
        

       
    }

    private boolean authenticate(Map<String, String> credentials, String username, String password) {
        return credentials.containsKey(username) && credentials.get(username).equals(password);
    }
}
