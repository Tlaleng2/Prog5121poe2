/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package prog5121poe;

import java.util.UUID;

public class message {

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    // Constructor for a new message
    public message(int messageNumber, String recipient,
                   String messageText) {

        this.messageID = UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 10);

        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash();
    }

    // Constructor for loading an existing message
    public message(String messageID, int messageNumber,
                   String recipient, String messageText,
                   String messageHash) {

        this.messageID = messageID;
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = messageHash;
    }

    // Check message ID
    public boolean checkMessageID() {
        return messageID != null
                && messageID.length() <= 10;
    }

    // Validate South African cellphone number
    public boolean checkRecipientCell() {
        return recipient != null
                && recipient.matches("^\\+27[6-8][0-9]{8}$");
    }

    // Check message length
    public boolean checkMessageLength() {
        return messageText != null
                && !messageText.trim().isEmpty()
                && messageText.length() <= 250;
    }

    // Create message hash
    public String createMessageHash() {

        String[] words = messageText.trim().split("\\s+");

        String firstWord = words.length > 0
                ? words[0] : "";

        String lastWord = words.length > 0
                ? words[words.length - 1] : "";

        String firstTwoID = messageID.substring(0, 2);

        return (firstTwoID + ":"
                + messageNumber + ":"
                + firstWord + lastWord).toUpperCase();
    }

    // Get message ID
    public String getMessageID() {
        return messageID;
    }

    // Get message number
    public int getMessageNumber() {
        return messageNumber;
    }

    // Get recipient
    public String getRecipient() {
        return recipient;
    }

    // Get message text
    public String getMessageText() {
        return messageText;
    }

    // Get message hash
    public String getMessageHash() {
        return messageHash;
    }

    // Display message details
    public String printMessage() {

        return "Message ID: " + messageID
                + "\nMessage Number: " + messageNumber
                + "\nRecipient: " + recipient
                + "\nMessage: " + messageText
                + "\nMessage Hash: " + messageHash;
    }
}

