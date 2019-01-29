package work.dao;

import org.springframework.data.repository.CrudRepository;
import work.model.Astronomy;

public interface AstronomyRepository extends CrudRepository<Astronomy, Integer> {

}
