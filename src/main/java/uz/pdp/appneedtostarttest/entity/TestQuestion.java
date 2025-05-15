package uz.pdp.appneedtostarttest.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import uz.pdp.appneedtostarttest.entity.template.AbsUUIDUserAuditEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
@DynamicUpdate
@Entity(name = "test_question")
@SQLDelete(sql = "UPDATE test_question SET deleted=true WHERE id=?")
@Where(clause = "deleted=false")
@Table(name = "test_question", uniqueConstraints = @UniqueConstraint(columnNames = {"test_id", "question_id"}))
public class TestQuestion extends AbsUUIDUserAuditEntity {

    @ManyToOne
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

}
