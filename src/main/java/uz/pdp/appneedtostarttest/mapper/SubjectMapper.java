package uz.pdp.appneedtostarttest.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import uz.pdp.appneedtostarttest.entity.Subject;
import uz.pdp.appneedtostarttest.payload.SubjectReqDTO;
import uz.pdp.appneedtostarttest.payload.SubjectResDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    Subject toEntity(SubjectReqDTO subjectReqDTO);

    SubjectResDTO toDTO(Subject subject);

    @IterableMapping
    List<Subject> toEntities(List<SubjectReqDTO> subjectReqDTOList);

    List<SubjectResDTO> toDTOs(List<Subject> subjects);

}
