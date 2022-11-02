package mg.dpe.siigpe.ca.repository;

import mg.dpe.siigpe.ca.model.SiigpeUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiigpeUserRepository extends JpaRepository<SiigpeUser, Short>{
    
	SiigpeUser findByUserName(String username);

	Boolean existsByUserName(String username);

}
