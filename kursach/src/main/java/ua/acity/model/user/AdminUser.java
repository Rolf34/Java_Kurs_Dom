package ua.acity.model.user;

public class AdminUser extends User {
    private String department;

    public AdminUser(String id, String name, String email, String department) {
        super(id, name, email);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String getRole() {
        return "Administrator";
    }
}
