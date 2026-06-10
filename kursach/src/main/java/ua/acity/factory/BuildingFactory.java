package ua.acity.factory;

import ua.acity.model.building.Building;

public interface BuildingFactory {
    Building createBuilding(BuildingCreationParams params);
}
