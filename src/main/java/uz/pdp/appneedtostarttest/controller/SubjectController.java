package uz.pdp.appneedtostarttest.controller;


import org.springframework.web.bind.annotation.*;
import uz.pdp.appneedtostarttest.net.ApiResult;
import uz.pdp.appneedtostarttest.payload.SubjectReqDTO;
import uz.pdp.appneedtostarttest.utils.AppConstant;

import java.util.UUID;

@RequestMapping(SubjectController.BASE_PATH_SUBJECT)
public interface SubjectController {

    String BASE_PATH_SUBJECT = AppConstant.BASE_PATH_V1+"/subject";

    @GetMapping("/{id}")
    ApiResult<?> getById(@PathVariable UUID id);

    @GetMapping("/all-subject")
    ApiResult<?> getAllSubject();

    @DeleteMapping("/{id}")
    ApiResult<?> deleteSubject(@PathVariable UUID id);

    @PostMapping("/add")
    ApiResult<?> createSubject(@RequestBody SubjectReqDTO subjectReqDTO);

    @PutMapping("/{id}")
    ApiResult<?> updateSubject(@PathVariable UUID id,@RequestBody SubjectReqDTO subjectReqDTO);
}
