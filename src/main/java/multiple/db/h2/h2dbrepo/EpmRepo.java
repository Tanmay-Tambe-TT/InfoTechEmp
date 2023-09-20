package multiple.db.h2.h2dbrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import multiple.db.h2.h2dbentity.EmpEntity;

@Repository
public interface EpmRepo extends JpaRepository<EmpEntity, Integer>{


}
