/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mg.dpe.siigpe.ca.service;

import java.util.List;
import java.util.Optional;
import mg.dpe.siigpe.ca.model.SiigpeUser;

/**
 *
 * @author BasilisseN
 */
public interface SiigpeUserService {
    
    Optional<SiigpeUser> findById(short id);
    
    SiigpeUser findByUsername(String username);
    
    List<SiigpeUser> findAll();
    
    SiigpeUser save(SiigpeUser user);
    
}
