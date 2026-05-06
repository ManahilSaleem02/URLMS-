
class DigitalEquipment extends Resource {
    private String manufacturer;
    private String serialNumber;

    public DigitalEquipment(String resourceId, String title, String category, String manufacturer, String serialNumber) {
        super(resourceId, title, category);
        this.manufacturer = manufacturer;
        this.serialNumber = serialNumber;
    }

    public String getManufacturer() { return manufacturer; }
    public String getSerialNumber() { return serialNumber; }

    @Override
    public String getResourceType() { return "Digital Equipment"; }

    @Override
    public void displayDetails() {
        System.out.println("Equipment ID: " + getResourceId());
        System.out.println("Name: " + getTitle());
        System.out.println("Manufacturer: " + manufacturer);
        System.out.println("Serial Number: " + serialNumber);
        System.out.println("Category: " + getCategory());
        System.out.println("Status: " + (isAvailable() ? "Available" : "Borrowed"));
    }
}
