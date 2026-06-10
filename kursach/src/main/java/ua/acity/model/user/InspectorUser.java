package ua.acity.model.user;

public class InspectorUser extends User {
    private String licenseNumber;

    public InspectorUser(String id, String name, String email, String licenseNumber) {
        super(id, name, email);
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    @Override
    public String getRole() {
        return "Inspector";
    }
}
