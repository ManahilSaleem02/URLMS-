
class Book extends Resource {
    private String author;
    private String isbn;
    private int publicationYear;

    public Book(String resourceId, String title, String category, String author, String isbn, int publicationYear) {
        super(resourceId, title, category);
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
    }

    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public int getPublicationYear() { return publicationYear; }

    @Override
    public String getResourceType() { return "Book"; }

    @Override
    public void displayDetails() {
        System.out.println("Book ID: " + getResourceId());
        System.out.println("Title: " + getTitle());
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("Category: " + getCategory());
        System.out.println("Year: " + publicationYear);
        System.out.println("Status: " + (isAvailable() ? "Available" : "Borrowed"));
    }
}
