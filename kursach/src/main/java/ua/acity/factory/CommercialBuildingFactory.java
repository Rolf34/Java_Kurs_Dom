package ua.acity.factory;

import ua.acity.model.building.Building;
import ua.acity.model.building.CommercialBuilding;

public class CommercialBuildingFactory implements BuildingFactory {
    @Override
    public Building createBuilding(BuildingCreationParams params) {
        int shops = ((Number) params.getParam("shops")).intValue();
        int offices = ((Number) params.getParam("offices")).intValue();
        return new CommercialBuilding(
                params.getId(),
                params.getAddress(),
                params.getFloors(),
                params.getArea(),
                params.getYearBuilt(),
                shops,
                offices);
    }
}
