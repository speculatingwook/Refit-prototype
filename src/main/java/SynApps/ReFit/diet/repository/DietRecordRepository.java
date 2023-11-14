package synApps.refit.diet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import synApps.refit.diet.entity.DietRecord;

@Repository
public interface DietRecordRepository extends JpaRepository<DietRecord, Long> {
    DietRecord findByDietRecordId(Long dietRecordId);
}
