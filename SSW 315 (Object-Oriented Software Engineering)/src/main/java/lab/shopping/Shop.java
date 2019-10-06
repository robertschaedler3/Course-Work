package lab.shopping;

import java.util.ArrayList;
import java.util.Scanner;

public class Shop {
    public static void main(String[] args) {
        // *** declare and instantiate a variable cart to be an empty ArrayList
        int free = 10;
        ArrayList<Item> cart = new ArrayList<Item>(10);

        Item item;
        String itemName;
        double itemPrice;
        int quantity;
        Scanner scan = new Scanner(System.in);

        String keepShopping = "y";
        do {
            System.out.print("Enter the name of the item: ");
            itemName = scan.nextLine();
            System.out.print("Enter the unit price: ");
            itemPrice = scan.nextDouble();
            System.out.print("Enter the quantity: ");
            quantity = scan.nextInt();

            // *** create a new item and add it to the cart
            item = new Item(itemName, itemPrice, quantity);
            if (free == 0) {
                // resize
                ArrayList<Item> resizedCart = new ArrayList<>(free * 2);
                for (int i = 0; i < free; i++) {
                    resizedCart.set(i, cart.get(i));
                }
                free *= 2;
            }

            cart.add(item);
            free--;

            // *** print the contents of the cart object using println

            // System.out.println(cart);

            System.out.println("\nCart\n-------------------------------------");
            System.out.println("Item\t\tPrice\t#\tTotal");
            System.out.println("-------------------------------------");
            double cartTotal = 0;
            for (int i = 0; i < cart.size(); i++) {
                item = cart.get(i);
                double itemTotal = item.getPrice() * item.getQuantity();
                System.out.println(
                        item.getName() + "\t\t" + item.getPrice() + "\t" + item.getQuantity() + "\t" + itemTotal);
                cartTotal += itemTotal;
            }
            System.out.println("-------------------------------------");
            System.out.println("\t\t\tTOTAL:\t" + cartTotal);

            System.out.print("Continue shopping (y/n)? ");
            scan.nextLine();
            keepShopping = scan.nextLine();
        } while (keepShopping.equals("y"));

        scan.close();
    }
}
