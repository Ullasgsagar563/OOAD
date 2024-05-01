package src.model;

// Ward.java
public class Ward {
    private int id;
    private String name;
    private int capacity;
    private int availableBeds;

    public Ward(int id, String name, int capacity, int availableBeds) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.availableBeds = availableBeds;
    }

    // Getter and setter methods for each field
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getAvailableBeds() {
        return availableBeds;
    }

    public void setAvailableBeds(int availableBeds) {
        this.availableBeds = availableBeds;
    }

    @Override
    public String toString() {
        return "Ward{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", availableBeds=" + availableBeds +
                '}';
    }
}
