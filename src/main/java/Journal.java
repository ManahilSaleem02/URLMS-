
class Journal extends Resource {
    private String publisher;
    private int volume;
    private int issue;

    public Journal(String resourceId, String title, String category, String publisher, int volume, int issue) {
        super(resourceId, title, category);
        this.publisher = publisher;
        this.volume = volume;
        this.issue = issue;
    }

    public String getPublisher() { return publisher; }
    public int getVolume() { return volume; }
    public int getIssue() { return issue; }

    @Override
    public String getResourceType() { return "Journal"; }

    @Override
    public void displayDetails() {
        System.out.println("Journal ID: " + getResourceId());
        System.out.println("Title: " + getTitle());
        System.out.println("Publisher: " + publisher);
        System.out.println("Volume: " + volume + ", Issue: " + issue);
        System.out.println("Category: " + getCategory());
        System.out.println("Status: " + (isAvailable() ? "Available" : "Borrowed"));
    }
}
