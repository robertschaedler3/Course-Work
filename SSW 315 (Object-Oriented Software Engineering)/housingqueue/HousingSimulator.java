package homework.housingqueue;

import java.util.ArrayList;
import java.util.Scanner;

public class HousingSimulator {

    public static MyQueue<Student> students = new MyQueue<>();
    public static ArrayList<Apartment> apartments = new ArrayList<>();

    // Functions for generating random double
    public static double randomDouble(int from, int to) {
        return Math.random() * to + from;
    }

    // Functions for generating random int
    public static int randomInt(int from, int to) {
        return (int) Math.random() * to + from;
    }

    // To be completed by you
    public static void runSimulation(int k, int N) {

        for (int j = 0; j < N; j++)
            apartments.add(new Apartment(randomDouble(0, 1), j, 0));

        for (int i = 0; i < k; i++)
            students.offer(new Student(randomDouble(0, 1), i));

        int year = 0;
        while (!allAptsVacant() && students.size() > 0) {

            System.out.println("------------------------------------------\nYEAR " + year
                    + "\n------------------------------------------");
            listVacancies();

            // Assignment process
            for (Apartment apt : apartments) {
                if (students.size() > 0) {
                    Student student = students.peek();
                    if (apt.getQuality() > student.getQualityThreshold() && apt.getYearsLeft() == 0) { // student
                                                                                                       // accepts
                        apt.setYearsLeft(randomInt(1, 5));
                        System.out.println("Student " + student.getIDNum() + " accepted apt " + apt.getIDNum()
                                + " after waiting " + student.getYearsOnList() + ". \n\tApt will be occupied for "
                                + apt.getYearsLeft() + " years.");
                        students.poll();
                    } else { // student rejects
                        System.out.println("Student " + student.getIDNum() + " (threhold: "
                                + student.getQualityThreshold() + ") rejected apt " + apt.getIDNum() + " with quality "
                                + apt.getQuality() + ".");
                        student.addDesperation();
                        student.addYear();
                        students.offer(students.poll());
                    }
                }
                // Decrement apartment years left
                for (int i = 0; i < apartments.size(); i++) {
                    int yearsLeft = apartments.get(i).getYearsLeft();
                    if (yearsLeft > 0)
                        apartments.get(i).setYearsLeft(yearsLeft - 1);
                }
            }
            System.out.println("------------------------------------------\n");
            year += 1;
        }

    }

    public static boolean allAptsVacant() {
        int i = 0;
        while (i < apartments.size() && apartments.get(i).getYearsLeft() == 0)
            i++;
        return i == apartments.size() - 1;
        // for (int i = 0; i < apartments.size(); i++) {
        // if (apartments.get(i).getYearsLeft() != 0) {
        // return false;
        // }
        // }
        // return true;
    }

    public static void listVacancies() {
        System.out.println(
                "\nVacancies:\n----------------------------------\nID\t\tQUALITY\n----------------------------------");

        for (Apartment apt : apartments) {
            if (apt.getYearsLeft() == 0)
                System.out.println(apt.getIDNum() + "\t\t" + apt.getQuality());
        }
        System.out.println("----------------------------------\n");
    }

    // Main method for simulation
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of students to run simulation with: ");
        int k = sc.nextInt();

        System.out.print("Enter number of apartments to run simulation with: ");
        int N = sc.nextInt();

        runSimulation(k, N);

        sc.close();
    }
}
