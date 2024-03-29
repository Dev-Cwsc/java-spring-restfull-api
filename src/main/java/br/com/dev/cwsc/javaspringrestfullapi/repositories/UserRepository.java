package br.com.dev.cwsc.javaspringrestfullapi.repositories;

import br.com.dev.cwsc.javaspringrestfullapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository // Sinaliza que a interface é um repositório com operações do banco de dados
public interface UserRepository extends JpaRepository<User, Long> {
    // Fazer o extends da classe JpaRepository faz com que a interface tenha todas as operações básicas de um CRUD
    @Query("SELECT u FROM User u WHERE u.userName =:userName")
    // Query JPQL
    User findByUsername(@Param("userName") String userName);
}
