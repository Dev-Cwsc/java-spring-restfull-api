package br.com.dev.cwsc.javaspringrestfullapi.model.vo.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@JsonPropertyOrder({"id", "userLogin", "userPassword"}) // Define a ordem na qual os atributos do objeto aparecem
public class UserVO extends RepresentationModel<UserVO> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("id") // Renomeia o atributo exibido no objeto
    private Long key;
    private String fullName;
    private String userName;
    private String userPassword;

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
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

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserVO vo = (UserVO) o;
        return Objects.equals(getKey(), vo.getKey()) && Objects.equals(getFullName(), vo.getFullName()) && Objects.equals(getUserName(), vo.getUserName()) && Objects.equals(getUserPassword(), vo.getUserPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getKey(), getFullName(), getUserName(), getUserPassword());
    }
}