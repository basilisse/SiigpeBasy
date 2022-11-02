/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.dpe.siigpe.ca.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author BasilisseN
 */
public class QuickPasswordEncoder {

    public static void main(String args[]){
        System.out.println(new BCryptPasswordEncoder().encode("test"));
    }
    
}
