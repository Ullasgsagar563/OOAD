package src.factory;

import src.model.*;

public class ServiceFactory {

    public static Service createService(String name, double price) {
        // Create a new Service object with the given parameters
        return new Service(name, price);
    }
}
