package ua.acity.factory;

import ua.acity.model.building.ArchitecturalMonument;
import ua.acity.model.building.Building;

public class ArchitecturalMonumentFactory implements BuildingFactory {
    @Override
    public Building createBuilding(BuildingCreationParams params) {
        String heritage = (String) params.getParam("heritage");
        return new ArchitecturalMonument(
                params.getId(),
                params.getAddress(),
                params.getFloors(),
                params.getArea(),
                params.getYearBuilt(),
                heritage);
    }
}
