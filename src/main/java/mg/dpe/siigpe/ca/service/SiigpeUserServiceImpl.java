/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.dpe.siigpe.ca.service;

import java.util.List;
import java.util.Optional;
import mg.dpe.siigpe.ca.model.SiigpeUser;
import mg.dpe.siigpe.ca.repository.SiigpeUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author BasilisseN
 */

@Service
@Transactional(readOnly = true)
public class SiigpeUserServiceImpl implements SiigpeUserService{
    
    @Autowired
    SiigpeUserRepository repository;

    @Override
    public Optional<SiigpeUser> findById(short id) {
        return repository.findById(id);
    }

    @Override
    public SiigpeUser findByUsername(String username) {
        return repository.findByUserName(username);
    }
    
    
    @Override
    public List<SiigpeUser> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public SiigpeUser save(SiigpeUser user) {
        return repository.save(user);
    }    
    
    
}
