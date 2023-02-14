import java.io.*;
import java.util.Scanner;

// class for managing the methods with the file read and write
public class FileManager {

    /**
     * This method is to store the data to the text file
     * @param queues this is to get a new fuel queue object
     * @throws IOException
     */
    public static void StoreDataToFile(FuelQueue[] queues) throws IOException {

        System.out.println("successfully stored!");
        for (int i = 0; i < queues.length; i++) {
            File file = new File("queue-" + i + ".txt");
            FileWriter fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(file);
            for (int j = 0; j < queues[i].getPassengers().size(); j++) {
                Passengers currentPassenger = queues[i].getPassengers().get(j);
                String data = String.format("%s,%s,%s,%d\n", currentPassenger.getFirstName(), currentPassenger.getLastName(), currentPassenger.getVehicleNo(), currentPassenger.getLitres());
                pw.print(data);
            }
            fw.close();
            pw.close();
        }
        File file = new File("WaitingQueue.txt");
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(file);
        for (int i = 0; i < Main.WaitingList.getCount(); i++) {
            Passengers newpassenger = Main.WaitingList.getPassengers().get(i);
            String data = String.format("%s,%s,%s,%d\n", newpassenger.getFirstName(), newpassenger.getLastName(), newpassenger.getVehicleNo(), newpassenger.getLitres());
            pw.print(data);

        }
        fw.close();
        pw.close();

    }

    /**
     * This method is to load data from the text file
     * @param size this is to get a variable
     * @return this returns the loaded queues
     */
    public static FuelQueue[] ReadDataFromFile(int size) {
        File file;
        FileReader fr;
        BufferedReader br;

        FuelQueue[] queues = new FuelQueue[size];

        try {

            for (int i = 0; i < queues.length; i++) {
                file = new File("queue-" + i + ".txt");
                fr = new FileReader(file);
                br = new BufferedReader(fr);

                queues[i] = new FuelQueue();

                for (int j = 0; j < 6; j++) {
                    String dataString = br.readLine();
                    if (dataString == null) continue;
                    String[] data = dataString.split(",");

                    Passengers passenger = new Passengers();

                    passenger.setFirstName(data[0]);
                    passenger.setLastName(data[1]);
                    passenger.setVehicleNo(data[2]);
                    passenger.setLitres(Integer.parseInt(data[3]));

                    queues[i].addPassengers(passenger);
                }

                fr.close();
                br.close();
            }


        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Successfully loaded!");
        return queues;


    }

}