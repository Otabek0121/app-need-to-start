package uz.pdp.appneedtostarttest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import uz.pdp.appneedtostarttest.entity.template.AbsUUIDUserAuditEntity;

@Getter
@Setter
@ToString
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity(name = "user_answer")
@SQLDelete(sql = "UPDATE user_answer SET deleted=true WHERE id=?")
@Where(clause = "deleted=false")
@Table(name = "user_answer")
public class UserAnswer extends AbsUUIDUserAuditEntity {

    @ManyToOne
    @JoinColumn(name = "attempt_id", nullable = false)
    private TestAttempt attempt;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @ManyToOne
    @JoinColumn(name = "chosen_answer_option_id", nullable = false)
    private AnswerOption chosenAnswerOption;

}
