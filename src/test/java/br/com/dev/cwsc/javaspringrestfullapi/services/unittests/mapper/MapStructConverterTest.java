package br.com.dev.cwsc.javaspringrestfullapi.services.unittests.mapper;

import br.com.dev.cwsc.javaspringrestfullapi.services.unittests.mapper.model.MockUser;
import org.junit.jupiter.api.BeforeEach;

public class MapStructConverterTest {

    MockUser input;

    @BeforeEach
    public void setUp(){
        input = new MockUser();
    }
}
