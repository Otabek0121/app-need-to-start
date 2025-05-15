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
import uz.pdp.appneedtostarttest.enums.QuestionDifficultyLevelEnum;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity(name = "question")
@SQLDelete(sql = "UPDATE question SET deleted=true WHERE id=?")
@Where(clause = "deleted=false")
@Table(name = "question")
public class Question extends AbsUUIDUserAuditEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    @ToString.Exclude
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    @ToString.Exclude
    private Topic topic;

    private String text; // Questionning texti

    private String imageUrl;

    private boolean isSingleChoice;

    @Enumerated(EnumType.STRING)
    private QuestionDifficultyLevelEnum difficultyLevel;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @ToString.Exclude
    private List<AnswerOption> answerOptions;

}
