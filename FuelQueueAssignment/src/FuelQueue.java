
import java.util.ArrayList;

public class FuelQueue {
    private int id;
    private ArrayList<Passengers> passengers;

    private int stocksSold;

    public FuelQueue() {
        this.passengers = new ArrayList<Passengers>();
        stocksSold = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getStocksSold() {
        return stocksSold;
    }

    public void updateStocksSold(int stocksSold) {
        this.stocksSold += stocksSold;
    }

    public ArrayList<Passengers> getPassengers() {
        return this.passengers;
    }

    public void addPassengers(Passengers passenger) {
        this.passengers.add(passenger);
    }

    public void removePassenger(int index) {
        this.passengers.remove(index);
    }

    public int getCount() {
        return this.passengers.size();
    }

    public void addToQueue(Passengers passenger) {
        passengers.add(passenger);
    }
}
