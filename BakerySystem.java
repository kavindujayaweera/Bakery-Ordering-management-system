package bakery_ordering;

import java.util.ArrayList;
import java.util.Scanner;

class Product {
    String name;
    double price;
    int stock;

    Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}

class Customer {
    String name;
    String phoneNumber;
    String address;

    Customer(String name, String phoneNumber, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}

public class BakerySystem {
    static ArrayList<Product> productList = new ArrayList<>();
    static ArrayList<Customer> customerList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize some products
        productList.add(new Product("Donuts", 500, 100));
        productList.add(new Product("Cookies", 300, 50));
        productList.add(new Product("Cupcakes", 350, 75));
        productList.add(new Product("Cheesecake", 450, 30));

        // Display Welcome Message and main menu
        while (true) {
            System.out.println("WELCOME TO THE BAKERY SYSTEM");
            System.out.println("================================");
            System.out.println("Please choose an option:");
            System.out.println("1. Customer");
            System.out.println("2. Admin");
            System.out.println("3. Employee");
            System.out.println("4. Exit");

            // Get the user's choice
            int choice = scanner.nextInt();

            // Handle the user's choice
            switch (choice) {
                case 1:
                    // Customer Section
                    System.out.println("You chose Customer. Welcome!");
                    customerMenu();
                    break;

                case 2:
                    // Admin Section
                    System.out.println("You chose Admin. Welcome!");
                    adminMenu();
                    break;

                case 3:
                    // Employee Section
                    System.out.println("You chose Employee. Welcome!");
                    employeeMenu();
                    break;

                case 4:
                    System.out.println("Exiting... Thank you for visiting the bakery!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please select 1, 2, 3, or 4.");
            }
        }
    }

    // Customer Menu
    public static void customerMenu() {
        while (true) {
            // Display Bakery Items with Prices and Cash on Delivery Option
            System.out.println("Here are the available bakery items: (Cash on Delivery)");
            for (int i = 0; i < productList.size(); i++) {
                Product product = productList.get(i);
                System.out.println((i + 1) + ". " + product.name + " - Rs." + product.price + "/- (Stock: " + product.stock + ")");
            }

            // Ask the customer to choose an item
            System.out.println("Please choose an item by number (1-" + productList.size() + "), or type 0 to go back:");
            int itemChoice = scanner.nextInt();

            if (itemChoice == 0) {
                return; // Go back to the main menu
            }

            if (itemChoice < 1 || itemChoice > productList.size()) {
                System.out.println("Invalid selection. Please choose a valid item.");
                continue;
            }

            // Ask for quantity
            Product selectedProduct = productList.get(itemChoice - 1);
            System.out.println("How many would you like to order? (Stock: " + selectedProduct.stock + ")");
            int quantity = scanner.nextInt();

            if (quantity > selectedProduct.stock) {
                System.out.println("Sorry, not enough stock available.");
                continue;
            }

            // Calculate the total price
            double totalPrice = quantity * selectedProduct.price;
            System.out.println("You ordered " + quantity + " of " + selectedProduct.name + ".");
            System.out.println("Total price: Rs." + totalPrice + "/-");

            // Confirm Order
            System.out.println("Do you want to confirm your order? (yes/no)");
            String confirmation = scanner.next();

            if (confirmation.equalsIgnoreCase("yes")) {
                // Ask for customer details (phone number and address)
                System.out.println("Please enter your name:");
                String name = scanner.next();
                System.out.println("Please enter your phone number:");
                String phoneNumber = scanner.next();
                System.out.println("Please enter your delivery address:");
                scanner.nextLine(); // Consume the newline character
                String address = scanner.nextLine();

                // Add customer to the list
                customerList.add(new Customer(name, phoneNumber, address));

                // Update stock after the order
                selectedProduct.stock -= quantity;

                // Generate a token number
                String tokenNumber = generateTokenNumber();

                // Display token number and customer details
                System.out.println("Your token number is: " + tokenNumber);
                System.out.println("Your delivery details:");
                System.out.println("Name: " + name);
                System.out.println("Phone Number: " + phoneNumber);
                System.out.println("Address: " + address);

                // Confirmation message after the token number
                System.out.println("Your order has been confirmed!");
                System.out.println("Thank you for shopping with us!");
            } else {
                System.out.println("Your order has been canceled.");
            }

            System.out.println("\nPress 0 to go back or any other key to confirm the order.");
            String backChoice = scanner.next();
            if (backChoice.equals("0")) {
                return; // Go back to the main menu
            }
        }
    }

