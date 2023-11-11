package synApps.refit.exercise.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import synApps.refit.exercise.dto.request.ExerciseRecordRequest;
import synApps.refit.exercise.entity.ExerciseRecord;
import synApps.refit.exercise.repository.ExerciseRecordRepository;
import synApps.refit.schedule.entity.Schedule;
import synApps.refit.schedule.repository.ScheduleRepository;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ExerciseRecordService {
    private final ExerciseRecordRepository exerciseRecordRepository;
    private final ScheduleRepository scheduleRepository;

    public ExerciseRecord saveInfo(Long scheduleId, ExerciseRecordRequest request) {
        Schedule schedule = scheduleRepository.findByScheduleId(scheduleId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        ExerciseRecord record = ExerciseRecord.of(schedule, LocalTime.parse(request.getStartAt(), formatter), LocalTime.parse(request.getEndAt(), formatter));
        exerciseRecordRepository.save(record);
        return record;
    }

    @Transactional
    public ExerciseRecord modifyTime(Long recordId, ExerciseRecordRequest request) {
        ExerciseRecord record = exerciseRecordRepository.findByExerciseRecordId(recordId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        record.modifyTime(LocalTime.parse(request.getStartAt(), formatter), LocalTime.parse(request.getEndAt(), formatter));
        return record;
    }

    public ExerciseRecord getInfo(Long recordId) {
        return exerciseRecordRepository.findByExerciseRecordId(recordId);
    }

    public String deleteInfo(Long recordId) {
        ExerciseRecord record = exerciseRecordRepository.findByExerciseRecordId(recordId);
        exerciseRecordRepository.delete(record);
        return "delete successful";
    }

}
