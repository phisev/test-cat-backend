package phi.sev.catsbackend.adapters.persistence;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataCatRepository extends CrudRepository<CatEntity, UUID> {

}
