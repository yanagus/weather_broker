package work.dao;

import org.springframework.data.repository.CrudRepository;
import work.model.Atmosphere;

public interface AtmosphereRepository extends CrudRepository<Atmosphere, Integer> {

}
