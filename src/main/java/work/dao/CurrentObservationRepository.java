package work.dao;

import org.springframework.data.repository.CrudRepository;
import work.model.CurrentObservation;

public interface CurrentObservationRepository extends CrudRepository<CurrentObservation, Integer> {

}
