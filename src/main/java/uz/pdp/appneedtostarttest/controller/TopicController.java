package uz.pdp.appneedtostarttest.controller;


import org.springframework.web.bind.annotation.*;
import uz.pdp.appneedtostarttest.net.ApiResult;
import uz.pdp.appneedtostarttest.payload.SubjectReqDTO;
import uz.pdp.appneedtostarttest.payload.TopicReqDTO;
import uz.pdp.appneedtostarttest.utils.AppConstant;

import java.util.UUID;

@RequestMapping(TopicController.BASE_PATH_TOPIC)
public interface TopicController {

    String BASE_PATH_TOPIC= AppConstant.BASE_PATH_V1+"/topic";

    @GetMapping("/{id}")
    ApiResult<?> getById(@PathVariable UUID id);

    @GetMapping
    ApiResult<?> getAll();

    @GetMapping("/get-all-by-subject/{subjectId}")
    ApiResult<?> getAllBySubject(@PathVariable UUID subjectId);

    @DeleteMapping("/{id}")
    ApiResult<?> deleteById(@PathVariable UUID id);

    @PostMapping("/add")
    ApiResult<?> createTopic(@RequestBody TopicReqDTO topicReqDTO);


}
