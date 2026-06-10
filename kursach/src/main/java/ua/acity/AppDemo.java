package ua.acity;

import java.time.LocalDate;

import ua.acity.factory.BuildingCreationParams;
import ua.acity.factory.BuildingFactoryProvider;
import ua.acity.model.building.AdministrativeBuilding;
import ua.acity.model.building.ArchitecturalMonument;
import ua.acity.model.building.Building;
import ua.acity.model.building.CommercialBuilding;
import ua.acity.model.building.ResidentialBuilding;
import ua.acity.observer.LoggingObserver;
import ua.acity.observer.StatisticsObserver;
import ua.acity.service.BuildingService;

public class AppDemo {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║     CITY BUILDING MANAGEMENT SYSTEM - ELECTRONIC CITY      ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        BuildingService service = new BuildingService();

        LoggingObserver loggingObserver = new LoggingObserver();
        StatisticsObserver statsObserver = new StatisticsObserver();
        service.getEventPublisher().subscribe(loggingObserver);
        service.getEventPublisher().subscribe(statsObserver);

        System.out.println(">>> Creating buildings...\n");

        BuildingCreationParams residentialParams = new BuildingCreationParams(
                "B001", "Central Avenue 45", 12, 5000, 2010);
        residentialParams.addParam("apartments", 80);
        Building residential = BuildingFactoryProvider.getFactory("RESIDENTIAL").createBuilding(residentialParams);
        ((ResidentialBuilding) residential).setFamilies(70);
        service.addBuilding(residential);

        BuildingCreationParams adminParams = new BuildingCreationParams(
                "B002", "Government Square 10", 8, 3500, 1995);
        adminParams.addParam("department", "City Administration");
        Building admin = BuildingFactoryProvider.getFactory("ADMINISTRATIVE").createBuilding(adminParams);
        ((AdministrativeBuilding) admin).setEmployees(200);
        service.addBuilding(admin);

        BuildingCreationParams commercialParams = new BuildingCreationParams(
                "B003", "Market Street 20", 10, 8000, 2005);
        commercialParams.addParam("shops", 15);
        commercialParams.addParam("offices", 40);
        Building commercial = BuildingFactoryProvider.getFactory("COMMERCIAL").createBuilding(commercialParams);
        ((CommercialBuilding) commercial).setTenants(55);
        service.addBuilding(commercial);

        BuildingCreationParams monumentParams = new BuildingCreationParams(
                "B004", "Heritage Square 1", 3, 1200, 1850);
        monumentParams.addParam("heritage", "Historical Cathedral");
        Building monument = BuildingFactoryProvider.getFactory("MONUMENT").createBuilding(monumentParams);
        ((ArchitecturalMonument) monument).setConservationStatus("EXCELLENT");
        service.addBuilding(monument);

        System.out.println("\n>>> Updating network statuses...\n");

        service.updateBuildingNetworkStatus("B001", "water", "GOOD");
        service.updateBuildingNetworkStatus("B001", "electricity", "FAIR");

        System.out.println("\n>>> Creating service requests...\n");

        String req1 = service.createServiceRequest("B001", "USER_001", "Elevator malfunction in building",
                "ELEVATOR_REPAIR");
        String req2 = service.createServiceRequest("B001", "USER_002", "Water leakage in apartment 5B", "PLUMBING");
        String req3 = service.createServiceRequest("B003", "USER_003", "Lighting repair on ground floor",
                "ELECTRICITY");

        System.out.println("\n>>> Planning maintenance works...\n");


        String maint1 = service.createMaintenanceWork("B001", "Complete electrical network inspection",
                "ELECTRICITY_NETWORK", LocalDate.now().plusMonths(1));
        String maint2 = service.createMaintenanceWork("B002", "Roof repair and waterproofing",
                "ROOF_REPAIR", LocalDate.now().plusMonths(2));
        String maint3 = service.createMaintenanceWork("B004", "Conservation work on facade",
                "CONSERVATION", LocalDate.now().plusMonths(3));

        System.out.println("\n>>> Processing service requests...\n");

        service.assignServiceRequest(req1, "John Technician");
        service.completeServiceRequest(req1, 5);

        service.assignServiceRequest(req2, "Mike Plumber");
        service.completeServiceRequest(req2, 4);

        System.out.println("\n>>> Starting maintenance works...\n");

        service.startMaintenanceWork(maint1);

        System.out.println("\n════════════════════════════════════════════════════════════");
        System.out.println("                     SYSTEM STATISTICS");
        System.out.println("════════════════════════════════════════════════════════════\n");

        System.out.println("📊 Buildings by Type:");
        service.getStatisticsByBuildingType().forEach((type, count) -> System.out.println("   " + type + ": " + count));

        System.out.println("\n👥 Total Occupants: " + service.getTotalOccupants());
        System.out.println(
                "📅 Average Building Age: " + String.format("%.1f", service.getAverageAgeOfBuildings()) + " years");

        System.out.println("\n📋 Service Requests by Status:");
        service.getRequestStatsByStatus().forEach((status, count) -> System.out.println("   " + status + ": " + count));

        System.out.println("\n════════════════════════════════════════════════════════════");
        System.out.println("                     BUILDINGS DETAILS");
        System.out.println("════════════════════════════════════════════════════════════\n");

        service.getAllBuildings().forEach((id, building) -> {
            System.out.println("🏢 " + building.getType() + " [" + id + "]");
            System.out.println("   Address: " + building.getAddress());
            System.out.println(
                    "   Year Built: " + building.getYearBuilt() + " (Age: " + building.getAgeInYears() + " years)");
            System.out.println("   Structure: " + building.getFloors() + " floors, " + building.getArea() + " m²");
            System.out.println("   Occupants: " + building.getOccupants());
            System.out.println("   Networks - Water: " + building.getWaterNetworkStatus() +
                    ", Electricity: " + building.getElectricityNetworkStatus() +
                    ", Gas: " + building.getGasNetworkStatus() +
                    ", Sewage: " + building.getSewageNetworkStatus());
            System.out.println("   Maintenance: " + building.getMaintenanceStatus());
            System.out.println();
        });

        System.out.println("════════════════════════════════════════════════════════════");
        System.out.println("✅ System is ready for use!");
        System.out.println("════════════════════════════════════════════════════════════");
    }
}
