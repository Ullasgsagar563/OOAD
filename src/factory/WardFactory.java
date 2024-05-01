package src.factory;

import src.model.*;

public class WardFactory {

    public static Ward createWard(int id, String name, int capacity, int availableBeds) {
        // Create a new Ward object with the given parameters
        return new Ward(id, name, capacity, availableBeds);
    }
}
