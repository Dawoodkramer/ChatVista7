/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chatvista7;
import javax.swing.JOptionPane;
/**
 *
 * @author lab_services_student
 */
public class ChatVista7 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         // Welcome message for QuickChat - First thing user sees
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");
        
        //Declaration of the variables that will be used
        String username = "";
        String password = "";
        String cellnumber = "";
        String firstName = "";
        String lastName = "";
        boolean isRegistered = false;
        boolean isLoggedIn = false;
        
        //Creating an object to instantiate the methods from the MethodDefinitions class
        MethodDefinitions li = new MethodDefinitions();
        
        // Main application loop - Login/Register/Exit menu
        while (true) {
            // Display initial menu options
            String initialChoice = JOptionPane.showInputDialog(
                "Please select an option:\n" +
                "1) Login\n" +
                "2) Register\n" +
                "3) Exit"
            );
            
            if (initialChoice == null || initialChoice.equals("3")) {
                // User chose to exit or cancelled
                JOptionPane.showMessageDialog(null, "Thank you for using QuickChat. Goodbye!");
                break;
            } else if (initialChoice.equals("1")) {
                // Login option - redirect to register
                if (!isRegistered) {
                    JOptionPane.showMessageDialog(null, "Please create an account to register.");
                    continue; // Take them back to the menu
                } else {
                    // If already registered, allow login with validation loop
                    isLoggedIn = loginUser(li, username, password, firstName, lastName);
                    if (isLoggedIn) {
                        break; // Exit to messaging menu only if login successful
                    }
                    // If login failed, continue loop to show menu again
                }
            } else if (initialChoice.equals("2")) {
                // Register option 
                Object[] registrationResult = registerUser(li);
                if (registrationResult != null) {
                    username = (String) registrationResult[0];
                    password = (String) registrationResult[1];
                    cellnumber = (String) registrationResult[2];
                    firstName = (String) registrationResult[3];
                    lastName = (String) registrationResult[4];
                    isRegistered = true;
                    
                    JOptionPane.showMessageDialog(null, "Registration completed successfully! You can now login.");
                }
            } else {
                // Invalid option
                JOptionPane.showMessageDialog(null, "Invalid option. Please select 1, 2, or 3.");
            }
        }
        
        // Part 2 & 3: messaging functionality - only if registered and logged in
        if (isRegistered && isLoggedIn) {
            // Creating Message object for messaging functionality
            Message messageHandler = new Message();
            
            // Messaging menu loop
            while (true) {
                // Display messaging menu options
                String menuChoice = JOptionPane.showInputDialog(
                    "Please select an option:\n" +
                    "1) Send Messages\n" +
                    "2) Show recently sent messages\n" +
                    "3) Display Reports\n" +
                    "4) Search Messages\n" +
                    "5) Delete Message\n" +
                    "6) View Disregarded Messages\n" +
                    "7) View Stored Messages\n" +
                    "8) Quit"
                );
                
                if (menuChoice == null || menuChoice.equals("8")) {
                    // User chose to quit or cancelled
                    JOptionPane.showMessageDialog(null, "Thank you for using QuickChat. Goodbye!");
                    break;
                } else if (menuChoice.equals("1")) {
                    // Send Messages functionality
                    sendMessages(messageHandler);
                } else if (menuChoice.equals("2")) {
                    // Show recently sent messages - now fully functional
                    showRecentMessages(messageHandler);
                } else if (menuChoice.equals("3")) {
                    // Display Reports functionality
                    displayReports(messageHandler);
                } else if (menuChoice.equals("4")) {
                    // Search Messages functionality
                    searchMessages(messageHandler);
                } else if (menuChoice.equals("5")) {
                    // Delete Message functionality
                    deleteMessage(messageHandler);
                } else if (menuChoice.equals("6")) {
                    // View Disregarded Messages functionality
                    viewDisregardedMessages(messageHandler);
                } else if (menuChoice.equals("7")) {
                    // View Stored Messages functionality
                    viewStoredMessages(messageHandler);
                } else {
                    // Invalid option
                    JOptionPane.showMessageDialog(null, "Invalid option. Please select 1, 2, 3, 4, 5, 6, 7, or 8.");
                }
            }
        }
    }
    
    /**
     * Method to handle user registration using existing validation methods
     */
    private static Object[] registerUser(MethodDefinitions li) {
        String username;
        String password;
        String cellnumber;
        String firstName;
        String lastName;
        
        // Get user's first and last name
        firstName = JOptionPane.showInputDialog("Enter your first name:");
        if (firstName == null) return null; // User cancelled
        
        lastName = JOptionPane.showInputDialog("Enter your last name:");
        if (lastName == null) return null; // User cancelled
        
        //Using a while loop so that when the user enters incorrect data
        //they will be prompted to enter again.
        while (true) {
            username = JOptionPane.showInputDialog("Enter a username 5 characters long \n It must contain an underscore:");
            
            if (username == null) return null; // User cancelled
            
        //Instantiating the method check user name that has the parameters for the username .   
            if (li.checkUserName(username)) {
                JOptionPane.showMessageDialog(null, "Username successfully captured.");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Username is not correctly formatted, please ensure that your username contains an underscore \n and is no more than five characters in length.");
            }
        }
        
        while (true) {
            password = JOptionPane.showInputDialog("Enter a password of 8 characters or more.\n Include a capital letter, number, and special character.");
            
            if (password == null) return null; // User cancelled
            
            //Instantiating the method check password that has the parameters for the password.
            if (li.checkPassword(password)) {
                JOptionPane.showMessageDialog(null, "Password successfully captured.");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Password is not correctly formatted; please ensure that the password contains at least eight characters, \n a capital letter, a number, and a special character.");
            }
        }
        
        while (true) {
            cellnumber =  JOptionPane.showInputDialog("Enter a cell phone number \n include +27 and 9 digits after it):");
            
            if (cellnumber == null) return null; // User cancelled
            
            //Instantiating the method check cell phone number that has the parameters for the cell phone number.
            if (li.checkCellPhoneNumber(cellnumber)) {
                JOptionPane.showMessageDialog(null, "Cell number successfully added.");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Cell phone number is incorrectly formatted \n or does not contain international code");
            }
        }
        
        // Final registration confirmation using the methods check username and check password to match what the user has entered.
        // Instead of typing out the display message again I have it typed in the other class already 
        // All I did was create a var to store the message that I'm calling from the other class.
        String regMessage = li.registerUser(username, password);
        JOptionPane.showMessageDialog(null, regMessage);
        
        return new Object[]{username, password, cellnumber, firstName, lastName};
    }
    
    /**
     * Method to handle user login with validation loop
     */
    private static boolean loginUser(MethodDefinitions li, String registeredUsername, String registeredPassword, String firstName, String lastName) {
        // Login validation loop - keep asking until correct credentials or user cancels
        while (true) {
            // Prompting the user for login credentials 
            // The username and password they originally chose
            String loginUsername = JOptionPane.showInputDialog("Enter your username to login:");
            
            if (loginUsername == null) {
                return false; // User cancelled login
            }
            
            String loginPassword = JOptionPane.showInputDialog("Enter your password to login:");
            
            if (loginPassword == null) {
                return false; // User cancelled login
            }

            // Calling loginUser method
            boolean isLoginSuccessful = li.loginUser(loginUsername, loginPassword, registeredUsername, registeredPassword);

            // Displaying login status
            // Calling the first and second name
            String loginMessage = li.returnLoginStatus(isLoginSuccessful, firstName, lastName);
            JOptionPane.showMessageDialog(null, loginMessage);
            
            if (isLoginSuccessful) {
                return true; // Login successful, exit method
            }
            
            // If login failed, ask if they want to try again
            int choice = JOptionPane.showConfirmDialog(
                null, 
                "Login failed. Would you like to try again?", 
                "Login Failed", 
                JOptionPane.YES_NO_OPTION
            );
            
            if (choice == JOptionPane.NO_OPTION) {
                return false; // User chose not to retry, exit login
            }
            // If user chose YES, loop continues to ask for credentials again
        }
    }

    /**
     * Method to handle sending multiple messages
     */
    private static void sendMessages(Message messageHandler) {
        // Ask user how many messages they want to send
        String numMessagesStr = JOptionPane.showInputDialog("How many messages would you like to send?");
        
        if (numMessagesStr == null) {
            return; // User cancelled
        }
        
        try {
            int numMessages = Integer.parseInt(numMessagesStr);
            
            if (numMessages <= 0) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number of messages.");
                return;
            }
            
            // Loop to send the specified number of messages
            for (int i = 1; i <= numMessages; i++) {
                JOptionPane.showMessageDialog(null, "Sending message " + i + " of " + numMessages);
                
                // Get recipient number
                String recipient = "";
                while (true) {
                    recipient = JOptionPane.showInputDialog("Enter recipient's cell phone number:");
                    
                    if (recipient == null) {
                        return; // User cancelled
                    }
                    
                    String recipientValidation = messageHandler.checkRecipientCell(recipient);
                    if (recipientValidation.equals("Cell phone number successfully captured.")) {
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, recipientValidation);
                    }
                }
                
                // Get message text
                String messageText = "";
                while (true) {
                    messageText = JOptionPane.showInputDialog("Enter your message (max 250 characters):");
                    
                    if (messageText.length() == 0 || messageText.length() > 250) {
                        JOptionPane.showMessageDialog(null, "You have exceeded 250 characters or you have not entered a message, please re-enter you message");
                    } else {
                        JOptionPane.showMessageDialog(null, "Message captured successfully");
                    }
                    
                    String messageValidation = messageHandler.checkMessageLength(messageText);
                    if (messageValidation.equals("Message ready to send.")) {
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, messageValidation);
                    }
                }
                
                // Generate message ID and hash
                String messageID = messageHandler.generateMessageID();
                String messageHash = messageHandler.createMessageHash(messageID, i, messageText);
                
                // Ask user what to do with the message
                String action = messageHandler.sentMessage();
                
                if (action.equals("Send")) {
                    // Add message to sent messages array
                    messageHandler.addToSentMessages(messageID, messageHash, recipient, messageText, i);
                    
                    // Display message details
                    String messageDetails = "Message Details:\n" +
                                          "Message ID: " + messageID + "\n" +
                                          "Message Hash: " + messageHash + "\n" +
                                          "Recipient: " + recipient + "\n" +
                                          "Message: " + messageText;
                    
                    JOptionPane.showMessageDialog(null, messageDetails);
                    
                    // Store message in JSON file
                    messageHandler.storeMessage(messageID, messageHash, recipient, messageText, i);
                    
                    JOptionPane.showMessageDialog(null, "Message successfully sent.");
                } else if (action.equals("Disregard")) {
                    // Add message to disregarded messages array
                    messageHandler.addToDisregardedMessages(messageID, messageHash, recipient, messageText, i);
                    JOptionPane.showMessageDialog(null, "Message disregarded.");
                    i--; 
                } else if (action.equals("Store")) {
                    // Add message to stored messages array
                    messageHandler.addToStoredMessages(messageID, messageHash, recipient, messageText, i);
                    // Store message in JSON file
                    messageHandler.storeMessage(messageID, messageHash, recipient, messageText, i);
                    JOptionPane.showMessageDialog(null, "Message successfully stored.");
                    i--;
                }
            }
            
            // Display total messages sent
            int totalSent = messageHandler.returnTotalMessages();
            JOptionPane.showMessageDialog(null, "Total messages sent: " + totalSent);
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number.");
        }
    }
    
    /**
     * Method to show recent messages
     */
    private static void showRecentMessages(Message messageHandler) {
        String recentMessages = messageHandler.displaySentMessages();
        if (recentMessages.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No messages have been sent yet.");
        } else {
            JOptionPane.showMessageDialog(null, "Recent Sent Messages:\n" + recentMessages);
        }
    }
    
    /**
     * Method to display reports
     */
    private static void displayReports(Message messageHandler) {
        String[] reportOptions = {
            "Display Longest Message",
            "Display Full Message Report",
            "Display Sender and Recipient Details"
        };
        
        String choice = (String) JOptionPane.showInputDialog(
            null,
            "Select a report to display:",
            "Message Reports",
            JOptionPane.QUESTION_MESSAGE,
            null,
            reportOptions,
            reportOptions[0]
        );
        
        if (choice == null) return; // User cancelled
        
        if (choice.equals("Display Longest Message")) {
            String longestMessage = messageHandler.displayLongestMessage();
            JOptionPane.showMessageDialog(null, longestMessage);
        } else if (choice.equals("Display Full Message Report")) {
            String fullReport = messageHandler.displayFullReport();
            JOptionPane.showMessageDialog(null, fullReport);
        } else if (choice.equals("Display Sender and Recipient Details")) {
            String senderRecipientDetails = messageHandler.displaySenderRecipientDetails();
            JOptionPane.showMessageDialog(null, senderRecipientDetails);
        }
    }
    
    /**
     * Method to search messages
     */
    private static void searchMessages(Message messageHandler) {
        String[] searchOptions = {
            "Search by Message ID",
            "Search by Recipient"
        };
        
        String choice = (String) JOptionPane.showInputDialog(
            null,
            "Select search type:",
            "Search Messages",
            JOptionPane.QUESTION_MESSAGE,
            null,
            searchOptions,
            searchOptions[0]
        );
        
        if (choice == null) return; // User cancelled
        
        if (choice.equals("Search by Message ID")) {
            String messageID = JOptionPane.showInputDialog("Enter Message ID to search:");
            if (messageID != null) {
                String result = messageHandler.searchByMessageID(messageID);
                JOptionPane.showMessageDialog(null, result);
            }
        } else if (choice.equals("Search by Recipient")) {
            String recipient = JOptionPane.showInputDialog("Enter recipient number to search:");
            if (recipient != null) {
                String result = messageHandler.searchByRecipient(recipient);
                JOptionPane.showMessageDialog(null, result);
            }
        }
    }
    
    /**
     * Method to delete a message
     */
    private static void deleteMessage(Message messageHandler) {
        String messageHash = JOptionPane.showInputDialog("Enter Message Hash to delete:");
        if (messageHash != null) {
            String result = messageHandler.deleteMessageByHash(messageHash);
            JOptionPane.showMessageDialog(null, result);
        }
    }
    
    /**
     * Method to view all disregarded messages
     */
    private static void viewDisregardedMessages(Message messageHandler) {
        String disregardedMessages = messageHandler.viewDisregardedMessages();
        JOptionPane.showMessageDialog(null, disregardedMessages, "Disregarded Messages", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Enhanced: Method to view all stored messages  
     */
    private static void viewStoredMessages(Message messageHandler) {
        String storedMessages = messageHandler.viewStoredMessages();
        JOptionPane.showMessageDialog(null, storedMessages, "Stored Messages", JOptionPane.INFORMATION_MESSAGE);
    }
}