package synApps.refit.diet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import synApps.refit.diet.entity.Dish;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    Dish findByDishId(Long dishId);
}
