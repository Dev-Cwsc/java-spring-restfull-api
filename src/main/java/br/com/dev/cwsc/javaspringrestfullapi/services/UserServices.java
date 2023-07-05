package br.com.dev.cwsc.javaspringrestfullapi.services;

import br.com.dev.cwsc.javaspringrestfullapi.model.vo.v1.UserVO;
import br.com.dev.cwsc.javaspringrestfullapi.exceptions.ResourceNotFoundException;
import br.com.dev.cwsc.javaspringrestfullapi.mapper.UserMapper;
import br.com.dev.cwsc.javaspringrestfullapi.model.User;
import br.com.dev.cwsc.javaspringrestfullapi.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
@Service // Annotation do Spring. Sinaliza que o objeto pode ser injetado em tempo de execução (não é necessário instanciar)
public class UserServices {
    private final Logger logger = Logger.getLogger(UserServices.class.getName());

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    public List<UserVO> findAll(){
        logger.info("Finding all users...");
        return mapper.userEntityListToUserVOList(repository.findAll());
    }

    public UserVO findById(Long id){
        logger.info("Finding one user...");
        return mapper.userEntityToUserVO(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!")));
    }

    public UserVO create(UserVO userVO){
        logger.info("Creating user...");
        User entity = mapper.userVOToUserEntity(userVO);
        return mapper.userEntityToUserVO(repository.save(entity));
    }

    public UserVO update(UserVO userVO){
        User entity = mapper.userVOToUserEntity(this.findById(userVO.getId()));
        logger.info("Updating user...");
        entity.setLogin(userVO.getUserLogin());
        entity.setPassword(userVO.getUserPassword());
        return mapper.userEntityToUserVO(repository.save(entity));
    }

    public void delete(Long id){
        logger.info("Deleting user...");
        User entity = mapper.userVOToUserEntity(this.findById(id));
        repository.delete(entity);
    }
}