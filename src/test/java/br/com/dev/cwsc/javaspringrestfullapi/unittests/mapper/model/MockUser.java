package br.com.dev.cwsc.javaspringrestfullapi.unittests.mapper.model;

import br.com.dev.cwsc.javaspringrestfullapi.model.User;
import br.com.dev.cwsc.javaspringrestfullapi.model.vo.v1.UserVO;

import java.util.ArrayList;
import java.util.List;

public class MockUser {
    public User mockEntity() {
        return mockEntity(0);
    }

    public UserVO mockVO() {
        return mockVO(0);
    }

    public List<User> mockEntityList() {
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < 15; i++) {
            users.add(mockEntity(i));
        }
        return users;
    }

    public List<UserVO> mockVOList() {
        List<UserVO> userVOs = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            userVOs.add(mockVO(i));
        }
        return userVOs;
    }

    public User mockEntity(Integer number) {
        User user = new User();
        user.setLogin("testLogin" + number.longValue());
        user.setPassword("#testPass123" + number.longValue());
        user.setId(number.longValue());
        return user;
    }

    public UserVO mockVO(Integer number) {
        UserVO userVO = new UserVO();
        userVO.setUserLogin("testLogin" + number.longValue());
        userVO.setUserPassword("#testPass123" + number.longValue());
        userVO.setKey(number.longValue());
        return userVO;
    }
}
