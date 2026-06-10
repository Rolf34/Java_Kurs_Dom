package ua.acity;

import ua.acity.factory.BuildingCreationParams;
import ua.acity.factory.BuildingFactoryProvider;
import ua.acity.model.building.Building;
import ua.acity.observer.LoggingObserver;
import ua.acity.observer.StatisticsObserver;
import ua.acity.service.BuildingService;

public class App {
    public static void main(String[] args) {
        System.out.println("=== City Building Management System ===\n");

        BuildingService service = new BuildingService();

        LoggingObserver loggingObserver = new LoggingObserver();
        StatisticsObserver statsObserver = new StatisticsObserver();
        service.getEventPublisher().subscribe(loggingObserver);
        service.getEventPublisher().subscribe(statsObserver);

        BuildingCreationParams params = new BuildingCreationParams("B1", "Main Street 1", 5, 1000, 2010);
        params.addParam("apartments", 20);

        Building building = BuildingFactoryProvider.getFactory("RESIDENTIAL").createBuilding(params);
        service.addBuilding(building);

        System.out.println("\nBuilding created: " + building.getId() + " (" + building.getType() + ")");
        System.out.println("Address: " + building.getAddress());
        System.out.println("Floors: " + building.getFloors());
        System.out.println("Area: " + building.getArea());

        service.createServiceRequest("B1", "USER_001", "Elevator repair", "ELEVATOR_REPAIR");

        System.out.println("\nSystem ready for use!");
    }
}
