package uz.pdp.appneedtostarttest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.appneedtostarttest.entity.Subject;
import uz.pdp.appneedtostarttest.exception.RestException;
import uz.pdp.appneedtostarttest.mapper.SubjectMapper;
import uz.pdp.appneedtostarttest.net.ApiResult;
import uz.pdp.appneedtostarttest.payload.SubjectReqDTO;
import uz.pdp.appneedtostarttest.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    @Override
    public ApiResult<?> getById(UUID id) {

        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> RestException.notFound("SUBJECT"));

        return ApiResult.success(subjectMapper.toDTO(subject));
    }

    @Override
    public ApiResult<?> getAllSubject() {
        List<Subject> subjects = subjectRepository.findAll();
        return ApiResult.success(subjectMapper.toDTOs(subjects));
    }

    @Override
    public ApiResult<?> deleteSubject(UUID id) {

        Optional<Subject> optionalSubject = subjectRepository.findById(id);

        optionalSubject.ifPresent(subjectRepository::delete);

        return ApiResult.success();
    }

    @Override
    public ApiResult<?> createSubject(SubjectReqDTO subjectReqDTO) {

        if (subjectRepository.existsByName(subjectReqDTO.getName())) {
            throw  RestException.alreadyExists("SUBJECT");
        }
        subjectRepository.save(subjectMapper.toEntity(subjectReqDTO));

        return getAllSubject();
    }

    @Override
    public ApiResult<?> updateSubject(UUID id, SubjectReqDTO subjectReqDTO) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> RestException.notFound("SUBJECT"));

        if (subjectRepository.existsByName(subjectReqDTO.getName())) {
            throw  RestException.alreadyExists("SUBJECT");
        }

        subject.setName(subjectReqDTO.getName());
        subject.setDescription(subjectReqDTO.getDescription());
        subjectRepository.save(subject);

        return ApiResult.success(subjectMapper.toDTO(subject));
    }
}
