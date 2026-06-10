package ua.acity.model.user;

public class CityEngineerUser extends User {
    private String specialization;

    public CityEngineerUser(String id, String name, String email, String specialization) {
        super(id, name, email);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    @Override
    public String getRole() {
        return "City Engineer";
    }
}
