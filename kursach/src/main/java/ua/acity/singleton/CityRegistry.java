package ua.acity.singleton;

import java.util.HashMap;
import java.util.Map;
import ua.acity.model.building.Building;
import ua.acity.model.user.User;

public class CityRegistry {
    private static volatile CityRegistry instance;
    private Map<String, Building> buildings;
    private Map<String, User> users;

    private CityRegistry() {
        this.buildings = new HashMap<>();
        this.users = new HashMap<>();
    }

    public static CityRegistry getInstance() {
        if (instance == null) {
            synchronized (CityRegistry.class) {
                if (instance == null) {
                    instance = new CityRegistry();
                }
            }
        }
        return instance;
    }

    public void registerBuilding(Building building) {
        buildings.put(building.getId(), building);
    }

    public Building getBuilding(String id) {
        return buildings.get(id);
    }

    public void removeBuilding(String id) {
        buildings.remove(id);
    }

    public boolean buildingExists(String id) {
        return buildings.containsKey(id);
    }

    public void registerUser(User user) {
        users.put(user.getId(), user);
    }

    public User getUser(String id) {
        return users.get(id);
    }

    public Map<String, Building> getAllBuildings() {
        return new HashMap<>(buildings);
    }

    public Map<String, User> getAllUsers() {
        return new HashMap<>(users);
    }
}
