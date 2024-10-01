package testmvn.bi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import testmvn.bi.domain.base.BaseEntity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "users")
public class User extends BaseEntity {

  @Column
  private String username;

  @Column
  private String password; // hashed value, ok ko anh tai

}
