package synApps.refit.diet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import synApps.refit.diet.dto.request.DietRecordRequest;
import synApps.refit.diet.entity.DietRecord;
import synApps.refit.diet.entity.Dish;
import synApps.refit.diet.entity.MealTime;
import synApps.refit.diet.repository.DietRecordRepository;
import synApps.refit.schedule.entity.Schedule;
import synApps.refit.schedule.repository.ScheduleRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DietRecordService {
    private final DietRecordRepository dietRecordRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public DietRecord saveInfo(Long scheduleId, DietRecordRequest request) {
        Schedule schedule = scheduleRepository.findByScheduleId(scheduleId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        DietRecord record = DietRecord.of(
                schedule,
                MealTime.of(request.getMealTime()),
                LocalTime.parse(request.getStartAt(), formatter),
                LocalTime.parse(request.getEndAt(), formatter),
                request.getDietCalorie(),
                request.getDietProtein(),
                request.getDietCarbohydrate(),
                request.getDietSugar(),
                request.getDietFat(),
                request.getUserInputDiet(),
                request.isText(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        dietRecordRepository.save(record);
        return record;
    }

    @Transactional
    public DietRecord modifyInfo(Long recordId, DietRecordRequest request) {
        DietRecord record = dietRecordRepository.findByDietRecordId(recordId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        record.modifyDietRecord(
                record.getSchedule(),
                MealTime.of(request.getMealTime()),
                LocalTime.parse(request.getStartAt(), formatter),
                LocalTime.parse(request.getEndAt(), formatter),
                request.getDietCalorie(),
                request.getDietProtein(),
                request.getDietCarbohydrate(),
                request.getDietSugar(),
                request.getDietFat(),
                request.getUserInputDiet(),
                request.isText(),
                LocalDateTime.now()
        );

        return record;
    }

    public DietRecord getInfo(Long recordId) {
        return dietRecordRepository.findByDietRecordId(recordId);
    }



    public String deleteInfo(Long recordId) {
        DietRecord record = dietRecordRepository.findByDietRecordId(recordId);
        dietRecordRepository.delete(record);
        return "delete successful";
    }
}
