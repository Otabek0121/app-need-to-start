package uz.pdp.appneedtostarttest.service;

import uz.pdp.appneedtostarttest.net.ApiResult;
import uz.pdp.appneedtostarttest.payload.SubjectReqDTO;

import java.util.UUID;

public interface SubjectService {

    ApiResult<?> getById(UUID id);

    ApiResult<?> getAllSubject();

    ApiResult<?> deleteSubject(UUID id);

    ApiResult<?> createSubject(SubjectReqDTO subjectReqDTO);

    ApiResult<?> updateSubject(UUID id, SubjectReqDTO subjectReqDTO);
}
