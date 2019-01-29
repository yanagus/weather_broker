package work.dao;

import org.springframework.data.repository.CrudRepository;
import work.model.Forecast;

public interface ForecastRepository extends CrudRepository<Forecast, Integer> {

}
