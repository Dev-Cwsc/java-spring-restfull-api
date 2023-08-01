package br.com.dev.cwsc.javaspringrestfullapi.mapper;

import br.com.dev.cwsc.javaspringrestfullapi.model.User;
import br.com.dev.cwsc.javaspringrestfullapi.model.vo.v1.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring") // Annotation do mapStruct
public interface UserMapper {

    @Mapping(source = "id", target = "key")
    @Mapping(source = "password", target = "userPassword")
    UserVO userEntityToUserVO(User user);

    @Mapping(source = "key", target = "id")
    @Mapping(source = "userPassword", target = "password")
    User userVOToUserEntity(UserVO userVO);

    List<UserVO> userEntityListToUserVOList(List<User> users);
}