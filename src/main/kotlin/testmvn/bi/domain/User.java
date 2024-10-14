package testmvn.bi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import testmvn.bi.domain.base.BaseEntity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.Arrays;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity {


    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String fullname;

    @Column
    private String phone;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",  // Changed to match the actual table name
            joinColumns = @JoinColumn(name = "user_id"),  // Changed to match the foreign key column name
            inverseJoinColumns = @JoinColumn(name = "role_id")  // Changed to match the foreign key column name
    )
    private Set<Role> roles = new HashSet<>();

    @Column(precision = 10, scale = 2)
    private BigDecimal balance = BigDecimal.ZERO;

    public User(String username, String email, String fullname) {
        this.username = username;
        this.email = email;
        this.fullname = fullname;
    }

    // Role management methods
    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
    }

    public boolean hasRole(String roleName) {
        return this.roles.stream().anyMatch(role -> role.getName().equalsIgnoreCase(roleName));
    }

    public void setRole(String roleName) {
        this.roles.clear();
        this.roles.add(new Role(roleName));
    }

    public String getPrimaryRole() {
        return this.roles.isEmpty() ? null : this.roles.iterator().next().getName();
    }

    public boolean hasAnyRole(String... roleNames) {
        return Arrays.stream(roleNames).anyMatch(this::hasRole);
    }

    // Balance management methods
    public void addBalance(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public void subtractBalance(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
    }

    public boolean hasEnoughBalance(BigDecimal amount) {
        return this.balance.compareTo(amount) >= 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(fullname, user.fullname) && Objects.equals(phone, user.phone) && Objects.equals(roles, user.roles) && Objects.equals(balance, user.balance);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}