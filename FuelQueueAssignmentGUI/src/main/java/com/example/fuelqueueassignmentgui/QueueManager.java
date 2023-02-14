package com.example.fuelqueueassignmentgui;

import java.util.ArrayList;
import java.util.Scanner;

public class QueueManager {
    public static FuelQueue[] queues;

    //Constructor
    public QueueManager(int size) {
        this.queues = new FuelQueue[size];
        for (int i = 0; i < queues.length; i++) {
            queues[i] = new FuelQueue();
        }
    }

    public static FuelQueue[] getQueues() {
        return queues;
    }

    public void setQueues(FuelQueue[] queues) {
        this.queues = queues;
    }

    /**
     * This method is to View all the queues
     */
    public void ViewAllQueues() {
        for (int i = 0; i < queues.length; i++) {
            System.out.printf("Queue No %d \n", i);
            if (queues[i].getPassengers().size() > 0) System.out.println(".....................");
            else System.out.println("Queue is Empty");
            for (int j = 0; j < queues[i].getCount(); j++) {
                Passengers currentPassenger = queues[i].getPassengers().get(j);
                System.out.println(currentPassenger.getFirstName() + "," + currentPassenger.getLastName() + "," + currentPassenger.getVehicleNo() + "," + currentPassenger.getLitres());
            }
            System.out.println();
        }
    }

    /**
     * This method is to view all the empty queues
     */
    public void ViewEmptyQueues() {
        for (int i = 0; i < queues.length; i++) {
            if (queues[i].getCount() < 6) {
                System.out.printf("Queue No %d is Empty \n", i);
            } else System.out.printf("Queue No %d is not Empty! \n", i);
        }
    }

    /**
     * This method is to remove the customer from a queue
     * @param scanner this is to get a scanner object
     */
    public void RemoveCustomer(Scanner scanner) {
        System.out.print("enter the queue you were in :");
        int queueIndex = scanner.nextInt();
        System.out.print("enter the position you were in :");
        int queuePosition = scanner.nextInt();
        System.out.println("Successfully removed!");
        Main.fuelAmount += queues[queueIndex].getPassengers().get(queuePosition).getLitres();
        queues[queueIndex].removePassenger(queuePosition);

        if (Main.WaitingList.getCount() > 0) {
            AddToSmallestQueue(Main.WaitingList.getPassengers().get(0));
            Main.WaitingList.removePassenger(0);
        }
    }

    /**
     * this method is to remove a served customer from the queue
     * @param scanner this is to get a scanner object
     */
    public void RemoveServedCustomer(Scanner scanner) {
        System.out.print("enter the queue you were in :");
        int queueIndex = scanner.nextInt();
        System.out.println("Successfully removed!");
        int servedLitres = queues[queueIndex].getPassengers().get(0).getLitres();
        queues[queueIndex].updateStocksSold(servedLitres);
        queues[queueIndex].removePassenger(0);

        if (Main.WaitingList.getCount() > 0) {
            AddToSmallestQueue(Main.WaitingList.getPassengers().get(0));
            Main.WaitingList.removePassenger(0);
        }
    }

    /**
     * this is to adding the smallestqueue to the passenger arraylist and add to the waiting list
     * @param passenger this is to get the passenger arraylist
     */
    public void AddToSmallestQueue(Passengers passenger) {
        FuelQueue smallestQueue = FindSmallestQueue();
        if (smallestQueue.getCount() >= 6) {
            System.out.println("All Queues are full. you have been added to the waiting queue");
            Main.WaitingList.addToQueue(passenger);
            PrintCustomQueue(Main.WaitingList.getPassengers());
        } else {
            smallestQueue.addToQueue(passenger);
        }
    }


    /**
     * this method is to find the smallest queue from the queue
     * @return this will return the smallest queue
     */
    private FuelQueue FindSmallestQueue() {
        FuelQueue minimumQueue = queues[0];

        for (int i = 0; i < queues.length; i++) {
            if (queues[i].getCount() < minimumQueue.getCount()) {
                minimumQueue = queues[i];
            }
        }

        return minimumQueue;
    }

    /**
     * this method is used to sort the arrays to the alphabetical order
     * @param index this will pass the input taken from the user to get the queue input
     */
    public void SortQueue(int index) {
        FuelQueue queue = queues[index];

        Passengers temp;
        ArrayList<Passengers> tempArray = queue.getPassengers();

        for (int i = 0; i < tempArray.size() - 1; i++) {
            for (int j = i + 1; j < tempArray.size(); j++) {
                if (tempArray.get(i).getFirstName().compareTo(tempArray.get(j).getFirstName()) > 0) {
                    temp = tempArray.get(i);
                    tempArray.set(i, tempArray.get(j));
                    tempArray.set(j, temp);
                }
            }
        }
        PrintCustomQueue(tempArray);
    }

    /**
     * this method will print the alphabetical order and the waiting list on a CustomQueue
     * @param customQueue this will pass the passengers arraylist from the FuelQueue class
     */
    public static void PrintCustomQueue(ArrayList<Passengers> customQueue) {
        System.out.println("Queue Sorted\n............");
        for (int i = 0; i < customQueue.size(); i++) {
            Passengers currentPassenger = customQueue.get(i);
            System.out.println(currentPassenger.getFirstName() + "," + currentPassenger.getLastName() + "," + currentPassenger.getVehicleNo() + "," + currentPassenger.getLitres());
        }
        System.out.println();
    }

    /**
     * this method will get the queue incomes of the fuelqueues
     * @param index this will pass the input taken from the user to get the queue input
     */
    public void QueueIncome(int index) {
        FuelQueue NewFuel = queues[index];
        int QueueAmount = NewFuel.getStocksSold() * 430;

        System.out.printf("Queue %d amount :" + QueueAmount, index);
    }

}
