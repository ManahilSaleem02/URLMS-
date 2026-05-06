// ==================== File: Librarian.java ====================
class Librarian extends User {
    private String employeeId;

    public Librarian(String userId, String name, String email, String password, String employeeId) {
        super(userId, name, email, password);
        this.employeeId = employeeId;
    }

    public String getEmployeeId() { return employeeId; }
    
    @Override
    public String getUserType() { return "Librarian"; }
}
