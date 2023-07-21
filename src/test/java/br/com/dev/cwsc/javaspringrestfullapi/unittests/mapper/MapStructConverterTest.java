package br.com.dev.cwsc.javaspringrestfullapi.unittests.mapper;

import br.com.dev.cwsc.javaspringrestfullapi.unittests.mapper.model.MockUser;
import org.junit.jupiter.api.BeforeEach;

public class MapStructConverterTest {

    MockUser input;

    @BeforeEach
    public void setUp() {
        input = new MockUser();
    }
}
