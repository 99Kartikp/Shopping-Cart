package shopping;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Shop {
	private Map<String, Product> products = new HashMap<>();
    private Map<String, Customer> customerMap = new HashMap<>();
    
    private Map<String, Set<String>> customerProductMap = new HashMap<>();
    
    Scanner scanner = new Scanner(System.in);
    
    private static void addProductForCustomer(Map<String, Set<String>> map, String customerId, String productId) {
        map.computeIfAbsent(customerId, k -> new HashSet<>()).add(productId);
    }
    
    public void addProduct(String pid, Product product) {
    	products.put(pid, product);
    }
    
    public void deleteProduct(String pid) {
    	  if (products.containsKey(pid)) {
              products.remove(pid);
              System.out.println("...!!! Product removed successfully. ...!!!\n ");
          } else {
              System.out.println("...!!! Invalid product id... !!! ");
          }
    }
    
    public void allProducts() {
    	 for (Map.Entry<String, Product> entry : products.entrySet()) {
             String productid = entry.getKey();
             Product product = entry.getValue();
             System.out.println("Product ID: " + productid + ", Name: " + product.getName() + " Price: " + product.getPrice());
         }
    }
    
    public void addCustomer(Customer c) {
      customerMap.put(c.getId(), c);
      System.out.println("... Customer Registered Successfully....\n");
    }
    
    public void allCustomers() {
    	 for (Map.Entry<String, Customer> entry : customerMap.entrySet()) {
             String customertid = entry.getKey();
             Customer customer = entry.getValue();
             System.out.println("Customer ID: " + customertid + ", Name: " + customer.getCustomername() + " Contact: " + customer.getCustomercontact());
         }
    }
    
    public void customerLogin(String customerid,String customerpassword) {
    	
    	//   Customer Login 
    	if (customerMap.containsKey(customerid)) {
            String password = customerMap.get(customerid).getPassword();
            System.out.println("Password for customer " + customerid + ": " + password);

            if (customerpassword.equals(password)) {
                System.out.println(".... Customer logged in Successfully....\n");

                while (true) {
                    System.out.println("Choose options:");
                    System.out.println("1. Products");
                    System.out.println("2. Cart");
                    System.out.println("3. Logout\n");

                    int choice;
                    try {
                        choice = scanner.nextInt();
                        scanner.nextLine(); 
                    } catch (Exception e) {
                        System.out.println("!!!...Enter a valid number...!!!");
                        scanner.nextLine(); 
                        continue;
                    }

                    switch (choice) {
                        case 1:
                        	
                        	
                            // All Products list

                        	boolean continueatbuying = true;
                            while (continueatbuying) {
                            	
                            	
                            	System.out.println("\nAvailable products:\n");
                                for (Map.Entry<String, Product> entry : products.entrySet()) {
                                    String productid = entry.getKey();
                                    Product product = entry.getValue();
                                    System.out.println("Product ID: " + productid + ", Name: " + product.getName() + " Price: " + product.getPrice());
                                }
                                System.out.println("===========================================================");
                                System.out.println("\nChoose an option:");
                                System.out.println("1. Buy a product");
                                System.out.println("2. Back \n");

                                int option = scanner.nextInt();
                                scanner.nextLine();

                                switch (option) {
                                    case 1:
                                    	
                                        // To buy a product 
                                    	boolean continueShopping = true;
                                    	
                                    	while(continueShopping){
                                        System.out.println("\nAvailable products:\n");
                                        for (Map.Entry<String, Product> entry : products.entrySet()) {
                                            String productid = entry.getKey();
                                            Product product = entry.getValue();
                                            System.out.println("Product ID: " + productid + ", Name: " + product.getName() + " Price: " + product.getPrice());
                                        }

                                        System.out.println("\nEnter product id: ");
                                        
                                        
                                        boolean validProduct = false;
                                        while (!validProduct) {
                                             
                                            String productid = scanner.nextLine();

                                            if (products.containsKey(productid)) {
                                            	addProductForCustomer(customerProductMap, customerid, productid);
                                                System.out.println("......Product added to cart......\n");
                                                validProduct = true;
                                            } else {
                                                System.out.println("!...Invalid product id! Please enter a valid product id...!");
                                            }
                                        }

                                        
                                        System.out.println("\nDo you want to continue shopping? (y/n)\n");
                                        String continueChoice = scanner.nextLine();
                                        if (!continueChoice.equalsIgnoreCase("y")) {
                                            continueShopping = false;
                                            System.out.println("....!!! Thank you for shopping...!!!");
                                        } 
                                   }
                                        break;

                                    case 2:
                                        continueatbuying = false;
                                        break;

                                    default:
                                        System.out.println("...!!! Invalid option. Please choose again...!!!\n");
                                }
                            }
                         
                            break;
                        case 2:
                        	
                        	// cart --> delete --> checkout
                        	
                        	System.out.println("\nSelected Items : \n");
                        	if (customerProductMap.containsKey(customerid)) {
                                Set<String> productIds = customerProductMap.get(customerid);
                                System.out.println("\n....Products for Customer  - " + customerid + ":...\n ");
                                for(String id:productIds) {
                                	Product product = products.get(id);
                                	 if (product != null) {
                                         System.out.println("Product ID: " + product.getId() + ", Name: " + product.getName() + " Price: " + product.getPrice());
                                         
                                     } else {
                                         System.out.println("!!!...Product not found for ID: " + id);
                                     } }
                                
                                
//                               
                                System.out.println("\nOptions:");
                                System.out.println("1. Delete an item");
                                System.out.println("2. Proceed to Checkout");
                                System.out.println("3. Back to Shoping\n");

                                int cartOption = scanner.nextInt();
                                scanner.nextLine(); 

                                switch (cartOption) {
                                    case 1:
                                    	
                                        // Delete an item
                                        System.out.println("Enter the product id of item to delete:");
                                        int itemToDelete = scanner.nextInt();
                                        scanner.nextLine(); 

                                        if (itemToDelete >= 1 && itemToDelete <= productIds.size()) {
                                            String productIdToDelete = (String) productIds.toArray()[itemToDelete - 1];
                                            productIds.remove(productIdToDelete);
                                            System.out.println("!!! ...Item deleted from the cart...!!!\n");
                                        } else {
                                            System.out.println("...Invalid item number....\n");
                                        }
                                        break;

                                    case 2:
                                    	
                                        // Proceed to Checkout
                                        double total = 0;
                                        Customer c = customerMap.get(customerid);
                                        System.out.println("\n......Billing.......\n");
                                        System.out.println("Product IDs for Customer ID - " + c.getCustomername() + ": \n");
                                        System.out.println("====================================================");

                                        System.out.printf("%-18s %-18s %-18s%n", "Product ID", "Name", "Price");
                                        for (String id : productIds) {
                                            Product p = products.get(id);
                                            System.out.printf("%-18s %-18s %-18s%n", p.getId(), p.getName(), p.getPrice());
                                            total += p.getPrice();
                                        }

                                        System.out.println("===================================================");
                                        System.out.printf("%-36s %-18s%n", "Total :", total);

                                        
                                        
                                        System.out.println("-------------------------------------------------------------");
                                        System.out.println("Product will be deliver to address: "+ c.getCustomeraddress());
                                        System.out.println("-------------------------------------------------------------");
                                        
                                        // Empty cart after checkout
                                        
                                        customerProductMap.remove(customerid);
                                        System.out.println("@@@......Checkout completed. Cart cleared......@@@\n\n");
                                        break;

                                    case 3:
                                    	
                                        // Back to previous menu
                                        System.out.println("Returning to previous menu...\n");
                                        break;

                                    default:
                                        System.out.println("....Invalid choice....\n");
                                }
                                
                          }else {
                                System.out.println("....No Product Purchased....\n");
                            }
                            break;
                        case 3:
                            System.out.println("....Exiting customer actions...\n");
                            return;    
                        default:
                            System.out.println("....Invalid choice....\n");
                    }
                }
            } else {
                System.out.println("!!..Password for customer " + customerid + " is Invalid..!!");
            }
        } else {
            System.out.println("!!..Customer not found...!!");
        }
    	
    }
}
