package lab.shopping;

import java.text.NumberFormat;
import java.util.Scanner;

public class ShoppingCart {
    private int itemCount; // total number of items in the cart
    private double totalPrice; // total price of items in the cart
    private int capacity; // current cart capacity
    private Item[] cart;

    public ShoppingCart() {
        capacity = 5;
        itemCount = 0;
        totalPrice = 0.0;
        cart = new Item[capacity];
    }

    public void addToCart(String itemName, double price, int quantity) {
        if (capacity == itemCount) {
            this.increaseSize();
        }
        cart[itemCount] = new Item(itemName, price, quantity);
        totalPrice += price * quantity;
        itemCount++;
    }

    public String toString() {
        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        int cartTotal = 0;
        String contents = "\nShopping Cart\n-------------------------------------";
        contents += "\nItem\t\tPrice\t#\tTotal";
        contents += "\n-------------------------------------\n";
        for (int i = 0; i < itemCount; i++) {
            Item item = cart[i];
            double itemTotal = item.getPrice() * item.getQuantity();
            contents += item.getName() + "\t\t" + fmt.format(item.getPrice()) + "\t" + item.getQuantity() + "\t"
                    + fmt.format(itemTotal) + "\n";
            cartTotal += itemTotal;
        }
        contents += "\n-------------------------------------\n";
        contents += "\t\t\tTOTAL:\t" + fmt.format(cartTotal) + "\n";
        return contents;
    }

    private void increaseSize() {
        int newSize = itemCount + 3;
        Item[] newCart = new Item[newSize];
        for (int i = 0; i < itemCount; i++) {
            newCart[i] = cart[i];
        }
        capacity = newSize;
        this.cart = newCart;
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String itemName;
        double price;
        int quantity;
        ShoppingCart cart = new ShoppingCart();

        String keepShopping = "y";

        do {
            System.out.print("Enter the name of the item: ");
            itemName = scan.nextLine();
            System.out.print("Enter the unit price: ");
            price = scan.nextDouble();
            System.out.print("Enter the quantity: ");
            quantity = scan.nextInt();

            // *** create a new item and add it to the cart

            cart.addToCart(itemName, price, quantity);
            ;

            // *** print the contents of the cart object using println

            System.out.println(cart);

            System.out.print("Continue shopping (y/n)? ");
            scan.nextLine();
            keepShopping = scan.nextLine();
        } while (keepShopping.equals("y"));

        scan.close();
    }
}
