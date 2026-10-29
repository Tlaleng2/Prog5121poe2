/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package prog5121poe;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuickChat {

    private final Scanner scanner;
    private final List<message> sentMessages;
    private int messageCounter;

    private static final Path FILE_PATH = Path.of("messages.txt");

    public QuickChat(Scanner scanner) {
        this.scanner = scanner;
        this.sentMessages = new ArrayList<>();
        this.messageCounter = 0;
        loadMessages();
    }

    public void startQuickChat() {

        System.out.println("\n===== QUICKCHAT =====");
        System.out.println("Welcome to QuickChat!");

        boolean running = true;

        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Send messages");
            System.out.println("2. View sent messages");
            System.out.println("3. Search for a message");
            System.out.println("4. Delete a message");
            System.out.println("5. Display message report");
            System.out.println("6. Quit");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {

                case "1":
                    sendMessages();
                    break;

                case "2":
                    viewSentMessages();
                    break;

                case "3":
                    searchMessage();
                    break;

                case "4":
                    deleteMessage();
                    break;

                case "5":
                    displayMessageReport();
                    break;

                case "6":
                    System.out.println("Thank you for using QuickChat!");
                    running = false;
                    break;

                default:
                    System.out.println(
                            "Invalid option. Please choose from 1 to 6.");
                    break;
            }
        }
    }

    private void sendMessages() {

        System.out.print("How many messages would you like to send? ");

        int numberOfMessages;

        try {
            numberOfMessages = Integer.parseInt(scanner.nextLine());

            if (numberOfMessages <= 0) {
                System.out.println(
                        "Please enter a number greater than zero.");
                return;
            }

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid whole number.");
            return;
        }

        for (int i = 0; i < numberOfMessages; i++) {

            System.out.println("\nMessage " + (i + 1));

            System.out.print(
                    "Enter recipient cellphone number (+27...): ");
            String recipient = scanner.nextLine().trim();

            if (!recipient.matches("^\\+27[6-8][0-9]{8}$")) {
                System.out.println(
                        "Invalid cellphone number. Message not processed.");
                continue;
            }

            System.out.print("Enter your message: ");
            String messageText = scanner.nextLine();

            if (messageText.trim().isEmpty()
                    || messageText.length() > 250) {

                System.out.println(
                        "Message must contain 1 to 250 characters.");
                continue;
            }

            message message = new message(
                    ++messageCounter,
                    recipient,
                    messageText
            );

            System.out.println("\nChoose an action:");
            System.out.println("1. Send message");
            System.out.println("2. Discard message");

            String action = scanner.nextLine().trim();

            if (action.equals("1")) {

                sentMessages.add(message);

                System.out.println("Message sent successfully!");
                System.out.println(message.printMessage());

                saveMessage(message);

            } else if (action.equals("2")) {

                System.out.println("Message discarded.");

            } else {

                System.out.println(
                        "Invalid choice. Message was not sent.");
            }
        }
    }

    private void viewSentMessages() {

        if (sentMessages.isEmpty()) {
            System.out.println("No sent messages available.");
            return;
        }

        System.out.println("\n===== SENT MESSAGES =====");

        for (message message : sentMessages) {
            System.out.println("--------------------");
            System.out.println(message.printMessage());
        }

        System.out.println("--------------------");
        System.out.println(
                "Total sent messages: " + sentMessages.size());
    }

    private void saveMessage(message message) {

        String record = message.printMessage()
                + System.lineSeparator()
                + "--------------------"
                + System.lineSeparator();

        try {
            Files.writeString(
                    FILE_PATH,
                    record,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );

            System.out.println("Message saved to messages.txt.");

        } catch (IOException e) {
            System.out.println(
                    "Error saving message: " + e.getMessage());
        }
    }

    private void searchMessage() {

        System.out.print("Enter the Message ID to search for: ");
        String searchID = scanner.nextLine().trim();

        for (message message : sentMessages) {

            if (String.valueOf(message.getMessageID())
                    .equalsIgnoreCase(searchID)) {

                System.out.println("Message found:");
                System.out.println(message.printMessage());
                return;
            }
        }

        System.out.println("Message not found.");
    }

    private void deleteMessage() {

        System.out.print("Enter the Message ID to delete: ");
        String deleteID = scanner.nextLine().trim();

        for (int i = 0; i < sentMessages.size(); i++) {

            message message = sentMessages.get(i);

            if (String.valueOf(message.getMessageID())
                    .equalsIgnoreCase(deleteID)) {

                sentMessages.remove(i);
                updateMessageFile();

                System.out.println("Message deleted successfully.");
                return;
            }
        }

        System.out.println("Message not found.");
    }

    private void updateMessageFile() {

        StringBuilder content = new StringBuilder();

        for (message message : sentMessages) {

            content.append(message.printMessage())
                    .append(System.lineSeparator())
                    .append("--------------------")
                    .append(System.lineSeparator());
        }

        try {
            Files.writeString(
                    FILE_PATH,
                    content.toString(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING
            );

            System.out.println("Saved message file updated.");

        } catch (IOException e) {
            System.out.println(
                    "Error updating message file: " + e.getMessage());
        }
    }

    private void loadMessages() {

        if (!Files.exists(FILE_PATH)) {
            return;
        }

        try {
            List<String> lines = Files.readAllLines(FILE_PATH);

            int number = 0;
            String recipient = null;
            String messageText = null;
            String hash = null;

            for (String line : lines) {

                if (line.startsWith("Message Number:")) {

                    String value = line.substring(
                            "Message Number:".length()).trim();

                    number = Integer.parseInt(value);

                } else if (line.startsWith("Recipient:")) {

                    recipient = line.substring(
                            "Recipient:".length()).trim();

                } else if (line.startsWith("Message:")) {

                    messageText = line.substring(
                            "Message:".length()).trim();

                } else if (line.startsWith("Message Hash:")) {

                    hash = line.substring(
                            "Message Hash:".length()).trim();

                    if (recipient != null && messageText != null) {

                        message message = new message(
                                number,
                                recipient,
                                messageText
                        );

                        sentMessages.add(message);

                        messageCounter = Math.max(
                                messageCounter, number);
                    }

                    number = 0;
                    recipient = null;
                    messageText = null;
                    hash = null;
                }
            }

            System.out.println(
                    "Saved messages loaded: " + sentMessages.size());

        } catch (IOException | NumberFormatException e) {

            System.out.println(
                    "Could not load messages: " + e.getMessage());
        }
    }

    private void displayMessageReport() {

        System.out.println("\n===== MESSAGE REPORT =====");

        if (sentMessages.isEmpty()) {
            System.out.println("No messages are currently available.");
            return;
        }

        System.out.println("Total messages: " + sentMessages.size());

        for (message message : sentMessages) {

            System.out.println("--------------------");
            System.out.println(
                    "Message ID: " + message.getMessageID());

            System.out.println(
                    "Recipient: " + message.getRecipient());

            System.out.println(
                    "Message Hash: " + message.getMessageHash());

            System.out.println(
                    "Message: " + message.getMessageText());
        }

        System.out.println("--------------------");
    }
}

