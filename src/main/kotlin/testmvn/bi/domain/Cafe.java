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
@Table(name = "cafe")
public class Cafe extends BaseEntity {

  @Column
  private String brand;

  @Column
  private String from;

}
