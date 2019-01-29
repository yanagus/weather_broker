package work.dao;

import org.springframework.data.repository.CrudRepository;
import work.model.Condition;

public interface ConditionRepository extends CrudRepository<Condition, Integer> {

}
