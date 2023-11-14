package synApps.refit.diet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Food extends JpaRepository<Food, Long> {
    Food findByFoodId(Long foodId);
}
