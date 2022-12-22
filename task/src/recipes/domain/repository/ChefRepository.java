package recipes.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import recipes.domain.entity.Chef;

@Repository
public interface ChefRepository extends CrudRepository<Chef, String> {
}