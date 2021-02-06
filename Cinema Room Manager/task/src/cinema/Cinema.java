package cinema;

import java.util.*;

public class Cinema {

    public static Scanner sc = new Scanner(System.in);
    public static int rows;
    public static int seatsPerRow;
    public static int numberOfSeats;
    public static int maxIncome = 0;
    public static int numberOfPurchasedSeats = 0;
    public static int currentIncome = 0;
    public static String[][] seats;
    public static int menuOptionSelected = -1;

    public static void main(String[] args) {
        // Write your code here
        inputNumberOfRowsAndSeatsPerRow();
        while (menuOptionSelected != 0) {
            selectMenuOption();
        }
    }

    private static void selectMenuOption() {
        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        menuOptionSelected = sc.nextInt();
        switch (menuOptionSelected) {
            case 1:
                printSeats();
                break;
            case 2:
                selectOneSeat();
                break;
            case 3:
                statistics();
            default:
        }
    }

    private static void statistics() {
        System.out.printf("Number of purchased tickets: %d%n", numberOfPurchasedSeats);
        System.out.printf("Percentage: %.2f%%%n", 1.0 * numberOfPurchasedSeats / numberOfSeats * 100);
        System.out.printf("Current income: $%d%n", currentIncome);
        System.out.printf("Total income: $%d%n", maxIncome);
    }

    private static void selectOneSeat() {
        boolean executeNewSelection = true;
        int selectedRow = 0;
        int selectedSeat = 0;
        while (executeNewSelection) {
            System.out.println("Enter a row number:");
            selectedRow = sc.nextInt();
            System.out.println("Enter a seat number in that row:");
            selectedSeat = sc.nextInt();
            System.out.println();
            if (selectedRow < 1 ||
                    selectedRow > rows ||
                    selectedSeat < 1 ||
                    selectedSeat > seatsPerRow
            ) {
                System.out.println("Wrong input!");
            } else if ("B".equals(seats[selectedRow - 1][selectedSeat - 1])) {
                System.out.println("That ticket has already been purchased!");
            } else {
                executeNewSelection = false;
            }
        }
        numberOfPurchasedSeats++;
        int ticketPrice = numberOfSeats > 60 && selectedRow > rows / 2 ? 8 : 10;
        currentIncome += ticketPrice;
        System.out.println("Ticket price: $" + ticketPrice);
        seats[selectedRow - 1][selectedSeat - 1] = "B";
    }

    private static void inputNumberOfRowsAndSeatsPerRow() {
        System.out.println("Enter the number of rows:");
        rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seatsPerRow = sc.nextInt();
        numberOfSeats = rows * seatsPerRow;
        maxIncome = numberOfSeats * 10;
        if (numberOfSeats > 60) {
            maxIncome -= (rows + 1) / 2 * seatsPerRow * 2;
        }
        seats = new String[rows][seatsPerRow];
        for (int row = 0; row < rows; row++) {
            Arrays.fill(seats[row], "S");
        }
    }

    private static void printSeats() {
        System.out.println("Cinema:");
        for (int i = 1; i <= seatsPerRow; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int row = 0; row < rows; row++) {
            System.out.print((row + 1));
            for (int col = 0; col < seatsPerRow; col++) {
                System.out.print(" " + seats[row][col]);
            }
            System.out.println();
        }
    }
}