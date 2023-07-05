package br.com.dev.cwsc.javaspringrestfullapi.model.vo.v1;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class UserVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    private String userLogin;
    private String userPassword;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
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
        UserVO userVO = (UserVO) o;
        return Objects.equals(getId(), userVO.getId()) && Objects.equals(getUserLogin(), userVO.getUserLogin()) && Objects.equals(getUserPassword(), userVO.getUserPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserLogin(), getUserPassword());
    }
}