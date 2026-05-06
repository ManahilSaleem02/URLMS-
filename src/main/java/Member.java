// ==================== File: Member.java ====================
class Member extends User {
    private String membershipType;
    private int maxBorrowLimit;
    private int currentBorrowCount;

    public Member(String userId, String name, String email, String password, String membershipType) {
        super(userId, name, email, password);
        this.membershipType = membershipType;
        this.maxBorrowLimit = membershipType.equals("Faculty") ? 10 : 5;
        this.currentBorrowCount = 0;
    }

    public String getMembershipType() { return membershipType; }
    public int getMaxBorrowLimit() { return maxBorrowLimit; }
    public int getCurrentBorrowCount() { return currentBorrowCount; }
    public void incrementBorrowCount() { currentBorrowCount++; }
    public void decrementBorrowCount() { currentBorrowCount--; }
    
    public boolean canBorrow() { return currentBorrowCount < maxBorrowLimit; }
    
    @Override
    public String getUserType() { return "Member (" + membershipType + ")"; }
}
