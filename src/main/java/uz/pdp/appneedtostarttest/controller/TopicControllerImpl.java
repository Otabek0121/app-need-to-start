package uz.pdp.appneedtostarttest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appneedtostarttest.net.ApiResult;
import uz.pdp.appneedtostarttest.payload.TopicReqDTO;
import uz.pdp.appneedtostarttest.service.TopicService;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class TopicControllerImpl implements TopicController {

    private final TopicService topicService;

    @Override
    public ApiResult<?> getById(UUID id) {
        return topicService.getById(id);
    }

    @Override
    public ApiResult<?> getAll() {
        return topicService.getAll();
    }

    @Override
    public ApiResult<?> getAllBySubject(UUID subjectId) {
        return topicService.getAllBySubject(subjectId);
    }

    @Override
    public ApiResult<?> deleteById(UUID id) {
        return topicService.deleteById(id);
    }

    @Override
    public ApiResult<?> createTopic(TopicReqDTO topicReqDTO) {
        return topicService.createTopic(topicReqDTO);
    }
}