    // Admin Menu
    public static void adminMenu() {
        while (true) {
            System.out.println("Admin Menu:");
            System.out.println("1. View Orders");
            System.out.println("2. View Customer Details");
            System.out.println("3. Update Product");
            System.out.println("4. Check Stock");
            System.out.println("0. Back");

            // Get Admin's choice
            int adminChoice = scanner.nextInt();

            if (adminChoice == 0) {
                return; // Go back to the main menu
            }

            // Handle Admin's choice
            switch (adminChoice) {
                case 1:
                    viewOrders();
                    break;

                case 2:
                    viewCustomerDetails();
                    break;

                case 3:
                    updateProduct();
                    break;

                case 4:
                    checkStock();
                    break;

                default:
                    System.out.println("Invalid option. Please choose a valid option from the menu.");
            }

            System.out.println("\nPress 0 to go back or any other key to continue.");
            String backChoice = scanner.next();
            if (backChoice.equals("0")) {
                return; // Go back to the main menu
            }
        }
    }

    // Employee Menu
    public static void employeeMenu() {
        while (true) {
            System.out.println("Employee Menu:");
            System.out.println("1. View Orders");
            System.out.println("2. Process Orders (Update Status)");
            System.out.println("3. Check Stock");
            System.out.println("0. Back");

            // Get Employee's choice
            int employeeChoice = scanner.nextInt();

            if (employeeChoice == 0) {
                return; // Go back to the main menu
            }

            // Handle Employee's choice
            switch (employeeChoice) {
                case 1:
                    viewOrders();
                    break;

                case 2:
                    processOrders();
                    break;

                case 3:
                    checkStock();
                    break;

                default:
                    System.out.println("Invalid option. Please choose a valid option from the menu.");
            }

            System.out.println("\nPress 0 to go back or any other key to continue.");
            String backChoice = scanner.next();
            if (backChoice.equals("0")) {
                return; // Go back to the main menu
            }
        }
    }

    // View Orders method
    public static void viewOrders() {
        System.out.println("Displaying all orders...");
        // Code to fetch and display orders
        for (Customer customer : customerList) {
            System.out.println("Customer Name: " + customer.name);
            System.out.println("Phone: " + customer.phoneNumber);
            System.out.println("Address: " + customer.address);
            System.out.println("--------");
        }
    }

    // View Customer Details method
    public static void viewCustomerDetails() {
        System.out.println("Displaying customer details...");
        for (Customer customer : customerList) {
            System.out.println("Name: " + customer.name);
            System.out.println("Phone: " + customer.phoneNumber);
            System.out.println("Address: " + customer.address);
            System.out.println("--------");
        }
    }

    // Update Product method
    public static void updateProduct() {
        System.out.println("Which product would you like to update? (1-" + productList.size() + ")");
        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            System.out.println((i + 1) + ". " + product.name + " - Rs." + product.price + "/- (Stock: " + product.stock + ")");
        }
        int productChoice = scanner.nextInt();

        if (productChoice < 1 || productChoice > productList.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        Product selectedProduct = productList.get(productChoice - 1);

        System.out.println("Update details for " + selectedProduct.name + ":");
        System.out.println("Enter new price:");
        selectedProduct.price = scanner.nextDouble();
        System.out.println("Enter new stock quantity:");
        selectedProduct.stock = scanner.nextInt();

        System.out.println("Product updated successfully!");
    }

    // Process Orders method (Employee)
    public static void processOrders() {
        System.out.println("Processing orders...");
        // Code to update order status (e.g., mark as "Delivered")
        // In this example, it will simply simulate this by displaying a message.
        System.out.println("All orders have been processed successfully!");
    }

    // Check Stock method
    public static void checkStock() {
        System.out.println("Current Stock Levels:");
        for (Product product : productList) {
            System.out.println(product.name + ": " + product.stock);
        }
    }

    // Generate Token Number for the Customer's Order
    public static String generateTokenNumber() {
        return "TK" + (int)(Math.random() * 10000);
    }
}
