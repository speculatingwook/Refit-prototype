package synApps.refit.schedule.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import synApps.refit.schedule.dto.request.ScheduleRequest;
import synApps.refit.schedule.entity.Schedule;
import synApps.refit.schedule.repository.ScheduleRepository;
import synApps.refit.user.entity.user.User;
import synApps.refit.user.service.ClientUserLoader;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ClientUserLoader clientUserLoader;


    public Schedule saveInfo(ScheduleRequest request) {
        User user = clientUserLoader.getClientUser();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Schedule schedule = Schedule.of(
                user,
                LocalDate.parse(request.getDate(), formatter),
                request.getTotalCalorie(),
                request.getTotalProtein(),
                request.getTotalCarbohydrates(),
                request.getTotalSugar(),
                request.getTotalFat(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        scheduleRepository.save(schedule);
        return schedule;
    }

    @Transactional
    public Schedule modifyInfo(Long scheduleId, ScheduleRequest request) {
        Schedule schedule = scheduleRepository.findByScheduleId(scheduleId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        schedule.modifySchedule(
                LocalDate.parse(request.getDate(), formatter),
                request.getTotalCalorie(),
                request.getTotalProtein(),
                request.getTotalCarbohydrates(),
                request.getTotalSugar(),
                request.getTotalFat(),
                LocalDateTime.now());
        return schedule;
    }

    public Schedule getSchedule(Long scheduleId) {
        return scheduleRepository.findByScheduleId(scheduleId);
    }

    public String deleteInfo(Long scheduleId) {
        Schedule schedule = scheduleRepository.findByScheduleId(scheduleId);
        scheduleRepository.delete(schedule);
        return "delete successful";
    }

}
