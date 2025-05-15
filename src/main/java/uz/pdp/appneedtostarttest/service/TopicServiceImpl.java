package uz.pdp.appneedtostarttest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.pdp.appneedtostarttest.entity.Subject;
import uz.pdp.appneedtostarttest.entity.Topic;
import uz.pdp.appneedtostarttest.exception.RestException;
import uz.pdp.appneedtostarttest.mapper.TopicMapper;
import uz.pdp.appneedtostarttest.net.ApiResult;
import uz.pdp.appneedtostarttest.payload.TopicReqDTO;
import uz.pdp.appneedtostarttest.repository.SubjectRepository;
import uz.pdp.appneedtostarttest.repository.TopicRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    private final SubjectRepository subjectRepository;

    private final TopicMapper topicMapper;

    @Override
    public ApiResult<?> getById(UUID id) {

        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> RestException.notFound("TOPIC"));

        return ApiResult.success(topicMapper.toDto(topic));
    }

    @Override
    public ApiResult<?> getAll() {

        List<Topic> topics = topicRepository.findAll();

        return ApiResult.success(topicMapper.toDTOs(topics));
    }

    @Override
    public ApiResult<?> getAllBySubject(UUID subjectId) {

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> RestException.notFound("SUBJECT"));

        List<Topic> topicsBySubjectId = topicRepository.findBySubject_Id(subject.getId());

        return ApiResult.success(topicMapper.toDTOs(topicsBySubjectId));
    }

    @Override
    public ApiResult<?> deleteById(UUID id) {

        if (topicRepository.existsById(id)) {
            topicRepository.deleteById(id);
        }

        return ApiResult.success(true);
    }

    @Override
    public ApiResult<?> createTopic(TopicReqDTO topicReqDTO) {

        if (topicRepository.existsByNameAndSubject_Id(topicReqDTO.getName(), topicReqDTO.getSubjectId())) {
            log.info("TOPIC CREATE METHOD -> Topic already exists with name {}, with subject_id {}", topicReqDTO.getName(), topicReqDTO.getSubjectId());
            throw RestException.alreadyExists("TOPIC");
        }

        Subject subject = subjectRepository.findById(topicReqDTO.getSubjectId())
                .orElseThrow(() -> RestException.notFound("SUBJECT"));

        Topic topic = new Topic();
        topic.setName(topicReqDTO.getName());
        topic.setDescription(topicReqDTO.getDescription());
        topic.setSubject(subject);

        topicRepository.save(topic);

        return ApiResult.success(topicMapper.toDto(topic));
    }
}
