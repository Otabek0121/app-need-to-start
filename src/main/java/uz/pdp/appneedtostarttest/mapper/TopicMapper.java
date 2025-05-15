package uz.pdp.appneedtostarttest.mapper;

import org.mapstruct.*;
import uz.pdp.appneedtostarttest.entity.Topic;
import uz.pdp.appneedtostarttest.payload.TopicReqDTO;
import uz.pdp.appneedtostarttest.payload.TopicResDTO;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SubjectMapper.class})
public interface TopicMapper {

    @Mapping(target = "subject",ignore = true)
    @Named( "toEntity" )
    Topic toEntity(TopicReqDTO topicReqDTO);

    @Named("toDTO")
    TopicResDTO toDto(Topic topic);

    @IterableMapping(qualifiedByName = "toEntity")
    List<Topic> toEntities(List<TopicReqDTO> topicReqDTOList);

    @IterableMapping(qualifiedByName = "toDTO")
    List<TopicResDTO> toDTOs(List<Topic> topics);

}
