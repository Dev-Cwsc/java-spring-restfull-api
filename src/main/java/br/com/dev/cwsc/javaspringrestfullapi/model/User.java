package br.com.dev.cwsc.javaspringrestfullapi.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity // Annotation que especifica que essa classe é uma entidade do banco de dados
@Table(name = "users")
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id // Esse atributo é a chave primária da tabela user
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Especifica que o id deve ser autoincrementado a cada persistência no banco
    private long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "user_name", nullable = false, unique = true)
    // O valor dessa coluna no banco de dados não pode ser nulo e nem repetido
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(name = "account_non_expired")
    private Boolean accountNonExpired;

    @Column(name = "account_non_locked")
    private Boolean accountNonLocked;

    @Column(name = "credentials_non_expired")
    private Boolean credentialsNonExpired;

    @Column(name = "enabled")
    private Boolean enabled;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId() == user.getId() && Objects.equals(getFullName(), user.getFullName()) && Objects.equals(getUserName(), user.getUserName()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getAccountNonExpired(), user.getAccountNonExpired()) && Objects.equals(getAccountNonLocked(), user.getAccountNonLocked()) && Objects.equals(getCredentialsNonExpired(), user.getCredentialsNonExpired()) && Objects.equals(getEnabled(), user.getEnabled());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFullName(), getUserName(), getPassword(), getAccountNonExpired(), getAccountNonLocked(), getCredentialsNonExpired(), getEnabled());
    }
}