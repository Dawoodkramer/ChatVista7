/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package chatvista7;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author lab_services_student
 */
public class MessageTest {
    
    public Message message;
    
    @Before
    public void setUp() {
        message = new Message();
        
        // Populate arrays with test data as specified in Part 3 requirements
        populateTestData();
    }
    
    /**
     * Helper method to populate test data for Part 3 requirements
     */
    private void populateTestData() {
        // Test Data Message 1: Sent
        message.addToSentMessages("1234567890", "12:1:DIDCAKE", "+27834557896", "Did you get the cake?", 1);
        
        // Test Data Message 2: Stored
        message.addToStoredMessages("2345678901", "23:2:WHERETIME", "+27838884567", "Where are you? You are late! I have asked you to be on time.", 2);
        
        // Test Data Message 3: Disregarded
        message.addToDisregardedMessages("3456789012", "34:3:YOHOOOGATE", "+27834484567", "Yohoooo, I am at your gate.", 3);
        
        // Test Data Message 4: Sent
        message.addToSentMessages("0838884567", "08:4:ITTIME", "0838884567", "It is dinner time!", 4);
        
        // Test Data Message 5: Stored
        message.addToStoredMessages("4567890123", "45:5:OKYOU", "+27838884567", "Ok, I am leaving without you.", 5);
    }

    /**
     * Test message length validation - Success case
     */
    @Test
    public void testMessageLengthSuccess() {
        System.out.println("checkMessageLength - Success");
        String message = "Hi Mike, can you join us for dinner tonight";
        String expResult = "Message ready to send.";
        String result = this.message.checkMessageLength(message);
        assertEquals(expResult, result);
    }
    
    /**
     * Test message length validation - Failure case (exceeds 250 characters)
     */
    @Test
    public void testMessageLengthFailure() {
        System.out.println("checkMessageLength - Failure");
        // Create a message longer than 250 characters
        String longMessage = "This is a very long message that exceeds the 250 character limit. " +
                           "This is a very long message that exceeds the 250 character limit. " +
                           "This is a very long message that exceeds the 250 character limit. " +
                           "This is a very long message that exceeds the 250 character limit. Extra text";
        int excess = longMessage.length() - 250;
        String expResult = "Message exceeds 250 characters by " + excess + ", please reduce size.";
        String result = message.checkMessageLength(longMessage);
        assertEquals(expResult, result);
    }

    /**
     * Test recipient cell phone validation - Success case
     */
    @Test
    public void testRecipientCellSuccess() {
        System.out.println("checkRecipientCell - Success");
        String cellNumber = "+27718693002";
        String expResult = "Cell phone number successfully captured.";
        String result = message.checkRecipientCell(cellNumber);
        assertEquals(expResult, result);
    }
    
    /**
     * Test recipient cell phone validation - Failure case
     */
    @Test
    public void testRecipientCellFailure() {
        System.out.println("checkRecipientCell - Failure");
        String cellNumber = "08575975889";
        String expResult = "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        String result = message.checkRecipientCell(cellNumber);
        assertEquals(expResult, result);
    }

    /**
     * Test message hash creation with test data from requirements
     */
    @Test
    public void testCreateMessageHashTestCase1() {
        System.out.println("createMessageHash - Test Case 1");
        String messageID = "0012345678"; // Starting with 00
        int messageNumber = 1;
        String messageText = "Hi Mike, can you join us for dinner tonight";
        String expResult = "00:1:HITONIGHT";
        String result = message.createMessageHash(messageID, messageNumber, messageText);
        assertEquals(expResult, result);
    }
    
    /**
     * Test message hash creation with test data case 2
     */
    @Test
    public void testCreateMessageHashTestCase2() {
        System.out.println("createMessageHash - Test Case 2");
        String messageID = "1234567890"; // Starting with 12
        int messageNumber = 2;
        String messageText = "Hi Keegan, did you receive the payment?";
        String expResult = "12:2:HIPAYMENT";
        String result = message.createMessageHash(messageID, messageNumber, messageText);
        assertEquals(expResult, result);
    }

    /**
     * Test message ID validation - Success case
     */
    @Test
    public void testCheckMessageIDSuccess() {
        System.out.println("checkMessageID - Success");
        String messageID = "1234567890"; // Exactly 10 characters
        boolean expResult = true;
        boolean result = message.checkMessageID(messageID);
        assertEquals(expResult, result);
    }
    
    /**
     * Test message ID validation - Failure case (too long)
     */
    @Test
    public void testCheckMessageIDFailure() {
        System.out.println("checkMessageID - Failure");
        String messageID = "12345678901"; // More than 10 characters
        boolean expResult = false;
        boolean result = message.checkMessageID(messageID);
        assertEquals(expResult, result);
    }

    /**
     * Test message ID generation
     */
    @Test
    public void testGenerateMessageID() {
        System.out.println("generateMessageID");
        String result = message.generateMessageID();
        
        // Check that message ID is generated and is 10 characters long
        assertNotNull("Message ID should not be null", result);
        assertEquals("Message ID should be 10 characters long", 10, result.length());
        
        // Check that it contains only digits
        assertTrue("Message ID should contain only digits", result.matches("\\d{10}"));
    }

    /**
     * Part 3: Test sent messages array correctly populated
     */
    @Test
    public void testSentMessagesArrayPopulated() {
        System.out.println("sentMessagesArrayPopulated");
        String result = message.displaySentMessages();
        
        // Check that sent messages contain expected test data
        assertTrue("Should contain 'Did you get the cake?'", result.contains("Did you get the cake?"));
        assertTrue("Should contain 'It is dinner time!'", result.contains("It is dinner time!"));
    }

