import java.util.*;

class URLMS {
    private List<User> users;
    private List<Resource> resources;
    private List<Transaction> transactions;
    private Scanner scanner;

    public URLMS() {
        this.users = new ArrayList<>();
        this.resources = new ArrayList<>();
        this.transactions = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        initializeData();
    }

    private void initializeData() {
        users.add(new Librarian("L001", "Ahmed Ali", "ahmed@uni.edu", "lib123", "EMP001"));
        users.add(new Librarian("L002", "Fatima Khan", "fatima@uni.edu", "lib456", "EMP002"));
        users.add(new Member("M001", "Hassan Raza", "hassan@student.edu", "pass123", "Student"));
        users.add(new Member("M002", "Ayesha Malik", "ayesha@student.edu", "pass456", "Student"));
        users.add(new Member("M003", "Dr. Imran Sheikh", "imran@faculty.edu", "pass789", "Faculty"));
        resources.add(new Book("B001", "Introduction to Algorithms", "Computer Science", "Cormen", "978-0262033848", 2009));
        resources.add(new Book("B002", "Clean Code", "Programming", "Robert Martin", "978-0132350884", 2008));
        resources.add(new Book("B003", "The Art of Computer Programming", "Computer Science", "Donald Knuth", "978-0201896831", 1997));
        resources.add(new Journal("J001", "IEEE Transactions on Computers", "Computer Science", "IEEE", 70, 12));
        resources.add(new Journal("J002", "ACM Computing Surveys", "Computer Science", "ACM", 54, 3));
        resources.add(new DigitalEquipment("E001", "Laptop Dell XPS 15", "Electronics", "Dell", "SN123456"));
        resources.add(new DigitalEquipment("E002", "iPad Pro", "Electronics", "Apple", "SN789012"));
    }

    private String getPasswordForDisplay(User user) {
        // This method extracts the password for display purposes
        // In production, passwords should NEVER be displayed like this
        if (user instanceof Librarian) {
            Librarian lib = (Librarian) user;
            if (lib.getUserId().equals("L001")) return "lib123";
            if (lib.getUserId().equals("L002")) return "lib456";
        } else if (user instanceof Member) {
            Member mem = (Member) user;
            if (mem.getUserId().equals("M001")) return "pass123";
            if (mem.getUserId().equals("M002")) return "pass456";
            if (mem.getUserId().equals("M003")) return "pass789";
        }
        return "****";
    }

