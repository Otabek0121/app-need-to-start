package uz.pdp.appneedtostarttest.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import uz.pdp.appneedtostarttest.entity.template.AbsUUIDUserAuditEntity;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity(name = "test")
@SQLDelete(sql = "UPDATE test SET deleted=true WHERE id=?")
@Where(clause = "deleted=false")
@Table(name = "test")
public class Test extends AbsUUIDUserAuditEntity {

    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    @ToString.Exclude
    private Subject subject;

    @Column(name="duration")
    private Duration duration;

    private boolean active=false;

    private Timestamp startTime;

    private Timestamp endTime;


}
