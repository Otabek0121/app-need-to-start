package uz.pdp.appneedtostarttest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
@Entity(name = "subject")
@SQLDelete(sql = "UPDATE subject SET deleted=true WHERE id=?")
@Where(clause = "deleted=false")
@Table(name = "subject",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})
    }
)
public class Subject extends AbsUUIDUserAuditEntity {

    @Column(name = "name",nullable = false,length = 100,unique = true)
    private String name;

    @Column(name = "description")
    private String description;
}
