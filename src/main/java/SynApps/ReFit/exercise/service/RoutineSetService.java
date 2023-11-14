package synApps.refit.exercise.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import synApps.refit.exercise.dto.request.RoutineSetRequest;
import synApps.refit.exercise.entity.Routine;
import synApps.refit.exercise.entity.RoutineSet;
import synApps.refit.exercise.repository.RoutineRepository;
import synApps.refit.exercise.repository.RoutineSetRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoutineSetService {
    private final RoutineSetRepository routineSetRepository;
    private final RoutineRepository routineRepository;

    public RoutineSet saveInfo(Long routineId, RoutineSetRequest request) {
        Routine routine = routineRepository.findByRoutineId(routineId);
        RoutineSet routineSet = RoutineSet.of(
                routine,
                request.getSetOrder(),
                request.getWeight(),
                request.getRepeat(),
                request.isDone(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        routineSetRepository.save(routineSet);
        return routineSet;
    }

    @Transactional
    public RoutineSet modifyInfo(Long routineSetId, RoutineSetRequest request) {
        RoutineSet routineSet = routineSetRepository.findByRoutineSetId(routineSetId);
        routineSet.modifyInfo(
                request.getSetOrder(),
                request.getWeight(),
                request.getRepeat(),
                request.isDone(),
                LocalDateTime.now()
        );
        return routineSet;
    }

    @Transactional
    public RoutineSet done(Long routineSetId) {
        RoutineSet routineSet = routineSetRepository.findByRoutineSetId(routineSetId);
        routineSet.done();
        return routineSet;
    }

    public RoutineSet getInfo(Long routineSetId) {
        return routineSetRepository.findByRoutineSetId(routineSetId);
    }

    public List<RoutineSet> getRoutineSetList(Long routineId) {
        Routine routine = routineRepository.findByRoutineId(routineId);
        return routineSetRepository.findAllByRoutine(routine);
    }

    public String deleteInfo(Long routineSetId) {
        RoutineSet routineSet = routineSetRepository.findByRoutineSetId(routineSetId);
        routineSetRepository.delete(routineSet);
        return "delete successful";
    }

}
