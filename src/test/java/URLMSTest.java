
// ==================== File: URLMSTest.java ====================

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class URLMSTest {

    @Test
    public void testLibrarianCreation() {
        Librarian lib = new Librarian("L001", "Ahmed", "ahmed@uni.edu", "pass", "EMP001");
        assertEquals("L001", lib.getUserId());
        assertTrue(lib.verifyPassword("pass"));
    }

    @Test
    public void testMemberBorrowLimit() {
        Member student = new Member("M001", "Hassan", "hassan@edu", "pass", "Student");
        Member faculty = new Member("M002", "Imran", "imran@edu", "pass", "Faculty");
        assertEquals(5, student.getMaxBorrowLimit());
        assertEquals(10, faculty.getMaxBorrowLimit());
    }

    @Test
    public void testResourceManagement() {
        Book book = new Book("B001", "Clean Code", "Programming", "Martin", "ISBN123", 2008);
        assertTrue(book.isAvailable());
        book.setAvailable(false);
        assertFalse(book.isAvailable());
    }

    @Test
    public void testTransactionWorkflow() {
        Transaction txn = new Transaction("M001", "B001", 14);
        assertFalse(txn.isReturned());
        txn.returnResource();
        assertTrue(txn.isReturned());
    }
}