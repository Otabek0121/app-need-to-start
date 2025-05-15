package uz.pdp.appneedtostarttest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appneedtostarttest.net.ApiResult;
import uz.pdp.appneedtostarttest.payload.SubjectReqDTO;
import uz.pdp.appneedtostarttest.service.SubjectService;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SubjectControllerImpl implements SubjectController {

    private final SubjectService subjectService;

    @Override
    public ApiResult<?> getById(UUID id) {
        return subjectService.getById(id);
    }

    @Override
    public ApiResult<?> getAllSubject() {
        return subjectService.getAllSubject();
    }

    @Override
    public ApiResult<?> deleteSubject(UUID id) {
        return subjectService.deleteSubject(id);
    }

    @Override
    public ApiResult<?> createSubject(SubjectReqDTO subjectReqDTO) {
        return subjectService.createSubject(subjectReqDTO);
    }

    @Override
    public ApiResult<?> updateSubject(UUID id, SubjectReqDTO subjectReqDTO) {
        return subjectService.updateSubject(id,subjectReqDTO);
    }
}
