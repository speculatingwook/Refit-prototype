package synApps.refit.diet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import synApps.refit.diet.entity.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    Food findByFoodId(Long foodId);
}
