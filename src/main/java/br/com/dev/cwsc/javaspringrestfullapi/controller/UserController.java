package br.com.dev.cwsc.javaspringrestfullapi.controller;
import br.com.dev.cwsc.javaspringrestfullapi.model.User;
import br.com.dev.cwsc.javaspringrestfullapi.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Annotation do Spring. Sinaliza que a classe é um controller
@RequestMapping("/user")
public class UserController {
    @Autowired // Annotation do Spring. Faz a instanciação do objeto de maneira dinâmica em tempo de execução (necessita usar também a annotation @Service)
    private UserServices services;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> findAll(){
        return services.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User findById(@PathVariable(value = "id") Long id){
        return services.findById(id);
    }

    @PostMapping (consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User create(@RequestBody User user){
        return services.create(user);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User update(@RequestBody User user){
        return services.update(user);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        services.delete(id);
        return ResponseEntity.noContent().build(); // Retorna o código de status 204 em vez de 200
    }
}
