package br.com.dev.cwsc.javaspringrestfullapi.unittests.services;

import br.com.dev.cwsc.javaspringrestfullapi.exceptions.RequiredObjectIsNullException;
import br.com.dev.cwsc.javaspringrestfullapi.mapper.UserMapper;
import br.com.dev.cwsc.javaspringrestfullapi.model.User;
import br.com.dev.cwsc.javaspringrestfullapi.model.vo.v1.UserVO;
import br.com.dev.cwsc.javaspringrestfullapi.repositories.UserRepository;
import br.com.dev.cwsc.javaspringrestfullapi.services.UserServices;
import br.com.dev.cwsc.javaspringrestfullapi.unittests.mapper.model.MockUser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class UserServicesTest {

    MockUser input;

    @InjectMocks // Similar a injeção de dependências, porém injeta mocks
    private UserServices service;

    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper mapper;

    @BeforeEach
    void setUp() throws Exception {
        input = new MockUser();

        // Inicializa os mocks dessa classe
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        User entity = input.mockEntity(1);

        UserVO vo = new UserVO();
        vo.setKey(entity.getId());
        vo.setUserPassword(entity.getPassword());
        vo.setUserName(entity.getUserName());

        // Quando o service chamar o método findById, o mockito irá retornar o mock entity declarado acima
        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(mapper.userEntityToUserVO(entity)).thenReturn(vo);

        var result = service.findById(entity.getId());

        // Verifica se os campos não estão nulos
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getUserName());
        assertNotNull(result.getUserPassword());
        assertNotNull(result.getLinks());

        // Verifica se o resultado contém os links (HATEOAS)
        assertTrue(result.toString().contains("links: [</user/1>;rel=\"self\"]"));

        // Verifica se os campos são iguais ao esperado
        assertEquals("testLogin1", result.getUserName());
        assertEquals("#testPass1231", result.getUserPassword());
    }

    @Test
    void findAll() {
        List<User> entities = input.mockEntityList();
        List<UserVO> entityVOs = input.mockVOList();

        when(mapper.userEntityListToUserVOList(entities)).thenReturn(entityVOs);
        when(repository.findAll()).thenReturn(entities);

        var result = service.findAll();

        // Verifica se a lista retornada não é nula e tem a quantidade certa de elementos
        assertNotNull(result);
        assertEquals(15, result.size());

        // Verifica alguns elementos aleatórios para garantir que foram gerados corretamente
        assertNotNull(result.get(0).getKey());
        assertNotNull(result.get(0).getUserName());
        assertNotNull(result.get(0).getUserPassword());
        assertNotNull(result.get(0).getLinks());
        assertEquals(0, result.get(0).getKey());
        assertEquals("testLogin0", result.get(0).getUserName());
        assertEquals("#testPass1230", result.get(0).getUserPassword());
        assertTrue(result.get(0).getLinks().toString().contains("</user/0>;rel=\"self\""));

        assertNotNull(result.get(4).getKey());
        assertNotNull(result.get(4).getUserName());
        assertNotNull(result.get(4).getUserPassword());
        assertNotNull(result.get(4).getLinks());
        assertEquals(4, result.get(4).getKey());
        assertEquals("testLogin4", result.get(4).getUserName());
        assertEquals("#testPass1234", result.get(4).getUserPassword());
        assertTrue(result.get(4).getLinks().toString().contains("</user/4>;rel=\"self\""));

        assertNotNull(result.get(14).getKey());
        assertNotNull(result.get(14).getUserName());
        assertNotNull(result.get(14).getUserPassword());
        assertNotNull(result.get(14).getLinks());
        assertEquals(14, result.get(14).getKey());
        assertEquals("testLogin14", result.get(14).getUserName());
        assertEquals("#testPass12314", result.get(14).getUserPassword());
        assertTrue(result.get(14).getLinks().toString().contains("</user/14>;rel=\"self\""));

        assertNotNull(result.get(5).getKey());
        assertNotNull(result.get(5).getUserName());
        assertNotNull(result.get(5).getUserPassword());
        assertNotNull(result.get(5).getLinks());
        assertEquals(5, result.get(5).getKey());
        assertEquals("testLogin5", result.get(5).getUserName());
        assertEquals("#testPass1235", result.get(5).getUserPassword());
        assertTrue(result.get(5).getLinks().toString().contains("</user/5>;rel=\"self\""));

        assertNotNull(result.get(3).getKey());
        assertNotNull(result.get(3).getUserName());
        assertNotNull(result.get(3).getUserPassword());
        assertNotNull(result.get(3).getLinks());
        assertEquals(3, result.get(3).getKey());
        assertEquals("testLogin3", result.get(3).getUserName());
        assertEquals("#testPass1233", result.get(3).getUserPassword());
        assertTrue(result.get(3).getLinks().toString().contains("</user/3>;rel=\"self\""));
    }

    @Test
    void create() {
        User entity = input.mockEntity(1);
        UserVO vo = new UserVO();

        vo.setKey(entity.getId());
        vo.setUserPassword(entity.getPassword());
        vo.setUserName(entity.getUserName());

        when(repository.save(entity)).thenReturn(entity);
        when(mapper.userVOToUserEntity(vo)).thenReturn(entity);
        when(mapper.userEntityToUserVO(entity)).thenReturn(vo);

        var result = service.create(vo);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getUserName());
        assertNotNull(result.getUserPassword());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</user/1>;rel=\"self\"]"));

        assertEquals("testLogin1", result.getUserName());
        assertEquals("#testPass1231", result.getUserPassword());
    }

    @Test
    void update() {
        User entity = input.mockEntity(1);
        UserVO vo = new UserVO();

        vo.setKey(entity.getId());
        vo.setUserPassword(entity.getPassword());
        vo.setUserName(entity.getUserName());

        when(mapper.userVOToUserEntity(vo)).thenReturn(entity);
        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(mapper.userEntityToUserVO(entity)).thenReturn(vo);
        when(repository.save(entity)).thenReturn(entity);

        var result = service.update(vo);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getUserName());
        assertNotNull(result.getUserPassword());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</user/1>;rel=\"self\""));

        assertEquals("testLogin1", result.getUserName());
        assertEquals("#testPass1231", result.getUserPassword());
    }

    @Test
    void delete() {
        User entity = input.mockEntity(1);

        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));

        service.delete(entity.getId());
    }

    @Test
    void createWithNullUser() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.create(null);
        });

        assertTrue(exception.getMessage().contains("It's not allowed to persist a null object!"));
    }

    @Test
    void updateWithNullUser() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.update(null);
        });

        assertTrue(exception.getMessage().contains("It's not allowed to persist a null object!"));
    }
}