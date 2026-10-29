/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog5121poe;


/**
 *
 * @author tlale
 */
public class Login {
    /**
     * Handles user registration, validation and login.
     */

    //Registration details
    private String username;
    private String password;
    private String cellPhoneNumber;
    private String firstName;
    private String lastname;
    
   // Checks if the username is valid 
    public boolean 
        checkUserName(String username){
            return username.contains("_")
                    && username.length() <=5;
        }
        
        //checks the password meets the required complixity
    public boolean
        checkPasswordComplixity(String password){
            return password.length()>=8
                    &&
        password.matches(".*[a-z].*")
                    &&
        password.matches(".*[0-9].*")
                    &&
        password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*");
                    
        }
        
        //Checks if the South African cellphone is valid
        public boolean
        checkCellPhoneNumber(String cellPhoneNumber) {
            return 
        cellPhoneNumber.matches(".*^\\+27[6-8][0-9]{8}$");
        } 
        
        //Registers the user if all information is valid 
        public String registerUser(String username, String password,String cellPhoneNumber,
                String firstName, String lastName){
            if (!checkUserName(username)){
                return"Username is incorrectly formatted.";
            }
            if(!
               checkPasswordComplixity(password)){
                return"Password does not meet the complixity requirements.";
            }
            if(!
               checkCellPhoneNumber(cellPhoneNumber)){
                return"Cell phone number is incorrectly formatted.";
            }
            
            this.username = username;
            this.password = password;
            this.cellPhoneNumber =cellPhoneNumber;
            this.firstName = firstName;
            this.lastname = lastName;
            
            return"Registration successful.";
            
        }  
        //Checks if the login details match the registered details 
        public boolean loginUser(String username ,String password){
            return
        this.username.equals(username)
                    &&
        this.password.equals(password);
        }
        
        //Returns the login success or failure message
        public String 
          returnLoginStatus (String username,String password){
             if (loginUser(username,password)) {
                 return "Welcome "+ firstName+ " " + lastname
                         +",it is great to see you again.";
             }
             else{
                 return "Username or password incorrect, please try again.";
             }
          }
           
}
