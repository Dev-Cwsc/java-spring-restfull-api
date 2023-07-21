package br.com.dev.cwsc.javaspringrestfullapi.unittests.services;

import br.com.dev.cwsc.javaspringrestfullapi.exceptions.RequiredObjectIsNullException;
import br.com.dev.cwsc.javaspringrestfullapi.mapper.DeviceMapper;
import br.com.dev.cwsc.javaspringrestfullapi.model.Device;
import br.com.dev.cwsc.javaspringrestfullapi.model.vo.v1.DeviceVO;
import br.com.dev.cwsc.javaspringrestfullapi.repositories.DeviceRepository;
import br.com.dev.cwsc.javaspringrestfullapi.services.DeviceServices;
import br.com.dev.cwsc.javaspringrestfullapi.unittests.mapper.model.MockDevice;

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
class DeviceServicesTest {

    MockDevice input;

    @InjectMocks
    private DeviceServices service;

    @Mock
    private DeviceRepository repository;

    @Mock
    private DeviceMapper mapper;

    @BeforeEach
    void setUp() throws Exception {
        input = new MockDevice();

        // Inicializa os mocks dessa classe
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Device entity = input.mockEntity(1);
        DeviceVO vo = new DeviceVO();

        vo.setKey(entity.getId());
        vo.setDevice(entity.getDeviceName());
        vo.setInstallation(entity.getInstallationName());

        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(mapper.deviceEntityToDeviceVO(entity)).thenReturn(vo);

        var result = service.findById(entity.getId());

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getDevice());
        assertNotNull(result.getInstallation());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</device/1>;rel=\"self\", </device/measurement>;rel=\"measurement-update\", </device/data>;rel=\"data-update\"]"));
        assertEquals("teste-restfull-api-1", result.getDevice());
        assertEquals("biblioteca", result.getInstallation());
    }

    @Test
    void findAll() {
        List<Device> entities = input.mockEntityList();
        List<DeviceVO> entitiyVOs = input.mockVOList();

        when(mapper.deviceEntityListToDeviceVOList(entities)).thenReturn(entitiyVOs);
        when(repository.findAll()).thenReturn(entities);

        var result = service.findAll();
        assertNotNull(result);
        assertEquals(15, result.size());

        assertNotNull(result.get(6).getKey());
        assertNotNull(result.get(6).getDevice());
        assertNotNull(result.get(6).getInstallation());
        assertNotNull(result.get(6).getLinks());
        assertEquals(6, result.get(6).getKey());
        assertEquals("teste-restfull-api-6", result.get(6).getDevice());
        assertEquals("biblioteca", result.get(6).getInstallation());
        assertTrue(result.get(6).getLinks().toString().contains("</device/6>;rel=\"self\",</device/measurement>;rel=\"measurement-update\",</device/data>;rel=\"data-update\""));

        assertNotNull(result.get(14).getKey());
        assertNotNull(result.get(14).getDevice());
        assertNotNull(result.get(14).getInstallation());
        assertNotNull(result.get(14).getLinks());
        assertEquals(14, result.get(14).getKey());
        assertEquals("teste-restfull-api-14", result.get(14).getDevice());
        assertEquals("biblioteca", result.get(14).getInstallation());
        assertTrue(result.get(14).getLinks().toString().contains("</device/14>;rel=\"self\",</device/measurement>;rel=\"measurement-update\",</device/data>;rel=\"data-update\""));

        assertNotNull(result.get(2).getKey());
        assertNotNull(result.get(2).getDevice());
        assertNotNull(result.get(2).getInstallation());
        assertNotNull(result.get(2).getLinks());
        assertEquals(2, result.get(2).getKey());
        assertEquals("teste-restfull-api-2", result.get(2).getDevice());
        assertEquals("biblioteca", result.get(2).getInstallation());
        assertTrue(result.get(2).getLinks().toString().contains("</device/2>;rel=\"self\",</device/measurement>;rel=\"measurement-update\",</device/data>;rel=\"data-update\""));

        assertNotNull(result.get(3).getKey());
        assertNotNull(result.get(3).getDevice());
        assertNotNull(result.get(3).getInstallation());
        assertNotNull(result.get(3).getLinks());
        assertEquals(3, result.get(3).getKey());
        assertEquals("teste-restfull-api-3", result.get(3).getDevice());
        assertEquals("biblioteca", result.get(3).getInstallation());
        assertTrue(result.get(3).getLinks().toString().contains("</device/3>;rel=\"self\",</device/measurement>;rel=\"measurement-update\",</device/data>;rel=\"data-update\""));

        assertNotNull(result.get(0).getKey());
        assertNotNull(result.get(0).getDevice());
        assertNotNull(result.get(0).getInstallation());
        assertNotNull(result.get(0).getLinks());
        assertEquals(0, result.get(0).getKey());
        assertEquals("teste-restfull-api-0", result.get(0).getDevice());
        assertEquals("biblioteca", result.get(0).getInstallation());
        assertTrue(result.get(0).getLinks().toString().contains("</device/0>;rel=\"self\",</device/measurement>;rel=\"measurement-update\",</device/data>;rel=\"data-update\""));
    }

    @Test
    void create() {
        Device entity = input.mockEntity(1);
        DeviceVO vo = new DeviceVO();

        vo.setKey(entity.getId());
        vo.setDevice(entity.getDeviceName());
        vo.setInstallation(entity.getInstallationName());

        when(repository.save(entity)).thenReturn(entity);
        when(mapper.deviceEntityToDeviceVO(entity)).thenReturn(vo);
        when(mapper.deviceVOToDeviceEntity(vo)).thenReturn(entity);

        var result = service.create(vo);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getDevice());
        assertNotNull(result.getInstallation());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</device/1>;rel=\"self\", </device/measurement>;rel=\"measurement-update\", </device/data>;rel=\"data-update\"]"));
        assertEquals("teste-restfull-api-1", result.getDevice());
        assertEquals("biblioteca", result.getInstallation());
    }

    @Test
    void measurementUpdate() {
        Device entity = input.mockEntity(1);
        DeviceVO vo = new DeviceVO();

        vo.setKey(entity.getId());
        vo.setDevice(entity.getDeviceName());
        vo.setInstallation(entity.getInstallationName());

        when(mapper.deviceVOToDeviceEntity(vo)).thenReturn(entity);
        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(mapper.deviceEntityToDeviceVO(entity)).thenReturn(vo);
        when(repository.save(entity)).thenReturn(entity);

        var result = service.measurementUpdate(vo);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getDevice());
        assertNotNull(result.getInstallation());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</device/1>;rel=\"self\", </device/measurement>;rel=\"measurement-update\", </device/data>;rel=\"data-update\""));
        assertEquals("teste-restfull-api-1", result.getDevice());
        assertEquals("biblioteca", result.getInstallation());
    }

    @Test
    void deviceDataUpdate() {
        this.measurementUpdate();
    }

    @Test
    void delete() {
        Device entity = input.mockEntity(1);

        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));

        service.delete(1L);
    }

    @Test
    void createWithNullDevice() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.create(null);
        });

        assertTrue(exception.getMessage().contains("It's not allowed to persist a null object!"));
    }

    @Test
    void measurementUpdateWithNullDevice() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.measurementUpdate(null);
        });

        assertTrue(exception.getMessage().contains("It's not allowed to persist a null object!"));
    }

    @Test
    void deviceDataUpdateWithNullDevice() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.deviceDataUpdate(null);
        });

        assertTrue(exception.getMessage().contains("It's not allowed to persist a null object!"));
    }
}