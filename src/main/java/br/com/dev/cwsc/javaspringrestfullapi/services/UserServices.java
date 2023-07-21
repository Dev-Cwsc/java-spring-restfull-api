package br.com.dev.cwsc.javaspringrestfullapi.services;

import br.com.dev.cwsc.javaspringrestfullapi.controller.UserController;
import br.com.dev.cwsc.javaspringrestfullapi.exceptions.RequiredObjectIsNullException;
import br.com.dev.cwsc.javaspringrestfullapi.exceptions.ResourceNotFoundException;
import br.com.dev.cwsc.javaspringrestfullapi.mapper.UserMapper;
import br.com.dev.cwsc.javaspringrestfullapi.model.User;
import br.com.dev.cwsc.javaspringrestfullapi.model.vo.v1.UserVO;
import br.com.dev.cwsc.javaspringrestfullapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
// Annotation do Spring. Sinaliza que o objeto pode ser injetado em tempo de execução (não é necessário instanciar)
public class UserServices {
    private final Logger logger = Logger.getLogger(UserServices.class.getName());

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    public List<UserVO> findAll() {
        logger.info("Finding all users...");

        List<UserVO> userVOs = mapper.userEntityListToUserVOList(repository.findAll());

        userVOs.forEach( // Adiciona um link de caminho para o próprio objeto (HATEOAS)
                u -> u.add(linkTo(methodOn(UserController.class).findById(u.getKey())).withSelfRel())
        );
        return userVOs;
    }

    public UserVO findById(Long id) {
        logger.info("Finding one user...");

        UserVO vo = mapper.userEntityToUserVO(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!")));

        vo.add(linkTo(methodOn(UserController.class).findById(id)).withSelfRel());
        return vo;
    }

    public UserVO create(UserVO userVO) {
        if (userVO == null) throw new RequiredObjectIsNullException();

        logger.info("Creating user...");

        UserVO vo = mapper.userEntityToUserVO(repository.save(mapper.userVOToUserEntity(userVO)));
        vo.add(linkTo(methodOn(UserController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public UserVO update(UserVO userVO) {
        if (userVO == null) throw new RequiredObjectIsNullException();

        User entity = mapper.userVOToUserEntity(this.findById(userVO.getKey()));
        logger.info("Updating user...");

        entity.setLogin(userVO.getUserLogin());
        entity.setPassword(userVO.getUserPassword());

        UserVO vo = mapper.userEntityToUserVO(repository.save(entity));
        vo.add(linkTo(methodOn(UserController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Long id) {
        logger.info("Deleting user...");

        User entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        repository.delete(entity);
    }
}