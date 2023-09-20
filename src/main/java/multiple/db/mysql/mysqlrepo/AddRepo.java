package multiple.db.mysql.mysqlrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import multiple.db.mysql.mysqlentity.AddEntity;

@Repository
public interface AddRepo extends JpaRepository<AddEntity, Integer> {

}
