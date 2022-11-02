/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.dpe.siigpe.ca.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mg.dpe.siigpe.ca.model.LoginRequest;
import mg.dpe.siigpe.ca.service.SiigpeUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author BasilisseN
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:3000"}, allowedHeaders = {"Content-type"})
public class AuthentificationController {

    private final Logger logger = LoggerFactory.getLogger(AuthentificationController.class);

    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    SiigpeUserService userService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {
        String username = loginRequest.getUserLogin();
        String password = loginRequest.getMotDePasse();
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(username, password));
        context.setAuthentication(authentication);
        logger.info("Authentication user : {} - {}", username, authentication.getName());
        Map<String, Object> data = new HashMap<>();
        if (authentication.isAuthenticated()) {
            logger.info("User authentified.");
            SecurityContextHolder.setContext(context);
            data.put("isAuthenticated", true);
            data.put("authToken", request.getSession().getId());
            response.setHeader("Authorities", request.getSession().getId());
            data.put("userDetails", userService.findByUsername(authentication.getName()));
            return ResponseEntity.ok(data);
        } else {
            logger.info("Bad credential with login : {}", username);
            data.put("isAuthenticated", false);
            return ResponseEntity.ok(data);
        }
    }
    
    @GetMapping("/isAuthenticated")
    public ResponseEntity<?> isAuthenticated(HttpServletRequest request, HttpServletResponse response){
        SecurityContext sc = SecurityContextHolder.getContext();
        if (sc.getAuthentication().isAuthenticated()){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.status(401).build();
        }
    }
}
