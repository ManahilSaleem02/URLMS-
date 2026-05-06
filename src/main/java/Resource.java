
abstract class Resource {
    private String resourceId;
    private String title;
    private String category;
    private boolean isAvailable;

    public Resource(String resourceId, String title, String category) {
        this.resourceId = resourceId;
        this.title = title;
        this.category = category;
        this.isAvailable = true;
    }

    public String getResourceId() { return resourceId; }
    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { this.isAvailable = available; }
    
    public abstract String getResourceType();
    public abstract void displayDetails();
}
