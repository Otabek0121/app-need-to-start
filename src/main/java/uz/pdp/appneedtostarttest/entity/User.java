package uz.pdp.appneedtostarttest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.appneedtostarttest.converter.UserRoleEnumSetConverter;
import uz.pdp.appneedtostarttest.entity.template.AbsUUIDUserAuditEntity;
import uz.pdp.appneedtostarttest.enums.UserRoleEnum;
import uz.pdp.appneedtostarttest.utils.AppConstant;
import uz.pdp.appneedtostarttest.utils.ColumnKey;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity(name = "users")
@SQLDelete(sql = "UPDATE users SET enabled= false, deleted = true, email=email ||'_deleted_'|| cast(now() as varchar) WHERE id =?")
@Where(clause = "deleted=false")
@Table(name = "users")
public class User extends AbsUUIDUserAuditEntity implements UserDetails {
    private static final long serialVersionUID = 8876145720071744944L;

    @Column(name = ColumnKey.FIRST_NAME)
    private String firstName;

    @Column(name = ColumnKey.LAST_NAME)
    private String lastName;

    @Column(name = ColumnKey.EMAIL,nullable = false,unique = true)
    private String email;

    @Column(name = ColumnKey.PASSWORD,nullable = false)
    private String password;

    @Column(name = "roles")
    @Convert(converter = UserRoleEnumSetConverter.class)
    @Enumerated(EnumType.STRING)
    private Set<UserRoleEnum> roles;

    //TOKENNI SHU USER UCHUN GENERATE QILINGAN VAQTI
    // (BITTA USER IKKITA DEVICEDAN BIR VAQTDA FOYDALAN OLMASLIGI UCHUN YECHIM)
    @Column(name = "token_issued_at")
    private Timestamp tokenIssuedAt;


    @Column(name = "account_non_expired")
    private boolean accountNonExpired = true;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked = true;

    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired = true;

    private boolean enabled = true;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        User user = (User) o;
//        return getId() != null && Objects.equals(getId(), user.getId());
//    }
//
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }
}
