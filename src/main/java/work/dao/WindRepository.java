package work.dao;

import org.springframework.data.repository.CrudRepository;
import work.model.Wind;

public interface WindRepository extends CrudRepository<Wind, Integer> {

}
