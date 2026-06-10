package ua.acity.factory;

import ua.acity.model.building.Building;
import ua.acity.model.building.ResidentialBuilding;

public class ResidentialBuildingFactory implements BuildingFactory {
    @Override
    public Building createBuilding(BuildingCreationParams params) {
        int apartments = ((Number) params.getParam("apartments")).intValue();
        return new ResidentialBuilding(
                params.getId(),
                params.getAddress(),
                params.getFloors(),
                params.getArea(),
                params.getYearBuilt(),
                apartments);
    }
}
