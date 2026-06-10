package ua.acity.model.user;

public class ResidentUser extends User {
    private String address;

    public ResidentUser(String id, String name, String email, String address) {
        super(id, name, email);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String getRole() {
        return "Resident";
    }
}
