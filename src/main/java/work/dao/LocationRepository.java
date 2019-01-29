package work.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import work.model.Location;

public interface LocationRepository extends CrudRepository<Location, Integer> {

    @Query("select l from Location l where lower(l.city) = lower(:city)")
    Location findByCity(@Param("city") String city);

}
