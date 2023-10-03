package synApps.refit.body.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import synApps.refit.body.dto.BodyStatusRequest;
import synApps.refit.body.entity.BodyStatus;
import synApps.refit.body.entity.Gender;
import synApps.refit.body.repository.BodyStatusRepository;
import synApps.refit.global.utils.DateTimeUtil;
import synApps.refit.user.entity.user.User;
import synApps.refit.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BodyStatusService {
    private final BodyStatusRepository bodyStatusRepository;
    private final UserRepository userRepository;

    public BodyStatus saveInfo(BodyStatusRequest request, String userId) {
        User user = userRepository.findByUserId(userId);
        DateTimeUtil dateTime = new DateTimeUtil();
        BodyStatus bodyStatus = BodyStatus.of(
                user,
                LocalDateTime.parse(request.getBirth(), dateTime.getDateTimeFormatter()),
                Gender.of(request.getGender()),
                request.getWeight(),
                request.getHeight(),
                request.getGoal(),
                dateTime.getNow(), dateTime.getNow());// 경우에 따라서 나눠서 보내줘야 할듯함
        bodyStatusRepository.save(bodyStatus);
        return bodyStatus;
    }

    public BodyStatus modifyInfo(Long bodyStatusId, BodyStatusRequest request) {
        BodyStatus bodyStatus = bodyStatusRepository.findByBodyStatusId(bodyStatusId);
        DateTimeUtil dateTime = new DateTimeUtil();
        bodyStatus.modifyBodyStatus(
                LocalDateTime.parse(request.getBirth(), dateTime.getDateTimeFormatter()),
                Gender.of(request.getGender()),
                request.getWeight(),
                request.getHeight(),
                request.getSkeletalMuscleMass(),
                request.getBodyFatMass(),
                request.getGoal(), dateTime.getNow());
        return bodyStatus;
    }

    public BodyStatus getInfo(Long bodyStatusId) {
        return bodyStatusRepository.findByBodyStatusId(bodyStatusId);
    }

    public List<BodyStatus> getInfoList(String userId) {
        User user = userRepository.findByUserId(userId);
        return bodyStatusRepository.findAllByUser(user);
    }

    public String deleteInfo(Long bodyStatusId) {
        BodyStatus bodyStatus = bodyStatusRepository.findByBodyStatusId(bodyStatusId);
        bodyStatusRepository.delete(bodyStatus);
        return "delete successful";
    }
}
