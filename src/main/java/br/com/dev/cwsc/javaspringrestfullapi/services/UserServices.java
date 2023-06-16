package br.com.dev.cwsc.javaspringrestfullapi.services;

import br.com.dev.cwsc.javaspringrestfullapi.exceptions.ResourceNotFoundException;
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
    UserRepository repository;

    public List<User> findAll(){
        logger.info("Finding all users...");
        return repository.findAll();
    }

    public User findById(Long id){
        logger.info("Finding one user...");
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    }

    public User create(User user){
        logger.info("Creating user...");
        return repository.save(user);
    }

    public User update(User user){
        User entity = this.findById(user.getId());
        logger.info("Updating user...");
        entity.setLogin(user.getLogin());
        entity.setPassword(user.getPassword());
        return repository.save(entity);
    }

    public void delete(Long id){
        logger.info("Deleting user...");
        User entity = this.findById(id);
        repository.delete(entity);
    }
}