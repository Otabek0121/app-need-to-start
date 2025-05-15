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
@Entity(name = "topic")
@SQLDelete(sql = "UPDATE topic SET deleted=true WHERE id=?")
@Where(clause = "deleted=false")
@Table(name = "topic",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name","subject_id"})
    }
)
public class Topic extends AbsUUIDUserAuditEntity {

    @Column(name = "name")
    private String name;

    @Column(name="description")
    private String description;

    @JoinColumn(name="subject_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Subject subject;

}
