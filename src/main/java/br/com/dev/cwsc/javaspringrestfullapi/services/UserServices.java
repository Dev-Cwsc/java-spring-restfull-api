package br.com.dev.cwsc.javaspringrestfullapi.services;

import br.com.dev.cwsc.javaspringrestfullapi.controller.UserController;
import br.com.dev.cwsc.javaspringrestfullapi.exceptions.RequiredObjectIsNullException;
import br.com.dev.cwsc.javaspringrestfullapi.exceptions.ResourceNotFoundException;
import br.com.dev.cwsc.javaspringrestfullapi.mapper.UserMapper;
import br.com.dev.cwsc.javaspringrestfullapi.model.User;
import br.com.dev.cwsc.javaspringrestfullapi.model.vo.v1.UserVO;
import br.com.dev.cwsc.javaspringrestfullapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
// Annotation do Spring. Sinaliza que o objeto pode ser injetado em tempo de execução (não é necessário instanciar)
public class UserServices implements UserDetailsService {
    private final Logger logger = Logger.getLogger(UserServices.class.getName());

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    // @Autowired -> É possível fazer a injeção de dependências no construtor, tornando o atributo required, porém isso fere o padrão SOLID
    public UserServices(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Finding a user by username " + username + "...");
        var user = repository.findByUsername(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("User not found by username " + username + "!");
        }
    }

    public UserVO findById(Long id) {
        logger.info("Finding one user...");

        UserVO vo = mapper.userEntityToUserVO(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!")));

        vo.add(linkTo(methodOn(UserController.class).findById(id)).withSelfRel());
        return vo;
    }

    public List<UserVO> findAll() {
        logger.info("Finding all users...");

        List<UserVO> userVOs = mapper.userEntityListToUserVOList(repository.findAll());

        userVOs.forEach( // Adiciona um link de caminho para o próprio objeto (HATEOAS)
                u -> u.add(linkTo(methodOn(UserController.class).findById(u.getKey())).withSelfRel())
        );
        return userVOs;
    }

    public UserVO create(UserVO userVO) {
        if (userVO == null) throw new RequiredObjectIsNullException();

        logger.info("Creating user...");

        String rawPassword = userVO.getUserPassword();

        userVO.setUserPassword(passwordEncoder.encode(userVO.getUserPassword()).substring("{pbkdf2}".length()));

        UserVO vo = mapper.userEntityToUserVO(repository.save(mapper.userVOToUserEntity(userVO)));

        vo.setUserPassword(rawPassword);
        vo.add(linkTo(methodOn(UserController.class).findById(vo.getKey())).withSelfRel());

        return vo;
    }

    public UserVO update(UserVO userVO) {
        if (userVO == null) throw new RequiredObjectIsNullException();

        User entity = mapper.userVOToUserEntity(this.findById(userVO.getKey()));
        logger.info("Updating user...");

        String rawPassword = userVO.getUserPassword();

        entity.setFullName(userVO.getFullName());
        entity.setUserName(userVO.getUserName());
        entity.setPassword(passwordEncoder.encode(userVO.getUserPassword()).substring("{pbkdf2}".length()));

        UserVO vo = mapper.userEntityToUserVO(repository.save(entity));

        vo.setUserPassword(rawPassword);
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