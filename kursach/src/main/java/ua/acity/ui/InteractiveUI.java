package ua.acity.ui;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import ua.acity.factory.BuildingCreationParams;
import ua.acity.factory.BuildingFactoryProvider;
import ua.acity.model.building.Building;
import ua.acity.observer.LoggingObserver;
import ua.acity.observer.StatisticsObserver;
import ua.acity.service.BuildingService;

public class InteractiveUI {
    private Scanner scanner;
    private BuildingService service;
    private boolean running;

    public InteractiveUI() {
        this.scanner = new Scanner(System.in);
        this.service = new BuildingService();
        this.running = true;

        LoggingObserver loggingObserver = new LoggingObserver();
        StatisticsObserver statsObserver = new StatisticsObserver();
        service.getEventPublisher().subscribe(loggingObserver);
        service.getEventPublisher().subscribe(statsObserver);
    }

    public void start() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║        CITY BUILDING MANAGEMENT SYSTEM - WELCOME!          ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        while (running) {
            displayMainMenu();
            int choice = getIntInput();
            handleMainMenuChoice(choice);
        }

        System.out.println("\n✅ Thank you for using the system. Goodbye!");
        scanner.close();
    }

    private void displayMainMenu() {
        System.out.println("\n═══════════════════════════════════════════");
        System.out.println("          MAIN MENU");
        System.out.println("═══════════════════════════════════════════");
        System.out.println("1. Manage Buildings");
        System.out.println("2. Manage Service Requests");
        System.out.println("3. Manage Maintenance Works");
        System.out.println("4. View Statistics");
        System.out.println("5. Exit");
        System.out.println("═══════════════════════════════════════════");
        System.out.print("Choose an option (1-5): ");
    }

    private void handleMainMenuChoice(int choice) {
        switch (choice) {
            case 1:
                manageBuildingsMenu();
                break;
            case 2:
                manageServiceRequestsMenu();
                break;
            case 3:
                manageMaintenanceWorksMenu();
                break;
            case 4:
                viewStatistics();
                break;
            case 5:
                running = false;
                break;
            default:
                System.out.println("❌ Invalid choice. Please try again.");
        }
    }

    private void manageBuildingsMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n───────────────────────────────────────────");
            System.out.println("       BUILDING MANAGEMENT");
            System.out.println("───────────────────────────────────────────");
            System.out.println("1. Add New Building");
            System.out.println("2. View All Buildings");
            System.out.println("3. View Building Details");
            System.out.println("4. Update Network Status");
            System.out.println("5. Back to Main Menu");
            System.out.println("───────────────────────────────────────────");
            System.out.print("Choose an option (1-5): ");

            int choice = getIntInput();
            switch (choice) {
                case 1:
                    addNewBuilding();
                    break;
                case 2:
                    viewAllBuildings();
                    break;
                case 3:
                    viewBuildingDetails();
                    break;
                case 4:
                    updateNetworkStatus();
                    break;
                case 5:
                    inMenu = false;
                    break;
                default:
                    System.out.println("❌ Invalid choice.");
            }
        }
    }

    private void addNewBuilding() {
        System.out.println("\n>>> Add New Building\n");

        System.out.print("Building ID: ");
        String id = scanner.nextLine().trim();

        System.out.print("Address: ");
        String address = scanner.nextLine().trim();

        System.out.print("Number of floors: ");
        int floors = getIntInput();

        System.out.print("Area (m²): ");
        double area = getDoubleInput();

        System.out.print("Year built: ");
        int year = getIntInput();

        System.out.println("\nBuilding Type:");
        System.out.println("1. Residential");
        System.out.println("2. Administrative");
        System.out.println("3. Commercial");
        System.out.println("4. Architectural Monument");
        System.out.print("Choose type (1-4): ");

        int typeChoice = getIntInput();
        BuildingCreationParams params = new BuildingCreationParams(id, address, floors, area, year);

        Building building = null;
        switch (typeChoice) {
            case 1:
                System.out.print("Number of apartments: ");
                int apartments = getIntInput();
                params.addParam("apartments", apartments);
                building = BuildingFactoryProvider.getFactory("RESIDENTIAL").createBuilding(params);
                break;
            case 2:
                System.out.print("Department name: ");
                String department = scanner.nextLine().trim();
                params.addParam("department", department);
                building = BuildingFactoryProvider.getFactory("ADMINISTRATIVE").createBuilding(params);
                break;
            case 3:
                System.out.print("Number of shops: ");
                int shops = getIntInput();
                System.out.print("Number of offices: ");
                int offices = getIntInput();
                params.addParam("shops", shops);
                params.addParam("offices", offices);
                building = BuildingFactoryProvider.getFactory("COMMERCIAL").createBuilding(params);
                break;
            case 4:
                System.out.print("Heritage description: ");
                String heritage = scanner.nextLine().trim();
                params.addParam("heritage", heritage);
                building = BuildingFactoryProvider.getFactory("MONUMENT").createBuilding(params);
                break;
            default:
                System.out.println("❌ Invalid type.");
                return;
        }

        if (building != null) {
            service.addBuilding(building);
            System.out.println("\n✅ Building added successfully!");
        }
    }

    private void viewAllBuildings() {
        Map<String, Building> buildings = service.getAllBuildings();
        if (buildings.isEmpty()) {
            System.out.println("\n❌ No buildings in the system.");
            return;
        }

        System.out.println("\n📊 All Buildings:");
        System.out.println("─".repeat(80));
        for (Building building : buildings.values()) {
            System.out.printf("%-8s | %-20s | %-25s | %s%n",
                    building.getId(), building.getType(), building.getAddress(),
                    "Built: " + building.getYearBuilt());
        }
        System.out.println("─".repeat(80));
    }

    private void viewBuildingDetails() {
        System.out.print("\nEnter building ID: ");
        String id = scanner.nextLine().trim();

        Building building = service.getBuilding(id);
        if (building == null) {
            System.out.println("❌ Building not found.");
            return;
        }

        System.out.println("\n📋 Building Details:");
        System.out.println("─".repeat(50));
        System.out.println("ID: " + building.getId());
        System.out.println("Type: " + building.getType());
        System.out.println("Address: " + building.getAddress());
        System.out.println("Year Built: " + building.getYearBuilt() + " (Age: " + building.getAgeInYears() + " years)");
        System.out.println("Floors: " + building.getFloors());
        System.out.println("Area: " + building.getArea() + " m²");
        System.out.println("Occupants: " + building.getOccupants());
        System.out.println("\nNetwork Status:");
        System.out.println("  Water: " + building.getWaterNetworkStatus());
        System.out.println("  Electricity: " + building.getElectricityNetworkStatus());
        System.out.println("  Gas: " + building.getGasNetworkStatus());
        System.out.println("  Sewage: " + building.getSewageNetworkStatus());
        System.out.println("Maintenance Status: " + building.getMaintenanceStatus());
        System.out.println("─".repeat(50));
    }

    private void updateNetworkStatus() {
        System.out.print("\nEnter building ID: ");
        String buildingId = scanner.nextLine().trim();

        if (service.getBuilding(buildingId) == null) {
            System.out.println("❌ Building not found.");
            return;
        }

        System.out.println("\nNetwork Type:");
        System.out.println("1. Water");
        System.out.println("2. Electricity");
        System.out.println("3. Gas");
        System.out.println("4. Sewage");
        System.out.print("Choose (1-4): ");

        int networkChoice = getIntInput();
        String networkType = "";
        switch (networkChoice) {
            case 1:
                networkType = "water";
                break;
            case 2:
                networkType = "electricity";
                break;
            case 3:
                networkType = "gas";
                break;
            case 4:
                networkType = "sewage";
                break;
            default:
                System.out.println("❌ Invalid choice.");
                return;
        }

        System.out.println("\nStatus:");
        System.out.println("1. GOOD");
        System.out.println("2. FAIR");
        System.out.println("3. POOR");
        System.out.print("Choose (1-3): ");

        int statusChoice = getIntInput();
        String status = "";
        switch (statusChoice) {
            case 1:
                status = "GOOD";
                break;
            case 2:
                status = "FAIR";
                break;
            case 3:
                status = "POOR";
                break;
            default:
                System.out.println("❌ Invalid choice.");
                return;
        }

        service.updateBuildingNetworkStatus(buildingId, networkType, status);
        System.out.println("\n✅ Network status updated!");
    }

    private void manageServiceRequestsMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n───────────────────────────────────────────");
            System.out.println("       SERVICE REQUESTS MANAGEMENT");
            System.out.println("───────────────────────────────────────────");
            System.out.println("1. Create Service Request");
            System.out.println("2. View Requests by Building");
            System.out.println("3. View Pending Requests");
            System.out.println("4. Complete Request");
            System.out.println("5. Back to Main Menu");
            System.out.println("───────────────────────────────────────────");
            System.out.print("Choose an option (1-5): ");

            int choice = getIntInput();
            switch (choice) {
                case 1:
                    createServiceRequest();
                    break;
                case 2:
                    viewRequestsByBuilding();
                    break;
                case 3:
                    viewPendingRequests();
                    break;
                case 4:
                    completeRequest();
                    break;
                case 5:
                    inMenu = false;
                    break;
                default:
                    System.out.println("❌ Invalid choice.");
            }
        }
    }

    private void createServiceRequest() {
        System.out.println("\n>>> Create Service Request\n");

        System.out.print("Building ID: ");
        String buildingId = scanner.nextLine().trim();

        if (service.getBuilding(buildingId) == null) {
            System.out.println("❌ Building not found.");
            return;
        }

        System.out.print("User ID: ");
        String userId = scanner.nextLine().trim();

        System.out.print("Category (PLUMBING/ELECTRICITY/HEATING/GENERAL/OTHER): ");
        String category = scanner.nextLine().trim().toUpperCase();

        System.out.print("Description: ");
        String description = scanner.nextLine().trim();

        String requestId = service.createServiceRequest(buildingId, userId, description, category);
        System.out.println("\n✅ Service request created with ID: " + requestId);
    }

    private void viewRequestsByBuilding() {
        System.out.print("\nEnter building ID: ");
        String buildingId = scanner.nextLine().trim();

        List<?> requests = service.getServiceRequestsByBuilding(buildingId);
        if (requests.isEmpty()) {
            System.out.println("❌ No requests for this building.");
            return;
        }

        System.out.println("\n📋 Requests for Building " + buildingId);
        System.out.println("─".repeat(80));
        for (Object req : requests) {
            System.out.println(req);
        }
        System.out.println("─".repeat(80));
    }

    private void viewPendingRequests() {
        List<?> requests = service.getServiceRequestsByStatus("PENDING");
        if (requests.isEmpty()) {
            System.out.println("\n❌ No pending requests.");
            return;
        }

        System.out.println("\n📋 Pending Service Requests");
        System.out.println("─".repeat(80));
        for (Object req : requests) {
            System.out.println(req);
        }
        System.out.println("─".repeat(80));
    }

    private void completeRequest() {
        System.out.print("\nEnter request ID: ");
        String requestId = scanner.nextLine().trim();

        if (service.getServiceRequest(requestId) == null) {
            System.out.println("❌ Request not found.");
            return;
        }

        System.out.print("Rating (1-5): ");
        int rating = getIntInput();

        if (rating < 1 || rating > 5) {
            System.out.println("❌ Invalid rating.");
            return;
        }

        service.completeServiceRequest(requestId, rating);
        System.out.println("\n✅ Request completed!");
    }

    private void manageMaintenanceWorksMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n───────────────────────────────────────────");
            System.out.println("       MAINTENANCE WORKS MANAGEMENT");
            System.out.println("───────────────────────────────────────────");
            System.out.println("1. Plan Maintenance Work");
            System.out.println("2. View Maintenance Works");
            System.out.println("3. Start Maintenance Work");
            System.out.println("4. Complete Maintenance Work");
            System.out.println("5. Back to Main Menu");
            System.out.println("───────────────────────────────────────────");
            System.out.print("Choose an option (1-5): ");

            int choice = getIntInput();
            switch (choice) {
                case 1:
                    planMaintenanceWork();
                    break;
                case 2:
                    viewMaintenanceWorks();
                    break;
                case 3:
                    startMaintenanceWork();
                    break;
                case 4:
                    completeMaintenanceWork();
                    break;
                case 5:
                    inMenu = false;
                    break;
                default:
                    System.out.println("❌ Invalid choice.");
            }
        }
    }

    private void planMaintenanceWork() {
        System.out.println("\n>>> Plan Maintenance Work\n");

        System.out.print("Building ID: ");
        String buildingId = scanner.nextLine().trim();

        if (service.getBuilding(buildingId) == null) {
            System.out.println("❌ Building not found.");
            return;
        }

        System.out.print("Description: ");
        String description = scanner.nextLine().trim();

        System.out.println("\nWork Type:");
        System.out.println("1. WATER_NETWORK");
        System.out.println("2. ELECTRICITY_NETWORK");
        System.out.println("3. GAS_NETWORK");
        System.out.println("4. ROOF_REPAIR");
        System.out.println("5. FACADE_REPAIR");
        System.out.println("6. ELEVATOR_REPAIR");
        System.out.println("7. HEATING_SYSTEM");
        System.out.println("8. CONSERVATION");
        System.out.print("Choose (1-8): ");

        String[] types = { "WATER_NETWORK", "ELECTRICITY_NETWORK", "GAS_NETWORK", "ROOF_REPAIR",
                "FACADE_REPAIR", "ELEVATOR_REPAIR", "HEATING_SYSTEM", "CONSERVATION" };
        int typeChoice = getIntInput();
        if (typeChoice < 1 || typeChoice > types.length) {
            System.out.println("❌ Invalid choice.");
            return;
        }

        System.out.print("Planned end date (yyyy-MM-dd): ");
        LocalDate endDate = LocalDate.parse(scanner.nextLine().trim());

        String maintenanceId = service.createMaintenanceWork(buildingId, description, types[typeChoice - 1], endDate);
        System.out.println("\n✅ Maintenance work planned with ID: " + maintenanceId);
    }

    private void viewMaintenanceWorks() {
        System.out.print("Enter building ID (or leave empty for all): ");
        String buildingId = scanner.nextLine().trim();

        List<?> works;
        if (buildingId.isEmpty()) {
            works = service.getAllMaintenanceWorks();
            System.out.println("📋 All Maintenance Works");
        } else {
            if (service.getBuilding(buildingId) == null) {
                System.out.println("❌ Building not found.");
                return;
            }
            works = service.getMaintenanceWorkByBuilding(buildingId);
            System.out.println("📋 Maintenance Works for Building " + buildingId);
        }

        if (works.isEmpty()) {
            System.out.println("   No maintenance works found.");
            return;
        }

        System.out.println("─".repeat(80));
        for (Object work : works) {
            System.out.println(work);
        }
        System.out.println("─".repeat(80));
    }

    private void startMaintenanceWork() {
        System.out.print("\nEnter maintenance work ID: ");
        String maintenanceId = scanner.nextLine().trim();

        if (service.getMaintenanceWork(maintenanceId) == null) {
            System.out.println("❌ Maintenance work not found.");
            return;
        }

        service.startMaintenanceWork(maintenanceId);
        System.out.println("\n✅ Maintenance work started!");
    }

    private void completeMaintenanceWork() {
        System.out.print("\nEnter maintenance work ID: ");
        String maintenanceId = scanner.nextLine().trim();

        if (service.getMaintenanceWork(maintenanceId) == null) {
            System.out.println("❌ Maintenance work not found.");
            return;
        }

        service.completeMaintenanceWork(maintenanceId);
        System.out.println("\n✅ Maintenance work completed!");
    }

    private void viewStatistics() {
        System.out.println("\n════════════════════════════════════════════════════════════");
        System.out.println("                     SYSTEM STATISTICS");
        System.out.println("════════════════════════════════════════════════════════════");

        System.out.println("\n📊 Buildings by Type:");
        Map<String, Integer> stats = service.getStatisticsByBuildingType();
        if (stats.isEmpty()) {
            System.out.println("   No buildings yet.");
        } else {
            stats.forEach((type, count) -> System.out.println("   " + type + ": " + count));
        }

        System.out.println("\n👥 Total Occupants: " + service.getTotalOccupants());
        System.out.println(
                "📅 Average Building Age: " + String.format("%.1f", service.getAverageAgeOfBuildings()) + " years");

        System.out.println("\n📋 Service Requests by Status:");
        Map<String, Long> reqStats = service.getRequestStatsByStatus();
        if (reqStats.isEmpty()) {
            System.out.println("   No requests yet.");
        } else {
            reqStats.forEach((status, count) -> System.out.println("   " + status + ": " + count));
        }

        System.out.println("\n════════════════════════════════════════════════════════════");
    }

    private int getIntInput() {
        try {
            int value = Integer.parseInt(scanner.nextLine().trim());
            return value;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private double getDoubleInput() {
        try {
            double value = Double.parseDouble(scanner.nextLine().trim());
            return value;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void main(String[] args) {
        InteractiveUI ui = new InteractiveUI();
        ui.start();
    }
}