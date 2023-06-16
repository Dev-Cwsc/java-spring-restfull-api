package br.com.dev.cwsc.javaspringrestfullapi.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity // Annotation que especifica que essa classe é uma entidade do banco de dados
@Table(name = "user") // Nomeia a tabela no banco de dados como "user"
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id // Esse atributo é a chave primária da tabela user
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Especifica que o id deve ser autoincrementado a cada persistência no banco
    private long id;

    @Column(nullable = false) // O valor dessa coluna no banco de dados não pode ser nulo
    private String login;

    @Column(nullable = false)
    private String password;

    public User(long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId() == user.getId() && Objects.equals(getLogin(), user.getLogin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLogin());
    }
}
