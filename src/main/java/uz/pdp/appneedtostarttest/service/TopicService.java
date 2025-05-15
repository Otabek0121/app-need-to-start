package uz.pdp.appneedtostarttest.service;

import uz.pdp.appneedtostarttest.net.ApiResult;
import uz.pdp.appneedtostarttest.payload.TopicReqDTO;

import java.util.UUID;

public interface TopicService {
    ApiResult<?> getById(UUID id);

    ApiResult<?> getAll();

    ApiResult<?> getAllBySubject(UUID subjectId);

    ApiResult<?> deleteById(UUID id);

    ApiResult<?> createTopic(TopicReqDTO topicReqDTO);
}
