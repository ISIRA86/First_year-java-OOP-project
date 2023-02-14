import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.module.FindException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static QueueManager queueManager;
    static int fuelAmount = 6600;

    public static FuelQueue WaitingList;

    public static void main(String[] args) throws IOException {
        //calling 5 QueueManager objects
        queueManager = new QueueManager(5);

        WaitingList = new FuelQueue();

        String userInput = "";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the fuel program!!");
        System.out.println("\n");
        System.out.println("100 or VFQ: View all Fuel Queues.\n" +
                "101 or VEQ: View all Empty Queues.\n" +
                "102 or ACQ: Add customer to a Queue.\n" +
                "103 or RCQ: Remove a customer from a Queue. (From a specific location)\n" +
                "104 or PCQ: Remove a served customer.\n" +
                "105 or VCS: View Customers Sorted in alphabetical order\n" +
                "106 or SPD: Store Program Data into file.\n" +
                "107 or LPD: Load Program Data from file.\n" +
                "108 or STK: View Remaining Fuel Stock.\n" +
                "109 or AFS: Add Fuel Stock.\n" +
                "110 or IFQ: income of fuel queue.\n" +
                "999 or EXT: Exit the Program.\n");

        myloop:
        while (true) {
            System.out.println("\n");
            System.out.print("enter the option :");
            userInput = scanner.next();

            switch (userInput) {

                case "100":
                case "VFQ":
                case "vfq":
                    queueManager.ViewAllQueues();
                    break;

                case "101":
                case "VEQ":
                case "veq":
                    //calling the viewEmptyQueues method to print the empty queues
                    queueManager.ViewEmptyQueues();
                    break;

                case "102":
                case "ACQ":
                case "acq":
                    if (fuelAmount < 500) {
                        System.out.println("fuel stock is less than 500!!!");
                    }
                    //calling the AddPassenger method for adding a new customer to the queue
                    AddPassenger(scanner,WaitingList);
                    break;

                case "103":
                case "RCQ":
                case "rcq":
                    queueManager.RemoveCustomer(scanner);

                    break;

                case "104":
                case "PCQ":
                case "pcq":
                    queueManager.RemoveServedCustomer(scanner);
                    break;

                case "105":
                case "VCS":
                case "vcs":
                    SortQueue(scanner);
                    break;


                case "106":
                case "SPD":
                case "spd":
                    FileManager.StoreDataToFile(queueManager.getQueues());
                    break;

                case "107":
                case "LPD":
                case "lpd":
                    queueManager.setQueues(FileManager.ReadDataFromFile(5));
                    break;

                case "108":
                case "STK":
                case "stk":
                    System.out.println("the remaining fuel stock is :" + fuelAmount + "L");
                    break;

                case "109":
                case "AFS":
                case "afs":
                    AddFuelStock(scanner);
                    break;

                case "110":
                case "IFQ":
                case "ifq":
                    Income(scanner);
                    break;


                case "999":
                case "EXT":
                case "ext":
                    System.out.println("exit the program");
                    break myloop;

                default:
                    System.out.println("Invalid Input");
                    break;
            }


        }
    }

    private static void AddPassenger(Scanner scanner,FuelQueue WaitingList) {
        Passengers newPassenger = new Passengers();

        System.out.print("enter first name :");
        newPassenger.setFirstName(scanner.next());
        System.out.print("enter Last Name :");
        newPassenger.setLastName(scanner.next());
        System.out.print("enter Vehicle number :");
        newPassenger.setVehicleNo(scanner.next());
        System.out.print("enter Amount of litres :");
        newPassenger.setLitres(scanner.nextInt());

        fuelAmount -= newPassenger.getLitres();

        queueManager.AddToSmallestQueue(newPassenger);

    }

    private static void AddFuelStock(Scanner scanner) {
        int newFuelStockAmount = 0;
        System.out.println("enter the amount of stock to be added :");
        newFuelStockAmount = scanner.nextInt();

        if (fuelAmount == 6600) {
            System.out.println("fuel stock is already full!");
        } else if ((fuelAmount + newFuelStockAmount) > 6600) {
            System.out.println("please enter a valid fuel amount to be added. the amount you entered exceeds the fuel capacity");
        } else {
            fuelAmount += newFuelStockAmount;
            System.out.println(newFuelStockAmount + "L successfully added to fuel stock!");
            System.out.println("fuel stock :" + fuelAmount + "L");
        }
    }

    private static void SortQueue(Scanner scanner) {
        System.out.print("Enter queue Number : ");
        int index = scanner.nextInt();

            queueManager.SortQueue(index);

    }

    private static void Income(Scanner scanner) {
        System.out.print("enter the queue number you want to see the income :");
        int index = scanner.nextInt();

        queueManager.QueueIncome(index);
    }



}



