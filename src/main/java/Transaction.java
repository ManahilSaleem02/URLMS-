
class Transaction {
    private static int transactionCounter = 1000;
    private String transactionId;
    private String memberId;
    private String resourceId;
    private long borrowDate;
    private long dueDate;
    private long returnDate;
    private double fine;
    private boolean isReturned;

    public Transaction(String memberId, String resourceId, int borrowDays) {
        this.transactionId = "TXN" + (++transactionCounter);
        this.memberId = memberId;
        this.resourceId = resourceId;
        this.borrowDate = System.currentTimeMillis();
        this.dueDate = borrowDate + (borrowDays * 24L * 60 * 60 * 1000);
        this.returnDate = 0;
        this.fine = 0.0;
        this.isReturned = false;
    }

    public String getTransactionId() { return transactionId; }
    public String getMemberId() { return memberId; }
    public String getResourceId() { return resourceId; }
    public long getDueDate() { return dueDate; }
    public double getFine() { return fine; }
    public boolean isReturned() { return isReturned; }

    public void returnResource() {
        this.returnDate = System.currentTimeMillis();
        this.isReturned = true;
        calculateFine();
    }

    private void calculateFine() {
        if (returnDate > dueDate) {
            long overdueDays = (returnDate - dueDate) / (24 * 60 * 60 * 1000);
            this.fine = overdueDays * 10.0;
        }
    }

    public double calculateCurrentFine() {
        if (!isReturned && System.currentTimeMillis() > dueDate) {
            long overdueDays = (System.currentTimeMillis() - dueDate) / (24 * 60 * 60 * 1000);
            return overdueDays * 10.0;
        }
        return fine;
    }

    public void displayDetails() {
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Member ID: " + memberId);
        System.out.println("Resource ID: " + resourceId);
        System.out.println("Borrow Date: " + new java.util.Date(borrowDate));
        System.out.println("Due Date: " + new java.util.Date(dueDate));
        if (isReturned) {
            System.out.println("Return Date: " + new java.util.Date(returnDate));
            System.out.println("Fine: Rs. " + fine);
        } else {
            System.out.println("Status: Active");
            double currentFine = calculateCurrentFine();
            if (currentFine > 0) {
                System.out.println("Outstanding Fine: Rs. " + currentFine);
            }
        }
    }
}
