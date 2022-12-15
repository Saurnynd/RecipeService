package recipes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import recipes.domain.entity.Chef;
import recipes.domain.entity.Recipe;

import java.util.List;

@Repository
public interface ChefRepository extends CrudRepository<Chef, String> {
}