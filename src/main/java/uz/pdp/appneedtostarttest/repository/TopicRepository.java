package uz.pdp.appneedtostarttest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appneedtostarttest.entity.Topic;

import java.util.List;
import java.util.UUID;

public interface TopicRepository extends JpaRepository<Topic, UUID> {

    List<Topic> findBySubject_Id(UUID subjectId);

    boolean existsByNameAndSubject_Id(String topicName, UUID subjectId);

}
