package uz.pdp.appneedtostarttest.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import uz.pdp.appneedtostarttest.entity.template.AbsUUIDUserAuditEntity;
import uz.pdp.appneedtostarttest.enums.AttemptStatusEnum;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity(name = "test_attempt")
@SQLDelete(sql = "UPDATE test_attempt SET deleted=true WHERE id=?")
@Where(clause = "deleted=false")
@Table(name = "test_attempt")
public class TestAttempt extends AbsUUIDUserAuditEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

    @Column(nullable = false)
    private Timestamp startTime;

    private Timestamp endTime;

    private Timestamp submittedAt;

    private int score=0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AttemptStatusEnum status;

}
