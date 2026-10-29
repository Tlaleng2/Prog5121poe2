/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog5121poe;

/**
 *
 * @author tlale
 */

public class MessageTest {
    public static void main(String[]args){
        int passed = 0;
        int failed = 0;
        
        message message = new message(
        1,
        "+27697014624",
         "Hello bestie"       
        );
        
        //Test1 : Message ID
        if(message.checkMessageID()){
            System.out.println("PASS:Message ID is valid.");
            passed++;
        }
        else{
            System.out.println("FAIL:Message ID is invalid.");
            failed++;
        }
        
        //Test 2: Recipient cellphone number
        if(message.checkRecipientCell()){
            System.out.println("PASS:Recipient number is valid.");
            passed++;
        }
        else{
            System.out.println("FAIL:Recipirnt number is invalid.");
            failed++;
        }
        
        //Test3:Message length
        if(message.checkMessageLength()){
            System.out.println("PASS:Message length is valid");
            passed++;
        }
        else{
            System.out.println("FAIL:Message length is invalid");
            failed++;
        }
        
        //Test 4:Message hash is created
        if(message.getMessageHash() !=
                null
                &&!
                message.getMessageHash().isEmpty()){
            System.out.println("PASS:Message hash was created.");
            passed++;
        }
        else{
            System.out.println("FAIL:Message hash was not created");
            failed++;
        }
        System.out.println("\n====TEST RESULTS=====");
        System.out.println("Tests passed:" + passed);
        System.out.println("Tests failed:"+ failed);
    }
    
}

