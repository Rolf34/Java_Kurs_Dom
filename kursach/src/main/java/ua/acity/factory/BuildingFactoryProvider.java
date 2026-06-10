package ua.acity.factory;

import java.util.HashMap;
import java.util.Map;

public class BuildingFactoryProvider {
    private static Map<String, BuildingFactory> factories = new HashMap<>();

    static {
        factories.put("RESIDENTIAL", new ResidentialBuildingFactory());
        factories.put("ADMINISTRATIVE", new AdministrativeBuildingFactory());
        factories.put("MONUMENT", new ArchitecturalMonumentFactory());
        factories.put("COMMERCIAL", new CommercialBuildingFactory());
    }

    public static BuildingFactory getFactory(String type) {
        return factories.get(type.toUpperCase());
    }
}