    public void run() {
        while (true) {
            System.out.println("\n╔══════════════════════════════════════════════════════════╗");
            System.out.println("║   University Resource & Loan Management System          ║");
            System.out.println("╚══════════════════════════════════════════════════════════╝");
            System.out.println("1. Login as Librarian");
            System.out.println("2. Login as Member");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = getIntInput();
            
            switch (choice) {
                case 1: librarianLogin(); break;
                case 2: memberLogin(); break;
                case 3:
                    System.out.println("Thank you for using URLMS. Goodbye!");
                    return;
                default: System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void librarianLogin() {
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║   Available Librarian Credentials                        ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println(String.format("%-10s %-25s %-15s", "User ID", "Name", "Password"));
        System.out.println("=".repeat(60));
        for (User user : users) {
            if (user instanceof Librarian) {
                System.out.println(String.format("%-10s %-25s %-15s", 
                    user.getUserId(), user.getName(), getPasswordForDisplay(user)));
            }
        }
        
        System.out.println("\n--- Librarian Login ---");
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        User user = findUser(userId);
        if (user instanceof Librarian && user.verifyPassword(password)) {
            librarianMenu((Librarian) user);
        } else {
            System.out.println("Invalid credentials!");
        }
    }

    private void memberLogin() {
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║   Available Member Credentials                           ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println(String.format("%-10s %-25s %-15s %-10s", "User ID", "Name", "Password", "Type"));
        System.out.println("=".repeat(70));
        for (User user : users) {
            if (user instanceof Member) {
                Member member = (Member) user;
                System.out.println(String.format("%-10s %-25s %-15s %-10s", 
                    user.getUserId(), user.getName(), getPasswordForDisplay(user), 
                    member.getMembershipType()));
            }
        }
        
        System.out.println("\n--- Member Login ---");
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        User user = findUser(userId);
        if (user instanceof Member && user.verifyPassword(password)) {
            memberMenu((Member) user);
        } else {
            System.out.println("Invalid credentials!");
        }
    }

    private void librarianMenu(Librarian librarian) {
        while (true) {
            System.out.println("\n╔══════════════════════════════════════════════════════════╗");
            System.out.println("║   Librarian Menu - Welcome " + librarian.getName());
            System.out.println("╚══════════════════════════════════════════════════════════╝");
            System.out.println("1. Add Resource");
            System.out.println("2. Remove Resource");
            System.out.println("3. Register New Member");
            System.out.println("4. View All Resources");
            System.out.println("5. View All Transactions");
            System.out.println("6. Search Resources");
            System.out.println("7. View Outstanding Fines");
            System.out.println("8. Logout");
            System.out.print("Enter your choice: ");
            int choice = getIntInput();
            switch (choice) {
                case 1: addResource(); break;
                case 2: removeResource(); break;
                case 3: registerMember(); break;
                case 4: viewAllResources(); break;
                case 5: viewAllTransactions(); break;
                case 6: searchResources(); break;
                case 7: viewOutstandingFines(); break;
                case 8: return;
                default: System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void memberMenu(Member member) {
        while (true) {
            System.out.println("\n╔══════════════════════════════════════════════════════════╗");
            System.out.println("║   Member Menu - Welcome " + member.getName());
            System.out.println("╚══════════════════════════════════════════════════════════╝");
            System.out.println("1. Search Resources");
            System.out.println("2. Borrow Resource");
            System.out.println("3. Return Resource");
            System.out.println("4. View My Checkouts");
            System.out.println("5. View My Fines");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");
            int choice = getIntInput();
            switch (choice) {
                case 1: searchResources(); break;
                case 2: borrowResource(member); break;
                case 3: returnResource(member); break;
                case 4: viewMyCheckouts(member); break;
                case 5: viewMyFines(member); break;
                case 6: return;
                default: System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addResource() {
        System.out.println("\n--- Add New Resource ---");
        System.out.println("1. Add Book");
        System.out.println("2. Add Journal");
        System.out.println("3. Add Digital Equipment");
        System.out.print("Enter resource type: ");
        int type = getIntInput();
        System.out.print("Enter Resource ID: ");
        String id = scanner.nextLine();
        if (findResource(id) != null) {
            System.out.println("Resource ID already exists!");
            return;
        }
        System.out.print("Enter Title/Name: ");
        String title = scanner.nextLine();
        System.out.print("Enter Category: ");
        String category = scanner.nextLine();
        switch (type) {
            case 1:
                System.out.print("Enter Author: ");
                String author = scanner.nextLine();
                System.out.print("Enter ISBN: ");
                String isbn = scanner.nextLine();
                System.out.print("Enter Publication Year: ");
                int year = getIntInput();
                resources.add(new Book(id, title, category, author, isbn, year));
                System.out.println("Book added successfully!");
                break;
            case 2:
                System.out.print("Enter Publisher: ");
                String publisher = scanner.nextLine();
                System.out.print("Enter Volume: ");
                int volume = getIntInput();
                System.out.print("Enter Issue: ");
                int issue = getIntInput();
                resources.add(new Journal(id, title, category, publisher, volume, issue));
                System.out.println("Journal added successfully!");
                break;
            case 3:
                System.out.print("Enter Manufacturer: ");
                String manufacturer = scanner.nextLine();
                System.out.print("Enter Serial Number: ");
                String serialNumber = scanner.nextLine();
                resources.add(new DigitalEquipment(id, title, category, manufacturer, serialNumber));
                System.out.println("Equipment added successfully!");
                break;
            default: System.out.println("Invalid resource type!");
        }
    }

    private void removeResource() {
        System.out.print("Enter Resource ID to remove: ");
        String id = scanner.nextLine();
        Resource resource = findResource(id);
        if (resource == null) {
            System.out.println("Resource not found!");
            return;
        }
        if (!resource.isAvailable()) {
            System.out.println("Cannot remove resource. It is currently borrowed!");
            return;
        }
        resources.remove(resource);
        System.out.println("Resource removed successfully!");
    }

    private void registerMember() {
        System.out.println("\n--- Register New Member ---");
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        if (findUser(userId) != null) {
            System.out.println("User ID already exists!");
            return;
        }
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Membership Type (Student/Faculty): ");
        String type = scanner.nextLine();
        users.add(new Member(userId, name, email, password, type));
        System.out.println("Member registered successfully!");
    }

    private void viewAllResources() {
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║   All Resources                                          ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        if (resources.isEmpty()) {
            System.out.println("No resources available.");
            return;
        }
        for (Resource resource : resources) {
            System.out.println("\n" + "=".repeat(60));
            resource.displayDetails();
        }
    }

    private void viewAllTransactions() {
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║   All Transactions                                       ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }
        for (Transaction txn : transactions) {
            System.out.println("\n" + "=".repeat(60));
            txn.displayDetails();
        }
    }

    private void searchResources() {
        System.out.println("\n--- Search Resources ---");
        System.out.println("1. Search by Title");
        System.out.println("2. Search by Author (Books only)");
        System.out.println("3. Search by Category");
        System.out.println("4. Search by Availability");
        System.out.print("Enter search type: ");
        int searchType = getIntInput();
        List<Resource> results = new ArrayList<>();
        switch (searchType) {
            case 1:
                System.out.print("Enter title (partial match): ");
                String title = scanner.nextLine().toLowerCase();
                for (Resource r : resources) {
                    if (r.getTitle().toLowerCase().contains(title)) {
                        results.add(r);
                    }
                }
                break;
            case 2:
                System.out.print("Enter author name: ");
                String author = scanner.nextLine().toLowerCase();
                for (Resource r : resources) {
                    if (r instanceof Book && ((Book) r).getAuthor().toLowerCase().contains(author)) {
                        results.add(r);
                    }
                }
                break;
            case 3:
                System.out.print("Enter category: ");
                String category = scanner.nextLine().toLowerCase();
                for (Resource r : resources) {
                    if (r.getCategory().toLowerCase().contains(category)) {
                        results.add(r);
                    }
                }
                break;
            case 4:
                System.out.print("Search available only? (yes/no): ");
                String avail = scanner.nextLine().toLowerCase();
                boolean availOnly = avail.equals("yes");
                for (Resource r : resources) {
                    if (!availOnly || r.isAvailable()) {
                        results.add(r);
                    }
                }
                break;
            default: System.out.println("Invalid search type!"); return;
        }
        if (results.isEmpty()) {
            System.out.println("No resources found matching your criteria.");
        } else {
            System.out.println("\nSearch Results (" + results.size() + " found):");
            for (Resource r : results) {
                System.out.println("\n" + "=".repeat(60));
                r.displayDetails();
            }
        }
    }

    private void borrowResource(Member member) {
        if (!member.canBorrow()) {
            System.out.println("You have reached your borrowing limit of " + member.getMaxBorrowLimit() + " items!");
            return;
        }
        System.out.print("Enter Resource ID to borrow: ");
        String resourceId = scanner.nextLine();
        Resource resource = findResource(resourceId);
        if (resource == null) {
            System.out.println("Resource not found!");
            return;
        }
        if (!resource.isAvailable()) {
            System.out.println("Resource is not available for borrowing!");
            return;
        }
        System.out.print("Enter borrowing period in days (default 14): ");
        int days = getIntInput();
        if (days <= 0) days = 14;
        Transaction txn = new Transaction(member.getUserId(), resourceId, days);
        transactions.add(txn);
        resource.setAvailable(false);
        member.incrementBorrowCount();
        System.out.println("Resource borrowed successfully!");
        System.out.println("Transaction ID: " + txn.getTransactionId());
        System.out.println("Due Date: " + new java.util.Date(txn.getDueDate()));
    }

    private void returnResource(Member member) {
        System.out.print("Enter Resource ID to return: ");
        String resourceId = scanner.nextLine();
        Transaction txn = findActiveTransaction(member.getUserId(), resourceId);
        if (txn == null) {
            System.out.println("No active transaction found for this resource!");
            return;
        }
        Resource resource = findResource(resourceId);
        if (resource != null) {
            resource.setAvailable(true);
        }
        txn.returnResource();
        member.decrementBorrowCount();
        System.out.println("Resource returned successfully!");
        if (txn.getFine() > 0) {
            System.out.println("Fine charged: Rs. " + txn.getFine());
            System.out.println("(Overdue by " + ((txn.getFine() / 10)) + " days)");
        } else {
            System.out.println("No fine charged. Returned on time!");
        }
    }

    private void viewMyCheckouts(Member member) {
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║   My Active Checkouts                                    ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        List<Transaction> myTransactions = new ArrayList<>();
        for (Transaction txn : transactions) {
            if (txn.getMemberId().equals(member.getUserId()) && !txn.isReturned()) {
                myTransactions.add(txn);
            }
        }
        if (myTransactions.isEmpty()) {
            System.out.println("You have no active checkouts.");
        } else {
            for (Transaction txn : myTransactions) {
                System.out.println("\n" + "=".repeat(60));
                txn.displayDetails();
                Resource r = findResource(txn.getResourceId());
                if (r != null) {
                    System.out.println("Resource: " + r.getTitle());
                }
            }
        }
    }

    private void viewMyFines(Member member) {
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║   My Fines                                               ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        double totalFines = 0;
        boolean hasFines = false;
        for (Transaction txn : transactions) {
            if (txn.getMemberId().equals(member.getUserId())) {
                double fine = txn.isReturned() ? txn.getFine() : txn.calculateCurrentFine();
                if (fine > 0) {
                    hasFines = true;
                    System.out.println("\n" + "=".repeat(60));
                    System.out.println("Transaction ID: " + txn.getTransactionId());
                    System.out.println("Resource ID: " + txn.getResourceId());
                    System.out.println("Fine: Rs. " + fine);
                    System.out.println("Status: " + (txn.isReturned() ? "Paid" : "Outstanding"));
                    totalFines += fine;
                }
            }
        }
        if (!hasFines) {
            System.out.println("You have no fines. Great job!");
        } else {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("Total Fines: Rs. " + totalFines);
        }
    }

    private void viewOutstandingFines() {
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║   Outstanding Fines Report                               ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        Map<String, Double> finesByMember = new HashMap<>();
        for (Transaction txn : transactions) {
            if (!txn.isReturned()) {
                double fine = txn.calculateCurrentFine();
                if (fine > 0) {
                    finesByMember.put(txn.getMemberId(), 
                        finesByMember.getOrDefault(txn.getMemberId(), 0.0) + fine);
                }
            }
        }
        if (finesByMember.isEmpty()) {
            System.out.println("No outstanding fines.");
        } else {
            for (Map.Entry<String, Double> entry : finesByMember.entrySet()) {
                User user = findUser(entry.getKey());
                System.out.println("\n" + "=".repeat(60));
                System.out.println("Member ID: " + entry.getKey());
                System.out.println("Member Name: " + (user != null ? user.getName() : "Unknown"));
                System.out.println("Outstanding Fine: Rs. " + entry.getValue());
            }
        }
    }

    private User findUser(String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) return user;
        }
        return null;
    }

    private Resource findResource(String resourceId) {
        for (Resource resource : resources) {
            if (resource.getResourceId().equals(resourceId)) return resource;
        }
        return null;
    }

    private Transaction findActiveTransaction(String memberId, String resourceId) {
        for (Transaction txn : transactions) {
            if (txn.getMemberId().equals(memberId) && 
                txn.getResourceId().equals(resourceId) && 
                !txn.isReturned()) {
                return txn;
            }
        }
        return null;
    }

    private int getIntInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void main(String[] args) {
        URLMS system = new URLMS();
        system.run();
    }
}