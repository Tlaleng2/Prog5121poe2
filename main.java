/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
  
package prog5121poe;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Login login = new Login();

        System.out.println("===== REGISTRATION =====");

        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        System.out.print("Enter your South African cellphone number: ");
        String cellPhoneNumber = scanner.nextLine();

        login.setFirstName(firstName);
        login.setLastName(lastName);
        login.setUsername(username);
        login.setPassword(password);
        login.setCellPhoneNumber(cellPhoneNumber);

        String registrationMessage = login.registerUser();
        System.out.println(registrationMessage);

        if (!registrationMessage.equals("Registration successful.")) {
            scanner.close();
            return;
        }

        System.out.println("\n===== LOGIN =====");

        System.out.print("Enter your username: ");
        String loginUsername = scanner.nextLine();

        System.out.print("Enter your password: ");
        String loginPassword = scanner.nextLine();

        boolean loggedIn = login.loginUser(
                loginUsername, loginPassword);

        System.out.println(login.returnLoginStatus(loggedIn));
        
        if (loggedIn){
            QuickChat quickChat = new QuickChat(scanner);
            quickChat.startQuickChat();
        }

        scanner.close();
    }

    public static class Login {

        private String username;
        private String password;
        private String cellPhoneNumber;
        private String firstName;
        private String lastName;

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setCellPhoneNumber(String cellPhoneNumber) {
            this.cellPhoneNumber = cellPhoneNumber;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String registerUser() {

            if (username == null
                    || username.length() > 5
                    || !username.contains("_")) {
                return "Username is not correctly formatted.";
            }

            if (password == null
                    || password.length() < 8
                    || !password.matches(".*[A-Z].*")
                    || !password.matches(".*[0-9].*")
                    || !password.matches(".*[^a-zA-Z0-9].*")) {
                return "Password does not meet the required complexity rules.";
            }

            if (cellPhoneNumber == null
                    || !cellPhoneNumber.matches(
                            "^\\+27[6-8][0-9]{8}$")) {
                return "Cell phone number is incorrectly formatted.";
            }

            return "Registration successful.";
        }

        public boolean loginUser(
                String enteredUsername, String enteredPassword) {

            return username != null
                    && password != null
                    && username.equals(enteredUsername)
                    && password.equals(enteredPassword);
        }

        public String returnLoginStatus(boolean success) {

            if (success) {
                return "Welcome " + firstName + " "
                        + lastName
                        + ", it is great to see you again.";
            }

            return "Username or password incorrect, please try again.";
        }
    }
}