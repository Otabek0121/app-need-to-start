package uz.pdp.appneedtostarttest.entity;


import jakarta.persistence.*;
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
@Entity(name = "answer_option")
@SQLDelete(sql = "UPDATE answer_option SET deleted=true WHERE id=?")
@Where(clause = "deleted=false")
@Table(name = "answer_option")
public class AnswerOption extends AbsUUIDUserAuditEntity {

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    private String text; // Answerning texti

    private boolean corrected;

}