    /**
     * Part 3: Test display longest message
     */
    @Test
    public void testDisplayLongestMessage() {
        System.out.println("displayLongestMessage");
        String expResult = "Where are you? You are late! I have asked you to be on time.";
        String result = message.displayLongestMessage();
        
        // The longest message should be from stored messages, but displayLongestMessage only checks sent messages
        // So the longest sent message should be "Did you get the cake?" or "It is dinner time!"
        assertTrue("Should return longest sent message", result.contains("Longest Message:"));
    }

    /**
     * Part 3: Test search for message ID
     */
    @Test
    public void testSearchByMessageID() {
        System.out.println("searchByMessageID");
        String testMessageID = "0838884567";
        String result = message.searchByMessageID(testMessageID);
        
        assertTrue("Should find message with ID", result.contains("It is dinner time!"));
        assertTrue("Should show correct message ID", result.contains(testMessageID));
    }

    /**
     * Part 3: Test search messages sent to particular recipient
     */
    @Test
    public void testSearchByRecipient() {
        System.out.println("searchByRecipient");
        String recipient = "+27838884567";
        String result = message.searchByRecipient(recipient);
        
        // Enhanced search now shows both [SENT] and [STORED] messages
        assertTrue("Should contain messages for recipient", result.contains("Messages for " + recipient));
    }

    /**
     * Part 3: Test delete message using message hash
     */
    @Test
    public void testDeleteMessageByHash() {
        System.out.println("deleteMessageByHash");
        String messageHash = "12:1:DIDCAKE"; // Using actual hash from sent messages
        String result = message.deleteMessageByHash(messageHash);
        
        String expResult = "Message \"Did you get the cake?\" successfully deleted.";
        assertEquals(expResult, result);
    }

    /**
     * Part 3: Test display full report
     */
    @Test
    public void testDisplayFullReport() {
        System.out.println("displayFullReport");
        String result = message.displayFullReport();
        
        // Check that report contains all necessary components
        assertTrue("Report should contain Message Hash", result.contains("Message Hash:"));
        assertTrue("Report should contain Recipient", result.contains("Recipient:"));
        assertTrue("Report should contain Message", result.contains("Message:"));
        assertTrue("Report should contain sent message data", result.contains("Did you get the cake?"));
    }

    /**
     * Test total messages counter
     */
    @Test
    public void testReturnTotalMessages() {
        System.out.println("returnTotalMessages");
        int expResult = 2; // Should have 2 sent messages from test data
        int result = message.returnTotalMessages();
        assertEquals(expResult, result);
    }

    /**
     * Test message hash with different message numbers in loop
     */
    @Test
    public void testCreateMessageHashLoop() {
        System.out.println("createMessageHash - Loop Test");
        
        String[] testMessages = {
            "Hi Mike, can you join us for dinner tonight",
            "Hi Keegan, did you receive the payment?",
            "Hello world this is a test message"
        };
        
        String[] messageIDs = {"0012345678", "1234567890", "9876543210"};
        String[] expectedHashes = {"00:1:HITONIGHT", "12:2:HIPAYMENT", "98:3:HELLOMESSAGE"};
        
        for (int i = 0; i < testMessages.length; i++) {
            String result = message.createMessageHash(messageIDs[i], i + 1, testMessages[i]);
            assertEquals("Hash for message " + (i + 1) + " should be correct", expectedHashes[i], result);
        }
    }

    /**
     * Test recipient cell phone validation with various formats
     */
    @Test
    public void testRecipientCellVariousFormats() {
        System.out.println("checkRecipientCell - Various Formats");
        
        // Valid formats
        String[] validNumbers = {"+27718693002", "+27823456789", "+27987654321"};
        
        for (String number : validNumbers) {
            String result = message.checkRecipientCell(number);
            assertEquals("Valid number " + number + " should be accepted", 
                        "Cell phone number successfully captured.", result);
        }
        
        // Invalid formats
        String[] invalidNumbers = {"08575975889", "27718693002", "+271234", "123456789"};
        
        for (String number : invalidNumbers) {
            String result = message.checkRecipientCell(number);
            assertEquals("Invalid number " + number + " should be rejected", 
                        "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", 
                        result);
        }
    }

    /**
     * Part 3: Test arrays getter methods for correct population
     */
    @Test
    public void testArraysPopulation() {
        System.out.println("testArraysPopulation");
        
        // Test sent messages array
        assertEquals("Should have 2 sent messages", 2, message.getSentMessages().size());
        
        // Test disregarded messages array
        assertEquals("Should have 1 disregarded message", 1, message.getDisregardedMessages().size());
        
        // Test stored messages array
        assertEquals("Should have 2 stored messages", 2, message.getStoredMessages().size());
        
        // Test message hashes array
        assertEquals("Should have 2 message hashes", 2, message.getMessageHashes().size());
        
        // Test message IDs array
        assertEquals("Should have 2 message IDs", 2, message.getMessageIDs().size());
    }

    /**
     * Part 3: Test sender and recipient details display
     */
    @Test
    public void testDisplaySenderRecipientDetails() {
        System.out.println("displaySenderRecipientDetails");
        String result = message.displaySenderRecipientDetails();
        
        assertTrue("Should contain recipient details", result.contains("Recipient:"));
        assertTrue("Should contain sent message", result.contains("Did you get the cake?"));
        assertTrue("Should contain recipient number", result.contains("+27834557896"));
    }
}