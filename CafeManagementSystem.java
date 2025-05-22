package cis4003_Assignment;

import java.io.*;
import java.util.Scanner;

	class CafeManagementSystem {
	    
	    private static final String ORDERS_FILE = "orders.txt";
	    private static final String MENU_FILE = "menu.txt";
	    private static final String ADMIN_USER = "admin";
	    private static final String ADMIN_PASS = "admin123";
	    private static Scanner scanner = new Scanner(System.in);

	    public static void main(String[] args) {
	        if (!authenticate()) {
	            System.out.println("**************************************");
	            System.out.println("* Authentication failed. Exiting...  *");
	            System.out.println("**************************************");
	            return;
	       
	        }

	        while (true) {
	            System.out.println("\n**************************************");
	            System.out.println("*   Café Mocha Management System   *");
	            System.out.println("**************************************");
	            System.out.println("1.  Add New Customer Order");
	            System.out.println("2.  Display Order Details");
	            System.out.println("3.  Calculate and Print Bill");
	            System.out.println("4.  Add New Item to Menu");
	            System.out.println("5.  Help");
	            System.out.println("6.  Exit");
	            System.out.print("Enter your choice: ");
	            int choice = scanner.nextInt();
	            scanner.nextLine();

	            switch (choice) {
	                case 1:
	                    addCustomerOrder();
	                    break;
	                case 2:
	                    displayOrders();
	                    break;
	                case 3:
	                    calculateBill();
	                    break;
	                case 4:
	                    addMenuItem();
	                    break;
	                case 5:
	                    showHelp();
	                    break;
	                case 6:
	                    System.out.println("**************************************");
	                    System.out.println("*   Exiting system. Goodbye!        *");
	                    System.out.println("**************************************");
	                    return;
	                default:
	                    System.out.println("Invalid choice. Try again.");
	            }
	        }
	    }

	    private static boolean authenticate() {
	        while (true) {
	        	
	        	System.out.println("");
	        	System.out.println("                                                                                                                                  # #        ");
	        	System.out.println("  #######        ####        #######  #######        ###        ###   #######    #######  ##      ##        ####                 # #          ");
	            System.out.println(" #######        ######       #######  #######        ####      ####  #########  #######   ##      ##       ######                 # #              ");
	            System.out.println(" ##            ##    ##      ##       ##             ##  ##   ## ##  ##     ##  ##        ##      ##      ##    ##          ###########              ");
	            System.out.println(" ##           ##########     #####    ######         ##   ## ##  ##  ##     ##  ##        ##########     ##########        ##         ####   ");
	            System.out.println(" ##          ##        ##    ##       ##             ##     #    ##  ##     ##  ##        ##      ##    ##        ##        ##       #   #     ");
	            System.out.println(" #######    ##          ##   ##       #######        ##          ##  #########  #######   ##      ##   ##          ##        ##     #####   ");
	            System.out.println("  #######  ##            ##  ##       #######        ##          ##   #######    #######  ##      ##  ##            ##        #######           ");
	        
	            System.out.println("");
	            
	            System.out.print("Enter username: ");
	            String username = scanner.nextLine();
	            System.out.print("Enter password: ");
	            String password = scanner.nextLine();
	            

	            if (ADMIN_USER.equals(username) && ADMIN_PASS.equals(password)) {
	            	System.out.println("");
	                System.out.println("**************************************");
	                System.out.println("*   Login successful! Welcome!      *");
	                System.out.println("**************************************");
	                return true;
	            } else {
	                System.out.println("**************************************");
	                System.out.println("* Invalid credentials. Try again.   *");
	                System.out.println("**************************************");
	            }
	        }
	    }


	    private static void addCustomerOrder() {
	        try (FileWriter fw = new FileWriter(ORDERS_FILE, true);
	             BufferedWriter bw = new BufferedWriter(fw);
	             PrintWriter out = new PrintWriter(bw)) {
	            System.out.println("\n**************************************");
	            System.out.println("*      Adding New Customer Order    *");
	            System.out.println("**************************************");
	            System.out.print("Enter Order Number: ");
	            String orderNumber = scanner.nextLine();
	            System.out.print("Enter Customer Name: ");
	            String name = scanner.nextLine();
	            System.out.print("Enter Address: ");
	            String address = scanner.nextLine();
	            System.out.print("Enter Telephone Number: ");
	            String phone = scanner.nextLine();
	            System.out.print("Enter Order Details: ");
	            String orderDetails = scanner.nextLine();
	            out.println(orderNumber + ", " + name + ", " + address + ", " + phone + ", " + orderDetails);
	            System.out.println("");
	            System.out.println("*      Order added successfully!    *");
	            System.out.println("**************************************");
	        } catch (IOException e) {
	            System.out.println("Error writing to file.");
	        }
	    }

	    private static void displayOrders() {
	        System.out.println("\n**************************************");
	        System.out.println("*        Displaying Orders          *");
	        System.out.println("**************************************");
	        try (BufferedReader br = new BufferedReader(new FileReader(ORDERS_FILE))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                System.out.println(line);
	            }
	        } catch (IOException e) {
	            System.out.println("Error reading file.");
	        }
	    }

	    private static void calculateBill() {
	        System.out.println("\n**************************************");
	        System.out.println("*        Calculate Bill             *");
	        System.out.println("**************************************");
	        System.out.print("Enter Order Number: ");
	        String orderNumber = scanner.nextLine();
	        boolean orderFound = false;
	        
	        try (BufferedReader br = new BufferedReader(new FileReader(ORDERS_FILE))) {
	        	String line;
	            while ((line = br.readLine()) != null) {
	                String[] details = line.split(", ");
	                if (details[0].equals(orderNumber)) {
	                    orderFound = true;
	                    System.out.println("\nOrder Found:");
	                    System.out.println("Customer Name: " + details[1]);
	                    System.out.println("Address: " + details[2]);
	                    System.out.println("Phone: " + details[3]);
	                    System.out.println("Order Details: " + details[4]);
	                    
	                    String[] items = details[4].split(", ");
	                    double total = 0;
	                    
	                    for (String item : items) {
	                        System.out.print("Enter price for " + item + ": ");
	                        double price = scanner.nextDouble();
	                        total += price;
	                    }
	                    
	                    double tax = total * 0.10;  
	                    double finalAmount = total + tax;
	                    
	                    System.out.println("\n**************************************");
	                    System.out.println("Welcome to Café Mocha ");
	                    System.out.println("**************************************");
	                    System.out.println("Subtotal: $" + total);
	                    System.out.println("Tax (10%): $" + tax);
	                    System.out.println("Total Amount: $" + finalAmount);
	                    break;
	                }
	            }
	            if (!orderFound) {
	                System.out.println("Order not found!");
	            }
	        } catch (IOException e) {
	            System.out.println("Error reading file.");
	        }
	    }

	    private static void addMenuItem() {
	        try (FileWriter fw = new FileWriter(MENU_FILE, true);
	             BufferedWriter bw = new BufferedWriter(fw);
	             PrintWriter out = new PrintWriter(bw)) {
	            System.out.println("\n**************************************");
	            System.out.println("*         Adding New Item           *");
	            System.out.println("**************************************");
	            System.out.print("Enter Item Name: ");
	            String itemName = scanner.nextLine();
	            System.out.print("Enter Description: ");
	            String description = scanner.nextLine();
	            System.out.print("Enter Price: ");
	            double price = scanner.nextDouble();
	            scanner.nextLine();
	            System.out.print("Enter Category: ");
	            String category = scanner.nextLine();
	            out.println(itemName + ", " + description + ", " + price + ", " + category);
	            System.out.println("");
	            System.out.println("*       Item added successfully!   *");
	            System.out.println("**************************************");
	        } catch (IOException e) {
	            System.out.println("Error writing to file.");
	        }
	    }
	    
	    private static void showHelp() {
	        System.out.println("\n**************************************");
	        System.out.println("*          Help Section             *");
	        System.out.println("**************************************");
	        System.out.println("1. Use option 1 to add a new customer order.");
	        System.out.println("2. Use option 2 to display order details.");
	        System.out.println("3. Use option 3 to calculate and print a bill.");
	        System.out.println("4. Use option 4 to add a new menu item.");
	        System.out.println("5. Use option 6 to exit the system.");
	    }
	}

	
