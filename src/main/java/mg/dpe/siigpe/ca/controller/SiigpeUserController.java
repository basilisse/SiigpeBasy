package mg.dpe.siigpe.ca.controller;

import java.util.List;
import mg.dpe.siigpe.ca.model.SiigpeUser;
import mg.dpe.siigpe.ca.repository.SiigpeUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class SiigpeUserController {
 @Autowired
	SiigpeUserRepository siigpeUserRepository;

	@GetMapping("/SiigpeUsers")
	public ResponseEntity<List<SiigpeUser>> getAllTutorials(@RequestParam(required = false) String title) {
		
	return null;
		
	}

	@GetMapping("/SiigpeUsers/{id}")
	public ResponseEntity<SiigpeUser> getTutorialById(@PathVariable("id") long id) {
		
	return null;
		
	}

	@PostMapping("/SiigpeUsers")
	public ResponseEntity<SiigpeUser> createTutorial(@RequestBody SiigpeUser sigpeUser) {
		return null;
	}

	@PutMapping("/SiigpeUsers/{id}")
	public ResponseEntity<SiigpeUser> updateTutorial(@PathVariable("id") long id, @RequestBody  SiigpeUser sigpeUser) {
	
	return null;
	
	}

	@DeleteMapping("/SiigpeUsers/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		
     return null;
	}

	@DeleteMapping("/SiigpeUsers")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
	
	return null;
	
	}

	@GetMapping("/SiigpeUsers/published")
	public ResponseEntity<List<SiigpeUser>> findByPublished() {
		
	return null;
		
	}
}   

